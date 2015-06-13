package com.rak.funnyfarmv4.desktop.ipl;

import com.badlogic.gdx.Gdx;
import com.rak.funnyfarmv4.itf.Gps;

public class DekstopGps  implements Gps {

	double latitude,longitude;
//	@Override
//	public void location(float latitude, float longtitude) {
		// TODO Auto-generated method stub
		
		
		
//		this.latitude=12;
//		this.longtitude=13;	
//		Gdx.app.log("masuk andro", "masuk andro");
//	}
	public DekstopGps(){
		this.latitude=12;
		this.longitude=13;	
//		Gdx.app.log("masuk andro", "masuk andro");
	}

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

	@Override
	public boolean isEnable() {
		// TODO Auto-generated method stub
		return false;
	}
	
//	public void setLatitude(float latitude){
//		this.latitude=12;
//	}
//	public void setLongtitude(float longtitude){
//		this.longtitude=13;
//	}
}
