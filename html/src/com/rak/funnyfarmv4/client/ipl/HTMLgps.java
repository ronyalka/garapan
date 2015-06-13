package com.rak.funnyfarmv4.client.ipl;

import com.badlogic.gdx.Gdx;
import com.rak.funnyfarmv4.itf.Gps;

public class HTMLgps implements Gps{
	double latitude=0,longitude=0;
//	@Override
//	public void location(float latitude, float longtitude) {
//		// TODO Auto-generated method stub
//		latitude=0.111f;
//		longtitude=0.222f;
//		this.latitude=latitude;
//		this.longtitude=longtitude;	
//		Gdx.app.log("masuk andro", "masuk andro");
//	}

	@Override
	public double getLatitude() {
		// TODO Auto-generated method stub
		return latitude;
	}

	@Override
	public double getLongitude() {
		// TODO Auto-generated method stub
		return longitude;
	}
	
}
