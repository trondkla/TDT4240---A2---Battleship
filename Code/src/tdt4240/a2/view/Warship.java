package tdt4240.a2.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by IntelliJ IDEA.
 * User: tk
 * Date: 15.03.12
 * Time: 11.19
 * To change this template use File | Settings | File Templates.
 */
public class Warship extends AbstractView {

    private tdt4240.a2.model.Warship model;
    private boolean dirty;
    private Paint paint = new Paint();

    public Warship(tdt4240.a2.model.Warship model){
        this.model = model;
        dirty = true;
        this.paint = new Paint();
        paint.setColor(Color.RED);
    }

    /**
     *
     */
    public void draw(Canvas canvas){
        // Generate Rect to draw (Controll?)
        canvas.drawRect(model.getRect(), paint);
        dirty = false;
    }

    /**
     *
     * @param timeElapsed
     */
    public void update(long timeElapsed){
        //
    }
}
