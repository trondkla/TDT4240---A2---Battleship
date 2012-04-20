package tdt4240.a2.states;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import tdt4240.a2.R;
import tdt4240.a2.variables.StaticVariables;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: asus
 * Date: 20.04.12
 * Time: 11:58
 * To change this template use File | Settings | File Templates.
 */
public class Settings extends State{
    
    private View view;
    private Activity activity;
    private StaticVariables variables = StaticVariables.getInstance();
    private ArrayList<String> spinnerArray = new ArrayList<String>();

    public Settings(Context context) {
        super(context);
        this.activity = variables.getActivity();
        view = View.inflate(activity, R.layout.settingsmenu, null);

        // Poplulate spinnerArray
        spinnerArray.add("Red");
        spinnerArray.add("Green");
        spinnerArray.add("Black");
        
        final TextView responseText = ((TextView)view.findViewById(R.id.change_response));
        
        final EditText playerOneName = ((EditText)view.findViewById(R.id.playerOneEdit));
        final EditText playerTwoName = ((EditText)view.findViewById(R.id.playerTwoEdit));

        final Spinner playerOneColor = ((Spinner)view.findViewById(R.id.playerOneColorSpinner));
        ArrayAdapter<String> spinnerArrayAdapter;
        spinnerArrayAdapter = new ArrayAdapter<String>(activity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,
                spinnerArray);
        playerOneColor.setAdapter(spinnerArrayAdapter);
        playerOneColor.setSelection(spinnerArray.indexOf(intToString(variables.getPlayerOneColor())));

        final Spinner playerTwoColor = ((Spinner)view.findViewById(R.id.playerTwoColorSpinner));
        playerTwoColor.setAdapter(spinnerArrayAdapter);
        playerTwoColor.setSelection(spinnerArray.indexOf(intToString(variables.getPlayerTwoColor())));

        playerOneName.setText(variables.getPlayerOneName());
        playerTwoName.setText(variables.getPlayerTwoName());

        // implement onclickcrap
        ((Button) view.findViewById(R.id.submit_data_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                variables.setPlayerOneName(playerOneName.getText().toString());
                variables.setPlayerTwoName(playerTwoName.getText().toString());
                variables.setPlayerOneColor(stringToColor((String)playerOneColor.getSelectedItem()));
                variables.setPlayerTwoColor(stringToColor((String) playerTwoColor.getSelectedItem()));
                responseText.setText("Saved changes.");
            }
        });

        ((Button)view.findViewById(R.id.reset_data_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerOneName.setText(variables.getPlayerOneName());
                playerTwoName.setText(variables.getPlayerTwoName());
                playerOneColor.setSelection(spinnerArray.indexOf(intToString(variables.getPlayerOneColor())));
                playerTwoColor.setSelection(spinnerArray.indexOf(intToString(variables.getPlayerTwoColor())));

                responseText.setText("Data reset.");
            }
        });
    }
    
    public int stringToColor(String color){
        if(color.equals("Red")){
            return Color.RED;
        } else if(color.equals("Black")){
            return Color.BLACK;
        } else {
            return Color.GREEN;
        }
    }
    
    public String intToString(int color){
        String returnValue = "";
        switch (color){
            case Color.RED:
                returnValue = "Red";
                break;
            case Color.GREEN:
                returnValue = "Green";
                break;
            case Color.BLACK:
                returnValue = "Black";
                break;
        }
        return returnValue;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onBackPressed() {
        pop(); // Get back to menu
    }
}
