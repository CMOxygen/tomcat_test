import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/hello")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JavaTestMySQL sql = new JavaTestMySQL();

        HttpSession session = req.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");

        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }
        session.setAttribute("visitCounter", visitCounter);
        String username = req.getParameter("username");

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        if (username == null) {
            printWriter.write("Hello Anon" + "<br>");
        } else {
            printWriter.write("Hello " + username + "<br>");
        }
        printWriter.write("Page was visited " + visitCounter + " times. <br>");
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            sql.connect("jdbc:mysql://localhost:3306/test_datagrip", "root", "qPwOeIrUtY_1");
//            193.124.112.30
            sql.executeQuery("INSERT INTO test_datagrip.Users(username, password) VALUES ('tomcat', '1488')");
            ArrayList<String> sqlOutput = sql.executeQueryReturn("SELECT * FROM test_datagrip.Users");

            for(int i = 0; i < sqlOutput.size(); i++)
                printWriter.write(sqlOutput.get(i) + "<br>");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
//        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Connection con = DriverManager.getConnection("jdbc:mysql://193.124.112.30:3306/test_datagrip", "root", "qPwOeIrUtY_1");
//            Statement stmt = con.createStatement();
//            stmt.executeUpdate("INSERT INTO test_datagrip.Users(username, password) VALUES ('tomcat', '1488')");
//            ResultSet rslt = stmt.executeQuery("SELECT * FROM test_datagrip.Users");
//
//            while (rslt.next())
//            {
//                printWriter.write(rslt.getString(1) + " | " + rslt.getString(2) + " | " + rslt.getString(3) + "<br>");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        printWriter.close();
    }
}