package tdt4240.a2.variables;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import tdt4240.a2.model.OceanSpaceSize;

/**
 * Created by IntelliJ IDEA.
 * User: asus
 * Date: 19.03.12
 * Time: 09:57
 * To change this template use File | Settings | File Templates.
 */
public class StaticVariables {

    public static final StaticVariables INSTANCE = new StaticVariables();
    
    private int pixelPerTile;
    private int canvasPixelWidth;
    private int canvasPixelHeight;
    private Resources resources;
    private Activity activity;
    private String gameOverText;

    private final String PLAYER_ONE_NAME = "playeronename";
    private final String PLAYER_TWO_NAME = "playertwoname";
    private final String PLAYER_ONE_COLOR = "playeronecolor";
    private final String PLAYER_TWO_COLOR = "playertwocolor";
    
    private String playerOneName;
    private String playerTwoName;
    private int playerOneColor;
    private int playerTwoColor;

    private int gridOffset;

    private OceanSpaceSize oceanSpaceSize;
    
    private StaticVariables(){

    }

    public static StaticVariables getInstance(){
        return INSTANCE;
    }
    
    public void setPixelPerTile(int pixelPerTile){
        this.pixelPerTile = pixelPerTile;
    }
    
    public int getPixelPerTile(){
        return this.pixelPerTile;
    }

    public int getCanvasPixelHeight() {
        return canvasPixelHeight;
    }

    public void setCanvasPixelHeight(int canvasPixelHeight) {
        this.canvasPixelHeight = canvasPixelHeight;
    }
    
    public int getGridOffset(){
        this.gridOffset = (getCanvasPixelHeight() - (getCanvasPixelWidth()))/2;
        return gridOffset;
    }

    public int getCanvasPixelWidth() {
        return canvasPixelWidth;
    }

    public void setCanvasPixelWidth(int canvasPixelWidth) {
        this.canvasPixelWidth = canvasPixelWidth;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public OceanSpaceSize getOceanSpaceSize() {
        return oceanSpaceSize;
    }

    public void setOceanSpaceSize(OceanSpaceSize oceanSpaceSize) {
        this.oceanSpaceSize = oceanSpaceSize;
    }

    public String getGameOverText() {
        return gameOverText;
    }

    public void setGameOverText(String gameOverText) {
        this.gameOverText = gameOverText;
    }

    public String getPlayerOneName() {
        this.playerOneName = activity.getSharedPreferences(PLAYER_ONE_NAME, 0).getString(PLAYER_ONE_NAME,
                "Player one");
        return playerOneName;
    }

    public void setPlayerOneName(String playerOneName) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(PLAYER_ONE_NAME, 0).edit();
        this.playerOneName = playerOneName;
        editor.putString(PLAYER_ONE_NAME, this.playerOneName);
        editor.commit();
    }

    public String getPlayerTwoName() {
        this.playerTwoName = activity.getSharedPreferences(PLAYER_TWO_NAME, 0).getString(PLAYER_TWO_NAME,
                "Player two");
        return playerTwoName;
    }

    public void setPlayerTwoName(String playerTwoName) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(PLAYER_TWO_NAME, 0).edit();
        this.playerTwoName = playerTwoName;
        editor.putString(PLAYER_TWO_NAME, this.playerTwoName);
        editor.commit();
    }

    public int getPlayerOneColor() {
        this.playerOneColor = activity.getSharedPreferences(PLAYER_ONE_COLOR, 0).getInt(PLAYER_ONE_COLOR, Color.GREEN);
        return playerOneColor;
    }

    public void setPlayerOneColor(int playerOneColor) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(PLAYER_ONE_COLOR, 0).edit();
        this.playerOneColor = playerOneColor;
        editor.putInt(PLAYER_ONE_COLOR, this.playerOneColor);
        editor.commit();
    }

    public int getPlayerTwoColor() {
        this.playerTwoColor = activity.getSharedPreferences(PLAYER_TWO_COLOR, 0).getInt(PLAYER_TWO_COLOR, Color.RED);
        return playerTwoColor;
    }

    public void setPlayerTwoColor(int playerTwoColor) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(PLAYER_TWO_COLOR, 0).edit();
        this.playerTwoColor = playerTwoColor;
        editor.putInt(PLAYER_TWO_COLOR, this.playerTwoColor);
        editor.commit();
    }
}
