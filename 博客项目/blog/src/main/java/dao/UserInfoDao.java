package dao;

import models.UserInfo;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author FCCC
 * @version 1.0
 * @date 2021/4/5 10:47
 */
public class UserInfoDao {

    //注册功能
    public int add(String username,String password) throws SQLException {
        int ret=0;
        Connection connection= DBUtils.getConnection();
        String sql="insert into userinfo(username,password) values(?,?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,username);
        statement.setString(2,password);
        ret=statement.executeUpdate();
        //关闭连接
        DBUtils.close(connection,statement,null);
        return ret;
    }

    //查询用户
    public UserInfo getUser(String username,String password) throws SQLException {
        UserInfo userInfo=new UserInfo();
        Connection connection=DBUtils.getConnection();
        String sql="select * from userinfo where username=? and password=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,username);
        statement.setString(2,password);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            userInfo.setId(resultSet.getInt("id"));
            userInfo.setUsername(resultSet.getString("username"));
            userInfo.setPassword(resultSet.getString("password"));
        }
        DBUtils.close(connection,statement,resultSet);
        return userInfo;
    }


}
