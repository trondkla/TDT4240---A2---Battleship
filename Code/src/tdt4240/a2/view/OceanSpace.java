package tdt4240.a2.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class OceanSpace {


    private Warship[] warshipList;
    private tdt4240.a2.model.OceanSpace model;
    private boolean dirty;

    private int noOfTilesVertical;
    private int noOfTilesHorizontal;
    private int tileSize;

    public OceanSpace(tdt4240.a2.model.OceanSpace oceanSpaceModel){
        this.model = oceanSpaceModel;
        this.dirty = true;

        noOfTilesVertical = 10;
        noOfTilesHorizontal = 10;
        tileSize = 10;
    }

    /**
     * @param canvas
     */
    public void draw(Canvas canvas){
        if(dirty){
            Paint paint = new Paint();
            paint.setColor(Color.rgb(35, 107, 142));
            canvas.drawRect(model.getRect(), paint);
            //drawGrid();
            for(Warship w : warshipList){
                w.draw(canvas);
            }
        }
        dirty = false;
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
        for(int i=0; i<noOfTilesVertical; i++){
            canvas.drawLine(0, i*tileSize, canvas.getHeight(), i*tileSize, paint);
        }
        for(int i=0; i<noOfTilesHorizontal; i++){
            canvas.drawLine(i*tileSize, 0, i*tileSize, canvas.getHeight(), paint);
        }
    }

}
