package com.agunga.db;

import com.agunga.cis.MyUtility;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by agunga on 2/2/2017.
 */
public class DbUtil {

    public static Connection connection;
    private static final String O_STRING = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String O_USER = "SYSTEM";
    private static final String O_PASSWORD = "pwd";

    private static final String M_STRING = "jdbc:mysql://localhost/CIS";
    private static final String M_USER = "root";
    private static final String M_PASSWORD = "";

    public static Connection connectDB(DbType dbType) {
        Connection connection = null;
        try {
            switch (dbType) {
                case MYSQL: {
                    return DriverManager.getConnection(M_STRING, M_USER, M_PASSWORD);
                }
                case ORACLE: {
                    return DriverManager.getConnection(O_STRING, O_USER, O_PASSWORD);
                }
                default:
                    return null;
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    public static boolean createTables() {
        boolean created = false;
        Connection conn = DbUtil.connectDB(DbType.MYSQL);
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

        String create_employee_table = "CREATE TABLE IF NOT EXISTS employees("//
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
                Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex1);
            }
            ex.printStackTrace();
        }

        return created;
    }

    public static void createTable(String sql_create_table, String table_name) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql_create_table);
            preparedStatement.executeUpdate();
            System.out.println(" Table created");
            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
                //				System.err.println("Rolled back.");
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            //			System.err.println(e.getMessage());
            if (e.getErrorCode() == 955) {
                System.out.println("Table name already in use. Enter 1 to overwrite the table, 0 to exit");
                int action = MyUtility.myScanner().nextInt();
                if (action == 1) {
                    PreparedStatement preparedStatement;
                    try {
                        preparedStatement = connection.prepareStatement("DROP TABLE " + table_name);
                        preparedStatement.executeUpdate();
                        System.out.println("Table droped, recreating the table..");
                        createTable(sql_create_table, table_name);

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        }
    }

    public static int insertMany(PreparedStatement[] preparedStatements) {
        connection = DbUtil.connectDB(DbType.MYSQL);
        int isInserted = -1;
        try {
            connection.setAutoCommit(false);
            for (int i = 0; i < preparedStatements.length; i++) {
                isInserted = preparedStatements[i].executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getErrorCode() == 1062) {
                MyUtility.myPrintln("Record exists.");
            }
        }
//        System.out.println("isInserted =  "+isInserted);
        return isInserted;
    }

    public static int insert(PreparedStatement preparedStatement) {
        int isInserted = -1;
        try {
            isInserted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getErrorCode() == 1062) {
                MyUtility.myPrintln("Record exists.");
            }
        }
//        System.out.println("isInserted =  "+isInserted);
        return isInserted;
    }

    public static ResultSet select(String sql_select) {
        connection = DbUtil.connectDB(DbType.MYSQL);
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_select);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int update(String sql_update, PreparedStatement preparedStatement) {
        int isUpdated = -1;
        try {
            isUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public static int delete(String sql_delete, int id) {
        int isDeleted = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_delete);
            preparedStatement.setInt(1, id);
            isDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

}
