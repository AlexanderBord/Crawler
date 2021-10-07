package exercises.ex3_bord_alexander;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Crawl servlet.
 */
@WebServlet(name = "CrawlServlet", value = "/Crawl")
public class CrawlServlet extends HttpServlet {

    private Integer MAXDEPTH;
    private static int userId;

    @Override
    public void init() {
        String temp = this.getServletContext().getInitParameter("maxdepth");
        MAXDEPTH = Integer.parseInt(temp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.html").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(true);
        String url = request.getParameter("website");

        Crawler crawler = new Crawler();
        if(crawler.checkUrl(url)){

            userId++;
            out.println("<div class=\"container-fluid\">" + "<br><br><br><br><br><br>" + " <div style=\"text-align: center\">");
            out.print("<img src='" + request.getContextPath() + "/images/crawling.jpg' alt='image' />");
            out.println("<h1><b>" + "Crawling!" +"</b></h1>");
            out.println("<h2>" + "Crawling started, visit <a href=\"ShowResults\">this page</a>" + " to track results!" +  "</h2>" + "</div></div>");

            //String userId = session.getId();
            ServletContext ctx = getServletContext();
            Controller controller = (Controller)ctx.getAttribute("controller");


            session.setAttribute("controller", controller);
            session.setAttribute("url", url);
            session.setAttribute("crawler", crawler);
            session.setAttribute("userId", userId);

            System.out.println("Starting Thread for url <" + url + ">");
            MyThread mt = new MyThread(url, userId, controller, crawler, MAXDEPTH);
            mt.start();
        }
        else{
            out.println("<div class=\"container-fluid\">" + "<br><br><br><br>" + " <div style=\"text-align: center\">");
            out.print("<img src='" + request.getContextPath() + "/images/error.JPG' alt='image' />");
            out.println("<h1><b>" + "Error" +"</b></h1>");
            out.println("<h2>" + "This URL is not valid, <a href=\"MainServlet\">try again</a>" + "</h2>" + "</div></div>");
        }
    }
}
