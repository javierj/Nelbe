package org.iwt2.nebel.model;

import java.util.*;

import org.iwt2.nebel.model.level.Level;
import org.iwt2.nebel.model.level.LevelPool;
import org.iwt2.nebel.util.TimeEvent;

import com.badlogic.gdx.math.Vector2;


public class World {
	List<ArrowButton> arrowButtons;
	protected List<ArrowButton> controlArrowButtons;
	protected List<Block> blocks;
	Statistics stats;
	
	private static final int HOUSE_TIME = 2000;
	TimeEvent houseTimer;
	
	Sizes sizes;
	protected House house;
	private List<Snake> snakes;

	public LevelPool pool;
	LevelPool.Dificulty d;
	
	public World(Sizes ss) {
		arrowButtons = new ArrayList<ArrowButton>();
		controlArrowButtons = new ArrayList<ArrowButton>();
		sizes = Sizes.getInstance();
		
		GameZone gz = new GameZone();
		blocks = gz.getBlocksFromGameZone();
		
		//this.stats = new Statistics();
		
		this.house = null;
		
		houseTimer = new TimeEvent(HOUSE_TIME);
		this.snakes = new ArrayList<Snake>();
		
		this.createArrows();

		pool = new LevelPool();
		// It will come from outside the class
		this.d = LevelPool.Dificulty.low;
	}
	
	/**
	 * Return list of buttons in game zone
	 * @return
	 */
	public List<ArrowButton> getArrowButtonList() {
		return arrowButtons;
	}

	
	/**
	 * Adds a new button in the game
	 * @param reference
	 * @param i
	 * @param j
	 */
	public void addButtonIn(ArrowButton reference, float i, float j) {
		
		this.arrowButtons.add(reference.copy(i, j));
		
	}
	
	/**
	 * Get the list of the four buttons in control zone
	 * @inv: size() == 4
	 * @return
	 */
	public List<ArrowButton> getControlArrowButtons() {
		return this.controlArrowButtons;
	}
	
	/**
	 * Set a fized size of 32x32
	 */
	protected void createArrows() {
		int width = 32;
		int height = 32;
		
		int x = ((int)this.sizes.getButtonZoneRectangle().width - width) / 2;
		
		int y = this.sizes.getInitialYForControlButton();
		int esp = this.sizes.getSpaceBetweenControlButton();
		
		controlArrowButtons.add(new ArrowButton(x, y, width, height, ArrowButton.RIGHT));
		y += esp;
		controlArrowButtons.add(new ArrowButton(x, y, width, height,ArrowButton.LEFT));
		y += esp;
		controlArrowButtons.add(new ArrowButton(x, y, width, height,ArrowButton.UP));
		y += esp;
		controlArrowButtons.add(new ArrowButton(x, y, width, height,ArrowButton.DOWN));
	}
	
	/**
	 * Returns null if the point is out of any control arrow button
	 * @param f
	 * @param g
	 * @return
	 */
	public ArrowButton getControlArrowButtonWhichContainsPoint(float f, float g) {
		return getButtonWhichContainsPoint(f, g, this.controlArrowButtons);
	}

	public ArrowButton getArrowButtonWhichContainsPoint(float f, float g) {
		return getButtonWhichContainsPoint(f, g, this.arrowButtons);
	}

	private ArrowButton getButtonWhichContainsPoint(float f, float g, List<ArrowButton> l) {
		for (ArrowButton ab: l) {
			if (ab.containsPoint(f, g))
				return ab;
		}
		return null;
	}

	public void snakeCollidesWithArrowBlock(Snake s, ArrowButton ab) {
		s.changeDirection(ab.getArrowDirection());
		this.removeArrowButton(ab);
	}

	public void snakeCollidesWithArrowBlock(ParSnakeArrowButton res) {
		this.snakeCollidesWithArrowBlock(res.getS(), res.getAb());
	}
	
	public void removeArrowButton(ArrowButton abInGameZone) {
		this.arrowButtons.remove(abInGameZone);
	}


	public void update(float delta, HouseCallback hc) {
		for (Snake snake: this.getSnakes()) {
			snake.update(delta);
		}
		if (this.houseTimer.expired()) {
			this.createHouse();
			hc.enterHouse();
			//initTime = -1;
		}

	}

	
	public List<Block> getBlocks() {
		return this.blocks;
	}

	public House getHouse() {
		return this.house;
	}

	/**
	 * Creates a house in the center of the screen
	 * House is a block.
	 */
	public void createHouse() {
		int y = (int) this.sizes.getHeight() / 2;
		int x = this.sizes.getWidth() / 2;
		
		Vector2 houseSize = this.sizes.getHouseSize();
		
		x -= houseSize.x / 2;
		y -= houseSize.y / 2;
		
		this.house = new House(x, y, houseSize.x, houseSize.y);
	}

	/**
	 * 
	 * @return Size object
	 */
	public Sizes getSizes() {
		return this.sizes;
	}

	public Statistics getStats() {
		return this.stats;
	}

	public List<Snake> getSnakes() {
		return this.snakes;
	}

	/**
	 * Deprecated
	 * @param s
	 */
	public void addSnake(Snake s) {
		this.snakes.add(s);
	}

	public void addSnakes(List<Snake> snakes2) {
		this.snakes.addAll(snakes2);
	}
	
	 /**
	  * Called when loading a level
	  * @param blocks2
	  */
	public void addBlocks(List<Block> blocks2) {
		this.blocks.addAll(blocks2);
	}


	public void removeSnake(Snake snake) {
		this.snakes.remove(snake);
	}

	/**
	 * Return false if there is no more levels.
	 * True if there is a new evel to play
	 * 
	 * f there is a new level, this method set the worodl object for the new level.
	 */
	public boolean loadNextLevel() {
		//System.out.println("loadNextLevel()");
		this.clear();
		Level l = this.pool.getNextLevel(d);
		if (l == null)
			return false;
		l.loadLevelIn(this);
		return true;
	}

	public boolean noMoreSnakes() {
		return this.snakes.isEmpty();
	}

	public void setdificulty(LevelPool.Dificulty d) {
		this.stats = new Statistics();
		this.d = d;
		this.stats.setDificulty(LevelPool.DificultyAsString(d));
		this.stats.setNumberOfLevels(this.pool.getLevelList(d).size());
	}
	
	/**
	 * Clear all the elements in the world.
	 * Call this method before loading a new level.
	 */
	 void clear() {
		this.arrowButtons.clear();
		this.blocks.clear();
		this.houseTimer.reset();
		this.snakes.clear();
		this.house = null;

		GameZone gz = new GameZone();
		blocks = gz.getBlocksFromGameZone();
	}

}
