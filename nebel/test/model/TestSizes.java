package model;

import static org.junit.Assert.*;

import java.util.List;

import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.GameZone;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.SnakeInitialPosition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Sizes knows the pixel size of the screen and the grafic elements
 * and decide the size of the zone of the csreen.
 * 
 * Refactor test cases.
 * 
 * @author Javier
 *
 */
public class TestSizes {
	
	Sizes s;
	Rectangle r;
	
	@Before
	public void setUp() throws Exception {
		s = new Sizes();
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	@Test
	public void testCalculateButtonZone_LittleScreen() {
		// Litlle screen is 300 or less
		int x = 320;
		s.setWithAndHeight(x, 200);
		s.setBlockSize(16, 16);
		
		int div = ((x / 16 ) * 2);
		int mod = (x % 16);
		float w =  (16*2) + mod;
		
		r = s.getButtonZoneRectangle();
		
		assertEquals(0.0, r.x, 0.0);
		assertEquals(0.0, r.y, 0.0);
		assertEquals(w, r.width, 0.0);
		assertEquals("r.withd = " + r.width, mod, (r.width % 16), 0.0);

		assertEquals(200f, r.height, 0.0);
	}
*/
	@Test
	public void testCalculateButtonZone_Width320Block16() {
		// Litlle screen is 300 or less
		int x = 320;
		s.setWithAndHeight(x, 200);
		s.setBlockAndArrowButtonSize(16, 16);
		
		float wExpected = (16*3) + (x % 16);
		
		r = s.getButtonZoneRectangle();
		
		assertEquals(0.0, r.x, 0.0);
		assertEquals(0.0, r.y, 0.0);
		assertEquals(wExpected, r.width, 0.0);
		//assertEquals("r.withd = " + r.width, mod, (r.width % 16), 0.0);

		assertEquals(200f, r.height, 0.0);
	}

	@Test
	public void testCalculateButtonZone_Widht800Block32() {
		int x = 800;
		s.setWithAndHeight(x, 200);
		s.setBlockAndArrowButtonSize(32, 32);
		
		float wExpected = (32*3) + (x % 32);
		
		r = s.getButtonZoneRectangle();
		
		assertEquals(0.0, r.x, 0.0);
		assertEquals(0.0, r.y, 0.0);
		assertEquals(wExpected, r.width, 0.0);
		//assertEquals("r.withd = " + r.width, mod, (r.width % 16), 0.0);

		assertEquals(200f, r.height, 0.0);
	}
	
	@Test
	public void testCalculateGameZone_Width800Block32() {
		int x = 800;
		s.setWithAndHeight(x, 200);
		s.setBlockAndArrowButtonSize(32, 32);
		
		float wExpected = x - ((32*3) + (x % 32));
		
		r = s.getGameZoneRectangle();
		
		assertEquals((32*3) + (x % 32), r.x, 0.0);
		assertEquals(0.0, r.y, 0.0);
		assertEquals(wExpected, r.width, 0.0);
		assertEquals(0.0, (r.width % 32), 0.0);

		assertEquals(200f, r.height, 0.0);
		
	}

	
	@Test
	public void testCalculateGameZone_LittleScreen() {
		// Litlle screen is 300 or less
		s.setWithAndHeight(300, 200);
		s.setBlockAndArrowButtonSize(16, 16);
		
		r = s.getGameZoneRectangle();
		
		// X, Y
		assertEquals(s.getButtonZoneRectangle().width, r.x, 0.0);
		assertEquals(0.0, r.y, 0.0);
		
		assertEquals((300 - s.getButtonZoneRectangle().width), r.width, 0.0);
		
		assertEquals(0, r.width % 16, 0.0);
		assertEquals(300, (r.width+s.getButtonZoneRectangle().width), 0.0);
	}
	
	@Test
	public void testNumerOfBlocksInWidhtForX300() {
		s.setWithAndHeight(300, 200);
		s.setBlockAndArrowButtonSize(16, 16);
		
		// 300 % 16 = 12
		
		int expectedBlocks = (300 / 16);
		
		assertEquals(expectedBlocks, s.getBlocksInWidht());		

	}

	@Test
	public void testNumerOfBlocksInGameZoneWidhtForX300() {
		s.setWithAndHeight(300, 200);
		s.setBlockAndArrowButtonSize(16, 16);
		
		// 300 % 16 = 12
		
		int expectedBlocks = (300 / 16)-3;
		
		assertEquals(expectedBlocks, s.getBlocksInGameZoneWidht());		

	}

	
	@Test
	public void testBlocksInGameZoneWidhtForX800() {
		s.setWithAndHeight(800, 200);
		s.setBlockAndArrowButtonSize(32, 32);
		
		// 300 % 16 = 12
		
		int expectedBlocks = (800 / 32)-3;
		
		assertEquals(expectedBlocks, s.getBlocksInGameZoneWidht());		

	}

	
	@Test
	public void coordinates1x1OutOfGameZone300x200() {
		s.setWithAndHeight(16*10, 16*10);
		s.setBlockAndArrowButtonSize(16, 16);
		assertFalse(s.isInGameZone(1, 1));
	}

	@Test
	public void coordinates290x190InGameZone320x200() {
		s.setWithAndHeight(16*10, 16*10);
		s.setBlockAndArrowButtonSize(16, 16);
		assertTrue(s.isInGameZone(16*9, 16*9));
	}
	
	
	@Test
	public void sizeSetsWidhtAndHeightOfArrowButton_16x16() {
		s.setBlockAndArrowButtonSize(16, 16);
		
		assertEquals(16, ArrowButton.XSIZE);
		assertEquals(16, ArrowButton.YSIZE);
	}

	@Test
	public void sizeSetsWidhtAndHeightOfArrowButton_32x32() {
		int v = 32;
		s.setBlockAndArrowButtonSize(v, v);
		
		assertEquals(v, ArrowButton.XSIZE);
		assertEquals(v, ArrowButton.YSIZE);
	}
	
	@Test
	public void setAndGetSizeOfHouse() {
		s.setHouseSize(40, 30);
		Vector2 v = s.getHouseSize();
		
		assertEquals(40, v.x, 0.0);
		assertEquals(30, v.y, 0.0);
	}
	
	@Test
	public void testTeztBlockHasAWidhtOf250() {
		assertEquals(310, s.getTextWight());
	}

	@Test
	public void testGetXIncForCenterText() {
		s.setWithAndHeight(320, 200);
		
		assertEquals(5, s.getXForBeginningText());
	}
	
	@Test
	public void testRandomCoordinatesInGameZone_320x200() {
		s.setWithAndHeight(320, 200);
		Vector2 pos;
		
		for (int i = 1; i < 1000000; i++) {
			 pos = s.getRandomCoordinatesInGameZone();
			 assertTrue(pos.x >= 96);
			 assertTrue(pos.x + "," + pos.y, pos.x <= 304);
			 assertTrue(pos.y >= 16);
			 assertTrue(pos.x + "," + pos.y, pos.y <= 184);
		}
	}

	
	@Test
	public void testRandomCoordinatesInGameZone_800x400() {
		s.setWithAndHeight(800, 400);
		Vector2 pos;
		
		for (int i = 1; i < 1000000; i++) {
			 pos = s.getRandomCoordinatesInGameZone();
			 assertTrue(pos.x >= 96);
			 assertTrue(pos.x <= 736);
			 assertTrue(pos.y >= 32);
			 assertTrue(pos.x + "," + pos.y, pos.y <= 336);
		}
	}

	
	@Test
	public void testExitButtonRectangle_480x320() {
		s.setWithAndHeight(480, 320);
		s.setBlockAndArrowButtonSize(16, 16);
		Rectangle r = s.getExitButtonRectangle();
		
		// space for buttons is 16 x 3
		assertEquals(8, r.x, 0.0);
		// 32 of the botton plus 8
		assertEquals(280, r.y, 0.0);
		
		assertEquals(32, r.height, 0.0);
		assertEquals(32, r.width, 0.0);
	}


	@Test
	public void testExitButtonRectangle_800x400() {
		s.setWithAndHeight(800, 400);
		s.setBlockAndArrowButtonSize(32, 32);
		Rectangle r = s.getExitButtonRectangle();
		
		// space for buttons is 32 x 3
		assertEquals(32, r.x, 0.0);
		// 32 of the botton plus 16
		assertEquals(400 - 32 - 16, r.y, 0.0);
		
		assertEquals(32, r.height, 0.0);
		assertEquals(32, r.width, 0.0);
	}


	@Test
	public void testInitialSnakesDoesNotCollidesWithBordersIn800x600() {
		s = Sizes.getInstance();
		s.setWithAndHeight(800, 400);
		s.setBlockAndArrowButtonSize(32, 32);
		GameZone gz = new GameZone();
		SnakeInitialPosition[] ini = s.getInitialSnakePos();
		
		List<Block> lb = gz.getBlocksFromGameZone();
		int i = 0;
		
		Rectangle r;
		for (Block b: lb) {
			i = 0;
			for (SnakeInitialPosition si: ini) {
				r = b.getRectangle();
				assertFalse("Snake: "+ i + ": " + r + "/ " + si.getPos().x + s.getBlockSizeX() + ", " +  si.getPos().y + s.getBlockSizeY(),
						r.contains(si.getPos().x + s.getBlockSizeX(), si.getPos().y + s.getBlockSizeY()));
				i++;
			}
		}
		
		
		
	}
	

	@Test
	public void testInitialSnakesDoesNotCollidesWithBordersIn640x480() {
		s = Sizes.getInstance();
		s.setWithAndHeight(640, 480);
		s.setBlockAndArrowButtonSize(32, 32);
		GameZone gz = new GameZone();
		SnakeInitialPosition[] ini = s.getInitialSnakePos();
		
		List<Block> lb = gz.getBlocksFromGameZone();
		
		Rectangle r;
		for (Block b: lb) {
			for (SnakeInitialPosition si: ini) {
				r = b.getRectangle();
				assertFalse(r.contains(si.getPos().x + s.getBlockSizeX(), si.getPos().y + s.getBlockSizeY()));
			}
		}
		
		
		
	}

	
	@Test
	public void testInitialSnakesDoesNotCollidesWithBordersIn320x240() {
		s = Sizes.getInstance();
		s.setWithAndHeight(320, 240);
		s.setBlockAndArrowButtonSize(16, 16);
		GameZone gz = new GameZone();
		SnakeInitialPosition[] ini = s.getInitialSnakePos();
		
		List<Block> lb = gz.getBlocksFromGameZone();
		
		Rectangle r;
		for (Block b: lb) {
			int i = 0;
			for (SnakeInitialPosition si: ini) {
				r = b.getRectangle();
				assertFalse("Snake: "+ i + ": " + r + "/ " + si.getPos().x + s.getBlockSizeX() + ", " +  si.getPos().y + s.getBlockSizeY(),
						r.contains(si.getPos().x + s.getBlockSizeX(), si.getPos().y + s.getBlockSizeY()));
				i++;
			}
		}
		
		

		
	}

	
	
	@Test
	public void testInitialSnakesDoesNotCollidesWithBordersIn432x240() {
		s = Sizes.getInstance();
		s.setWithAndHeight(432, 240);
		//s.setBlockAndArrowButtonSize(16, 16);
		GameZone gz = new GameZone();
		SnakeInitialPosition[] ini = s.getInitialSnakePos();
		
		List<Block> lb = gz.getBlocksFromGameZone();
		
		Rectangle r;
		for (Block b: lb) {
			int i = 0;
			for (SnakeInitialPosition si: ini) {
				r = b.getRectangle();
				assertFalse("Snake: "+ i + ": " + r + "/ " + si.getPos().x + s.getBlockSizeX() + ", " +  si.getPos().y + s.getBlockSizeY(),
						r.contains(si.getPos().x + s.getBlockSizeX(), si.getPos().y + s.getBlockSizeY()));
				i++;
			}
		}
	}

	
	@Test
	public void testBlocksInColumnsInStatScreenFor320x240() {
		s = Sizes.getInstance();
		s.setWithAndHeight(320, 240);
		s.setBlockAndArrowButtonSize(16, 16);
		
		List<Block> lb = s.getBlocksForColumnsInStatsScreen();
		
		assertEquals( 0, lb.size(), 0.0);
	}

	@Test
	public void testBlocksInColumnsInStatScreenFor400x240() {
		s = Sizes.getInstance();
		s.setWithAndHeight(400, 240);
		s.setBlockAndArrowButtonSize(16, 16);
		
		List<Block> lb = s.getBlocksForColumnsInStatsScreen();
		
		assertEquals( 30, lb.size());
	}

}
