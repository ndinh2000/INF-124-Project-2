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
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
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
//        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
        HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");

        if (cart == null) {
            // Add the newly created ArrayList to session, so that it could be retrieved next time
//            cart = new ArrayList<>();
            cart = new HashMap<String, Integer>();
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
                "    </div>\n");
//                "</body>\n");
//        writer.println("<Html> <body>");
        synchronized (cart) {
            if (pet_id != null && "POST".equals(request.getMethod())) {
                writer.println("<h2 style='text-align:center;color:rgb(95, 139, 235);'>Your Product is Added to the Cart!<h2>");
//                writer.println("pet_id is not null: " + pet_id);
//                cart.add(pet_id); // Add the new item to the previousItems ArrayList
                cart.put(pet_id, cart.getOrDefault(pet_id, 0) + 1);
            } else {
                writer.println("<h2 style='text-align:center;color:rgb(95, 139, 235);'>Your Cart is Empty!<h2>");
            }

            // Display the current previousItems ArrayList
            if (cart.size() != 0){
//                writer.println("<ul>");
                writer.println("<h2 style='padding-left:2%;'>Your Cart:</h2>");
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    String dbName = "petstore";
                    String userName = System.getProperty("RDS_USERNAME");
                    String password = System.getProperty("RDS_PASSWORD");
                    String hostname = System.getProperty("RDS_HOSTNAME");
                    String port = System.getProperty("RDS_PORT");
                    String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                    Connection con = DriverManager.getConnection(jdbcUrl);
                    Statement stmt = con.createStatement();
//                    writer.println("<i>Connected to sql server</i>");
                    float total = 0;
                    Integer qty = 0;
//                    for (String item : cart) {
                    writer.println("<div id='main'>");
                    for (String item : cart.keySet()) {
//                    writer.println("<li>" + item);
                        String sql = "SELECT name, price,profile_picture" +
                                " FROM pet" +
                                " WHERE pet_id = '" + item + "';";
                        ResultSet rs = stmt.executeQuery(sql);
                        qty = cart.get(item);
                        String imgPath = "";
                        while (rs.next()) {
                            total += rs.getFloat("price") * qty;

                            writer.println("<div class=\"row\" style=\"text-align: left\">\n" +
                                    "            <div class=\"col-3 col-s-5\">\n" +
                                    "                <div class=\"profile\">");
                            imgPath = rs.getString("profile_picture");
                            writer.println("<img src='"+imgPath+"'>");
                            writer.println("</div></div>"+
                                    "            <div class=\"col-3 col-s-5\" style=\"text-align: left; padding-top: 35px;\">" +
                                    "                <h2>Name: "+ rs.getString("name")+"</h2>\n" +
                                    "                <h2>Price: $"+rs.getFloat("price")+"</h2>\n" +
                                    "                <h2>Qty: "+qty+"</h2>\n" +
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
//                        writer.println("</body> </Html> ");
                    }
                    writer.println("<hr class='solid'>");
                    writer.println("<div class='row' style='text-align:right;'>\n"+
                            "            <div class=\"col-12\">\n" +
                            "<h2 style='text-align:left;padding-left:25%;'>Total: $"+total+"</h2></div></div>");
//                            "<div class='col-6 col-s-5 addToCartButton' style='text-align:left;padding-top:20px;'><button>Proceed To Checkout</button>\n"+
//                            "</div></div>");
                    writer.println("<fieldset>\n" +
                            "            <legend>\n" +
                            "                Order Information\n" +
                            "            </legend>\n" +
                            "            <div class=\"col-12\" style=\"text-align: left\">\n" +
                            "                <form action=/PA2/Checkout method='post'> \n" +
                            "                    <div class=\"row\">\n" +
                            "                        <div class=\"col-2\" style=\"text-align: left\"> \n" +
                            "                            <label for=\"fname\">First Name: </label><br>\n" +
                            "                            <input type=\"text\" id=\"fname\" name=\"fname\" required><br><br>\n" +
                            "                            <label for=\"lname\">Last Name: </label><br>\n" +
                            "                            <input type=\"text\" id=\"lname\" name=\"lname\" required><br>\n" +
                            "                        </div>\n" +
                            "                        <div class=\"col-2\" style=\"text-align: left\"> \n" +
                            "                            <label for=\"phone\">Phone Number: </label><br>\n" +
                            "                            <input type=\"tel\" id=\"phone\" name=\"phone\" pattern=\"[0-9]{3}-[0-9]{3}-[0-9]{4}\" required placeholder=\"123-456-7890\"><br><br>\n" +
                            "                            <label for=\"clientEmail\">Email: </label><br>\n" +
                            "                            <input type=\"text\" name=\"clientEmail\" required><br><br>\n" +
                            "                        </div>\n" +
                            "                        <div class=\"col-2\" style=\"text-align: left\">\n" +
                            "                            <label for=\"credit-card\">Credit Card Number: </label><br>\n" +
                            "                            <input type=\"text\" id=\"credit-card\" name=\"credit-card\" placeholder=\"VISA Only\" required><br><br>\n" +
                            "                            <label for=\"expiration-date\">Expiration Date: </label><br>\n" +
                            "                            <!-- <input type=\"datetime\" id=\"expiration-date\" name=\"expiration-date\" pattern=\"[0-9]{2}/[0-9]{4}\" placeholder=\"MM/YYYY\" required><br> -->\n" +
                            "                            <select name='expireMM' id='expireMM' required>\n" +
                            "                                <option value=''>Month</option>\n" +
                            "                                <option value='01'>January</option>\n" +
                            "                                <option value='02'>February</option>\n" +
                            "                                <option value='03'>March</option>\n" +
                            "                                <option value='04'>April</option>\n" +
                            "                                <option value='05'>May</option>\n" +
                            "                                <option value='06'>June</option>\n" +
                            "                                <option value='07'>July</option>\n" +
                            "                                <option value='08'>August</option>\n" +
                            "                                <option value='09'>September</option>\n" +
                            "                                <option value='10'>October</option>\n" +
                            "                                <option value='11'>November</option>\n" +
                            "                                <option value='12'>December</option>\n" +
                            "                            </select> \n" +
                            "                            <select name='expireYY' id='expireYY' required>\n" +
                            "                                <option value=''>Year</option>\n" +
                            "                                <option value='21'>2021</option>\n" +
                            "                                <option value='22'>2022</option>\n" +
                            "                                <option value='23'>2023</option>\n" +
                            "                                <option value='24'>2024</option>\n" +
                            "                                <option value='25'>2025</option>\n" +
                            "                            </select>\n" +
                            "                            <br/>\n" +
                            "                        </div>\n" +
                            "                        <div class=\"col-2\" style=\"text-align: left\">\n" +
                            "                            <label for=\"address\">Shipping Address: </label><br>\n" +
                            "                            <input type=\"text\" id=\"address\" name=\"address\" required><br><br>\n" +
                            "                            <label for=\"state\">State: </label><br>\n" +
                            "                            <input type=\"text\" id=\"state\" name=\"state\" required><br>\n" +
                            "                        </div>\n" +
                            "                        <div class=\"col-2\" style=\"text-align: left\">\n" +
                            "                            <label for=\"zip\">Zip Code: </label><br>\n" +
                            "                            <input type=\"number\" id=\"zip\" name=\"zip\" required><br><br>\n" +
                            "                            <label for=\"shipping-method\">Shipping Method: </label><br>\n" +
                            "                            <select name=\"shipping-method\" id=\"shipping-method\">\n" +
                            "                                <option value=\"overnight\">First Overnight</option>\n" +
                            "                                <option value=\"2days\">2 days</option>\n" +
                            "                                <option value=\"ground\">Ground</option>\n" +
                            "                            </select><br/><br>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                    <div class=\"row\" style=\"padding-top: 0;\">\n" +
                            "                        <div class=\"col-12\"  >\n" +
                            "                            <button>Submit Order</button></div>"+
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                </form>\n" +
                            "            </div>\n" +
                            "        </fieldset>\n");
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