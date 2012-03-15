package tdt4240.a2.model;

/**
 * Created by IntelliJ IDEA.
 * User: tk
 * Date: 15.03.12
 * Time: 11.19
 * To change this template use File | Settings | File Templates.
 */
public enum WarshipType {
    AIRCRAFT_CARRIER(5),
    BATTLESHIP(4),
    SUBMARINE(3),
    DESTROYER(3),
    PATROL_BOAT(2);
    
    private int size;
    private WarshipType(int size){
        this.size = size;
    }
    public int getSize(){
        return size;
    }
}
