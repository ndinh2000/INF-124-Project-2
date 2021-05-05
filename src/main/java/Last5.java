/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ndinh
 */
@WebServlet(name = "Last5", value="/Last5")
public class Last5 extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            Statement stmt = con.createStatement();
            HttpSession session = request.getSession(true);
            String user_id = (String) session.getAttribute("user_id");
            String sql = "SELECT p.pet_id,p.name, p.profile_picture, p.price" +
                    " FROM pet p, orders o" +
                    " WHERE p.pet_id = o.pet_id" +
                    " AND o.user_id = 1" +
//                    + user_id +
                    " LIMIT 5";
            ResultSet rs = stmt.executeQuery(sql);
//            con.setAutoCommit(true);
//            String sql = "INSERT INTO `Ratings` VALUES ('2', 'D101', '6');";
//            
////            PreparedStatement preparedStatement = con.prepareStatement(sql);
////            preparedStatement.setInt  (1, 1);
////            preparedStatement.setString(2, "xz");
////            preparedStatement.setInt  (3, 4);
////            preparedStatement.setInt  (1, 5);
////            preparedStatement.setString(2, "D102");
////            preparedStatement.setInt  (3, 3);
////            int rowsAffected = preparedStatement.executeUpdate();
//            String sql = "UPDATE Ratings SET rating = '?' WHERE user_id = '?' AND pet_id = '?';";
//            Statement stmt = con.createStatement();
//            stmt.execute(sql);
//            
////            con.commit();
//            
//            con.close();
//            con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
//                    + "petstore", "root", "root");
            
//            Statement stmt = con.createStatement();
//            sql = "SELECT * FROM Ratings;";
//            ResultSet rs = stmt.executeQuery(sql);
            
//            PrintWriter writer = response.getWriter();
//            writer.println("<Html> <body>");
//            writer.println("<p>");
//            writer.println("this many rows got affected: " + rowsAffected);
//            writer.println("this many rows got affected: ");
//            writer.println("</p>");
//            while (rs.next()) {
//                writer.println("<div>");
//                writer.println(rs.getInt("user_id"));
//                writer.println("</div>");
//                writer.println("<div>");
//                writer.println(rs.getString("pet_id"));
//                writer.println("</div>");
//                writer.println("<div>");
//                writer.println(rs.getInt("stars"));
//                writer.println("</div>");
//            }
//            writer.println("</body></Html>");
            


//            String sql = "INSERT INTO `Ratings` (user_id, pet_id, rating) VALUES ('3', 'asdf', '7');";
//            stmt.executeUpdate(sql);

            PrintWriter writer = response.getWriter();
            writer.println("<head>");
            writer.println("<p>");
            writer.println(request.getContextPath() + "/myStyle.css");
            writer.println("</p>");
            writer.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/myStyle.css' />\n");
//            writer.println("<link rel='stylesheet' type='text/css' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />\n");
            writer.println("<link rel='stylesheet' type='text/css' href='https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' />\n");
            writer.println("</head>");
            writer.println("<Html> <body>");
            writer.println("<div id=\"main\">");
            writer.println("<h3 style=\"text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 0;\">" +
                            "Latest Purchases:</h3>");
//            
            writer.println("<script>\n" +
            "            function handleRating(pet_id, stars) {\n" +
            "                console.log(pet_id, stars);\n" +
            "                let str = \"\";\n" +
            "                for (let i = 5; i > stars; --i) {\n" +
            "                    str = str + \"<i class=\\\"fa fa-star \\\" style=\\\"font-size:24px; padding: 5px;\\\" onclick=\\\"handleRating(this.parentNode.id,\" + i + \")\\\"></i>\\n\";\n" +
            "                }\n" +
            "                for (let i = stars; i > 0; --i) {\n" +
            "                    str = str + \"<i class=\\\"fa fa-star\\\" style=\\\"color: gold; font-size:24px; padding: 5px;\\\" onclick=\\\"handleRating(this.parentNode.id,\" + i + \")\\\"></i>\\n\";\n" +
            "                }\n" +
            "                console.log(str);\n" +
            
            "let data = {\n" +
                "                    pet_id: \"pet_id\",\n" +
                "                    stars: \"stars\",\n" +
                "                    user_id: 1,\n" +
                "                };\n" +
                "\n" +
                "                fetch(\"/PA2/HandleRating\", {\n" +
                "                    method: \"POST\", \n" +
                "                    body: JSON.stringify(data)\n" +
                "                    }).then(res => {\n" +
                "                    console.log(\"Request complete! response:\", res);\n" +
                "                });" +
                    "                document.getElementById(pet_id).innerHTML = str;\n" +
            "            }\n" +
            "        </script>");
