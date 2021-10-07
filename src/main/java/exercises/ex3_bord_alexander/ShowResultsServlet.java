package exercises.ex3_bord_alexander;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Show results servlet.
 */
@WebServlet(name = "ShowResultsServlet", value = "/ShowResults")
public class ShowResultsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Integer userId = (Integer) session.getAttribute("userId");
        Controller controller = (Controller) session.getAttribute("controller");

        if(controller == null){
            request.getRequestDispatcher("index.html").include(request, response);
        }
        else{
            String url = (String)session.getAttribute("url");
            Crawler crawler = (Crawler) session.getAttribute("crawler");
            boolean status = crawler.getStatus();

            String done = "<h2><b>" + "Done!" +"</b></h2>";
            String notDone = "<h3><b>" + "still crawling... "+ "<a href=\"ShowResults\">reload this page</a>" + " for final results!" + "</b></h3>";
            String temp;

            if(status){
                crawler.setStatus(false);
                temp = done;
            }
            else{
                temp = notDone;
            }

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            out.println("<div class=\"container-fluid\">" + "<h1> <b> Crawling </b> </h1>");
            out.println("<h1> <b>" + url + "</b> </h1>");
            out.println("<h2>" + "Found "  + controller.getImageCounter(userId) +  " images!" + "</h2>");
            out.println(temp);
            out.println("<h4>" + "<a href=\"MainServlet\">back to main page</a>" + "</h4>"  + "</div>" );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.html").include(request, response);
    }
}
