package demon.of.the.fridge.sunrize;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button button = (Button) findViewById(R.id.startNewGameButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	try {
	            	Intent i = new Intent(v.getContext(), GameActivity.class);
	                startActivity(i);
            	}catch(ActivityNotFoundException ex){
            		ex.printStackTrace();
                }
            }
        });
    }
}
