package com.example.sos;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements AccelerometerListener{
	
	public double latitude, longitude, altitude;
	private EditText coorLoc;
	private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        coorLoc = (EditText)findViewById(R.id.editText1);
        submit = (Button)findViewById(R.id.button1);
        submit.setOnClickListener(l);
        // Check onResume Method to start accelerometer listener
    }

    private OnClickListener l = new OnClickListener(){

		@Override
		public void onClick(View v) {
			//............
		}
    	
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onAccelerationChanged(float x, float y, float z) {
		
	}


	@Override
	public void onShake(float force) {
		// Do your stuff here
        
        // Called when Motion Detected
        Toast.makeText(getBaseContext(), "Motion detected", 
                Toast.LENGTH_SHORT).show();
		
        /**
         * Shack Detected
         */
        
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
        
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        latitude = location.getLatitude();     //经度   
        longitude = location.getLongitude(); //纬度   
        altitude =  location.getAltitude();     //海拔  
        
        coorLoc.setText(latitude+":"+longitude);
	}
	
	
	
	/**
	 * onResume()
	 * onStop()
	 * onDestroy()
	 * 
	 * ↓↓↓↓↓↓
	 */
	
	
	@Override
    public void onResume() {
            super.onResume();
            Toast.makeText(getBaseContext(), "onResume Accelerometer Started", 
                    Toast.LENGTH_SHORT).show();
             
            //Check device supported Accelerometer senssor or not
            if (AccelerometerManager.isSupported(this)) {
                 
                //Start Accelerometer Listening
                AccelerometerManager.startListening(this);
            }
    }
     
    @Override
    public void onStop() {
            super.onStop();
             
            //Check device supported Accelerometer senssor or not
            if (AccelerometerManager.isListening()) {
                 
                //Start Accelerometer Listening
                AccelerometerManager.stopListening();
                 
                Toast.makeText(getBaseContext(), "onStop Accelerometer Stoped", 
                         Toast.LENGTH_SHORT).show();
            }
            
    }
     
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Sensor", "Service  distroy");
         
        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {
             
            //Start Accelerometer Listening
            AccelerometerManager.stopListening();
             
            Toast.makeText(getBaseContext(), "onDestroy Accelerometer Stoped", 
                   Toast.LENGTH_SHORT).show();
        }
             
    }
}
