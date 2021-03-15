package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//注解的使用：小括号包裹多个属性，属性名=属性值，多个之间逗号间隔，属性名为value时可以缺省
//Servlet定义服务：注意服务路径必须是/开始，否则tomcat启动就会报错
@WebServlet("/sen")
public class SensitiveServlet extends HttpServlet {

    /**
     * 每次http请求映射到某个Servlet的资源路径，都会调用service生命周期方法
     * 如果请求方法没有重写，就调用父类的doXXX（对应请求方法），返回405
     * 如果重写，会将请求数据包装为一个Request请求对象，这时候虽然还没有响应，但是也
     * 包装了一个Response响应对象
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求，响应编码，及响应数据类型（为响应体设置Content-Type数据类型）
        req.setCharacterEncoding("UTF-8");//设置请求体编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        //返回响应数据
        PrintWriter pw = resp.getWriter();//response获取io输出流
        // 每一个敏感资源，都获取session，通过是否为空验证用户是否登录：代码耦合性太高
        // 可以使用Filter过滤器，统一处理敏感资源验证
        HttpSession session = req.getSession(false);
        if(session == null){
            System.out.println("session为空");
            resp.setStatus(401);//没有登录，访问敏感资源，返回401，表示Unauthorized
            pw.println("敏感资源，需要登录后访问");
        }else{
            String username = (String) session.getAttribute("username");
            System.out.println("session存在，用户名为："+username);

            //伪代码：禁止访问的敏感资源（没有权限）
//            User user = (User) session.getAttribute("user");
//            if(user.允许访问的资源不包含当前敏感资源uri){
//                resp.setStatus(403);//登录了，但没有权限：Forbidden
//            }
        }

        pw.flush();//有缓冲的io操作，需要刷新缓冲区，才会真正的发送数据
        pw.close();//io流操作完，一定要记住关闭资源
    }
}
