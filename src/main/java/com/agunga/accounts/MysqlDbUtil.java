package com.agunga.accounts;

import java.sql.*;

public class MysqlDbUtil implements DBUtil{

    public Connection conn;
    private static final String M_STRING = "jdbc:mysql://localhost/account1";
    private static final String M_USER = "root";
    private static final String M_PASSWORD = "";

    public Connection connectDB() {
        if (conn != null) {
            return conn;
        } else {
            return setConnectDB();
        }
    }

    @Override
    public Connection setConnectDB() {
        conn = null;
        try {
            return DriverManager.getConnection(M_STRING, M_USER, M_PASSWORD);
        } catch (SQLException ne) {
            System.out.println("ERROR" + ne.getMessage());
        }
        return conn;
    }

    @Override
    public int insertMany(PreparedStatement[] preparedStatements, Connection conn) {
        this.conn = new MysqlDbUtil().connectDB();
        int isInserted = -1;
        try {
            conn.setAutoCommit(false);
            for (PreparedStatement preparedStatement : preparedStatements) {
                isInserted = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());

            if (e.getErrorCode() == 1062) {
                System.out.println("Record exists.");
            }
        }
        return isInserted;
    }

    @Override
    public int insert(PreparedStatement preparedStatement, Connection conn) {
        int isInserted = -1;
        try {
            isInserted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());

            if (e.getErrorCode() == 1062) {
                System.out.println("Record exists.");
            }
        }
//        System.out.println("isInserted =  "+isInserted);
        return isInserted;
    }

    public ResultSet select(String sql_select, Connection conn) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql_select);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
        return resultSet;
    }

    @Override
    public int update(String sql_update, PreparedStatement preparedStatement, Connection conn) {
        int isUpdated = -1;
        try {
            isUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
        return isUpdated;
    }

    @Override
    public int delete(String sql_delete, int id, Connection conn) {
        int isDeleted = -1;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql_delete);
            preparedStatement.setInt(1, id);
            isDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());
        }
        return isDeleted;
    }

}
