package dao;

import models.ArticleInfo;
import models.vo.ArticleInfoVO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FCCC
 * @version 1.0
 * @date 2021/4/6 15:23
 */
public class ArticleInfoDao {
    public List<ArticleInfo> getListByUid(int uid) throws SQLException {
        List<ArticleInfo> list=new ArrayList<>();
        Connection connection= DBUtils.getConnection();
        String sql="select * from articleinfo where uid=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,uid);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            ArticleInfo articleInfo=new ArticleInfo();
            articleInfo.setId(resultSet.getInt("id"));
            articleInfo.setRcount(resultSet.getInt("rcount"));
            articleInfo.setTitle(resultSet.getString("title"));
            articleInfo.setContent(resultSet.getString("Content"));
            articleInfo.setCreatetime(resultSet.getDate("createtime"));
            list.add(articleInfo);
        }
        DBUtils.close(connection,statement,resultSet);
        return list;
    }


    //删除文章
    public int del(int id) throws SQLException {
        int result=-1;
        if (id > 0) {
            Connection connection=DBUtils.getConnection();
            String sql="delete from articleinfo where id=?";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            result=statement.executeUpdate();
            DBUtils.close(connection,statement,null);

        }
        return result;
    }

    //通过id获取文章
    public ArticleInfoVO getArtById(int id) throws SQLException {
        ArticleInfoVO articleInfo=new ArticleInfoVO();
        if (id > 0) {
            Connection connection = DBUtils.getConnection();
            String sql = "select a.* , u.username from articleinfo a left join userinfo u on  a.uid=u.id where a.id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                articleInfo.setId(resultSet.getInt("id"));
                articleInfo.setTitle(resultSet.getString("title"));
                articleInfo.setContent(resultSet.getString("content"));
                articleInfo.setUsername(resultSet.getString("username"));
                articleInfo.setCreatetime(resultSet.getDate("createtime"));
                articleInfo.setRcount(resultSet.getInt("rcount"));
            }
            DBUtils.close(connection, statement, resultSet);
        }
        return articleInfo;
    }

    //修改文章
    public int upArt(int id, String title, String content) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnection();
        String sql = "update articleinfo set title=?,content=? where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, content);
        statement.setInt(3, id);
        result = statement.executeUpdate();
        DBUtils.close(connection, statement, null);
        return result;
    }

    //添加文章
    public int add(String title, String content, int uid) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnection();
        String sql = "insert into articleinfo(title,content,uid) values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, content);
        statement.setInt(3, uid);
        result = statement.executeUpdate();
        DBUtils.close(connection, statement, null);
        return result;
    }

    public List<ArticleInfoVO> getListByPage(int page, int psize) throws SQLException {
        List<ArticleInfoVO> list=new ArrayList<>();
        Connection connection=DBUtils.getConnection();
        String sql="select a.*,u.username from articleinfo a left join userinfo u on a.uid=u.id limit ?,?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,(page-1)*psize);
        statement.setInt(2,psize);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            ArticleInfoVO articleInfoVO=new ArticleInfoVO();
            articleInfoVO.setId(resultSet.getInt("id"));
            articleInfoVO.setTitle(resultSet.getString("title"));
            articleInfoVO.setCreatetime(resultSet.getDate("createtime"));
            articleInfoVO.setRcount(resultSet.getInt("rcount"));
            articleInfoVO.setUsername(resultSet.getString("username"));
            list.add(articleInfoVO);
        }
        DBUtils.close(connection,statement,resultSet);
        return list;
    }

    // 阅读量+1
    public int upRcount(int id) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnection();
        String sql = "update articleinfo set rcount=rcount+1 where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        result = statement.executeUpdate();
        return result;
    }
}

