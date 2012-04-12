package tdt4240.a2.view;

import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import tdt4240.a2.R;
import tdt4240.a2.model.OceanSpaceModel;
import tdt4240.a2.variables.StaticVariables;

public class OceanSpaceView extends AbstractView{


    private WarshipView[] warshipViewList;
    private OceanSpaceModel model;
    private boolean dirty;

    private int noOfTilesVertical;
    private int noOfTilesHorizontal;
    private StaticVariables variables;

    public OceanSpaceView(OceanSpaceModel oceanSpaceModelModel){
        this.model = oceanSpaceModelModel;
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
//            for(WarshipModel w : warshipViewList){
//              w.draw(canvas);
//        }


        Drawable d = variables.getResources().getDrawable(R.drawable.bombed_water);
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();

        Rect[] bombedTiles = this.model.getBombedTiles();
        for(Rect rects : bombedTiles){
            canvas.drawBitmap(bitmap, null, new Rect(rects.left,rects.top,rects.right,rects.bottom),new Paint());
        }
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
