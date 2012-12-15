package org.iwt2.nebel.model;

import com.badlogic.gdx.math.Rectangle;

public class Block {
	// To be set from outside
	public static int XSIZE = -1;
	public static int YSIZE = -1;
	
	public static int C = 0;
		
		Rectangle r;
	int id;
	
	public Block(float i, float j) {
			r = new Rectangle();
			r.width = XSIZE;
			r.height = YSIZE;
			r.x = i;
			r.y = j;
		id = C;
		C++;
	}
		
/*
	public Block(int i, float f, float y) {
		this(f, y);
		id = i;
		//C--;
	}
*/

		public Rectangle getRectangle() {
			return this.r;
		}


		public float getX() {
			return this.r.x;
		}
		public float getY() {
			return this.r.y;
		}
	
	public int getId() {
		return this.id;
	}

		public String toString() {
			String s = "Block (";
			s += this.getX();
			s+= ",";
			s += this.getY();
			s += ")";
			s += " width: " + this.r.width;
			s += " height: " + this.r.getHeight();
			return s;
					
		}

}
