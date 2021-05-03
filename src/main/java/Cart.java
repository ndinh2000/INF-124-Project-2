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
import java.util.ArrayList;
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
@WebServlet(urlPatterns = {"/Cart"})
public class Cart extends HttpServlet {

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
        String pet_id = request.getParameter("pet_id");
        HttpSession session = request.getSession();
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
        if (cart == null) {
            // Add the newly created ArrayList to session, so that it could be retrieved next time
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html><html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>The Pet Shop</title>\n");
        writer.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/myStyle.css' />");
        writer.println("</head>"+
                "<body>\n" +
                "    <div id=\"header\"><h1><img src=\"images/shopLogo.png\"></h1></div>\n" +
                "    <div id = \"top-nav-bar\">\n" +
                "        <ul>\n" +
                "            <li><a href=./><h3> Home </h3></a></li>\n" +
                "            <li><a href=./DogsServlet><h3> Dogs </h3></a></li>\n" +
                "            <li><a href=./CatsServlet><h3> Cats </h3></a></li>\n" +
                "            <li><a href=./ContactServlet><h3> Contact </h3></a></li>\n" +
                //Trying below for now
                "<li><a href=./Products><h3> Latest Purchases </h3></a></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</body>\n");
        writer.println("<Html> <body>");
        synchronized (cart) {
            if (pet_id != null && "POST".equals(request.getMethod())) {
                writer.println("<h2 style='text-align:center;'>Your Product is Added to the Cart!<h2>");
//                writer.println("pet_id is not null: " + pet_id);
                cart.add(pet_id); // Add the new item to the previousItems ArrayList
            } else {
                writer.println("pet_id is null");
            }

            // Display the current previousItems ArrayList
            if (cart.size() == 0) {
                writer.println("<i>No items</i>");
            } else {
//                writer.println("<ul>");
                writer.println("<h2 style='padding-left:2%;'>Your Cart:</h2>");
                try {
//                    writer.println("<i>Trying to connect to sql server</i>");
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                            + "petstore", "root", "anqizhong1999.");
                    Statement stmt = con.createStatement();
//                    writer.println("<i>Connected to sql server</i>");
                    float total = 0;
                    for (String item : cart) {
//                    writer.println("<li>" + item);
                        String sql = "SELECT name, price,profile_picture" +
                                " FROM pet" +
                                " WHERE pet_id = '" + item + "';";
                        ResultSet rs = stmt.executeQuery(sql);
                        String imgPath = "";
                        while (rs.next()) {
                            total = total+rs.getFloat("price");
                            writer.println("<div id='main'>");
                            writer.println("<div class=\"row\" style=\"text-align: left\">\n" +
                                    "            <div class=\"col-3 col-s-5\">\n" +
                                    "                <div class=\"profile\">");
                            imgPath = rs.getString("profile_picture");
                            writer.println("<img src='"+imgPath+"'>");
                            writer.println( "</div>\n" +
                                    "            </div>\n" +
                                    "            <div class=\"col-3 col-s-5\" style=\"text-align: left; padding-top: 35px;\">" +
                                    "                <h2>Name: "+ rs.getString("name")+"</h2>\n" +
                                    "                <h2>Price: $"+rs.getFloat("price")+"</h2>\n" +
                                    "            </div>\n" +
                                    "        </div>");
//                            writer.println("<div>");
//                            writer.println(rs.getString("name"));
//                            writer.println("</div>");
//                            imgPath = rs.getString("profile_picture");
//                            writer.println("<div>");
//                            writer.println("<img style='width: 200px;' src='" + imgPath + "'");
//                            writer.println("</div>");
//                            writer.println("</br>");
                        }
                        writer.println("</body> </Html> ");
                    }
                    writer.println("<hr class='solid'>");
                    writer.println("<div class='row' style='text-align:right;'>\n"+
                            "            <div class=\"col-6 col-s-5\">\n" +
                            "<h2 style='text-align:left;padding-left:52%;'>Total: $"+total+"</h2></div>\n"+
                            "<div class='col-6 col-s-5 addToCartButton' style='text-align:left;padding-top:20px;'><button>Proceed To Checkout</button>\n"+
                            "</div></div>");
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
//                writer.println("</ul>");
            }
        }
        writer.println("</body> </Html> ");
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