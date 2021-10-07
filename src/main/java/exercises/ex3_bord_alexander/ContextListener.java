package exercises.ex3_bord_alexander;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;


/**
 * The type Context listener.
 */
@WebListener
public class ContextListener implements ServletContextListener{

    /**
     * Instantiates a new Context listener.
     */
    public ContextListener() {
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("contextInitialized!");

        ServletContext ctx = servletContextEvent.getServletContext();
        Controller controller = new Controller();

        ctx.setAttribute("controller", controller);
    }

    public void contextDestroyed(ServletContextEvent sce) {

        ServletContext ctx = sce.getServletContext();
        Controller controller = (Controller) ctx.getAttribute("controller");
        controller.destroyMap();

        System.out.println("Database was cleared.");
    }
}
