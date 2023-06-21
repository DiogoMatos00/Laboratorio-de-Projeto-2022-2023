package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connection {
    
    public static Connection connect(){
        /*
         * Connection to database. It will use a username and a password all together with the db url in this case is localhost:3306/projeto.
         * It Will return a Connection from java.sql
         * 
         * 
         * Note: We should store our login credentials(uname & password) in a safer way
         */
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/projeto";
        String uname = "teste";
        String password = "Adminadmin123";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, uname, password);
            return conn;
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void disconnect(Connection conn){
        /*
         * Disconnect to a database. It will get a Connection from java.sql and disconnect.
         * It will not return anything.
         * 
         * Parameters:
         *  Connection.
         */
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet execute_query(Connection conn, String query){
        /*
         * Execute query on a database. This will Execute a given query.
         * It will return a ResultSet object from java.sql.
         * 
         * Parameters:
         *  Connection.
         *  String query.
         */
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}