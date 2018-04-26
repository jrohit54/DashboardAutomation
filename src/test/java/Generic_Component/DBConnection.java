package Generic_Component;


import java.sql.*;

/**
 * Created by rohit on 11/4/18.
 */
public class DBConnection {

    private Connection connection;
    private static Statement statement;
    private static ResultSet rs;


    public void setUp()  {
        String databaseURL = "jdbc:mysql://http://localhost:3306/mowgli";
        String user = "root";
        String password = "videoads";
        connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to Database...");
            connection = DriverManager.getConnection(databaseURL, user, password);
            if (connection != null) {
                System.out.println("Connected to the Database...");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteQuery(String id) {
        try {
            statement = connection.createStatement();
            int rs = statement.executeUpdate(id);


            System.out.println(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void tearDown() {
        if (connection != null) {
            try {
                System.out.println("Closing Database Connection...");
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }






}