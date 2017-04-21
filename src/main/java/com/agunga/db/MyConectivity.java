/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.Resource;

/**
 *
 * @author agunga
 */
@Resource(name = "myConnectivity")
public interface MyConectivity {

    public Connection connectDB();

    public boolean createTables(Connection conn);

    public int insertMany(PreparedStatement[] preparedStatements, Connection conn);

    public int insert(PreparedStatement preparedStatement, Connection conn);

    public ResultSet select(String sql_select, Connection con);

    public int update(PreparedStatement preparedStatement, Connection conn);

    public int delete(String sql_delete, int id, Connection conn);
}
