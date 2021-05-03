/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Last5 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            Statement stmt = con.createStatement();
            String sql = "SELECT p.name, p.profile_picture, p.price, p.message" +
                        " FROM pet p, orders o" +
                        " WHERE p.pet_id = o.pet_id" +
                        " AND o.user_id = 1" +
                        " LIMIT 5";
            ResultSet rs = stmt.executeQuery(sql);

            PrintWriter writer = response.getWriter();

            writer.println("<Html> <body>");
            writer.println("<h2>Latest Purchases:</h2>");
            String imgPath = "";
            while (rs.next()) {
                writer.println("<div>");
                writer.println(rs.getString("name"));
                writer.println("</div>");
                imgPath = rs.getString("profile_picture");
                writer.println("<div>");
                writer.println("<img style='width: 200px;' src='" + imgPath + "'");
//                writer.println(imgPath);
                writer.println("</div>");
                writer.println("</br>");
            }
            writer.println("</body> </Html> ");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
