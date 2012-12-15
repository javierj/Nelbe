package org.iwt2.nebel.model;

import com.badlogic.gdx.math.Vector2;

public class SnakeInitialPosition {

	Vector2 pos;
	int dir;
	
	public SnakeInitialPosition(float x, float y, int pos) {
		this.pos = new Vector2(x, y);
		this.dir = pos;
	}

	public Vector2 getPos() {
		return pos;
	}

	public int getDir() {
		return dir;
	}
	
	
	
}
