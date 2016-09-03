package com.baiye.HTTPClient;

import com.baiye.Model.HTTPResData;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

/**
 * Created by Baiye on 9/3/16.
 */
public class HTTPPostClient extends HTTPClient{

    public HTTPResData sendRequest(String httpUrl, String data) throws Exception
    {
        Socket socket = new Socket();

        URL url = new URL(httpUrl);

        String path = url.getPath();
        String host = url.getHost();
        int port = url.getPort();
        String query = url.getQuery();

        SocketAddress dest = new InetSocketAddress(host,port);

        socket.connect(dest);

        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());

        BufferedWriter bw = new BufferedWriter(osw);

        StringBuffer sb = new StringBuffer();

        String contentType = "application/x-www-form-urlencoded";

        sb.append("POST " + path + " HTTP/1.1\r\n");
        sb.append("Host: " + host + ":" + port + "\r\n");
        sb.append("Content-Length: " + data.length() + "\r\n");
        sb.append("Content-Type: " + contentType + "\r\n");
        sb.append("\r\n");
        sb.append(data);

        System.out.println(sb.toString());

        bw.write(sb.toString());

        bw.flush();

        InputStream is = socket.getInputStream();

        String head = "";

        String line = null;

        int contentLength = 0;

        do
        {
            line = readLine(is,0);
            if(line.startsWith("Content-Length"))
            {
                contentLength = Integer.parseInt(line.split(":")[1].trim());
            }

            head += line;
        } while(!line.equals("\r\n"));

        String resContent = readLine(is,contentLength);

        HTTPResData resData = new HTTPResData(head,resContent,contentLength);


        return resData;
    }
}
