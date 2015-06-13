package com.rak.funnyfarmv4.android.ipl;

import sun.security.krb5.internal.APReq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.rak.funnyfarmv4.android.AndroidLauncher;
import com.rak.funnyfarmv4.itf.ConfirmReqHan;
import com.rak.funnyfarmv4.itf.ConfirmUI;
import com.rak.funnyfarmv4.itf.Gps;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;

import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GpsTracker extends AndroidApplication implements
		LocationListener, Gps {

	Location location; // location
	double latitude = 9; // latitude
	double longitude = 9; // longitude

//	 private LocationManager locationManager;
	private String provider;
//	private Context context;
	public static boolean GpsisEnable=false;
	
//	LocationListener listener;
	public GpsTracker(Bundle bundle) {
		Log.d("lewat gpstracer", "lewat gpstracer");
//		this.locationService=context;
		
		onCreate(bundle);
	}
	
    
	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
		Log.d("lewat app", "lewat app");

//		 locationManager = (LocationManager)
//		 getSystemService(AndroidLauncher.Locationservice);
		// Define the criteria how to select the locatioin provider -> use
		// default
		Criteria criteria = new Criteria();
		
		provider = AndroidLauncher.getLocationManager().getBestProvider(
				criteria, false);
		
		AndroidLauncher.setProvider(provider);
		if(AndroidLauncher.getLocationManager().isProviderEnabled(provider)){
			GpsisEnable=true;
		}
		
		Location location = AndroidLauncher.getLocationManager()
				.getLastKnownLocation(AndroidLauncher.getProvider());

		// Initialize the location fields
		if (location != null) {
			Log.d("Location tidak null", "Provider " + provider
					+ " has been selected.");
			AndroidLauncher.lat = (double) (location.getLatitude());
			AndroidLauncher.lng = (double) (location.getLongitude());
			// onLocationChanged(location);
		} else {

			Log.d("Location null", " kosong");
			// latituteField.setText("Location not available");
			// longitudeField.setText("Location not available");
		}

		// super.onCreate();
	}

	public double getLatitude() {
//		Log.d("lewat ", "lewat reload");
		return AndroidLauncher.lat;
	}

	public double getLongitude() {

		return AndroidLauncher.lng;
	}

	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		AndroidLauncher.lat = (double) (location.getLatitude());
		AndroidLauncher.lng = (double) (location.getLongitude());
		Log.d("lewat2 ", "lewat onlocation");
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Log.d("lewat2 ", "lewat onProviderDisabled");
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Log.d("lewat2 ", "lewat onProviderEnabled");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		Log.d("lewat2 ", "lewat onStatusChanged");
	}





	@Override
	public boolean isEnable() {
		// TODO Auto-generated method stub
		return GpsisEnable;
	}

}
