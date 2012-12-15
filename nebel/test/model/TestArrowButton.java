package model;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.iwt2.nebel.controller.input.GameInputProcessor;
import org.iwt2.nebel.model.ArrowButton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestArrowButton {

	ArrowButton b;
	
	@Before
	public void setUp() throws Exception {
		ArrowButton.XSIZE = 32;
		ArrowButton.YSIZE = 32;
		b  = new ArrowButton(100, 100, ArrowButton.RIGHT);
	}

	@After
	public void tearDown() throws Exception {
	}


	
	@Test
	public void copyArrowButton() {
		int x = 200;
		int y = 200;
		ArrowButton b2 = b.copy(x, y);
		
		assertEquals(x, b2.getX(), 0.0);
		assertEquals(y, b2.getY(), 0.0);
		assertEquals(b.getArrowDirection(), b2.getArrowDirection());
		assertNotSame(b, b2);
	}
	
	
	@Test
	public void pointOutJustInTheLowerBorder() {
				
		// 100, 100 is out
		boolean res = b.containsPoint(100, 100);
		assertFalse(res);
	}

	@Test
	public void userTouchJustInThehigherBorderOfABotton() {
		
		// 131, 131 is in
		boolean res = b.containsPoint(132, 132);
		assertFalse(res);
	
	}

	
	@Test
	public void userTouchFirstPointInsideABotton() {
		boolean res = b.containsPoint(b.getX()+1, b.getY()+1);
		assertTrue(res);	
	}

	@Test
	public void equalsSameObject() {
		assertTrue(b.equals(b));	
	}

	@Test
	public void equalsDiferentObjectsWithSameCoordinates() {
		ArrowButton b2  = new ArrowButton(100, 100, ArrowButton.RIGHT);
		assertTrue(b.equals(b2));	
	}

}
