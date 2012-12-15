package org.iwt2.nebel.model.level;

import java.util.ArrayList;
import java.util.List;

import org.iwt2.nebel.controller.collisions.CollisionsChecker;
import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.SnakeInitialPosition;
import org.iwt2.nebel.model.World;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

//import org.iwt2.nebel.model.level.LevelPool.Dificulty;

public class LevelPool {
	
	public enum Dificulty {low, medium, /*high,*/ infinite};
	
	final List<Level> lowLevels;
	int levelIndex;
	Rectangle gameZone;
	Sizes ss;
	private List<Level> mediumLevels;
	InfiniteLevel infinite;
	//World w;
	
	/**
	 * inv: infiniteLevels.size() == 1;
	 */
	private List<Level> infiniteLevels;
	
	
	public static String DificultyAsString(Dificulty d) {
		return d.toString();
	}

	public LevelPool(/*World w*/) {
		ss = Sizes.getInstance();
		this.gameZone = ss.getGameZoneRectangle();

		this.lowLevels = new ArrayList<Level>();
		this.createLowLevels();
		this.mediumLevels = new ArrayList<Level>();
		this.createMediumLevels();
		this.infiniteLevels = new ArrayList<Level>();
		this.createInfiniteLevels();

		levelIndex = 0;
		infinite = new InfiniteLevel();

		//this.w = w;
	}

	/*
	public LevelPool(Sizes ss) {
		this.gameZone = ss.getGameZoneRectangle();

		this.lowLevels = new ArrayList<Level>();
		this.createLowLevels();
		levelIndex = 0;

	}
*/
	
	public void createLowLevels() {
		Snake s;
		Level l;
		float baseVel = 20f;
		
		// 1- One snake
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel, ArrowButton.RIGHT);
		this.lowLevels.add(Level.createLevel(s));

