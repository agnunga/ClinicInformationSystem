/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.accounts;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import javax.ejb.Remote;

/**
 *
 * @author agunga
 */
@Remote
public interface DBUtil {

    public Connection setConnectDB();

    public int insertMany(PreparedStatement[] preparedStatements, Connection conn);

    public int insert(PreparedStatement preparedStatement, Connection conn);

    public ResultSet select(String sql_select, Connection conn);

    public int update(String sql_update, PreparedStatement preparedStatement, Connection conn);

    public int delete(String sql_delete, int id, Connection conn);

}
