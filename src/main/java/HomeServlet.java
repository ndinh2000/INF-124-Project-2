
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
            String sql = "SELECT name, age, gender, price, message, profile_picture FROM petstore.pet";
            ResultSet rs = stmt.executeQuery(sql);




            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html><html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>The Pet Shop | Home</title>\n");
            writer.println("<link rel='stylesheet' type='text/css' href='" + req.getContextPath() +  "/myStyle.css' />\n");
            writer.println("</head>"+
                    "<body>\n" +
                    "    <div id=\"header\"><h1><img src=\"images/shopLogo.png\"></h1></div>\n" +
                    "    <div id = \"top-nav-bar\">\n" +
                    "        <ul>\n" +
                    "            <li><a class=\"active\" href=\"./\"><h3> Home </h3></a></li>\n" +
                    "            <li><a href=./DogsServlet><h3> Dogs </h3></a></li>\n" +
                    "            <li><a href=./CatsServlet><h3> Cats </h3></a></li>\n" +
                    "            <li><a href=./ContactServlet><h3> Contact </h3></a></li>\n" +
                    "        </ul>\n" +
                    "    </div>\n" +
                    "\n");


            writer.println("<h3 style=\"text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 0;\">Our featured pets:</h3>\n" +
                    "        <!-- <div class=\"featuredPets\"> -->\n" +
                    "            <div class=\"row\" style=\"padding-top: 0;\">");
            writer.println("<Html> <body>");
            while (rs.next()) {
//                writer.println(rs.getString("name"));
                writer.println("<div class=\"col-3 col-s-5 featuredPets\">\n" +
                        "                    <a href=\"../CatPgs/cat1.html\" style=\"text-decoration: none\">\n" +
                        "                        <div style=\"height: 275px;\">\n" +
                        "                            <img src=\"images/CatImages/"+ rs.getString("profile_picture")+"\">\n" +
                        "                        </div>\n" +
                        "                        <h3> "+ rs.getString("name") +"- $"+ rs.getString("price") +"</h3>\n" +
                        "                    </a>\n" +
                        "                    <p>  "+ rs.getString("message") +"</p>\n" +
                        "                    <hr class=\"solid\">\n" +
                        "                </div>");
            }
            writer.println("</div>\n" +
                    "\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
