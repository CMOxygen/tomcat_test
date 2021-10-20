import java.sql.*;
import java.util.ArrayList;

public class JavaTestMySQL {

//    private static final String url = "jdbc:mysql://localhost:3306/java_test";
//    private static final String url = "jdbc:mysql://193.124.112.30:3306/test_datagrip";
//    private static final String user = "root";
//    //    private static final String password = "1q2w3e4r5t";
//    private static final String password = "qPwOeIrUtY_1";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public void connect(String url, String user, String password) {

//        String querySelect = "SELECT * FROM test_datagrip.Users";
//        String queryInsert = "INSERT INTO test_datagrip.Users(username, password) VALUES ('" + inString + "', '1488')";
        //      String queryTruncate = "TRUNCATE TABLE test_datagrip.Users";

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void executeQuery(String query) {

        try {
            stmt.executeUpdate(query);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String> executeQueryReturn(String query) {

        ArrayList<String> queryResult = new ArrayList<String>();

        try {
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                queryResult.add(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return queryResult;
    }

    public void closeConnection() {
        try {
            rs.close();
        } catch (SQLException ex) {
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
        }
    }
}