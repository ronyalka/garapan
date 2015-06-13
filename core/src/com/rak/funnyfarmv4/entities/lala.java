package com.rak.funnyfarmv4.entities;

import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;

public class lala extends Action {
	private ParticleController emitter;
	Vector3 axis;
	float angle;

	Matrix4 tmpMatrix = new Matrix4(), tmpMatrix4 = new Matrix4();
	Vector3 tmpVector = new Vector3(); Vector3 tmpVector2 = new Vector3();
	Quaternion tmpQuaternion = new Quaternion();
	
	public lala(ParticleController emitter, Vector3 axis,
			float angle) {
		this.emitter = emitter;
		this.axis = axis;
		this.angle = angle;
	}
	


	@Override
	public boolean act(float delta) {
		emitter.getTransform(tmpMatrix);
		tmpQuaternion.set(axis, angle * delta).toMatrix(tmpMatrix4.val);
		
		tmpMatrix4.mul(tmpMatrix);
		emitter.setTransform(tmpMatrix4);
		return false;
	}
	
}
