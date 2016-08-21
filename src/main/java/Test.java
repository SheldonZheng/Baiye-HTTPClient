import java.io.*;
import java.net.*;

/**
 * Created by Baiye on 8/20/16.
 */
public class Test {

    public static void main(String args[]) throws Exception {

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

        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(bis,"utf-8"));

        String line = null;

        while((line = br.readLine()) != null)
        {
            System.out.println(line);
        }

        br.close();
        bw.close();
        socket.close();




    }


}
