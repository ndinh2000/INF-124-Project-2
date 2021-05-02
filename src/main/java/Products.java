/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.OutputStream;
import javax.servlet.ServletOutputStream;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author ndinh
 */
public class Products extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            Statement stmt = con.createStatement();
            String sql = "SELECT name, profile_picture, pet_id FROM petstore.pet";
            ResultSet rs = stmt.executeQuery(sql);

            PrintWriter writer = response.getWriter();
            writer.println("<Html> <body>");
            writer.println("<h2>Available Products</h2>");
            String imgPath = "";
            while (rs.next()) {
                writer.println("<div>");
                writer.println(rs.getString("name"));
                writer.println("</div>");
                imgPath = rs.getString("profile_picture");
                writer.println("<div>");
                writer.println("<a href=\"/PA2/ProductDetail?pet_id=" + rs.getString("pet_id") + "\">");
                writer.println("<img style='width: 200px;' src='" + imgPath + "'");
                writer.println("</a>");
                writer.println("</div>");
                writer.println("</br>");
            }
//            writer.println("<img src='./images/1.png'>");
//            writer.println("<img src='./images/cat1.jpg' >");
            writer.println("<img src='./images/CatImages/cat1.jfif' >");
//            writer.println("<img src=\"../../../../pics/1.png\">");
//            writer.println("<img src=\"../../../pics/1.png\">");
//            writer.println("<img src=\"../../pics/1.png\">");
//            writer.println("<img src=\"../pics/1.png\">");
//            writer.println("<img src=\"pics/1.png\">");

            writer.println("</body> </Html> ");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
