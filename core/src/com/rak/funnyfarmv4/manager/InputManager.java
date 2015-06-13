package com.rak.funnyfarmv4.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.rak.funnyfarmv4.gamestate.GameplayState;

public class InputManager implements InputProcessor, GestureListener {
	InputMultiplexer inputMultiPlexer;
	InputProcessor ip;
	GameStateManager gsm;
	GameplayState gps;
	
	public static boolean down;
	public int CurState;
	
	public static boolean TouchDown;
	public static boolean KeyDown;
	
	public static int KeyCode;
	
	public InputManager(int CurState){
		inputMultiPlexer=new InputMultiplexer();
		ip =null;
		Gdx.app.log("masuk InputHandler= ","ya ");
		down=false;
		this.CurState=CurState;
	}
	
	
	public int getCurState() {
		return CurState;
	}




	public void setCurState(int curState) {
		CurState = curState;
	}




	public void setInputListener(InputProcessor... processors) {
		// TODO Auto-generated method stub
		
		for(InputProcessor ip : processors){
			inputMultiPlexer.addProcessor(ip);
		}
		Gdx.input.setInputProcessor(inputMultiPlexer);
		
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		
		
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		
		
		
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
//		return false;

//		if (keycode == Keys.SPACE)
////			spaceKey = true;
//		if(CurState==gsm.GAMEPLAY){
//			KeyDown=true;
//			KeyCode=keycode;
//			if (keycode == Keys.LEFT){
////				leftKey = true;
//				Gdx.app.log("masuk","kiri2");
//			}
//			if (keycode == Keys.RIGHT){
//				
//			}
//			if (keycode == Keys.UP){
//
//			}
//			if (keycode == Keys.DOWN){
//
//			}
//		}
		
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.DOWN){
//			downKey = true;
//			down=false;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
