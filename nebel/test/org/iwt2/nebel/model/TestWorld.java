package org.iwt2.nebel.model;

import static org.junit.Assert.*;
import harness.Opener;
import harness.TestData;

import org.iwt2.nebel.controller.NebelGameScreen;
import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.World;
import org.iwt2.nebel.model.level.LevelPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Rectangle;

public class TestWorld {

	World main;
	Snake snake100x100;
	
	@Before
	public void setUp() throws Exception {
		ArrowButton.XSIZE = 16;
		ArrowButton.YSIZE = 20;

		main = TestData.createWorld320x320();
		//main.createArrows();
		
		snake100x100 = new Snake(100f, 100f);

	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void addNewButtonInGameZone() {
		//World w = new World();
		ArrowButton reference = new ArrowButton(10, 10, ArrowButton.RIGHT);
		
		assertTrue(main.getArrowButtonList().isEmpty());
		
		main.addButtonIn(reference, 100, 100);
		
		assertFalse(main.getArrowButtonList().isEmpty());
		ArrowButton b = main.getArrowButtonList().get(0);
		
		// Button appears centered
		assertEquals(100, b.getX(), 0.0);
		assertEquals(100, b.getY(), 0.0);
		assertEquals(ArrowButton.RIGHT, b.getArrowDirection());
		
		
	}

	
	@Test
	public void createFourControlArrowButtons() {
	
		assertEquals(4, main.getControlArrowButtons().size());	
		// ¿Cómo probar que uan lista contiene 4 valores distintos?
	}

	@Test
	public void createFourControlArrowButtons_FirstOneIsIn8x10() {
		
		ArrowButton b = main.getControlArrowButtons().get(0);
		
		assertEquals(8, b.getX(), 0.0);
		assertEquals(10, b.getY(), 0.0);
	}

	
	
	@Test
	public void createFourControlArrowButtonsAndSetSize() {
		Rectangle r;
		for (ArrowButton b: main.getControlArrowButtons()) {
			r = (Rectangle) Opener.open(b).get("r");
			assertEquals(32, r.width, 0.0);
			assertEquals(32, r.height, 0.0);
		}
	}
	
	@Test
	public void createControlArrowsButton_setXUsingSizesIn320x200() {
		Sizes ss = this.main.getSizes();
		ss.setWithAndHeight(320, 200);
		ss.setBlockAndArrowButtonSize(16, 16);
		
		this.main.controlArrowButtons.clear();
		this.main.createArrows();
		
		assertEquals(4, this.main.controlArrowButtons.size());
		
		for (ArrowButton ab: this.main.controlArrowButtons) {
		
			Rectangle r = ab.getRectangle();
		
			assertEquals(r.x, 8.0, 0.0);
			assertEquals(r.width, 32.0, 0.0);
		
		}
	}

	@Test
	public void createControlArrowsButton_setXUsingSizesIn800x200() {
		Sizes ss = this.main.getSizes();
		ss.setWithAndHeight(800, 200);
		ss.setBlockAndArrowButtonSize(32, 32);
		
		this.main.controlArrowButtons.clear();
		this.main.createArrows();
		
		assertEquals(4, this.main.controlArrowButtons.size());
		
		//int xExpected = (32 * 3) - 32
		for (ArrowButton ab: this.main.controlArrowButtons) {
		
			Rectangle r = ab.getRectangle();
		
			assertEquals(r.x, 32.0, 0.0);
			assertEquals(r.width, 32.0, 0.0);
		
		}
	}

	
	@Test
	public void testCreationOrArrowButtons() {
		ArrowButton button = main.getControlArrowButtons().get(0);
	
		assertTrue(button.containsPoint(button.getX()+1, button.getY()+1));
	}

	
	@Test
	public void testSearchForAndArrowButtons() {
		ArrowButton button = main.getControlArrowButtons().get(0);
		
		ArrowButton r = main.getControlArrowButtonWhichContainsPoint(button.getX() +1, button.getY() +1);
		
		assertEquals(button, r);
	}

	@Test
	public void testSearchForAndArrowButtons_NoButtonFoundGetsNull() {
		ArrowButton button = main.getControlArrowButtons().get(0);
		
		ArrowButton r = main.getControlArrowButtonWhichContainsPoint(button.getX(), button.getY() );
		
		assertNull(r);
	}
	
	/*
	@Test
	public void testCreateSnake() {
		main.createSnake();
		
		Snake s = main.getSnake();
		assertNotNull(s);
	}*/
	
	@Test
	public void testCreateHouseBlock() {
		main.getSizes().setHouseSize(10, 10);
		House house = main.getHouse();
		assertNull(house);
		
		main.createHouse();
		house = main.getHouse();
		assertNotNull(house);
	}
	
	
	@Test
	public void testSnakeCollidesWithArrowBlock_SnakeChangesDirection() {
		ArrowButton ab = new ArrowButton(100f, 100f, ArrowButton.DOWN);
		
		this.main.snakeCollidesWithArrowBlock(snake100x100, ab);
		
		assertEquals(Snake.VDOWN.x, snake100x100.getDirection().x, 0.0);
		assertEquals(Snake.VDOWN.y, snake100x100.getDirection().y, 0.0);
	}
	
	
	@Test
	public void testRemoveSnake_OneSnake() {
		this.main.addSnake(snake100x100);
		assertEquals(1, this.main.getSnakes().size());
		
		this.main.removeSnake(snake100x100);
		assertEquals(0, this.main.getSnakes().size());
		
	}

	@Test
	public void testRemoveSnake_TwoSnake() {
		this.main.addSnake(snake100x100);
		Snake s2 = new Snake(200f, 200f);
		this.main.addSnake(s2);
		
		this.main.removeSnake(snake100x100);
		assertEquals(s2, this.main.getSnakes().get(0));
		
	}


	@Test
	public void testSearchForArrowButtonInGameZone_NotFound() {
		main.getArrowButtonList().clear();
		main.getArrowButtonList().add(new ArrowButton(100f, 100f, ArrowButton.DOWN));
		
		ArrowButton r = main.getArrowButtonWhichContainsPoint(99f, 99f);
		
		assertNull(r);
	}


	@Test
	public void testSearchForArrowButtonInGameZone_Found() {
		main.getArrowButtonList().clear();
		main.getArrowButtonList().add(new ArrowButton(100f, 100f, ArrowButton.DOWN));
		
		ArrowButton r = main.getArrowButtonWhichContainsPoint(101f, 101f);
		
		assertNotNull(r);
		assertEquals(100f, r.getX(), 0.0f);
		assertEquals(100f, r.getY(), 0.0f);
	}
	
	@Test
	public void testWorldSetTheSnakeAtTheFirstLevel() {
		main.loadNextLevel();
		
		Snake sInWord = this.main.getSnakes().get(0);
		Snake sInLevel = this.main.pool.getLevelList(LevelPool.Dificulty.low).get(0).getSnakeList().get(0);
		
		
		assertEquals(sInWord.getX(), sInLevel.getX(), 0.0f);
		assertEquals(sInWord.getY(), sInLevel.getY(), 0.0f);
		
		
	}

	@Test
	public void testWorldHasTwoLevels() {
		main.loadNextLevel();
		boolean b = main.loadNextLevel();

		assertTrue(b);
		
		Snake sInWord = this.main.getSnakes().get(0);
		Snake sInLevel = this.main.pool.getLevelList(LevelPool.Dificulty.low).get(1).getSnakeList().get(0);
		
		
		assertEquals(sInWord.getX(), sInLevel.getX(), 0.0f);
		assertEquals(sInWord.getY(), sInLevel.getY(), 0.0f);
		
		
	}
	
	@Test
	public void testLevelReloadSnakesAfterWorldLoadLevel() {
		main.loadNextLevel();
		Snake sInWord = this.main.getSnakes().get(0);

		main.removeSnake(sInWord);
		
		boolean b = main.loadNextLevel();
		
		assertTrue(b);
		assertFalse(main.noMoreSnakes());
	}
	
	@Test
	public void testClearWorld() {
		main.clear();
		
		assertTrue(main.getArrowButtonList().isEmpty());
		//assertTrue(main.getBlocks().isEmpty()); - It has the blocks of the gamexone
		assertTrue(main.getSnakes().isEmpty());
	}

	@Test
	public void testAfterLoadingANewLevelSnakeHasdiferenCoordinates() {
		main.loadNextLevel();
		Snake s = main.getSnakes().get(0);
		
		s.r.x = -12;
		s.r.y = -12;
		
		main.loadNextLevel();
		Snake s2 = main.getSnakes().get(0);
		
		assertEquals(1, main.getSnakes().size());
		assertTrue(s.getX() +", "+ s2.getX(), s.getX() != s2.getX());
		assertTrue(s.getY() != s2.getY());
		
		
	}
	
}
