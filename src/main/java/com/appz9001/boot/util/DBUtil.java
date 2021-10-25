package com.appz9001.boot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Map;
import java.util.function.Predicate;

public class DBUtil {

    private static final Logger logger = LoggerFactory.getLogger(DBUtil.class);
    public static final  String url = "jdbc:sqlserver://yxnat.softdev.top:1111;databaseName=KHMS";

    public static Connection getConnection(String url,String user,String password){
        Connection conn = null;
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.error("获取数据库连接失败",e);
            return null;
        }
    }

    public static ResultSet queryList(Connection connection,String sql) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps.executeQuery();
    }

    public static long getCount(Connection connection,String sql) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs =  ps.executeQuery();
        if(rs.next()){
            return rs.getLong("cnt");
        }
        return 0L;
    }

    public static void main(String[] args){
        Connection connection = DBUtil.getConnection(DBUtil.url,"sa","yijia815@126.com");
        logger.info("connectioned");
    }
}
