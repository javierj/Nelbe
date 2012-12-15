package game;

import static org.junit.Assert.*;
import harness.TestData;

import org.iwt2.nebel.NebelGame;
import org.iwt2.nebel.controller.NebelGameScreen;
import org.iwt2.nebel.controller.input.GameInputProcessor;
import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Snake;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This class shpuld be deeply refactored
 * Too low level details to be a tets of the whole game.
 * 
 * @author Javier
 *
 */
public class TestGame {

	NebelGameScreen screen;
	GameInputProcessor ip;

	
	@Before
	public void setUp() throws Exception {
		ArrowButton.XSIZE = 16;
		ArrowButton.YSIZE = 20;

		screen = new NebelGameScreenWithoutView(null);
		screen.show();
		ip = (GameInputProcessor)screen.getInputProcessor();

	}
	
	public void touch(int x, int y) {
		ip.touchDown(x, y, 0, 0);

	}

	@After
	public void tearDown() throws Exception {
		//screen.dispose();
	}

/*	
	@Test
	public void testMarkArrow() {
		ArrowButton button = screen.getWorld().getControlArrowButtons().get(0);
		
		touch((int)button.getX()+1, (int)button.getY()+1);

		assertNotNull(ip.getMarkedArrow());
		assertEquals(button, ip.getMarkedArrow());		
	}
*/	
	
	//@Test
	public void addANewArrowInTheGame() {
		ArrowButton button = screen.getWorld().getControlArrowButtons().get(3);
		
		touch((int)button.getX()+1, (int)button.getY()+1);
		
		int xGame = 200;
		int yGame = 200;
		touch(xGame, yGame);
		
		assertEquals(1, screen.getWorld().getArrowButtonList().size());
		ArrowButton newButton = screen.getWorld().getArrowButtonList().get(0);
		assertEquals(button.getArrowDirection(), newButton.getArrowDirection() );
	}
	
/*
	@Test
	public void doNotAddANewArrowButtonIfNoControlArrowButtonIsSelected() {
		
		int xGame = 200;
		int yGame = 200;
		touch(xGame, yGame);
		
		assertEquals(0, screen.getWorld().getArrowButtonList().size());
	}
*/
	/*
	@Test
	public void executeACollision() {
		Snake s = this.screen.getWorld().getSnakes().get(0); //this.screen.getWorld().getSnake();
		ArrowButton ab = new ArrowButton(s.getX() + 5, s.getY() + 5, ArrowButton.UP);
		this.screen.getWorld().getArrowButtonList().add(ab);
		int abs = this.screen.getWorld().getArrowButtonList().size();
		this.screen.getWorld().createHouse();
		
		//this.screen.render(1.0f);
		this.screen.checkCollisions();
		
		assertEquals(abs-1, this.screen.getWorld().getArrowButtonList().size());
	}
	*/
	//--------------------------
	
	class NebelGameScreenWithoutView extends NebelGameScreen {
		
		public NebelGameScreenWithoutView(NebelGame game) {
			super(null);
		}
		
		@Override
		public Sizes createView() {
			return null;
		}
		
		@Override
		public void createModel(Sizes ss) {
			super.createModel(TestData.createSizes320x320());
		}
	
		@Override
		public void linkInputProcesor() {
		}
	
		@Override
		public void setWorldIntoView() {
			
		}

	}

}
