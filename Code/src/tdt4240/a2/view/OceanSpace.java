package tdt4240.a2.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by IntelliJ IDEA.
 * User: tk
 * Date: 15.03.12
 * Time: 11.27
 * To change this template use File | Settings | File Templates.
 */
public class OceanSpace {


    private Warship[] warshipList;
    private tdt4240.a2.model.OceanSpace model;
    private boolean dirty;

    public OceanSpace(tdt4240.a2.model.OceanSpace oceanSpaceModel){
        this.model = oceanSpaceModel;
        this.dirty = true;
    }

    /**
     *
     */
    public void draw(Canvas canvas){
        if(dirty){
            Paint paint = new Paint();
            paint.setColor(Color.rgb(35, 107, 142));
            canvas.drawRect(model.getRect(), paint);
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

}
