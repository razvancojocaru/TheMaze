package ro.razvan.themaze;

import ro.razvan.maze3d.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SelectLevel extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_level);
		
		Button back = (Button)findViewById(R.id.back);
		
		back.setOnClickListener(
	    		new View.OnClickListener()
				{
					public void onClick(View view)
					{
			            finish();
			            System.exit(0);
					}
				
				});
		
		Button lvl1 = (Button)findViewById(R.id.lvl1);
		Button lvl2 = (Button)findViewById(R.id.lvl2);
		Button lvl3 = (Button)findViewById(R.id.lvl3);
		Button lvl4 = (Button)findViewById(R.id.lvl4);
		
		lvl1.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent= new Intent (SelectLevel.this, Game.class);
						intent.putExtra("lvl", 1);
						intent.putExtra("height", 13);
						intent.putExtra("width", 13);
    					startActivityForResult(intent, 100);
					}
				});
		
		lvl2.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent= new Intent (SelectLevel.this, Game.class);
						intent.putExtra("lvl", 2);
						intent.putExtra("height", 17);
						intent.putExtra("width", 17);
						startActivityForResult(intent, 100);
					}
				});
		
		lvl3.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent= new Intent (SelectLevel.this, Game.class);
						intent.putExtra("lvl", 3);
						intent.putExtra("height", 27);
						intent.putExtra("width", 27);
						startActivityForResult(intent, 100);
					}
				});
		
		lvl4.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent= new Intent (SelectLevel.this, Game.class);
						intent.putExtra("lvl", 4);
						intent.putExtra("height", 42);
						intent.putExtra("width", 42);
						startActivityForResult(intent, 100);
					}
				});
	}
	
	@Override
    public void onActivityResult (int requestCode, int responseCode, Intent data)
    {
    	// raspunsul vine de la formularul de adaugare
    	
    	if (requestCode == 100)
    	{
    		if (responseCode==2)
    		{
    			Intent intent= new Intent (SelectLevel.this, Game.class);
				intent.putExtra("lvl", 2);
				intent.putExtra("height", 17);
				intent.putExtra("width", 17);
				startActivityForResult(intent, 100);    			
    		}
    		else if (responseCode==3) {
    			Intent intent= new Intent (SelectLevel.this, Game.class);
    			intent.putExtra("lvl", 3);
				intent.putExtra("height", 27);
				intent.putExtra("width", 27);
				startActivityForResult(intent, 100);
    		}
    		else if (responseCode==4) {
    			Intent intent= new Intent (SelectLevel.this, Game.class);
				intent.putExtra("lvl", 2);
				intent.putExtra("height", 17);
				intent.putExtra("width", 17);
				startActivityForResult(intent, 100);
    		}
    		else if (responseCode==0) { }
    	}
    }

}
