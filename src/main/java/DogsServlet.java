
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebListener
//class Config implements ServletContextListener {
//    doGet();
//}

@WebServlet(name = "DogsServlet", value="/DogsServlet")
public class DogsServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            Statement stmt = con.createStatement();
            String sql = "SELECT name, age, gender, price, message, profile_picture FROM petstore.pet WHERE pet_id LIKE 'D%'";
            ResultSet rs = stmt.executeQuery(sql);

            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>The Pet Shop | Dogs</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"myStyle.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div id=\"header\"><h1><img src=\"images/shopLogo.png\"></h1></div>\n" +
                    "    <div id = \"top-nav-bar\">\n" +
                    "        <ul>\n" +
                    "            <li><a href=./><h3> Home </h3></a></li>\n" +
                    "            <li><a class=\"active\" href=\"./DogsServlet\"><h3> Dogs </h3></a></li>\n" +
                    "            <li><a href=./CatsServlet><h3> Cats </h3></a></li>\n" +
                    "            <li><a href=./ContactServlet><h3> Contact </h3></a></li>\n" +
                    "        </ul>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div id=\"main\">\n" +
                    "        <!-- <div class=\"featuredPets\"> -->\n" +
                    "            <div class=\"row\">");


            while (rs.next()) {
//                writer.println(rs.getString("name"));
                writer.println("<div class=\"col-3 col-s-5 featuredPets\">\n" +
                        "                    <a href=\"../DogPgs/dog1.html\" style=\"text-decoration: none\">\n" +
                        "                        <div style=\"height: 275px;\">\n" +
                        "                            <img src=\"images/DogImages/"+ rs.getString("profile_picture")+"\">\n" +
                        "                        </div>\n" +
                        "                        <h3> "+ rs.getString("name") +"- $"+ rs.getString("price") +"</h3>\n" +
                        "                    </a>\n" +
                        "                    <p>  "+ rs.getString("message") +"</p>\n" +
                        "                    <hr class=\"solid\">\n" +
                        "                </div>");
            }
            writer.println("</div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
