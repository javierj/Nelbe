package org.iwt2.nebel.controller.collisions;

import java.util.List;

import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.ParSnakeArrowButton;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.World;
import org.iwt2.nebel.model.Block;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * This class could be static adding world to the parameters of all methods.
 * @author Javier
 *
 */
public class CollisionsChecker {

	private World world;
	//Snake s;
	
	public CollisionsChecker(World w) {
		this.world = w;
		//s = this.world.getSnakes().get(0); //this.world.getSnake();
	}

	public ParSnakeArrowButton checkSnakeCollidesWithArrowButton() {
		
		for (Snake snake: world.getSnakes()) {
			for (ArrowButton ab: world.getArrowButtonList()) {
				if (colideWithSnake(snake, ab.getRectangle())) {
				//System.out.println("CollisionsChecker - Snake: " + s.getX() + ", " + s.getY() + " / Button:" + ab.getX() + ", " + ab.getY());
				
					return new ParSnakeArrowButton(snake, ab);
				}
			}
		}
		return null;
	}


	private static boolean colideWithSnake(Snake snake, Rectangle rectangle) {
		//return Intersector.overlapCircleRectangle(snake.getBounds(), rectangle);
		return Intersector.intersectRectangles(snake.getBounds(), rectangle);
		
	}

	
	public Snake checkSnakeCollidesWithBlock() {
		for (Snake snake: world.getSnakes()) {

		for (Block b: this.world.getBlocks()) {
			if (colideWithSnake(snake, b.getRectangle())) {
				//System.out.println("CollisionsChecker - Snake: " + snake.getX() + ", " + snake.getY() 
				//		+ " / rectangle:" + b.getX() + ", " + b.getY());
				return snake;
			}
		}
		}
		return null;
	}


	public Snake checkSnakeCollidesWithHouse() {
		Block house = this.world.getHouse();
		if ( house == null)
			return null;

		for (Snake snake: world.getSnakes()) {
			if ( this.colideWithSnake(snake, this.world.getHouse().getRectangle()) ) {
				//System.out.println("CollisionsChecker - Snake: " + snake.getX() + ", " + snake.getY() 
				//			+ " / house:" + house.getX() + ", " + house.getY());

				return snake;
			}
		}
		return null;

	}

	/**
	 * Checks if the rectangle collides with snakes, houses, blocks and arrowbuttons
	 * @param r
	 * @return
	 */
	public boolean rectangleCollidesWithAnyInTheWorld(Rectangle r) {
		for (Snake snake: world.getSnakes()) {
			if (colideWithSnake(snake, r))
				return true;
		}
		for (Block b: this.world.getBlocks()) {
			if (Intersector.intersectRectangles(b.getRectangle(), r))
				return true;
		}
		for (ArrowButton ab: this.world.getArrowButtonList()) {
			if (Intersector.intersectRectangles(ab.getRectangle(), r))
				return true;
		}
		if (this.world.getHouse() != null) {
			if (Intersector.intersectRectangles(this.world.getHouse().getRectangle(), r))
				return true;
		}
		
		return false;
	}

	/**
	 * Not in use, but test cases
	 * Stateless
	 * @param snakes
	 * @param b
	 * @param i
	 * @return
	 */
	public static boolean checkSnakesAreNearABlock(List<Snake> snakes, Block b, float dst) {
		//Vector vs;
		Vector2 vb = new Vector2(b.getX(), b.getY());
		for (Snake s: snakes) {
			//System.out.println(s+ " - " + b + ": " + vb.dst(s.getX(), s.getY()));
			if (vb.dst(s.getX(), s.getY()) < dst)
				return true;
		}
		return false;
	}
	
	public static boolean isSnakeCollidesWithBlock(List<Snake> l, Block b) {
		for (Snake snake: l) {

			if (colideWithSnake(snake, b.getRectangle())) {
				//System.out.println("CollisionsChecker - Snake: " + snake.getX() + ", " + snake.getY() 
				//		+ " / rectangle:" + b.getX() + ", " + b.getY());
				return true;
			}
		}
		return false;
	}

	

}
