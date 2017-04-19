package com.agunga.dbold;

import com.agunga.util.MyUtility;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by agunga on 2/2/2017.
 */
@ConnectionType(ConnectionType.Type.MYSQL)
public class MysqlDbUtil implements MyConectivity {

    public Connection conn;

    @Override
    public Connection connectDB() {
        if (conn != null) {
            return conn;
        } else {
            return setConnectDB();
        }
    }

    private Connection setConnectDB() {
        conn = null;
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:jboss/datasources/mysqlCIS");
            conn = dataSource.getConnection();

        } catch (NamingException | SQLException ne) {
            ne.getMessage();
        }
        return conn;
    }

    @Override
    public int insertMany(PreparedStatement[] preparedStatements, Connection conn) {
         int isInserted = -1;
        try {
            conn.setAutoCommit(false);
            for (PreparedStatement preparedStatement : preparedStatements) {
                isInserted = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage());

            if (e.getErrorCode() == 1062) {
                MyUtility.myPrintln("Record exists.");
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
                MyUtility.myPrintln("Record exists.");
            }
        }
//        System.out.println("isInserted =  "+isInserted);
        return isInserted;
    }

    @Override
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
    public int update(PreparedStatement preparedStatement, Connection conn) {
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

    @Override
    public boolean createTables(Connection conn) {
        boolean created = false;
        String create_person_table = "CREATE TABLE IF NOT EXISTS persons("
                + " nationalid varchar(11) PRIMARY KEY, "
                + " name varchar(40) NOT NULL, "
                + " dob varchar(15) NOT NULL, "
                + " phone varchar(13) NOT NULL, "
                + " sex varchar(8) NOT NULL "
                + " )";

        String create_patient_table = "CREATE TABLE IF NOT EXISTS patients("
                + " id int(11) AUTO_INCREMENT PRIMARY KEY, "
                + " patientid varchar(11) NOT NULL, "
                + " nationalid int(11) NOT NULL, "
                + " checkin TIMESTAMP NOT NULL, "
                + " checkout TIMESTAMP NOT NULL, "
                + " addedby varchar(15) "
                + " );";

        String create_employee_table = "CREATE TABLE IF NOT EXISTS employees("  
                + " employeeno varchar(15) PRIMARY KEY, "
                + " nationalid int(11) NOT NULL, "
                + " dateemployed varchar(30) NOT NULL, "
                + " salary varchar(11) NOT NULL, "
                + " password varchar(50) NOT NULL, "
                + " title varchar(255) "
                + " );";

        String create_receptionist_table = "CREATE TABLE IF NOT EXISTS receptionists("
                + " employeeno varchar(15) PRIMARY KEY, "
                + " assignment varchar(255) NOT NULL, "
                + " dateassigned TIMESTAMP NOT NULL "
                + " );";

        String insert_person_table = "INSERT INTO "
                + " persons(nationalid , name,  dob,  phone, sex)"
                + " VALUES(?,?,?,?,?) "
                + " ON DUPLICATE KEY UPDATE nationalid = nationalid";

        String insert_employee_table = "INSERT INTO "
                + " employees(employeeno, nationalid, dateemployed, salary, password, title ) "
                + " VALUES(?,?,?,?,?,?) "
                + " ON DUPLICATE KEY UPDATE employeeno = employeeno";

//        String create_labtech_table = "";
//        String create_doctor_table = "";
//        String create_nurse_table = "";
//        String create_admin_table = "";
        try {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(create_person_table);
            PreparedStatement stm1 = conn.prepareStatement(create_patient_table);
            PreparedStatement stm2 = conn.prepareStatement(create_employee_table);
            PreparedStatement stm3 = conn.prepareStatement(create_receptionist_table);

            PreparedStatement stm4 = conn.prepareStatement(insert_person_table);
            stm4.setString(1, "31254883");
            stm4.setString(2, "Godfey Agunga");
            stm4.setString(3, "27/07/1994");
            stm4.setString(4, "0706091094");
            stm4.setString(5, "male");

            PreparedStatement stm5 = conn.prepareStatement(insert_employee_table);
            stm5.setString(1, "A001");
            stm5.setString(2, "31254883");
            stm5.setString(3, "27/05/2014");
            stm5.setString(4, "1286000");
            stm5.setString(5, "31254883");
            stm5.setString(6, "a");
//            PreparedStatement stm5 = conn.prepareStatement(create_nurse_table);
//            PreparedStatement stm7 = conn.prepareStatement(create_admin_table);
            stm.executeUpdate();
            stm1.executeUpdate();
            stm2.executeUpdate();
            stm3.executeUpdate();
            stm4.executeUpdate();
            stm5.executeUpdate();

//            stm4.executeUpdate();
//            stm5.executeUpdate();
//            stm6.executeUpdate();
//            stm7.executeUpdate();
            conn.commit();
            created = true;

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MysqlDbUtil.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return created;
    }
 
}
