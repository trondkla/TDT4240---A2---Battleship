package tdt4240.a2.variables;

import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: asus
 * Date: 19.03.12
 * Time: 09:57
 * To change this template use File | Settings | File Templates.
 */
public class StaticVariables {

    public static final StaticVariables INSTANCE = new StaticVariables();
    
    private int pixelPerTile;
    private int canvasPixelWidth;
    private int canvasPixelHeight;

    private int gridOffset;
    
    private StaticVariables(){

    }

    public static StaticVariables getInstance(){
        return INSTANCE;
    }
    
    public void setPixelPerTile(int pixelPerTile){
        this.pixelPerTile = pixelPerTile;
    }
    
    public int getPixelPerTile(){
        return this.pixelPerTile;
    }

    public int getCanvasPixelHeight() {
        return canvasPixelHeight;
    }

    public void setCanvasPixelHeight(int canvasPixelHeight) {
        this.canvasPixelHeight = canvasPixelHeight;
    }
    
    public int getGridOffset(){
        this.gridOffset = (getCanvasPixelHeight() - (getCanvasPixelWidth()))/3;
        return gridOffset;
    }

    public int getCanvasPixelWidth() {
        return canvasPixelWidth;
    }

    public void setCanvasPixelWidth(int canvasPixelWidth) {
        this.canvasPixelWidth = canvasPixelWidth;
    }
}
