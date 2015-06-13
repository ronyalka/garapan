//package com.rak.funnyfarmv4.cls;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.physics.bullet.collision.ContactListener;
//import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
//import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
//import com.rak.funnyfarmv4.gamestate.GameplayState;
//public class MyContactListener extends ContactListener {
//	Array<GameObject> instances2;
//	GameplayState gs;
//	public MyContactListener(Array<GameObject> instances2){
//		this.instances2 = instances2;
//	}
//	
////	@Override
////	public boolean onContactAdded (int userValue0, int partId0, int index0, boolean match0, int userValue1, int partId1, int index1, boolean match1) {
////		Gdx.app.log("uv0 = ",""+userValue0+", match0 = "+match0+", index0 = "+index0+", partid0="+partId0);
////		Gdx.app.log("uv1 = ",""+userValue1+", match1 = "+match1+", index1 = "+index1+", partid1="+partId1);
////
////		if(userValue0==0&&userValue1==1 || userValue0==1&&userValue1==0){
////			gs.charMovement=false;
////		}
////		return true;
////	}
//	
//	@Override
//	public boolean onContactAdded (int userValue0, int partId0, int index0, boolean match0, 
//	                int userValue1, int partId1, int index1, boolean match1) {
//	    if (match0)
//	        ((ColorAttribute)instances2.get(userValue0).materials.get(0).get(ColorAttribute.Diffuse)).color.set(Color.RED);
//	    if (match1){
//	    	
//	    }
////	        ((ColorAttribute)instances2.get(userValue1).materials.get(0).get(ColorAttribute.Diffuse)).color.set(Color.BLUE);
//	    return true;
//	}
//	
//	@Override
//    public void onContactProcessed(int userValue0, int userValue1) {
////		if(userValue0==1 || userValue1==1){
////		Gdx.app.log("uv0 = ",""+userValue0);
////		Gdx.app.log("uv1 = ",""+userValue1);
////		if(userValue0==0)
////		onContactAdded(userValue0,0,0,true,userValue1,0,0,true);
////		
////		if(userValue1==1)
////			onContactAdded(userValue0,0,0,true,userValue1,0,0,true);
////		else{
////			gs.charMovement=true;
////		}
//    }
//	
//
//}
