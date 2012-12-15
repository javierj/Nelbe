package contoller;

import static org.junit.Assert.*;

import harness.TestData;

import java.util.Arrays;

import org.iwt2.nebel.controller.NebelGameScreen;
import org.iwt2.nebel.controller.input.GameInputProcessor;
import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.OpenWorld;
import org.iwt2.nebel.model.World;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGameInputProcessor {

	World w ;
	GameInputProcessor input;

	@Before
	public void setUp() throws Exception {
		w  = new OpenWorld(TestData.createWorld320x320());
		ArrowButton b = new ArrowButton(100, 100, ArrowButton.RIGHT);
		
		w.getControlArrowButtons().clear();
		w.getControlArrowButtons().add(b);
		
		input = new GameInputProcessor(w);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 		//input.touchDown(x, y, null, null);
		
		// Llamoa una clase que controle la x e y ?
		// Llamo a world para que marque el botón ?
		// Eso e smás del controlador

	 */
	
	/*
	@Test
	public void controlArrowButtonRemainsMarkedIfPlayerClicksInOtherPart() {
		ArrowButton b = w.getControlArrowButtons().get(0);
		int x = (int)b.getX();
		int y = (int)b.getY();
		input.touchDown(x+1, y+1, 0, 0);
		
		assertEquals(b, input.getMarkedArrow());
		input.touchDown(x, y, 0, 0);
		
		assertNotNull(b);
		assertEquals(b, input.getMarkedArrow());
	}*/
	
	
	
	/*
	public World createMock extends World {
		public WorldMock() {
			super(null);
			ArrowButton b = new ArrowButton(100, 100, ArrowButton.RIGHT);
			
			this.controlArrowButtons.clear();
			this.controlArrowButtons.add(b);
		}
	}*/

	
	//-----------------------------------------------
	
	
	
	//ip.touchDown((int)button.getX()+1, (int)button.getY()+1, 0, 0);

}
