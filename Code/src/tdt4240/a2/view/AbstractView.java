package tdt4240.a2.view;

import android.graphics.Canvas;

import java.beans.PropertyChangeEvent;

/**
 * Created by IntelliJ IDEA.
 * User: asus
 * Date: 19.03.12
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractView {

    public AbstractView(){

    }

    public void modelPropertyChange(PropertyChangeEvent event){

    }
    
    public void draw(Canvas canvas){
    }
}
