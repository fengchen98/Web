package services;

import dao.ArticleInfoDao;
import dao.UserInfoDao;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * @date 2021/4/7 10:17
 */
@WebServlet("/mydel")
public class MyDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ=-1;
        String msg="";
        //1.获取参数
        int id=Integer.parseInt(request.getParameter("id"));
        //2.业务逻辑
        ArticleInfoDao articleInfoDao=new ArticleInfoDao();
        try {
            succ=articleInfoDao.del(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //3.返回参数
        HashMap<String,Object> map=new HashMap<>();
        map.put("succ",succ);
        map.put("msg",msg);
        ResultJSONUtils.write(response,map);
    }
}
