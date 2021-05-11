package services;

import dao.ArticleInfoDao;
import models.ArticleInfo;
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
 * @date 2021/4/7 11:21
 */
@WebServlet("/addart")
public class AddArtServlet  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ=-1;
        String msg="";
        // 1.从前端获取参数
        String title = request.getParameter("title");
        String content = request.getParameter("content");
//        if ( title != null && content != null &&
//                !title.equals("") && !content.equals("")){
//
//        }
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userinfo") != null) {
            UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
            // 2.调用数据库执行相应的业务逻辑
            ArticleInfoDao articleInfoDao = new ArticleInfoDao();
            try {
                succ = articleInfoDao.add(title, content, userInfo.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            msg = "非登录状态，请先登录。";
        }

        // 3.将上一步操作的结果返回给前端
        HashMap<String, Object> result = new HashMap<>();
        result.put("succ", succ);
        result.put("msg", msg);
        ResultJSONUtils.write(response, result);
    }
}