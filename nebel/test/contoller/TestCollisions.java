package contoller;

import static org.junit.Assert.*;

import java.util.List;


import harness.TestData;

import org.iwt2.nebel.controller.collisions.CollisionsChecker;
import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.House;
import org.iwt2.nebel.model.ParSnakeArrowButton;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.OpenWorld;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCollisions {
	Snake s;
	OpenWorld w;
	List<ArrowButton> arrows;
	CollisionsChecker cc ;
	ParSnakeArrowButton res;
	Block h;
	House house; 
	int houseSize = 32;
	
	@Before
	public void setUp() throws Exception {
		w = new OpenWorld(TestData.createWorld320x320());
		arrows = w.getArrowButtonList();
		
		ArrowButton ab = new ArrowButton(100, 100, ArrowButton.DOWN);
		arrows.add(ab);
		
		putSnakeInWorldIn(100,100);
		//s = new Snake(100, 100);
		//w.setSnake(s);

		//cc = new CollisionsChecker(w);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSnakeCollidesWithArrowButton() {
		
		res = cc.checkSnakeCollidesWithArrowButton();
		
		assertNotNull(res);
	}

	
	@Test
	public void testSnakeDoesNotCollidesWithArrowButton() {
		this.w.getSnakes().clear();
		putSnakeInWorldIn(200, 200);
		
		res = cc.checkSnakeCollidesWithArrowButton();
		
		assertNull(res);
	}

	//--- Blocks ---------------------------------------------
	

	@Test
	public void testSnakeCollidesWithBlock() {
		Block b = new Block(s.getX(), s.getY());
		this.w.getBlocks().add(b);
		Snake res = cc.checkSnakeCollidesWithBlock();
		
		assertNotNull(res);
	}
	
	@Test
	public void testSnakeDoesNotCollidesWithBlock() {
		Block b = new Block(s.getX() + s.getBounds().x+ 1, s.getY()+ s.getBounds().y + 1);
		this.w.getBlocks().add(b);
		Snake res = cc.checkSnakeCollidesWithBlock();
		
		assertNull(res);
	}
	
	
	@Test
	public void testThreeSnakesAndOneCollidesWithBlock() {
		this.w.getSnakes().clear();
		putSnakeInWorldIn(200, 200);
		putSnakeInWorldIn(100,100);
		putSnakeInWorldIn(50,50);

		Block b = new Block(100, 100);
		this.w.getBlocks().add(b);

		Snake s = cc.checkSnakeCollidesWithBlock();
		
		assertEquals(100f, s.getX(), 0.0);
		assertEquals(100f, s.getY(), 0.0);
	}

	
	//--- House ------------------------------------------------------------------

	@Test
	public void testSnakeCollidesWithHouse() {
		float x = s.getX() + 1; //+ s.getBounds().radius;
		float y = s.getY()+  1;
		
		house =new House(x, y, houseSize, houseSize);
		this.w.setHouse(house);
		
		Snake snake = cc.checkSnakeCollidesWithHouse();
		
		assertNotNull(s + " / " + house, snake);
	}

	@Test
	public void testSnakeDoesNotCollidesWithHouse() {
		house = new House(s.getX() + s.getBounds().x, s.getY()+ s.getBounds().y + 1, houseSize, houseSize);
		this.w.setHouse(house);
		
		Snake s = cc.checkSnakeCollidesWithHouse();
		
		assertNull(s);
	}

	@Test
	public void testThreeSnakesAndOneCollidesWithHouse() {
		putSnakeInWorldIn(200, 200);
		putSnakeInWorldIn(300,300);
		putSnakeInWorldIn(50,50);
		
		house =new House(300, 300, houseSize, houseSize);
		this.w.setHouse(house);
		
		Snake snake = cc.checkSnakeCollidesWithHouse();
		
		assertEquals(300f, snake.getX(), 0.0f);
		assertEquals(300f, snake.getY(), 0.0f);
	}
	
	
	@Test
	public void testSnakeCollidesWithHouseCreatedByWorld() {
		this.w.createHouse();
		Block house = this.w.getHouse();
		
		assertNotNull(house);
		this.w.getSnakes().clear();
		
		
		Snake s = new Snake(house.getX(), house.getY());
		this.w.getSnakes().add(s);
		
		Snake s2 = cc.checkSnakeCollidesWithHouse();
		
		assertNotNull(s2);
		assertEquals(s, s2);
	}
	

	@Test
	public void testSnakeCollidesWithHouseCreatedByWorld_InTheBorder() {
		
		House house = createHouseWithSize32x32InWord();
		
		assertNotNull(house);
		this.w.getSnakes().clear();
		
		
		Snake s = new Snake(house.getX() + house.getRectangle().width-1, 
							house.getY() + house.getRectangle().height -1);
		this.w.getSnakes().add(s);
		
		Snake s2 = cc.checkSnakeCollidesWithHouse();
		
		assertNotNull(s + "/" + house, s2);
		assertEquals(s, s2);
	}

	

	@Test
	public void testRemoveArrowButtonWhenSnakeCollied_OnlyOneButton() {
		
		res = cc.checkSnakeCollidesWithArrowButton();
		w.snakeCollidesWithArrowBlock(res);
		
		assertEquals(0, w.getArrowButtonList().size());
		assertFalse(w.getArrowButtonList().contains(res));
	}
	
	@Test
	public void testRemoveArrowButtonWhenSnakeCollied_MoreThanOneButton() {
		this.arrows.add( new ArrowButton(1, 1, ArrowButton.DOWN));
		
		res = cc.checkSnakeCollidesWithArrowButton();
		w.snakeCollidesWithArrowBlock(res);
		
		assertEquals(1, w.getArrowButtonList().size());
		assertFalse(w.getArrowButtonList().contains(res));
	}
	
	@Test
	public void testsnakechangesDirectionWhenCollides() {
		res = cc.checkSnakeCollidesWithArrowButton();
		w.snakeCollidesWithArrowBlock(res);
		
		assertEquals(1.0f, s.direction.y, 0f);
		assertEquals(0f, s.direction.x, 0f);
	}



	@Test
	public void testSecondSnakeCollides() {
		this.w.getSnakes().clear();
		
		putSnakeInWorldIn(200, 200);
		putSnakeInWorldIn(100,100);
		
		res = cc.checkSnakeCollidesWithArrowButton();

		assertNotNull(res);
		assertEquals(100f, res.getS().getX(), 0.0);
		assertEquals(100f, res.getS().getY(), 0.0);
	}
	

	@Test
	public void testThreeSnakesAndNoneCollided() {
		this.w.getSnakes().clear();
		
		putSnakeInWorldIn(200, 200);
		putSnakeInWorldIn(300,300);
		putSnakeInWorldIn(50,50);
		
		res = cc.checkSnakeCollidesWithArrowButton();

		assertNull(res);
	}

	
	
	@Test
	public void testSnakeIrsNearABlock() {
		this.w.getSnakes().clear();		
		putSnakeInWorldIn(200, 200);
		float d = 36f; // distance is 35'35 rougtly
		Block b = new Block(225, 225);
		
		boolean res = cc.checkSnakesAreNearABlock(this.w.getSnakes(), b, d);
		
		assertTrue(res);
	}

	
	@Test
	public void testSnakeIsNotNearABlock() {
		this.w.getSnakes().clear();		
		putSnakeInWorldIn(200, 200);
		float d = 30f; // distance is 43'84
		Block b = new Block(231, 231);
		
		boolean res = cc.checkSnakesAreNearABlock(this.w.getSnakes(), b, d);
		
		assertFalse(res);

	}

	//---------------------------------------------
	
	private void putSnakeInWorldIn(int i, int j) {
		s = new Snake(i, j);
		w.addSnake(s);
		cc = new CollisionsChecker(w);

		
	}

	
	private House createHouseWithSize32x32InWord() {
		this.w.getSizes().setHouseSize(32, 32);
		this.w.createHouse();
		return this.w.getHouse();
	}


}
