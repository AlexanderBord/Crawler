package exercises.ex3_bord_alexander;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;

/**
 * The type Crawler.
 */
public class Crawler {

    private HashSet<String> links;

    /**
     * The Counter.
     */
    int counter = 0;
    /**
     * The Status.
     */
    static boolean status = false;

    /**
     * Instantiates a new Crawler.
     */
    public Crawler() {
        links = new HashSet<>();
    }

    /**
     * Get status boolean.
     *
     * @return the boolean
     */
    public boolean getStatus(){
        return status;
    }

    /**
     * Set status.
     *
     * @param newStatus the new status
     */
    public void setStatus(boolean newStatus){
        status = newStatus;
    }

    /**
     * Reset counter.
     */
    public void resetCounter(){
        counter = 0;
    }

    /**
     * Gets page links.
     *
     * @param URL        the url
     * @param depth      the depth
     * @param userId     the user id
     * @param controller the controller
     * @param MAXDEPTH   the maxdepth
     */
    public void getPageLinks(String URL, int depth, int userId, Controller controller, Integer MAXDEPTH) {
        if ((!links.contains(URL) && (depth < MAXDEPTH))) {

            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            try {
                links.add(URL);
                Document document = Jsoup.connect(URL).get();
                Elements images = document.select("img[src]");

                synchronized(this){
                    counter += images.size();
                    controller.addImage(userId, counter);
                    System.out.println("Number of images: "+  counter + ", found for <" + URL +">");
                }

                Elements linksOnPage = document.select("a[href]");

                depth++;
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth, userId, controller, MAXDEPTH);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    /**
     * Check url boolean.
     *
     * @param u the u
     * @return the boolean
     */
    boolean checkUrl(String u) {
        URL checkUrl;
        HttpURLConnection con;
        int result;

        try{
            checkUrl = new URL(u);
            con = (HttpURLConnection) checkUrl.openConnection();
            con.setRequestMethod("HEAD");
            result = con.getResponseCode();
        }
        catch(IOException e){
            return false;
        }
        return result == HttpURLConnection.HTTP_OK;
    }

}
