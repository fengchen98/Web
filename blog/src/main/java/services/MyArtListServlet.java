package services;

import dao.ArticleInfoDao;
import models.ArticleInfo;
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
import java.util.List;

/**
 * @author FCCC
 * @version 1.0
 * @date 2021/4/5 10:03
 */
public class MyArtListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ=-1;
        String msg="";
        List<ArticleInfo> list=null;
        //1.得到参数
        HttpSession session=request.getSession(false);
        if (session==null){
            msg="非法请求，请先登录";
        }else {
            UserInfo userInfo=(UserInfo)session.getAttribute("userinfo");
            int uid=userInfo.getId();
            ArticleInfoDao articleInfoDao=new ArticleInfoDao();
            try {
                list=articleInfoDao.getListByUid(uid);
                succ=1;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            HashMap<String,Object> map=new HashMap<>();
            map.put("succ",succ);
            map.put("msg",msg);
            map.put("list",list);
            ResultJSONUtils.write(response,map);

        }

    }
}
