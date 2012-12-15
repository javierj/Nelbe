package org.iwt2.nebel.model;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Snake {

	//public static int R = 16;
	//public static int YSIZE = 16;

	public static int X = 16;
	public static int Y = 16;
	
	public static int C = 0;
	
	Rectangle r;
	//Circle r;
	public Vector2 vel;
	public Vector2 direction;
	public static final Vector2 VDOWN = new Vector2(0, 1);
	int id;
	
	public Snake(float i, float j) {
		//r = new Circle(i, j, R);
		//r.radius = XSIZE;
		//r.height = YSIZE;
		//r.x = i;
		//r.y = j;
		r = new Rectangle(i, j, X, Y);
		vel = new Vector2(20.0f, 20.0f);
		direction = new Vector2(1f, 0f);
		id = C;
		C++;
	}
	
	public Snake(float f, float g, float i, int right) {
		this(f, g);
		this.setVelocity(i);
		this.changeDirection(right);
	}

	public Snake(SnakeInitialPosition snakeInitialPosition, float vel2) {
		this(snakeInitialPosition.getPos().x, snakeInitialPosition.getPos().y, vel2, snakeInitialPosition.getDir());
	}

	public float getX() {
		return r.x;
	}

	public float getY() {
		return r.y;
	}

	public int getId() {
		return this.id;
	}
	
	public void update(float dt) {
		this.r.x += dt * this.vel.x *this.direction.x;
		this.r.y += dt * this.vel.y * this.direction.y;
	}

	public Rectangle getBounds() {
		return this.r;
	}

	public void changeDirection(int newDirection) {
		if (newDirection == ArrowButton.DOWN) {
			this.direction.x = VDOWN.x ;
			this.direction.y = VDOWN.y;
			return;
		}
		if (newDirection == ArrowButton.UP) {
			this.direction.x = 0f;
			this.direction.y = -1f;
			return;
		}
		if (newDirection == ArrowButton.LEFT) {
			this.direction.x = -1f;
			this.direction.y = 0f;
			return;
		}
		if (newDirection == ArrowButton.RIGHT) {
			this.direction.x = 1f;
			this.direction.y = 0f;
			return;
		}
		System.out.println("Snake::changeDirection - Error: " + newDirection);

	}

	public void setVelocity(float f) {
		this.vel.x = f;
		this.vel.y = f;
		
	}
	
	public String toString() {
		String s = "Snake (";
		s += this.getX();
		s+= ",";
		s += this.getY();
		s += ")";
		s += " : " + Snake.X;
		return s;
				
	}

	/**
	 * For tetsing onlu
	 * @return
	 */
	public Vector2 getDirection() {
		return this.direction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((r == null) ? 0 : r.hashCode());
		result = prime * result + ((vel == null) ? 0 : vel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Snake other = (Snake) obj;
		/*
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (r == null) {
			if (other.r != null)
				return false;
		} else if (!r.equals(other.r))
			return false;
		if (vel == null) {
			if (other.vel != null)
				return false;
		} else if (!vel.equals(other.vel))
			return false;*/
		if (this.getX() != other.getX())
			return false;
		if (this.getY() != other.getY())
			return false;
		
		return true;
	}

	public void setXY(float i, float j) {
		this.r.x = i;
		this.r.y = j;
	}

	public void setInitialPos(SnakeInitialPosition snakeInitialPosition) {
		this.r.x = snakeInitialPosition.getPos().x;
		this.r.y = snakeInitialPosition.getPos().y;
		this.changeDirection(snakeInitialPosition.getDir());
	}

}
