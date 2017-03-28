/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.dao;

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

    public void createTable(String sql_create_table, String table_name, Connection conn);

    public int insertMany(PreparedStatement[] preparedStatements, Connection conn);

    public int insert(PreparedStatement preparedStatement,  Connection conn);

    public ResultSet select(String sql_select, Connection con);

    public int update(String sql_update, PreparedStatement preparedStatement, Connection conn);

    public int delete(String sql_delete, int id,  Connection conn);
}
