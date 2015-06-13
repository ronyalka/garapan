//package com.rak.funnyfarmv4.cls;
//
//import com.badlogic.gdx.graphics.g3d.Model;
//import com.badlogic.gdx.graphics.g3d.ModelInstance;
//import com.badlogic.gdx.graphics.g3d.model.Node;
//import com.badlogic.gdx.math.Matrix4;
//import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
//import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
//import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
//import com.badlogic.gdx.physics.bullet.linearmath.btMotionState;
//import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.utils.Disposable;
//
//public  class GameObject 
//extends ModelInstance implements Disposable
//{
////	public final btRigidBody body;
////	
////	public boolean moving;
////	public GameObject(Model model,String node,btRigidBody.btRigidBodyConstructionInfo constructionInfo) {
////		super(model,node);
////		body = new btRigidBody(constructionInfo);
//////		body.setCollisionShape(shape);
////		
////		// TODO Auto-generated constructor stub
////	}
////	@Override
////	public void dispose() {
////		// TODO Auto-generated method stub
////		body.dispose();
////		
////	}
//	
//	public final btRigidBody body;
//    public final MyMotionState motionState;
//    
////	private Array<GameObject> instances2;
// 
//    public GameObject (Model model, String node, btRigidBody.btRigidBodyConstructionInfo constructionInfo) {
//        super(model, node);
//        motionState = new MyMotionState();
//        motionState.transform = transform;
//        body = new btRigidBody(constructionInfo);
//        body.setMotionState(motionState);
//    }
// 
//    @Override
//    public void dispose () {
//        body.dispose();
//        motionState.dispose();
//    }
//	
//	public static class Constructor implements Disposable{
//		public final Model model;
//		public final String node;
//		public final btCollisionShape shape;
//		public final btRigidBody.btRigidBodyConstructionInfo constructionInfo;
//        private static Vector3 localInertia = new Vector3();
//
//		public Constructor(Model model, String node, btCollisionShape shape,float mass){
//			this.model = model;
//			this.node = node;
//			this.shape = shape;
//			if (mass > 0f)
//                shape.calculateLocalInertia(mass, localInertia);
//            else{
//                localInertia.set(0, 0, 0);
////			shape.calculateLocalInertia(mass, localInertia);
//            }
//			this.constructionInfo = new btRigidBody.btRigidBodyConstructionInfo(mass, null, shape, localInertia);
//
//		}
//		
//		public GameObject construct(){
//			return new GameObject(model,node,constructionInfo);
//		}
//		
//		@Override
//		public void dispose() {
//			// TODO Auto-generated method stub
//			shape.dispose();
//		}
//		
//	}
//	public static class MyMotionState extends btMotionState {
//	    Matrix4 transform;
//	    Array<GameObject> instances2;
//	    public boolean moving;
//	    @Override
//	    public void getWorldTransform (Matrix4 worldTrans) {
//	        worldTrans.set(transform);
//	    }
//	    @Override
//	    public void setWorldTransform (Matrix4 worldTrans) {
//	        transform.set(worldTrans);
//	    }
//	    public void setFromEulerAngles (float x, float y, float z){
//	    	transform.setFromEulerAngles(x, y, z);
//	    }
//	    public void trn (float x, float y, float z){
//	    	transform.trn(x, y, z);
//	    }
//	    public int getSize(){
//	    	
//	    	return instances2.size;
//	    }
//	    public void setInstances(Array<GameObject> instances2) {
//			// TODO Auto-generated method stub
//			this.instances2 =instances2;
//		}
//	    
//	    public Array<GameObject> getInstances( ) {
//			return instances2;
//		}
//	}
//	
//	
//
//}
