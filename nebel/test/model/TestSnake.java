package model;

import static org.junit.Assert.*;
import org.iwt2.nebel.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSnake {

	Snake snake;
	
	@Before
	public void setUp() throws Exception {
		snake = new Snake(100, 100);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testDefaultSnakeDirection() {
		assertEquals(0f, snake.direction.y, 0f);
		assertEquals(1f, snake.direction.x, 0f);

	}

	@Test
	public void testSnakeChangesDirectionDown() {
		snake.changeDirection(ArrowButton.DOWN);
		
		assertEquals(1, snake.direction.y, 0f);
		assertEquals(0, snake.direction.x, 0f);
	}

	@Test
	public void testSnakeChangesDirectionUp() {
		snake.changeDirection(ArrowButton.UP);
		
		assertEquals(-1, snake.direction.y, 0f);
		assertEquals(0, snake.direction.x, 0f);
	}
	
	@Test
	public void testSnakeUpdate_CheckVelocityX() {
		snake.setVelocity(10f);
		float oldX = snake.getX();
		float oldY = snake.getY();
		
		snake.update(1f);
		
		assertEquals(oldY, snake.getY(), 0.0f);
		assertEquals(oldX+10f, snake.getX(), 0.0f);
		
		
	}

	
	@Test
	public void testSnakeUpdate_ChangeDirectionAndCheckVelocityY() {
		snake.changeDirection(ArrowButton.UP);
		snake.setVelocity(10f);
		float oldX = snake.getX();
		float oldY = snake.getY();
		
		snake.update(1f);
		
		assertEquals(oldY-10f, snake.getY(), 0.0f);
		assertEquals(oldX, snake.getX(), 0.0f);
		
		
	}
	
	@Test
	public void testSnakeEquals() {
		Snake s2 = new Snake(this.snake.getX(), this.snake.getY());
		
		assertTrue(this.snake.equals(s2));
		assertTrue(s2.equals(snake));
	}


}
