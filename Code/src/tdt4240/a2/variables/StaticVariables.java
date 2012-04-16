package tdt4240.a2.variables;

import android.app.Activity;
import android.content.res.Resources;
import tdt4240.a2.model.OceanSpaceSize;

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
    private Resources resources;
    private Activity activity;

    private int gridOffset;

    private OceanSpaceSize oceanSpaceSize;
    
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
        this.gridOffset = (getCanvasPixelHeight() - (getCanvasPixelWidth()))/2;
        return gridOffset;
    }

    public int getCanvasPixelWidth() {
        return canvasPixelWidth;
    }

    public void setCanvasPixelWidth(int canvasPixelWidth) {
        this.canvasPixelWidth = canvasPixelWidth;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public OceanSpaceSize getOceanSpaceSize() {
        return oceanSpaceSize;
    }

    public void setOceanSpaceSize(OceanSpaceSize oceanSpaceSize) {
        this.oceanSpaceSize = oceanSpaceSize;
    }
}
