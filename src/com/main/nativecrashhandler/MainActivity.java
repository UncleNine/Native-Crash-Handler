package com.main.nativecrashhandler;

import com.github.nativehandler.NativeError;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button button = null;
	private TextView log = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button = (Button)   findViewById(R.id.button);
		log    = (TextView) findViewById(R.id.log);
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Try to crash application by invoking assert() in native code
				try {
					Broken b = new Broken();
					b.testWrapper();
				} catch(Exception e){
					Log.e("Main", "Exception in JNI call", e);
					log.setText(e.toString());
				} catch(Error e){
					Log.e("Main", "Exception in JNI call", e);
					log.setText(e.toString());
				} 
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
