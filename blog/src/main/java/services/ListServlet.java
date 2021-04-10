package services;

import dao.ArticleInfoDao;
import models.vo.ArticleInfoVO;
import utils.DBUtils;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @author FCCC
 * @version 1.0
 * @date 2021/4/9 10:50
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ=-1;
        String msg="";
        List<ArticleInfoVO> list=null;
        //1.获取参数
        int page=Integer.parseInt(req.getParameter("page"));
        int psize=Integer.parseInt(req.getParameter("psize"));
        //2.业务处理
        ArticleInfoDao articleInfoDao=new ArticleInfoDao();
        try {
            list=articleInfoDao.getListByPage(page,psize);
            succ=1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("succ",succ);
        map.put("msg",msg);
        map.put("list",list);
        ResultJSONUtils.write(resp,map);
    }
}
