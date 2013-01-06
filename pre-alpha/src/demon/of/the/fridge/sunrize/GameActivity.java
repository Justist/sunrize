package demon.of.the.fridge.sunrize;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.game);
       sunTrack();
       //finish();
    }
    
    //Variables which needed to be defined outside of functions are placed here, in order to avoid polluting their parent functions.
    private boolean attackSucceeded;

    public void sunTrack() {
    	int height = 10; //needs to be changed to half the height of the gaming view
    	int max_height = 20; //needs to be changed to the top height of the gaming view
    	//min_height is 0 (not needed, maybe later implemented for modularity (o.s.))
    	int energy = 0; //energy for the attacks of the shadowy being: starts on 0 (for now)
    	int max_energy = 100; //to prevent reaching absurd heights (which is for now impossible)
    	boolean winner = false;
    	TextView t = new TextView(this);
        t =(TextView)findViewById(R.id.gameText1); 
    	while(!winner) { //if someone wins, the game is over
    		t.append("\n" + "height = " + String.valueOf(height) + " and energy = " + String.valueOf(energy));
    		if (height >= max_height) {
    			winner = true;
    			//show a pop-up which states that the sun has won
    			
    			//Log.d("winner", "sun");
    		} else if (height <= 0) {
    			winner = true;
    			//show a pop-up which states that the shadowy being has won
    			//Log.d("winner", "shadowy being");
    		} else {
    			if(shadowyBeingAttack(energy)){ height -= 10; energy -= 5; }
    			wachtTijdHoogte.run();
    		} // if, else if en else
    		if(energy != max_energy){ energy++; }
    		height++; //cannot be (higher than) max_height, so no need to check for that
    		//Log.d("height", String.valueOf(height));
    	} //while (!winner)
    	
    } //void sunTrack
    
    public boolean shadowyBeingAttack(int energie) {
    	//Log.d("energy", String.valueOf(energie));
    	final Button button = (Button) findViewById(R.id.shadowyBeingAttackButton);
    	attackSucceeded = false;
    	if(energie >= 10) {
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	        		attackSucceeded = true;
	            }
	        });
    	}
		return attackSucceeded;
    }
    
    //functie om 1 seconde te wachten
    Thread wachtTijdHoogte = new Thread(){
        @Override
        public void run(){
            try {
                synchronized(this){
                    wait(1000);
                }
            }
            catch(InterruptedException ex){                    
            }
        }
    };
}
