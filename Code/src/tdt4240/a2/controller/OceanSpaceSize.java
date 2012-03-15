package tdt4240.a2.controller;

/**
 * Created by IntelliJ IDEA.
 * User: tk
 * Date: 15.03.12
 * Time: 11.28
 * To change this template use File | Settings | File Templates.
 */
public enum OceanSpaceSize {
    SMALL(10),
    MEDIUM(12),
    LARGE(14);
    
    private int size;
    private OceanSpaceSize(int size){
        this.size = size;
    }
    
    public int getSize(){
        return size;
    }
}