		// 1- One snake. 1'5 velocity
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel * 1.5f, ArrowButton.RIGHT);
		this.lowLevels.add(Level.createLevel(s));
		
		// 3- Two snakes base velocity
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel, ArrowButton.RIGHT);
		l = Level.createLevel(s);
		s = new Snake(this.gameZone.width - 20, this.gameZone.y + 60, baseVel, ArrowButton.DOWN);
		l.addSnake(s);
		this.lowLevels.add(l);

		// 4- Two snakes, more velocuty
		float nVel = baseVel * 1.5f;
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, nVel, ArrowButton.RIGHT);
		l = Level.createLevel(s);
		s = new Snake(this.gameZone.width - 60, this.gameZone.y +200, nVel, ArrowButton.UP);
		l.addSnake(s);
		this.lowLevels.add(l);
		
		// 5- Three snakes base velocity
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel, ArrowButton.RIGHT);
		l = Level.createLevel(s);
		s = new Snake(this.gameZone.width - 60, this.gameZone.y + 200, baseVel, ArrowButton.UP);
		l.addSnake(s);
		s = new Snake(this.gameZone.x + 160, this.gameZone.y +200, baseVel, ArrowButton.LEFT);
		l.addSnake(s);
		this.lowLevels.add(l);

	}

	
	public void createMediumLevels() {
		Snake s;
		Level l;
		float baseVel = 25f;
		
		// 1- One snake. Two random blocks
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel, ArrowButton.RIGHT);
		l = Level.createLevel(s);
		
		//for (int i= 0; i < 120; i++)
		this.addRandomBlockInLevel(l);
		this.addRandomBlockInLevel(l);
		this.mediumLevels.add(l);

		// 2- Two snake. Four random blocks
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel, ArrowButton.RIGHT);
		l = Level.createLevel(s);
		s = new Snake(this.gameZone.width - 60, this.gameZone.y + 200, baseVel, ArrowButton.UP);
		l.addSnake(s);
		this.addRandomBlocksInLevel(l, 3);
		this.mediumLevels.add(l);

		// 3- Three snake. Three random blocks
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel, ArrowButton.RIGHT);
		l = Level.createLevel(s);
		s = new Snake(this.gameZone.width - 60, this.gameZone.y + 200, baseVel, ArrowButton.UP);
		l.addSnake(s);
		s = new Snake(this.gameZone.x + 40, this.gameZone.y + 220, baseVel, ArrowButton.UP);
		l.addSnake(s);
		this.addRandomBlocksInLevel(l, 3);
		this.mediumLevels.add(l);

		// 4- Four snake. Three random blocks
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel, ArrowButton.RIGHT);
		l = Level.createLevel(s);
		s = new Snake(this.ss.getWidth() - 100, this.gameZone.y + 200, baseVel, ArrowButton.UP);
		l.addSnake(s);
		s = new Snake(this.ss.getWidth() - 70, this.ss.getHeight() -70, baseVel, ArrowButton.LEFT);
		l.addSnake(s);
		s = new Snake(this.gameZone.x + 40, this.gameZone.y + 100, baseVel, ArrowButton.DOWN);
		l.addSnake(s);
		this.addRandomBlocksInLevel(l, 2);
		this.mediumLevels.add(l);

		// 5- One snake. Four random blocks. 1'5 faster
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel, ArrowButton.RIGHT);
		s.setVelocity(baseVel * 1.5f);
		l = Level.createLevel(s);
		this.addRandomBlocksInLevel(l, 4);
		this.mediumLevels.add(l);

		// 6- One snake. Five random blocks. 1'5 faster
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel, ArrowButton.RIGHT);
		s.setVelocity(baseVel * 1.5f);
		l = Level.createLevel(s);
		this.addRandomBlocksInLevel(l, 5);
		this.mediumLevels.add(l);
		

	}

	public void createInfiniteLevels() {
		Snake s;
		float baseVel = 30f;
		
		// 1- One snake
		s = new Snake(this.gameZone.x + 60, this.gameZone.y + 60, baseVel, ArrowButton.RIGHT);
		this.infiniteLevels.add(Level.createLevel(s));
	}
	
	

	public List<Level> getLevelList(Dificulty d) {
		if (d == Dificulty.low) { 
				return this.lowLevels;
		}
		if (d == Dificulty.medium) { 
			return this.mediumLevels;
		}
		if (d == Dificulty.infinite) { 
			return this.infiniteLevels;
		}
		
		System.out.println("LevelPool. No levels for: " + d);
		return null;
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public Level getNextLevel(Dificulty d) {
		Level l;
		
		if (d == Dificulty.low) {
			if (this.levelIndex < this.lowLevels.size()) {
				l = this.lowLevels.get(this.levelIndex);
				this.levelIndex++;
				return l;
			}
			return null;
		}
		if (d == Dificulty.medium) {
			if (this.levelIndex < this.mediumLevels.size()) {
				l = this.mediumLevels.get(this.levelIndex);
				this.levelIndex++;
				return l;
			}
			return null;
		}

		if (d == Dificulty.infinite) {
			this.levelIndex++;
			//modifyInfiniteLevel();
			this.infinite.updateLevel(this.infiniteLevels.get(0), this.levelIndex);
			return this.infiniteLevels.get(0);
		}

		System.out.println("LevelPool. No levels for: " + d);
		return null;
	}


	void addRandomBlockInLevel(Level l) {
		Vector2 pos = ss.getRandomCoordinatesInGameZone();
		Block b = new Block(pos.x, pos.y);
		if (CollisionsChecker.isSnakeCollidesWithBlock(l.getSnakeList(), b) == false )
			l.addBlock(b);
	}
	
	void addRandomBlocksInLevel(Level l, int num) {
		for (int i = 0; i < num; i++)
			this.addRandomBlockInLevel(l);
	}


	//-------------
	
	class InfiniteLevel {
		float initVel = ss.getBaseVelocity();
		float finalVel = 200f;
		float incVel = 15f;
		float vel = initVel;
		boolean addingVel = true;
		int blockLimit = 7;
		boolean addingBlocks = true;
		//int snakeLimit = 3;
		boolean addingSnakes = true;
		SnakeInitialPosition[] snakeInit = ss.getInitialSnakePos();
		
		//int snakeIndex = 0;
		int i = 0;
		
		private void resetSnakes(Level l) {	
			for (Snake s: l.getSnakeList()) {
				s.setInitialPos(snakeInit[i]);
				s.setVelocity(vel);
				incI();
			}
		}

		void incI() {
			i++;
			if (i == snakeInit.length)
				i = 0;

		}
		
		public void updateLevel(Level l, int levelI) {
			
			resetSnakes(l);
			
			// Vel
			if ((levelI % 2) == 0) {
				if (addingVel) {
					vel += incVel;
					addingVel = (vel >= finalVel);
				} else {
					vel -= incVel;
					addingVel = (vel <= initVel);
				}
				/*for (Snake s: l.getSnakeList()) {
					s.setVelocity(vel);
				}*/
			}

			// Snakes
			if ((levelI % 3) == 0) {
				if (addingSnakes) {
					l.addSnake(new Snake(snakeInit[i], vel));
					incI();
					addingSnakes = (l.getSnakeList().size() < 4);
				} else {
					l.getSnakeList().remove(0);
					//snakeIndex = 0;
					addingSnakes = (l.getSnakeList().size() == 1);
				}
			}
			
			// Random blocks
			//if ((levelI%2) == 0) {
				if (addingBlocks) {
					addRandomBlockInLevel(l);
					addingBlocks = (l.getBlockList().size() < blockLimit);

				} else {
					l.getBlockList().clear();
					addingBlocks = true;
				}
			//}
			
			
			
		} // Method


		
		
	} // inner class
}
