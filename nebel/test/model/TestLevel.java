package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import harness.TestData;

import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.World;
import org.iwt2.nebel.model.level.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.badlogic.gdx.math.Vector3;

public class TestLevel {

	Sizes ss;
	World w;
	Level l;

	@Before
	public void setUp() throws Exception {
		ss = TestData.createSizes320x320();
		w = new World (ss);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLevelLoad_CreatesLevelWithoutSnakes() {
		l = new Level();
		
		assertEquals(0, w.getSnakes().size());
		l.loadLevelIn(w);
		assertEquals(0, w.getSnakes().size());
	}

	@Test
	public void testLevelLoad_CreatesOneSnake() {
		l = Level.createLevel(new Snake(10, 10));
		
		assertEquals(0, w.getSnakes().size());
		l.loadLevelIn(w);
		assertEquals(1, w.getSnakes().size());
	}

	
	@Test
	public void testLevelLoad_Create4Snakes() {
		List<Snake> snakes = new ArrayList<Snake>();
		
		snakes.add(new Snake(10, 10));
		snakes.add(new Snake(20, 20));
		snakes.add(new Snake(30, 30));

		l = Level.createLevel(snakes);

		
		assertEquals(0, w.getSnakes().size());
		l.loadLevelIn(w);
		assertEquals(3, w.getSnakes().size());
			
	}
	
	
	@Test
	public void testAddSnakeIntoLevel() {
		l = new Level();
		
		l.addSnake(new Snake(100, 100));
		
		assertEquals(1, l.getSnakeList().size());
	}

	@Test
	public void testAddBlockIntoLevel() {
		Level l = new Level();
		
		l.addBlock(new Block(100, 100));
		
		assertEquals(1, l.getBlockList().size());
	}

	@Test
	public void testLevelLoad_CreatesLevelWithOneBlock() {
		l = new Level();
		l.addBlock(new Block(100, 100));
		
	
		int blockNum = w.getBlocks().size();
		l.loadLevelIn(w);
		assertEquals((blockNum +1), w.getBlocks().size());
	}


}
