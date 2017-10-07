package pathfinding;

/**
 * 07.10.2017
 *
 * @author SWirries
 */
public class Path {
    boolean possible = false;
    boolean meet = false;
    boolean best = false;

    public Path(boolean possible, boolean meet) {
        this.possible = possible;
        this.meet = meet;
    }

    public boolean isPossible() {
        return possible;
    }

    public boolean isMeet() {
        return meet;
    }

    public boolean isBest(){
        if(possible){
            if (meet){
                best = true;
            }
        }
        return best;
    }
}
