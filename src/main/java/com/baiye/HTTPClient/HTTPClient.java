package com.baiye.HTTPClient;

import com.baiye.Model.HTTPResData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Baiye on 8/21/16.
 */
public abstract class HTTPClient {

    abstract public HTTPResData sendRequest(String httpUrl, String data) throws Exception;


    /*
     * 这里我们自己模拟读取一行，因为如果使用API中的BufferedReader时，它是读取到一个回车换行后
     * 才返回，否则如果没有读取，则一直阻塞，直接服务器超时自动关闭为止，如果此时还使用BufferedReader
     * 来读时，因为读到最后一行时，最后一行后不会有回车换行符，所以就会等待。如果使用服务器发送回来的
     * 消息头里的Content-Length来截取消息体，这样就不会阻塞
     *
     * contentLe 参数 如果为0时，表示读头，读时我们还是一行一行的返回；如果不为0，表示读消息体，
     * 时我们根据消息体的长度来读完消息体后，客户端自动关闭流，这样不用先到服务器超时来关闭。
     */
    protected static String readLine(InputStream is, int contentLength) throws IOException {
        ArrayList lineByteList = new ArrayList();

        byte readByte;

        int total = 0;

        if(contentLength != 0)
        {
            do
            {
                readByte = (byte) is.read();
                lineByteList.add(Byte.valueOf(readByte));
                total++;
            } while(total < contentLength);
        }
        else
        {
            do
            {
                readByte = (byte) is.read();
                lineByteList.add(Byte.valueOf(readByte));
            } while(readByte != 10);
        }

        byte[] tempByteArr = new byte[lineByteList.size()];
        for(int i = 0;i < lineByteList.size();i++)
        {
            tempByteArr[i] = ((Byte) lineByteList.get(i)).byteValue();
        }

        lineByteList.clear();

        return new String(tempByteArr,"utf-8");



    }
}
