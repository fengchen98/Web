/**
 * @author FCCC
 * @version 1.0
 * @date 2021/4/21 11:27
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 自定义HTTP服务器
 * 根据URI内容返回不同的信息
 * 如果URI里面包含了404，给浏览器输出“未找到此页面”
 * 如果URI里面包含了200，返回“你好世界”
 */
public class MyHTTP {
    private static final int port=9004;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(port);
        System.out.println("服务器已启动");

        //2.等待客户端连接
        Socket socket=serverSocket.accept();

        //3.构建读写对象
        try(BufferedReader reader=new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer=new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            )

        ){
            //4.得到客户端信息
            String firstLine=reader.readLine();
            String[] fLineArr=firstLine.split(" ");
            String method=fLineArr[0];
            String uri=fLineArr[1];
            String httpVersion=fLineArr[2];
            System.out.println(String.format("首行信息-》方法类型:%s,URI:%s,httpversion:%s",method,uri,httpVersion));
            String content="";
            if (uri.contains("404")){
                content="<h1>没有找到此页面</h1>";
            }else if (uri.contains("200")){
                content="<h1>你好，世界 </h1>";
            }
            writer.write(String.format("%s 200 ok",httpVersion)+"\n");
            writer.write("content-Type:text/html;charset=utf-8\n");
            writer.write("content-Length:"+content.getBytes().length+"\n");
            writer.write("\n");
            writer.write(content);
            writer.flush();
        }
    }
}
