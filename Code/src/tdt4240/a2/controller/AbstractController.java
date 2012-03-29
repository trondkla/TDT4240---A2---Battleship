package tdt4240.a2.controller;

import tdt4240.a2.model.AbstractModel;
import tdt4240.a2.view.AbstractView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: asus
 * Date: 19.03.12
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractController implements PropertyChangeListener {

    private AbstractView registeredView;
    private AbstractModel registeredModel;

    public AbstractController() {

    }


    public void addModel(AbstractModel model) {
        registeredModel = model;
        model.addPropertyChangeListener(this);
    }

    public void removeModel(AbstractModel model) {
        registeredModel = null;
        model.removePropertyChangeListener(this);
    }

    public void addView(AbstractView view) {
        registeredView = view;
    }

    public void removeView(AbstractView view) {
        registeredView = null;
    }

    public AbstractModel getRegisteredModel(){
        return registeredModel;
    }

    public AbstractView getRegisteredView(){
        return registeredView;
    }


    //  Use this to observe property changes from registered models
    //  and propagate them on to all the views.


    public void propertyChange(PropertyChangeEvent evt) {
        registeredView.modelPropertyChange(evt);
    }


    /**
     * This is a convenience method that subclasses can call upon
     * to fire property changes back to the models. This method
     * uses reflection to inspect each of the model classes
     * to determine whether it is the owner of the property
     * in question. If it isn't, a NoSuchMethodException is thrown,
     * which the method ignores.
     *
     * @param propertyName = The name of the property.
     * @param newValue = An object that represents the new value
     * of the property.
     */
    protected void setModelProperty(String propertyName, Object newValue) {

        AbstractModel model = registeredModel;
            try {

                Method method = model.getClass().
                        getMethod("set"+propertyName, new Class[] {
                                newValue.getClass()
                        });
                method.invoke(model, newValue);

            } catch (Exception ex) {
                //  Handle exception.
            }
    }


}
