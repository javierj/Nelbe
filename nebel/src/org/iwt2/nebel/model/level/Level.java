package org.iwt2.nebel.model.level;

import java.util.ArrayList;
import java.util.List;

import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.World;

import com.badlogic.gdx.math.Vector3;

public class Level {
	List<Snake> snakes;
	List<Block> blocks;
	
	/**
	 * Not in use
	 * @param xSnake
	 * @param ySnake
	 * @param vSnake
	 * @return
	 
	public static Level createLevel(float xSnake, float ySnake,	float vSnake) {
		Level l = new Level();
		Snake s = new Snake(xSnake, ySnake);
		s.setVelocity(vSnake);
		l.snakes.add(s);
		return l;
	}
*/
	
	public static Level createLevel(Snake s) {
		Level l = new Level();
		l.snakes.add(s);
		return l;
	}
	
	//public static Level createLevel(List<Vector3> snakes) {
	public static Level createLevel(List<Snake> snakes) {
		Level l = new Level();
		l.snakes.addAll(snakes);
		return l;
	}


	public Level() {
		snakes = new ArrayList<Snake>();
		blocks = new ArrayList<Block>();
	}
	
	public void loadLevelIn(World w) {
		// Fake
		
		// Create snake
		//w.createSnake();

		/*
		Snake s;
		
		for (Vector3 v3: this.snakes) {
			s = new Snake(v3.x, v3.y);
			s.setVelocity(v3.z);
			w.addSnake(s);
		}*/
		
		w.addSnakes(this.snakes);
		w.addBlocks(this.blocks);
	
	}

	/**
	 * for tetsing purpouses
	 * @return
	 */
	public List<Snake> getSnakeList() {
		return this.snakes;
	}

	public void addSnake(Snake snake) {
		this.snakes.add(snake);
		
	}

	public void addBlock(Block block) {
		this.blocks.add(block);
	}

	public List<Block> getBlockList() {
		return this.blocks;
	}

}
