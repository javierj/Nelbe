package model;

import static org.junit.Assert.*;
import harness.TestData;

import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.level.Level;
import org.iwt2.nebel.model.level.LevelPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLevelPool {

	LevelPool lpOneLevel;
	
	@Before
	public void setUp() throws Exception {
		Sizes.ss = TestData.createSizes320x320();
		
		lpOneLevel = new LevelPool();
		lpOneLevel.getLevelList(LevelPool.Dificulty.low).clear();
		lpOneLevel.getLevelList(LevelPool.Dificulty.low).add(Level.createLevel(new Snake(10, 10)));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFirstLevel() {
		assertNotNull(Sizes.getInstance().getGameZoneRectangle());
		
		Level l = this.lpOneLevel.getNextLevel(LevelPool.Dificulty.low);
		
		assertNotNull(l);
	}

	
	@Test
	public void testGetNextLevel_NoMoreLevels() {
		Level l = this.lpOneLevel.getNextLevel(LevelPool.Dificulty.low);
		l = this.lpOneLevel.getNextLevel(LevelPool.Dificulty.low);
		
		assertNull(l);
	}
	
	@Test
	public void testCreateLowLevel() {
		LevelPool lp = new LevelPool();
		
		//lp.createLowLevels();
		
		assertFalse(lp.getLevelList(LevelPool.Dificulty.low).isEmpty());
		
		Level l = lp.getLevelList(LevelPool.Dificulty.low).get(0);
		assertEquals(1, l.getSnakeList().size());
		
	}

	
	@Test
	public void testCreateLowLevel_HasNextLevel() {
		LevelPool lp = new LevelPool();
		
		
		Level l = lp.getNextLevel(LevelPool.Dificulty.low);
		assertNotNull(l);
	}

	@Test
	public void testCreateLowLevel_HasThreeNextLevel() {
		LevelPool lp = new LevelPool();
		
		
		Level l = lp.getNextLevel(LevelPool.Dificulty.low);
		l = lp.getNextLevel(LevelPool.Dificulty.low);
		l = lp.getNextLevel(LevelPool.Dificulty.low);
		assertNotNull(l);
	}

	
	@Test
	public void testCreateLowLevel_ThirdLevelHasTwoSnakes() {
		LevelPool lp = new LevelPool();
		
		//lp.createLowLevels();
		
		assertFalse(lp.getLevelList(LevelPool.Dificulty.low).isEmpty());
		
		Level l = lp.getLevelList(LevelPool.Dificulty.low).get(2);
		assertEquals(2, l.getSnakeList().size());
		
	}

	@Test
	public void testCreateLowLevel_FifthLevelHasThreeSnakes() {
		LevelPool lp = new LevelPool();
		
		//lp.createLowLevels();
		
		assertFalse(lp.getLevelList(LevelPool.Dificulty.low).isEmpty());
		
		Level l = lp.getLevelList(LevelPool.Dificulty.low).get(4);
		assertEquals(3, l.getSnakeList().size());
		
	}

}
