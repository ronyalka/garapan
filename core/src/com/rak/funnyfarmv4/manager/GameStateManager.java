package com.rak.funnyfarmv4.manager;

import javax.swing.text.StyledEditorKit.BoldAction;

import com.rak.funnyfarmv4.gamestate.BaseGameState;
import com.rak.funnyfarmv4.gamestate.GameplayState;
import com.rak.funnyfarmv4.gamestate.MenuState;
import com.rak.funnyfarmv4.itf.ConfirmReqHan;
import com.rak.funnyfarmv4.itf.Gps;
import com.rak.funnyfarmv4.itf.LunchApp;


public class GameStateManager {

	private BaseGameState gameState;
//	private InputManager inputManager;
	private LoadingScreen loadingScreen;
	
	public static boolean loadRAK;
	public static final int MENU = 0;
	public static final int GAMEPLAY = 132313;
	
	
	public GameStateManager(int state){
		if(gameState != null) gameState.RAKdispose();
		setState(state);
		loadRAK=true;
		gameState.RAKinit();
		gameState.RAKhandleInput();
	}
	
	public void setState(int state){
		
		if(state == MENU){
			gameState = new MenuState(this);
		}
		if(state == GAMEPLAY){
			gameState = new GameplayState(this);
		}
	}
	
	public void update(float dt){
		
		if(loadRAK==true)
		gameState.RAKload();
		else if(loadRAK==false){
			gameState.RAKupdate(dt);
			gameState.RAKdraw(dt);
		}
	}
	
	
	
	
}
