package tdt4240.a2.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
        // Draw the vessel.
        canvas.drawRect(model.getRect(), paint);

        // DEBUG COLOR
        Paint bombedPaint = new Paint();
        bombedPaint.setColor(Color.BLUE);

        // Retrieve tiles that needs to be repainted
        Rect[] bombedTiles = model.getBombedTiles();

        // Draw all the bombed tiles one by one.
        for(Rect rect : bombedTiles){
            if(rect != null)
                canvas.drawRect(rect, bombedPaint);
        }
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
