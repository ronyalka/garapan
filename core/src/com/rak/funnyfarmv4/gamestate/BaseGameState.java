package com.rak.funnyfarmv4.gamestate;

import com.rak.funnyfarmv4.manager.GameStateManager;
import com.rak.funnyfarmv4.manager.InputManager;



public abstract class BaseGameState {
	
	protected GameStateManager gsm;
//	protected InputManager im;
//	 BaseGameState (GameStateManager gsm, InputManager im){
//		this.gsm = gsm;
//		this.im =im;
//	}
	
	protected BaseGameState (GameStateManager gsm){
		this.gsm = gsm;
	}
//	InputManager getIm(){
//		return this.im;
//	}
	
	public abstract void RAKinit();
	public abstract void RAKupdate(float dt);
	public abstract void RAKdraw(float dt);
	public abstract void RAKhandleInput();
	public abstract void RAKdispose();
	public abstract void RAKload();
}
