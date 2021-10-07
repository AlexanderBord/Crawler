package exercises.ex3_bord_alexander;

import java.util.HashMap;

/**
 * The type Controller.
 */
public class Controller {

    /**
     * The Image counter.
     */
    HashMap<Integer, Integer> imageCounter = new HashMap<>();

    /**
     * Instantiates a new Controller.
     */
    public Controller(){}

    /**
     * Add image.
     *
     * @param userId  the user id
     * @param counter the counter
     */
    public void addImage(Integer userId, Integer counter){ imageCounter.put(userId,  counter);}

    /**
     * Get image counter int.
     *
     * @param userId the user id
     * @return the int
     */
    public int getImageCounter(Integer userId){ return imageCounter.get(userId); }

    /**
     * Destroy map.
     */
    public void destroyMap(){imageCounter.clear();}
}
