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
@WebServlet(name = "ShowList", value="/ShowList")
public class ShowListServlet extends HttpServlet {
    
    private String imagePath;
    
    public void init() throws ServletException {

        this.imagePath = "/pics";
        
    }

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
        
//        ServletContext sc = getServletContext();
//
//        try (InputStream is = sc.getResourceAsStream("/pics/1.png")) {
//
//            // it is the responsibility of the container to close output stream
//            OutputStream os = response.getOutputStream();
//            os.write("Getting image ...".getBytes());
//
//            if (is == null) {
//
//                response.setContentType("text/plain");
//                os.write("Failed to send image".getBytes());
//            } else {
//
//                response.setContentType("image/jpeg");
//
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//
//                while ((bytesRead = is.read(buffer)) != -1) {
//
//                    os.write(buffer, 0, bytesRead);
//                }
//            }
//        }
          
        ServletOutputStream writer = response.getOutputStream();
        writer.println("<Html> <body>");
//        writer.println("<img src=\"./pics/1.png>\"");
//        writer.println("<img src='" + request.getContextPath() + "/pics/1.png' >");
        writer.println("<img src='pics/1.png' >");
//        writer.println("<img src='images/cat1.jfif' >");
        writer.println("</body> </Html> ");
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
////            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
////                    + "petstore", "root", "root");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petstore",
//                                "root", "root");
//            Statement stmt = con.createStatement();
//            String sql = "SELECT name, profile_picture FROM petstore.pet";
//            ResultSet rs = stmt.executeQuery(sql);
//
////            PrintWriter writer = response.getWriter();
//            ServletOutputStream writer = response.getOutputStream();
//            writer.println("<Html> <body>");
//            while (rs.next()) {
//                writer.println(rs.getString("name"));
//                String imgPath = rs.getString("profile_picture");
////                writer.println("<img src=" + imgPath +"/>");
//                writer.println("<img src=\"https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-1100x628.jpg\">");
//                writer.println("</br>");
//                String s = request.getServletContext().getRealPath("/");
//                writer.println(s);
//                writer.println("</br>");
//                String p = request.getPathInfo();
//                writer.println(p);
//                writer.println(this.imagePath);
////                writer.println(request.getServletContext());
//            }

//            response.setContentType("text/html");
//            PrintWriter writer = response.getWriter();
//            writer.println("<Html> <body>");
//            writer.println("<div style=\"width: 500px\">");
            
//            response.setContentType("image/jpeg");
//            ServletOutputStream outStream = response.getOutputStream();
//            FileInputStream fin = new FileInputStream("C:\\Users\\ndinh\\Documents\\INF 124 Internet Application Engineering\\Code\\Project 2\\PA2\\pics\\1.png");
//
//            BufferedInputStream bin = new BufferedInputStream(fin);
//            BufferedOutputStream bout = new BufferedOutputStream(outStream);
//            int ch =0; ;
//            while((ch=bin.read())!=-1)
//                bout.write(ch);
//
//            bin.close();
//            fin.close();
//            bout.close();
//            outStream.close();
//             rd.include(request, response);
//
//            writer.println("</div>");RequestDispatcher rd=request.getRequestDispatcher("servlet2");
//            rd.include(request, response);
//
//            writer.println("</div>");
            
//            File img = new File(imagePath, "1.png");
//            String contentType = getServletContext().getMimeType(image.getName());
//            writer.println(contentType);
//            writer.println("<img src=" + imgPath +"/>");
//            writer.println("<img src=\"../../../../pics/1.png\">");
//            writer.println("<img src=\"../../../pics/1.png\">");
//            writer.println("<img src=\"../../pics/1.png\">");
//            writer.println("<img src=\"../pics/1.png\">");
//            writer.println("<img src=\"pics/1.png\">");

//            writer.println("</body> </Html> ");
//            response.reset();
//            response.setContentType(contentType);
//            response.setHeader("Content-Length", String.valueOf(img.length()));
//
//            // Write image content to response.
//            File.copy(img.toPath(), response.getOutputStream());


//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ShowListServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>The list of emails:</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
