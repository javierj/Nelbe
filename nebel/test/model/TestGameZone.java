package model;

import static org.junit.Assert.*;

import java.util.List;

import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.GameZone;
import org.iwt2.nebel.model.Sizes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Rectangle;

public class TestGameZone {

	int width;
	int height;
	Rectangle rGZ;
	GameZone gz;
	List<Block> blocks ;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	private Sizes createSizesObject(int width, int height) {
		Sizes s = Sizes.getInstance();
		s.setWithAndHeight(width, height);
		s.setBlockAndArrowButtonSize(16, 16);
		rGZ = s.getGameZoneRectangle();
		gz = new GameZone();

		return s;
	}

/* Impossible. A GameZone must have at least two rows of blocks
	@Test
	public void testCreateGameZone_CheckListOf1x1Blocks() {
		width = 16 *10;
		height = 16 * 1;
		
		Sizes s = createSizesObject(width, height);
		
		assertEquals(8,  (int)(rGZ.getWidth() / s.getBlockSizeX()));
		assertEquals(1,  (int)(rGZ.getHeight() / s.getBlockSizeY()));
		
		blocks = gz.getBlocksFromGameZone();
		
		assertEquals(8, blocks.size());
	}
*/

	@Test
	public void testCreateGameZone_CheckListOf1x2Blocks() {
		width = 16 *10;
		height = 16 * 2;
		
		Sizes s = createSizesObject(width, height);
		
		assertEquals(7,  (int)(s.getGameZoneRectangle().getWidth() / s.getBlockSizeX()));
		blocks = gz.getBlocksFromGameZone();
		
		assertEquals(7 + 7, blocks.size());
	}

	
	@Test
	public void testCreateGameZone_CheckListOf10x10Blocks() {
		width = 16 *10;
		height = 16 * 10;
		
		createSizesObject(width, height);
		blocks = gz.getBlocksFromGameZone();
		
		assertEquals(7 +7 + 10 + 10, blocks.size());
		

	}

	
	@Test
	public void testCreateGameZone_CheckSizeOfBlocks() {
		width = 16 *10;
		height = 16 * 10;
		
		createSizesObject(width, height);
		blocks = gz.getBlocksFromGameZone();
		
		for (Block b: blocks) {
			assertEquals(16f, b.getRectangle().height, 0.0);
			assertEquals(16f, b.getRectangle().width, 0.0);
		}

	}

}
