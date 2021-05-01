
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebListener
//class Config implements ServletContextListener {
//    doGet();
//}

@WebServlet(name = "ContactServlet", value="/ContactServlet")
public class ContactServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            PrintWriter writer = resp.getWriter();

            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">      \n" +
                    "    <title>The Pet Shop | Contact</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"myStyle.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div id=\"header\"><h1><img src=\"images/shopLogo.png\"></h1></div>\n" +
                    "    <div id = \"top-nav-bar\">\n" +
                    "        <ul>\n" +
                    "            <li><a href=./><h3> Home </h3></a></li>\n" +
                    "            <li><a href=./DogsServlet><h3> Dogs </h3></a></li>\n" +
                    "            <li><a href=./CatsServlet><h3> Cats </h3></a></li>\n" +
                    "            <li><a class=\"active\" href=\"./ContactServlet\"><h3> Dogs </h3></a></li>\n" +
                    "        </ul>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div id=\"main\">\n" +
                    "\n" +
                    "        <div>\n" +
                    "            <h3 style=\"text-align: left;font-size: 1.5em;margin-left: 0.5in;\">Plan Your Visit</h3>\n" +
                    "            <p style=\"margin-left: 0.5in; margin-right: 0.5in;font-size: 20px;\"> 5365 Alton Pkwy, Irvine, CA 92604 </p>\n" +
                    "            <p style=\"margin-left: 0.5in; margin-right: 0.5in;font-size: 20px;\"> Hours: 10 am - 7 pm every day</p>\n" +
                    "            <div class=\"col-3 col-s-5 img-container\" style=\"width:100%; text-align: center;\">\n" +
                    "                <img style = \"max-width: 100%; max-height: 100%; object-fit: contain;\"; src=\"images/Map.jpg\">\n" +
                    "            </div>\n" +
                    "\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div>\n" +
                    "            <h3 style=\"text-align: left;font-size: 1.5em;margin-left: 0.5in;\">Get In Touch!</h3>\n" +
                    "            <ul style=\"margin-left: 0.5in;font-size: 20px;\">\n" +
                    "                <li>Email: ThePetShop@gmail.com</li>\n" +
                    "                <li>Phone: 999-545-9023</li>\n" +
                    "            </ul>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div>\n" +
                    "            <h3 style=\"text-align: left;font-size: 1.5em;margin-left: 0.5in;\">Additional Content Credits</h3>\n" +
                    "            <p style=\"margin-left: 0.5in; margin-right: 0.5in;font-size: 20px;\">Some of the writings and pictures for the animals on our website came from the websited below.</p>\n" +
                    "            <ul style=\"margin-left: 0.5in;font-size: 20px;\">\n" +
                    "                <li>OC Animal Care Website</li>\n" +
                    "                <li>Irvine Animal Shelter Website</li>\n" +
                    "            </ul>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");

        } catch (Exception e){
        }
    }
}