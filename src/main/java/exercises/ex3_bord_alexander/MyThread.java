package exercises.ex3_bord_alexander;


/**
 * The type My thread.
 */
public class MyThread extends Thread {

    private final String url;
    private final int userId;
    private final Controller controller;
    private final Crawler crawler;
    private final Integer MAXDEPTH;


    /**
     * Instantiates a new My thread.
     *  @param url        the url
     * @param userId     the user id
     * @param controller the controller
     * @param crawler    the crawler
     * @param MAXDEPTH   the maxdepth
     */
    public MyThread(String url, int userId, Controller controller, Crawler crawler, Integer MAXDEPTH) {
        this.url = url;
        this.userId = userId;
        this.controller = controller;
        this.crawler = crawler;
        this.MAXDEPTH = MAXDEPTH;
    }

    public void run() {

        try {
            Thread.sleep((long)(Math.random() * 200));
            System.out.println("Begin crawling <"+ url + ">" + " at depth <0>");
            crawler.getPageLinks(url,0, userId, controller, MAXDEPTH);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        crawler.setStatus(true); //thread is done
        crawler.resetCounter();
        System.out.println("End of thread for url <" + url + ">");
    }
}

