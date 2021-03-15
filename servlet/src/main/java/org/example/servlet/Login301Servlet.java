package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//注解的使用：小括号包裹多个属性，属性名=属性值，多个之间逗号间隔，属性名为value时可以缺省
//Servlet定义服务：注意服务路径必须是/开始，否则tomcat启动就会报错
@WebServlet("/login301")
public class Login301Servlet extends HttpServlet {

    /**
     * 每次http请求映射到某个Servlet的资源路径，都会调用service生命周期方法
     * 如果请求方法没有重写，就调用父类的doXXX（对应请求方法），返回405
     * 如果重写，会将请求数据包装为一个Request请求对象，这时候虽然还没有响应，但是也
     * 包装了一个Response响应对象
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求，响应编码，及响应数据类型（为响应体设置Content-Type数据类型）
        req.setCharacterEncoding("UTF-8");//设置请求体编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        //解析数据
        //request.getParameter方法获取请求数据：url和请求体，数据格式为k1=v1&k2=v2
        String u = req.getParameter("username");
        String p = req.getParameter("password");
        System.out.printf("=====================用户名（%s）密码（%s）%n", u, p);
        if("abc".equals(u) && "123".equals(p)) {
            //重定向：http响应状态码设置为301/302/307，响应头Location
            resp.sendRedirect("home.html");
        }else if("abc".equals(u)){
            //转发
            req.getRequestDispatcher("home.html").forward(req, resp);
        }else{
            //返回响应数据
            PrintWriter pw = resp.getWriter();//response获取io输出流
            pw.println("登录失败");
            pw.println("<h3>用户名："+u+"或密码错误</h3>");
            pw.flush();//有缓冲的io操作，需要刷新缓冲区，才会真正的发送数据
            pw.close();//io流操作完，一定要记住关闭资源
        }


    }
}
