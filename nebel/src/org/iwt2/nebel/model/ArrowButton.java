package org.iwt2.nebel.model;

import com.badlogic.gdx.math.Rectangle;

public class ArrowButton {

	public static final int RIGHT = 0;
	public static final int LEFT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	public enum Direction {RIGHT,LEFT, UP, DOWN}

	// To be set from outside
	public static int XSIZE = -1;
	public static int YSIZE = -1;
	
	Rectangle r;
	int arrow;

	public ArrowButton(float i, float j, int left2) {
		this.arrow = left2;
		r = new Rectangle();
		r.width = XSIZE;
		r.height = YSIZE;
		r.x = i;
		r.y = j;
	}

	public ArrowButton(float i, float j, int width, int height, int left2) {
		/*this.arrow = left2;
		r = new Rectangle();
		r.width = width;
		r.height = height;
		r.x = i;
		r.y = j;*/
		this(i, j, left2);
		r.width = width;
		r.height = height;

	}

	
	public float getX() {
		return r.x;
	}

	public float getY() {
		return r.y;
	}

	public int getArrowDirection() {
		return this.arrow;
	}


	/**
	 * Returns a new arrow button with the same arrow than
	 * the original 
	 * @param i Xo coordinade
	 * @param j Y coordinada
	 * @return
	 */
	public ArrowButton copy(float i, float j) {
		ArrowButton copy = new ArrowButton(i, j, this.arrow);
		return copy;
	}

	/**
	 * Return true if the point (i,j) is in the array button
	 * The border of the botton is out.
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean containsPoint(float i, float j) {
		return this.r.contains(i, j);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((r == null) ? 0 : r.hashCode());
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
		ArrowButton other = (ArrowButton) obj;
		if (r == null) {
			if (other.r != null)
				return false;
		} else if ((r.x != other.r.x) || (r.y != other.r.y)) 
			return false;
		return true;
	}

	public Rectangle getRectangle() {
		return this.r;
	}

	
}
