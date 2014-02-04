package ro.razvan.themaze;


import ro.razvan.maze3d.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button exit = (Button)findViewById(R.id.exitbuton);
		Button newgame = (Button)findViewById(R.id.newgame);
		
		newgame.setOnClickListener(
        		new View.OnClickListener()
    			{
    				public void onClick(View view)
    				{
    					Intent intent= new Intent (MainActivity.this, SelectLevel.class);
    					startActivity(intent);
    				}
    			
    			});
		
		exit.setOnClickListener(
        		new View.OnClickListener()
    			{
    				public void onClick(View view)
    				{
    					showDialog(1);
    				}
    			
    			});
	}

	public Dialog onCreateDialog(int id) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?")
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   finish();
			           System.exit(0);
                   }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                   }
               });

        return builder.create();
    }
}
