package tdt4240.a2.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import tdt4240.a2.model.WarshipModel;

/**
 * Created by IntelliJ IDEA.
 * User: tk
 * Date: 15.03.12
 * Time: 11.19
 * To change this template use File | Settings | File Templates.
 */
public class WarshipView extends AbstractView {

    private WarshipModel model;
    private boolean dirty;
    private Paint paint = new Paint();

    public WarshipView(WarshipModel model){
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
