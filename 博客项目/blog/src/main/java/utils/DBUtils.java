package utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author FCCC
 * @version 1.0
 * @date 2021/4/5 10:29
 */
public class DBUtils {
    private static MysqlDataSource dataSource=null;

    //获取connection对象
    public static Connection getConnection() throws SQLException {
        if (dataSource==null){
            dataSource=new MysqlDataSource();
            //dataSource.setURL("jdbc:mysql://127.0.0.1:3306/java18blog?characterEncoding=utf-8");
            dataSource.setURL("jdbc:mysql://127.0.0.1:3306/blog?characterEncoding=utf-8");
            dataSource.setUser("root");
            dataSource.setPassword("12345678");
            //dataSource.setPassword("111111");
        }
        return dataSource.getConnection();
    }

    //关闭连接
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        if (resultSet!=null)  resultSet.close();
        if (statement!=null)  statement.close();
        if (connection!=null)  connection.close();
    }

}
