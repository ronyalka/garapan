package com.rak.funnyfarmv4;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.GdxBuild;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.badlogic.gdx.utils.GdxRuntimeException;

import com.rak.funnyfarmv4.itf.ConfirmReqHan;
import com.rak.funnyfarmv4.itf.ConfirmUI;
import com.rak.funnyfarmv4.itf.LunchApp;
//import com.rak.funnyfarmv4.android.GpsTracker;
import com.rak.funnyfarmv4.itf.Gps;
import com.rak.funnyfarmv4.manager.GameStateManager;
//import com.rak.funnyfarmv4.android.GpsTracker;

public class Maingame 
implements ApplicationListener
			

{
	public CameraInputController inputController;
	public PerspectiveCamera camera;
	GameStateManager gsm;
	
	

	float a=0,b=0;
	
	
	public static double la;
	public static double lo;
//	private final Leaderboard leaderboard;
	
	public static Gps gps;
	public static LunchApp lunchApp;
	public static ConfirmReqHan confirmReqHan;
	public Maingame(Gps gps, double lat, double lng, ConfirmReqHan confirmReqHan, LunchApp lunchApp) {
		la = lat;
		lo = lng;
		this.gps = gps;
		this.confirmReqHan = confirmReqHan;
		this.lunchApp = lunchApp;
	}



	public static void showConfirmDialog(){
		Gdx.app.debug("click", "lewat");
		confirmReqHan.
		confirm(
				new ConfirmUI(){
                @Override
                public void yes() {
                        // The user clicked yes!
                	Gdx.app.log("click", "yes");
                	lunchApp.lunchSetting();

                }
 

               @Override
                public void no() {
                        // The user clicked no!
            	   Gdx.app.log("click", "no");    
                }
        });
    }




	@Override
	public void create() {
		// TODO Auto-generated method stub
		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0f, 0f, 0f);
		camera.lookAt(0, 0, 0);//rotate,
		camera.near = 1f;
		camera.far = 1000f;
		camera.update();
		
		gsm =new GameStateManager(gsm.GAMEPLAY);
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
//		hud.getViewport().update(width, height, true);
//		hudWidth = hud.getWidth();
//		hudHeight = hud.getHeight();
//		super.resize(width, height);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		
		
//		Gdx.app.log("masuk percobaan spesifik implementasi", "hasil la="+gps.getLatitude()+" dan hasil lo="+gps.getLongitude());
		
		la=gps.getLatitude();
		lo=gps.getLongitude();
		
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}







	

	
}