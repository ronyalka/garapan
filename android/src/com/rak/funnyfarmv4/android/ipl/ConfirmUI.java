package com.rak.funnyfarmv4.android.ipl;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.rak.funnyfarmv4.Maingame;
import com.rak.funnyfarmv4.itf.ConfirmReqHan;
import com.sun.org.apache.regexp.internal.recompile;

public class ConfirmUI extends AndroidApplication implements ConfirmReqHan {

	static View gameView;
	Context context;
	 RelativeLayout layout;


	
	public ConfirmUI(){
		
	}
	public ConfirmUI(Context context,View view) {

		this.context=context;
		
		
		
	}
	public static void setView(View view){
		gameView=view;
	}
	public static View getView(){
		return gameView;
	}
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// super.onCreate(savedInstanceState);

		Log.d("lewat sini android leaderboard oncreate", "succes2");

	}

	@Override
	public void confirm(final com.rak.funnyfarmv4.itf.ConfirmUI confirmInterface) {
		Log.d("masuk", "sini2");
		gameView.post(new Runnable() {
			public void run() {
				new AlertDialog.Builder(context)
						.setTitle("Confirm")
						.setMessage("Your Gps is disabled, would you like to enable it? if not enable it it will use default weather(sunny weather)")
						.setCancelable(false)
						.setPositiveButton("enable",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {

										confirmInterface.yes();
										dialog.cancel();
									}
								})
						.setNegativeButton("disabled",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										confirmInterface.no();
										dialog.cancel();
									}
								}).create().show();
			}
		});
	}
	// public void submitScore(String user, int score) {
	//
	// }
}
