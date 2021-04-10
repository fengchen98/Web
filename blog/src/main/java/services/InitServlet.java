package services;

import dao.ArticleInfoDao;
import dao.UserInfoDao;
import models.ArticleInfo;
import models.UserInfo;
import models.vo.ArticleInfoVO;
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
 * @date 2021/4/7 10:58
 */
@WebServlet("/init")
public class InitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ=-1;
        String msg="";
        ArticleInfoVO articleInfo=null;
        //1.获取参数
        int id= Integer.parseInt(request.getParameter("id"));
        //2.业务逻辑
        if (id>0){
            ArticleInfoDao articleInfoDao=new ArticleInfoDao();
            try {
                articleInfo=articleInfoDao.getArtById(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            succ=1;
        }else {
            msg="请求参数有误";
        }
        //3.返回参数
        HashMap<String,Object> map=new HashMap<>();
        map.put("succ",succ);
        map.put("msg",msg);
        map.put("art",articleInfo);
        ResultJSONUtils.write(response,map);
    }
}
