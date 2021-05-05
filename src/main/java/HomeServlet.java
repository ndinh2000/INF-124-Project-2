
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeServlet", value="/HomeServlet")
public class HomeServlet extends HttpServlet {



    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            writer.println("<link rel='stylesheet' type='text/css' href='" + req.getContextPath() + "/myStyle.css' />\n");
//            writer.println("<link rel='stylesheet' type='text/css' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />\n");
            writer.println("</head>" +
                    "<body>\n" +
                    "    <div id=\"header\"><h1><img src=\"./images/Logo/shopLogo.png\"></h1></div>\n" +
                    "    <div id = \"top-nav-bar\">\n" +
                    "        <ul>\n" +
                    "            <li><a class=\"active\" href=\"./\"><h3> Home </h3></a></li>\n" +
                    "            <li><a href=./DogsServlet><h3> Dogs </h3></a></li>\n" +
                    "            <li><a href=./CatsServlet><h3> Cats </h3></a></li>\n" +
                    "            <li><a href=./ContactServlet><h3> Contact </h3></a></li>\n" +
                    "        </ul>\n" +
                    "    </div>\n" +
                    "\n");

            writer.println("</html>");
            
            writer.println("<div class=\"rating\">\n" +
"            <input type=\"radio\" name=\"rating\" value=\"5\" id=\"5\" onchange=\"javascript:alert(5)\"><label for=\"5\">★</label>\n" +
"            <input type=\"radio\" name=\"rating\" value=\"4\" id=\"4\" onchange=\"javascript:alert(4)\"><label for=\"4\">★</label>\n" +
"            <input type=\"radio\" name=\"rating\" value=\"3\" id=\"3\" onchange=\"javascript:alert(3)\"><label for=\"3\">★</label>\n" +
"            <input type=\"radio\" name=\"rating\" value=\"2\" id=\"2\" onchange=\"javascript:alert(2)\" checked><label for=\"2\">★</label>\n" +
"            <input type=\"radio\" name=\"rating\" value=\"1\" id=\"1\" onchange=\"javascript:alert(1)\"><label for=\"1\">★</label>\n" +
"        </div>");


            resp.setContentType("text/html;charset=UTF-8");
            String url1 = "/Last5";
            String url2 = "/Products";
            RequestDispatcher rd = req.getRequestDispatcher(url1);
            rd.include(req, resp);
            rd = req.getRequestDispatcher(url2);
            rd.include(req, resp);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}