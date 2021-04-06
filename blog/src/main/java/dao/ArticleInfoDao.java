package dao;

import models.ArticleInfo;
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
}
