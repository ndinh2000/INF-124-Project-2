/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ndinh
 */
//@WebServlet(urlPatterns = {"/Welcome"})
public class Welcome extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private int id = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getID(request, response);
        
        response.setContentType("text/html;charset=UTF-8");
        String url1 = "/Last5";
        String url2 = "/Products";

        RequestDispatcher rd = request.getRequestDispatcher(url1);
        rd.include(request, response);
        rd = request.getRequestDispatcher(url2);
        rd.include(request, response);
    }
    
    private synchronized void getID(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        Integer curID = (Integer) session.getAttribute("curID");
        
        String heading;

        if (curID == null) {
            session.setAttribute("curID", this.id);
            ++this.id;
            heading = "Welcome, New-Comer, your ID is "
                    + session.getAttribute("curID")
                    + ", nextID is " + this.id;
        } else {
            heading = "Welcome Back, your ID is "
                    + session.getAttribute("curID")
                    + ", nextID is " + this.id;
        }
        
        PrintWriter out = response.getWriter();
        out.println(heading);
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
