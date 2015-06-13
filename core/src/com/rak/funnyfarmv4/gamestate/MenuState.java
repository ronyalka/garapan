package com.rak.funnyfarmv4.gamestate;

import com.badlogic.gdx.Gdx;
import com.rak.funnyfarmv4.manager.GameStateManager;

public class MenuState extends BaseGameState{

	public MenuState (GameStateManager gsm){
		super(gsm);
	}
	
	@Override
	public void RAKinit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RAKupdate(float dt) {
		// TODO Auto-generated method stub
		Gdx.app.log("updating update function MenuState"," berhasil" );
	}

	@Override
	public void RAKdraw(float dt) {
		// TODO Auto-generated method stub
		Gdx.app.log("updating draw function MenuState"," berhasil" );
	}

	@Override
	public void RAKhandleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RAKdispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RAKload() {
		// TODO Auto-generated method stub
		
	}

	

}
