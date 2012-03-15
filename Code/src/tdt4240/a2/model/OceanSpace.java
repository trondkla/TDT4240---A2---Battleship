package tdt4240.a2.model;

/**
 */
public class OceanSpace {

    private OceanSpaceSize oceanSpaceSize;
    private OceanTile oceanSpace[][];
    private Player player;


    public OceanSpace(OceanSpaceSize oceanSpaceSize, Player player){
        this.oceanSpaceSize = oceanSpaceSize;
        this.player = player;
        oceanSpace = new OceanTile[oceanSpaceSize.getSize()][oceanSpaceSize.getSize()];

    }
    
    public OceanTile[][] getOceanSpace(){
        return oceanSpace;
    }
}
