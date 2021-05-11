package services;

import dao.UserInfoDao;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author FCCC
 * @version 1.0
 * @date 2021/4/5 10:03
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ=-1;
        String msg="";
        //1.获取参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //2.业务逻辑
        if (username!=null && !username.equals("") && password!=null && !password.equals("")){
            UserInfoDao userInfoDao=new UserInfoDao();
            try {
                UserInfo userInfo=userInfoDao.getUser(username,password);
                if (userInfo.getId()>0){
                    succ=1;
                    HttpSession session=request.getSession();
                    session.setAttribute("userinfo",userInfo);
                }else {
                    succ=0;
                    msg="用户名或密码输入错误";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else {
            msg="非法请求，参数不完整";
        }
        //3.返回参数
        HashMap<String,Object> map=new HashMap<>();
        map.put("succ",succ);
        map.put("msg",msg);
        ResultJSONUtils.write(response,map);
    }
}
