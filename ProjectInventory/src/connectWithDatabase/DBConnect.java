/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectWithDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBConnect {

    static final String DB_URL = "jdbc:mysql://localhost/inventory";
    static final String USER = "root";
    static final String PASS = "";

    public Connection getConnection() {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    
    public void close(Connection con, Statement stmnt, ResultSet rs){
        try {
            if (con!=null) {
                 con.close();
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stmnt!=null) {
                stmnt.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (rs!=null) {
                rs.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("connected");
    }
    
    
    
}
