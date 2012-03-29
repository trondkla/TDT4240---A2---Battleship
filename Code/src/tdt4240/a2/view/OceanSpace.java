package tdt4240.a2.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.util.Log;
import tdt4240.a2.variables.StaticVariables;

public class OceanSpace extends AbstractView{


    private Warship[] warshipList;
    private tdt4240.a2.model.OceanSpace model;
    private boolean dirty;

    private int noOfTilesVertical;
    private int noOfTilesHorizontal;
    private StaticVariables variables;

    public OceanSpace(tdt4240.a2.model.OceanSpace oceanSpaceModel){
        this.model = oceanSpaceModel;
        this.dirty = true;

        noOfTilesVertical = this.model.getOceanSpaceSize().getSize();
        noOfTilesHorizontal = this.model.getOceanSpaceSize().getSize();
        variables = StaticVariables.getInstance();
    }

    /**
     *
     */
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.rgb(35, 107, 142));
        //paint.setStrokeWidth(10);
        canvas.drawRect(this.model.getRect(), paint);
        drawGrid(canvas);
//            for(Warship w : warshipList){
//              w.draw(canvas);
//        }
    }

    /**
     *
     * @param timeElapsed
     */
    public void update(long timeElapsed){

    }

    public void setDirty(){
        this.dirty = true;
    }

    private void drawGrid(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);

        int startPixel = variables.getGridOffset();
        int endPixel = startPixel + (noOfTilesVertical*variables.getPixelPerTile());

        for(int i=0; i<noOfTilesVertical+1; i++){
            canvas.drawLine(0, i*variables.getPixelPerTile() + startPixel, endPixel,
                    i*variables.getPixelPerTile() + startPixel, paint);
        }
        for(int i=0; i<noOfTilesHorizontal+1; i++){
            canvas.drawLine(i*variables.getPixelPerTile(), startPixel, i*variables.getPixelPerTile(),
                    endPixel, paint);
        }
    }

}
