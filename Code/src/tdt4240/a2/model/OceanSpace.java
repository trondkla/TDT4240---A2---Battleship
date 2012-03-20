package tdt4240.a2.model;

/**
 */
public class OceanSpace extends AbstractModel{

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

    public OceanTile getOceanTile(int x, int y){
        if((x < oceanSpaceSize.getSize() && y < oceanSpaceSize.getSize()) && (x >= 0 && y >= 0)){
            return oceanSpace[x][y];
        }else{
            IndexOutOfBoundsException exception = new IndexOutOfBoundsException();
            throw exception;
        }
    }
}
