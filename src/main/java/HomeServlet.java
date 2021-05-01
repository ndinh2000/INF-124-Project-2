
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeServlet", value="/HomeServlet")
public class HomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            Statement stmt = con.createStatement();
            String sql = "SELECT name, profile_picture FROM petstore.pet";
            ResultSet rs = stmt.executeQuery(sql);




            PrintWriter writer = resp.getWriter();
            writer.println("<Html> <body>");
            String imgPath = "";
            while (rs.next()) {
                writer.println(rs.getString("name"));
                imgPath = rs.getString("profile_picture");
                writer.println("</br>");
            }
            writer.println("</body> </Html> ");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
