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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ndinh
 */
public class HandleRating extends HttpServlet {

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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet HandleRating</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet HandleRating at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO `Ratings` VALUES ('3', 'asdf', '7');";
            stmt.executeUpdate(sql);
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
//        processRequest(request, response);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "petstore", "root", "root");
//            con.setAutoCommit(true);
            
            Statement stmt = con.createStatement();
//            String sql = "INSERT INTO `Ratings` VALUES ('2', 'D101', '6');";
//            stmt.execute(sql);
            
            String pet_id = request.getParameter("pet_id");
            String user_id = request.getParameter("user_id");
            String stars = request.getParameter("stars");
            
//            String sql = "INSERT INTO `Ratings` VALUES (3, 'asdf', 7);";
//            stmt.execute(sql);
//            String sql = "SELECT rating FROM Rating WHERE pet_id = " + pet_id + " AND user_id = " + user_id + ";";
//            ResultSet rs = stmt.executeQuery(sql);
//            ResultSet rs = stmt.executeQuery(sql);
            
            String sql = "INSERT INTO `Ratings` VALUES (" + user_id + ", '" + pet_id + "', " + stars + ");";
            stmt.execute(sql);
            
//            sql = "UPDATE Ratings SET rating = " + stars + " WHERE user_id = '" + user_id + "' AND pet_id = '" + pet_id + "';";
//            if (rs == null) {
//                sql = "INSERT INTO `Ratings` VALUES ('" + user_id + "', '" + pet_id + "', '" + stars + "');";
//            } else {
//                sql = "UPDATE Ratings SET rating = " + stars + " WHERE user_id = '" + user_id + "' AND pet_id = '" + pet_id + "';";
////                sql = "UPDATE Ratings SET rating = " + stars + " WHERE user_id = 1 AND pet_id = '" + pet_id + "';";
//            }
//            String sql = "UPDATE Ratings SET rating = " + stars + " WHERE user_id = 1 AND pet_id = '" + pet_id + "';";
//            String sql = "INSERT INTO `Ratings` VALUES ('2', 'D101', '6');";
//            sql = "INSERT INTO `Ratings` VALUES ('2', 'D101', '7');";
//            String sql = "INSERT INTO `Ratings` VALUES ('1', 'D999', '7');";
//            Connection con2 = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
//                    + "petstore", "root", "root");
//            Statement stmt2 = con.createStatement();
//            stmt2.execute(sql);
//            stmt.execute(sql);

//            PreparedStatement preparedStatement = con.prepareStatement(sql);

//            preparedStatement.setString(1, user_id);
//            preparedStatement.setString(2, pet_id);
//            preparedStatement.setLong  (3, 7);

//            preparedStatement.executeUpdate();
//            Statement stmt = con.createStatement();
//            stmt = con.createStatement();
//            stmt.execute(sql);
//            stmt.execute(sql);
            
//            stmt.executeUpdate(sql);
//            PrintWriter out = response.getWriter();
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            out.print(pet_id);
//            out.print(user_id);
//            out.print(stars);
//            out.flush();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