//            
            writer.println("<div class=\"row\" style=\"padding-top: 0;\">");
            String imgPath = "";
            while (rs.next()) {
                writer.println("<div class=\"col-3 col-s-5 featuredPets\">");
                writer.println("<a href=\"/PA2/ProductDetail?pet_id=" + rs.getString("pet_id") + "\">");
                writer.println("<div style=\"height: 275px;\">");
                imgPath = rs.getString("profile_picture");
                writer.println("<img src="+ imgPath +">");
                writer.println("</div>"); //for style=height
                writer.println("<h3>" + rs.getString("name") +
                                " - $" + rs.getString("price") + "</h3>");
                writer.println("</a>");
                
                String pet_id = rs.getString("pet_id");
                writer.println("<div class=\"rate-container\" id=\"" + pet_id + "\">\n" +
                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating('" + pet_id + "', 5)\"></i>\n" +
                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating('" + pet_id + "', 4)\"></i>\n" +
                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating('" + pet_id + "', 3)\"></i>\n" +
                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating('" + pet_id + "', 2)\"></i>\n" +
                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating('" + pet_id + "', 1)\"></i>\n" +
                        "        </div>");
                
//                writer.println("<div class=\"rate-container " + rs.getString("pet_id") + "\">" +
//                                "    <i class=\"fa fa-star \"></i>\n" +
//                                "    <i class=\"fa fa-star \"></i>\n" +
//                                "    <i class=\"fa fa-star \"></i>\n" +
//                                "    <i class=\"fa fa-star \"></i>\n" +
//                                "    <i class=\"fa fa-star \"></i>\n" +
//                                "</div>");
                
//                writer.println("<div class=\"rating\">");
//                writer.println("<input type=\"radio\" name=\"rating\" value=\"5\" id=\"5\" onchange=\"javascript:alert(5)\"><label for=\"5\">☆</label>");
//                writer.println("<input type=\"radio\" name=\"rating\" value=\"4\" id=\"4\" onchange=\"javascript:alert(4)\"><label for=\"4\">☆</label>");
//                writer.println("<input type=\"radio\" name=\"rating\" value=\"3\" id=\"3\" onchange=\"javascript:alert(3)\"><label for=\"3\">☆</label>");
//                writer.println("<input type=\"radio\" name=\"rating\" value=\"2\" id=\"2\" onchange=\"javascript:alert(2)\" checked><label for=\"2\">☆</label>");
//                writer.println("<input type=\"radio\" name=\"rating\" value=\"1\" id=\"1\" onchange=\"javascript:alert(1)\"><label for=\"1\">☆</label>");
//                writer.println("</div>");

//                writer.println("<div class=\"rate\">\n" +
//                                "    <input type=\"radio\" id=\"star5\" name=\"rate\" value=\"5\" />\n" +
//                                "    <label for=\"star5\" title=\"text\">5 stars</label>\n" +
//                                "    <input type=\"radio\" id=\"star4\" name=\"rate\" value=\"4\" />\n" +
//                                "    <label for=\"star4\" title=\"text\">4 stars</label>\n" +
//                                "    <input type=\"radio\" id=\"star3\" name=\"rate\" value=\"3\" />\n" +
//                                "    <label for=\"star3\" title=\"text\">3 stars</label>\n" +
//                                "    <input type=\"radio\" id=\"star2\" name=\"rate\" value=\"2\" />\n" +
//                                "    <label for=\"star2\" title=\"text\">2 stars</label>\n" +
//                                "    <input type=\"radio\" id=\"star1\" name=\"rate\" value=\"1\" />\n" +
//                                "    <label for=\"star1\" title=\"text\">1 star</label>\n" +
//                                "  </div>");
                
                writer.println("<hr class=\"solid\">");
                writer.println("</div>");
            }
            writer.println("</div>"); //for class=row
            writer.println("</div>"); //for id= main
            writer.println("</body> </Html> ");

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