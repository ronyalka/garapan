package com.rak.funnyfarmv4.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.rak.funnyfarmv4.extra.BulletEntity;

public class Character extends BulletEntity{

	public Character(Model model, btCollisionObject body, float x, float y,
			float z) {
		super(model, body, x, y, z);
		// TODO Auto-generated constructor stub
	}

}
