package agaze.com.manchesterunited.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by agaze on 22/7/17.
 */

public class DBManager {

    public static  Connection m_connection = null;
    public static Connection m_connectionread = null;
    Statement m_statement;

    private  String m_dbName = " ";
    private  String m_dbPath = " ";
    private  String m_tableName = " ";
    private  String m_tarDirName = " ";

    private  boolean m_createDb = false;


    /**
     * Create the sqlite Db for storing the beacon details
     * @param name
     *          store the name of the DB
     *          path of the db
     *
     * @return
     *          the status while creating the db
     */
    public boolean createDB(String name,String Path){

        return m_createDb;
    }

    /**
     * Update the status of the beacon to db
     * @param value
     * @param identifier
     * @param source
     * @throws SQLException
     */
    public void updateStatus(String value,String identifier,String source) throws SQLException {
        String sql = "UPDATE "+m_tableName+" SET Value = ? WHERE Identifier = ? AND Source = ?";
        m_connection  = DriverManager.getConnection("jdbc:sqlite:"+m_tarDirName +"/apkinfo.db");
        try (
                PreparedStatement pstmt = m_connection.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, value);
            pstmt.setString(2, identifier);
            pstmt.setString(3, source);
            // update
            pstmt.executeUpdate();
            System.out.println("success");
        }
    }

}


