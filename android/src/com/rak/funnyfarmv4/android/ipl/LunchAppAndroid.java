package com.rak.funnyfarmv4.android.ipl;

import android.content.Context;
import android.content.Intent;

import com.rak.funnyfarmv4.itf.LunchApp;

public class LunchAppAndroid implements LunchApp{

	String uri;
	Context context;
	public LunchAppAndroid(Context context){
		this.context=context;
	}
	
	
	
	@Override
	public void lunchSetting() {
		// TODO Auto-generated method stub
		Intent callGPSSettingIntent = new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(callGPSSettingIntent);
	}

}
