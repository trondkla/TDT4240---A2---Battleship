package tdt4240.a2.states;

import android.view.View;
import tdt4240.a2.model.Player;
import tdt4240.a2.model.PlayerState;
import tdt4240.a2.view.OceanSpace;

public class GameState extends State{

    private boolean running;

    private OceanSpace oceanSpace;
    
    private Player player;

    public GameState(){
        //player.addPropertyChangeListener(); // Listen for change of player state (fire / observe)
        running = false;

    }

    private void gameLoop(){
        while(running){
            if(player.getPlayerState() == PlayerState.FIRE){
                // Wait for input

                // Handle input

            } else {
                // Observe and wait for oceanSpace to change

            }
            // Check for finished game


            player.swapPlayerState(); // next playerstate
        }
    }

    @Override
    public View getView() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
