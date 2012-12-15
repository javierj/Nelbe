package org.iwt2.nebel.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.iwt2.nebel.NebelGame;
import org.iwt2.nebel.controller.collisions.CollisionsChecker;
import org.iwt2.nebel.controller.input.GameInputProcessor;
import org.iwt2.nebel.controller.input.TouchListener;
import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.HouseCallback;
import org.iwt2.nebel.model.ParSnakeArrowButton;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.World;
import org.iwt2.nebel.model.level.LevelPool;
import org.iwt2.nebel.util.TimeEvent;
import org.iwt2.nebel.view.PhysicScreen;
import org.iwt2.nebel.view.SoundSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class NebelGameScreen extends AbstractScreen {

	//PhysicScreen screen; - Goes to abstractscreen
	//World world; - Goes to abstractScreeb
	private SoundSystem soundSystem;
	private GameInputProcessor ip;
	CollisionsChecker collisions;
	Sizes ss;
	TouchProcessor touchListener;
	private boolean isInPause;
	boolean isInTransition;
	Vector2 banner;
	HouseEvent he;
	boolean stillInTouch = false;
	
	public NebelGameScreen(NebelGame game) {
		super(game);
		isInTransition = false;
		banner = new Vector2();
		he = new HouseEvent();
	}

	
	/**
	 * Creates the world
	 */
	public void createModel(Sizes ss) {
		world = new World(ss);
		world.setdificulty((LevelPool.Dificulty) this.game.getFromSession("dificulty"));
		//world.createArrows(); -- Dentro de World()
		//world.createSnake(); -- World calls level
		world.loadNextLevel();
	}

	public Sizes createView() {
		screen = new PhysicScreen();
		screen.init();
		
		//Sizes ss = new Sizes();
		ss = Sizes.getInstance();
		screen.fillWirhSizes(ss);
		
		soundSystem = this.game.getSound();	
		//soundSystem.init();
		
		return ss;
	}

	/**
	 * Load game
	 * Refactor: move to splash screem
	 */
	@Override
	public void show() {
		this.isInPause = false;
		this.ss = createView();		
		createModel(ss);

		// Move
		ip = new GameInputProcessor(this.world);
		linkInputProcesor();
		touchListener = new TouchProcessor();
		ip.registerTouchListener(touchListener);

		setWorldIntoView();
		this.collisions = new CollisionsChecker(world);
		
		//initTime = TimeUtils.millis();
		//houseTimer = new TimeEvent(HOUSE_TIME);
		this.world.getStats().setInitTime(TimeUtils.millis());
	}
	
	public void linkInputProcesor() {
		//Gdx.input.setInputProcessor(ip);
		
	}


	public void setWorldIntoView() {
		this.screen.setWorld(this.world);
	}


	/**
	 * If game is closed before beginning a game, screen is null.
	 */
	@Override
	public void dispose() {
		if (this.screen != null)
			this.screen.dispose();
	}

	
	public World getWorld() {
		return this.world;
	}

	public InputProcessor getInputProcessor() {
		return this.ip;
	}


	//-------------------------------------------

	/**
	 * Draws screen
	 * Indicate if there are anymarked arrow button
	 * to be draw different
	 */
	@Override
	public void render(float delta) {
		if ((this.isInPause == false) && (this.isInTransition == false)) {

			// Avoid rebote
			if ((Gdx.input.isTouched()) && (stillInTouch == false)) {
				this.touchListener.touchDown(Gdx.input.getX(), Gdx.input.getY());
				stillInTouch = true;
			}
			if (Gdx.input.isTouched() == false) {
				stillInTouch = false;
			}
			
			checkCollisions();
			this.world.update(delta, he);
		}
		if (this.isInTransition) {
			updateTransitionBanner(delta);
			this.screen.draw(this.touchListener.getMarkedArrow(), banner);
		} else {
			this.screen.draw(this.touchListener.getMarkedArrow(), null);
		}
		/* -- Trace
		if (this.world.getSnakes().size() > 0)
			System.out.println("Snake: " + world.getSnakes().get(0).getBounds().width + ", " + world.getSnakes().get(0).getBounds().height);
		if (this.world.getBlocks().size() > 0)
			System.out.println("Block: " + world.getBlocks().get(0).getRectangle().width + ", " + world.getBlocks().get(0).getRectangle().height);
		if (this.world.getArrowButtonList().size() > 0)
			System.out.println("Arrow: " + world.getArrowButtonList().get(0).getRectangle().width + ", " + world.getArrowButtonList().get(0).getRectangle().height);
		
		if (this.world.getHouse() != null)
			System.out.println("House: " + world.getHouse().getRectangle().width + ", " + world.getHouse().getRectangle().height);
		*/
	}


	private void updateTransitionBanner(float delta) {
		float bannerVel = 550f;
		banner.x += bannerVel  * delta;
		
		if (banner.x >= ss.getWidth()) {
			this.isInTransition = false;
		}
	}


	/**
	 * Esto va aquí o tengo que refactorizarlo ??
	 */
	public void checkCollisions() {
		
		
		ParSnakeArrowButton ab = this.collisions.checkSnakeCollidesWithArrowButton();
		Snake s;
		if (ab != null) {
			this.world.getStats().incArrowsUsedBySnake();
			this.world.snakeCollidesWithArrowBlock(ab);
			this.soundSystem.playSnakeHitsArrow();
		}
		s = this.collisions.checkSnakeCollidesWithBlock(); 
		if (s != null) {
			this.world.getStats().incDeathSnake();
			this.world.removeSnake(s);
			this.soundSystem.playSnakeDiesSound();
		}
		s = this.collisions.checkSnakeCollidesWithHouse();
		if (s != null) {
			this.world.getStats().incSavedSnake();
			this.world.removeSnake(s);
			this.soundSystem.playSnakeInHouseSound();
		}
		
		if (this.world.noMoreSnakes()) {
			this.endOfLevel();
		}
		
	}

	
	private void endOfLevel() {
		//initTime = ;
		this.world.getStats().setFinalTime(TimeUtils.millis());
		this.world.getStats().incPlayedLevel();
		
		//createModel(ss);
		if (world.loadNextLevel() == false) {
			this.endOfGame();
			return;
		}

		
		//this.game.goToStatisticScreen();
		
		this.collisions = new CollisionsChecker(world);
		ip = new GameInputProcessor(this.world);
		linkInputProcesor();
		touchListener = new TouchProcessor();
		ip.registerTouchListener(touchListener);
		
		this.isInTransition = true;
		
		this.soundSystem.playMoreNebels();
		
		banner.x = -500f;
		banner.y = 200f;


	}

	private void endOfGame() {
		//setWorldIntoView();		
		//System.out.println("Animation and go back to main screen");
		this.game.setPhysicScreen(this.screen);
		this.game.setWorld(this.world);

		this.game.goToStatisticScreen();
	}


	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		this.isInPause = true;
	}

	@Override
	public void resume() {
		this.isInPause = false;
	}


	@Override
	public void hide() {
		this.isInPause = true;		
	}
	
	//------------------------------------------------------------------
	
	class TouchProcessor implements TouchListener {
		private ArrowButton markedArrowButton;
		Rectangle exitButtonBound = ss.getExitButtonRectangle();

		
		public void touchDown (int x, int y) {
			//System.out.println("TouchProcessor - Touchdown " + x+ ", " + y);
			if (this.exitButtonBound.contains(x, y)) {
				//System.out.println("TouchProcessor - Exit button tocuhed.");
				world.getStats().setFinalTime(TimeUtils.millis());
				endOfGame();
				return;
			}
			ArrowButton ab = world.getControlArrowButtonWhichContainsPoint(x, y);
			if (ab != null) {
				//System.out.println("Marked button in " + x + ", "+ y);
				soundSystem.playButtonTouchedSound();
				this.markedArrowButton = ab;
				return;
			}
			if (this.markedArrowButton != null) {
				if (ss.isInGameZone(x, y) == false) {
					return;
				}
				//System.out.println("Placing button in " + x + ", "+ y);
			
				ArrowButton abInGameZone = world.getArrowButtonWhichContainsPoint(x, y);
				if (abInGameZone != null) {
					world.removeArrowButton(abInGameZone);
					world.getStats().incArrowsErasedByPlayer();
					soundSystem.playRemoveArrow();
					return;
				}
					
				
				// Apaño que hay que arreglar
				float xorg;
				float yorg;
				
				xorg = x - (ss.getBlockSizeX() /2 );
				yorg = y - (ss.getBlockSizeY() /2 );
				Rectangle r = new Rectangle(xorg, yorg, ArrowButton.XSIZE, ArrowButton.YSIZE);
				
				if (collisions.rectangleCollidesWithAnyInTheWorld(r))
					return;
				
				world.addButtonIn(this.markedArrowButton, xorg, yorg);
				world.getStats().incArrowsPlaced();
				soundSystem.playAddArrowSound();
			}
			
		}
		
		public ArrowButton getMarkedArrow() {
			return this.markedArrowButton;
		}


	}
	
	//---------------
	
	class HouseEvent implements HouseCallback {

		@Override
		public void enterHouse() {
			soundSystem.playEnterHouse();
		}
		
	}
	
}
