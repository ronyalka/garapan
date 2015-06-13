package com.rak.funnyfarmv4.android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources.Theme;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.location.Location;
import android.location.LocationListener;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.rak.funnyfarmv4.Maingame;
import com.rak.funnyfarmv4.android.ipl.GpsTracker;
import com.rak.funnyfarmv4.android.ipl.ConfirmUI;
import com.rak.funnyfarmv4.android.ipl.LunchAppAndroid;
import com.rak.funnyfarmv4.itf.ConfirmReqHan;
import com.rak.funnyfarmv4.itf.Gps;
import com.rak.funnyfarmv4.itf.LunchApp;

public class AndroidLauncher extends AndroidApplication 
//implements ConfirmReqHan
{
	
	private static Context context;
	
	private static LocationManager locationManager;
	private static String provider;
	public LocationListener listener;
//	public static String Locationservice;
	GpsTracker gps;
	ConfirmUI confirmUI;
	static View view;
	RelativeLayout layout;
	LunchAppAndroid lunchAppAndroid;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		

		layout = new RelativeLayout(this);
		confirmUI= new ConfirmUI(this,view);
		gps= new GpsTracker(savedInstanceState);
		lunchAppAndroid= new LunchAppAndroid(this);
		view =initializeForView(new Maingame(gps,lat,lng,confirmUI,lunchAppAndroid), config);
		confirmUI.setView(view);
		layout.addView(confirmUI.getView());
		setContentView(layout);

	
	}
	
	
	public static View getView(){
		return view;
	}
	public static String getProvider() {
		
        return provider;
    }
	public static void setProvider(String p) {
		provider=p;
    }
	public static LocationManager getLocationManager(){
		
		return locationManager;
	}

	public static double lat ;
	public static double lng;

	
	 @Override
	  protected void onResume() {
	    super.onResume();
	    getLocationManager().requestLocationUpdates(provider, 400, 1, gps);
//	    gps.onResume();
	    Log.d("lewat", "onresume");
	  }

	  /* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    getLocationManager().removeUpdates(gps);
//	    gps.onPause();
	    Log.d("lewat", "onPause");
	  }

	
}
