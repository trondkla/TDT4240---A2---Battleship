package tdt4240.a2.model;

/**
 * Created by IntelliJ IDEA.
 * User: tk
 * Date: 15.03.12
 * Time: 11.19
 * To change this template use File | Settings | File Templates.
 */
public enum WarshipType {
    AIRCRAFT_CARRIER(5,0),
    BATTLESHIP(4,0),
    SUBMARINE(3,0),
    DESTROYER(3,0),
    PATROL_BOAT(2,0);
    
    private int size;
    private int sprite;
    private WarshipType(int size,int sprite){
        this.size = size;
    }
    public int getSize(){
        return size;
    }
    public int getSprite(){
        return sprite;
    }
}
