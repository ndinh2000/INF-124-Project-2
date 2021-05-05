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

            PrintWriter writer = response.getWriter();
            writer.println("<head>");
            writer.println("<p>");
            writer.println("</p>");
            writer.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/myStyle.css' />\n");
            writer.println("<link rel='stylesheet' type='text/css' href='https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' />\n");
            writer.println("</head>");
            writer.println("<Html> <body>");
            writer.println("<div id=\"main\">");
            writer.println("<h3 style=\"text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 0;\">" +
                            "Latest Purchases:</h3>");
//            
            writer.println("<script>\n" +
"            function handleRating(user_id, pet_id, stars) {\n" +
"                let str = \"\";\n" +
"                for (let i = 5; i > stars; --i) {\n" +
"                    str = str + \"<i class=\\\"fa fa-star \\\" style=\\\"font-size:24px; padding: 5px;\\\" onclick=\\\"handleRating(\" + user_id + \", this.parentNode.id,\" + i + \")\\\"></i>\\n\";\n" +
"                }\n" +
"                for (let i = stars; i > 0; --i) {\n" +
"                    str = str + \"<i class=\\\"fa fa-star\\\" style=\\\"color: gold; font-size:24px; padding: 5px;\\\" onclick=\\\"handleRating(\" + user_id + \", this.parentNode.id,\" + i + \")\\\"></i>\\n\";\n" +
"                }\n" +
"                document.getElementById(pet_id).innerHTML = str;\n" +
"                let destination = \"/PA2/HandleRating?\";\n" +
"                destination = destination + \"user_id=\" + user_id + \"&\";\n" +
"                destination = destination + \"pet_id=\" + pet_id + \"&\";\n" +
"                destination = destination + \"stars=\" + stars;\n" +
"\n" +
"                fetch(destination, {\n" +
"                    method: \"POST\",\n" +
"                });\n" +
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
                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(0, '" + pet_id + "', 5)\"></i>\n" +
                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(0, '" + pet_id + "', 4)\"></i>\n" +
                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(0, '" + pet_id + "', 3)\"></i>\n" +
                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(0, '" + pet_id + "', 2)\"></i>\n" +
                        "            <i class=\"fa fa-star \" style=\"font-size:24px; padding: 5px;\" onclick=\"handleRating(0, '" + pet_id + "', 1)\"></i>\n" +
                        "        </div>");
                
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