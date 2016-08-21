import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Created by Baiye on 8/20/16.
 */
public class Test {

    public static void main(String args[]) throws Exception {

        long beforec = System.currentTimeMillis();

        String path = "";
        String host = "";
        int port = 0;
        URL url = new URL("http://127.0.0.1:8080/test");
      //  System.out.println(url.getContent());
        System.out.println(url.getQuery());
        System.out.println(url.getHost());
        System.out.println(url.getPath());
        System.out.println(url.getPort());

        host = url.getHost();
        port = url.getPort();
        path = url.getPath();

        Socket socket = new Socket();

        SocketAddress dest = new InetSocketAddress(host,port);

        socket.connect(dest);

        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());

        BufferedWriter bw = new BufferedWriter(osw);

        StringBuffer sb = new StringBuffer();

        sb.append("GET " + path + " HTTP/1.1\r\n");
        sb.append("Host: " + host + ":" + port + "\r\n");
        sb.append("\r\n");

        System.out.println(sb.toString());

        bw.write(sb.toString());

        bw.flush();

        InputStream is = socket.getInputStream();

        String line = null;

        int contentLength = 0;

        do
        {
            line = readLine(is,0);
            if(line.startsWith("Content-Length"))
            {
                contentLength = Integer.parseInt(line.split(":")[1].trim());
            }

            System.out.println(line);
        } while(!line.equals("\r\n"));

        System.out.println(readLine(is,contentLength));


       /* BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(bis,"utf-8"));

        String line = null;

        while((line = br.readLine()) != null)
        {
            System.out.println(line);
        }*/

        is.close();
        bw.close();
        socket.close();


        System.out.println(System.currentTimeMillis() - beforec);


    }

    private static String readLine(InputStream is,int contentLength) throws IOException {
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
