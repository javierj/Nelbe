package org.iwt2.nebel.model;

import java.util.*;


import com.badlogic.gdx.math.Rectangle;


public class GameZone {

	Sizes ss;
	
	
	public GameZone() {
		ss = Sizes.getInstance();
	}

	
	public List<Block> getBlocksFromGameZone() {
		Rectangle r = ss.getGameZoneRectangle();
		List<Block> bs = new ArrayList<Block>();
		
		float x = r.getX();
		float y = r.getY();
		float desY = r.getHeight() - ss.getBlockSizeY();
		
		for (int i = 0; i < this.ss.getBlocksInGameZoneWidht(); i++) {
			bs.add(new Block(x, y));
			bs.add(new Block(x, y + desY));
			x += ss.getBlockSizeX();
		}
	/*	
		x = r.getX();
		y = r.height - this.ss.getBlockSizeY();
		
		if (y > 0) {
			bs.addAll(this.createBlocks(x, y, r.getWidth()));
		}
	*/
		if ( (r.getHeight() / ss.getBlockSizeY()) > 2) {
	
		x = r.getX();
		 y = r.getY();
		 float desX = r.getWidth() - ss.getBlockSizeX();
		 for (int i = 0; i < (r.getHeight() / ss.getBlockSizeY()); i++) {
				bs.add(new Block(x, y));
				bs.add(new Block(x + desX, y));
				y += ss.getBlockSizeY();
			}
		}	 
		 // Apaño
		 //Set<Block> s = new HashSet<Block>(bs);
		 //bs = new ArrayList<Block>(s);
			
		return bs;
	
		
	}
	
	private List<Block> createBlocks(float x, float y, float dim) {
		List<Block> bs = new ArrayList<Block>();
		for (int i = 0; i < (dim / ss.getBlockSizeX()); i++) {
			bs.add(new Block(x, y));
			x += ss.getBlockSizeX();
		}
		
		return bs;
	}

}
