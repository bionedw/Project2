package com.javaweb.utils;

import java.sql.*;

public class JdbcConnectionUtil {
    static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
    static final String USER="root";
    static final String PASS="Thinh123";

    public static Connection getConnection(){
        Connection conn=null;
        try
        {
            conn= DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
