package org.iwt2.nebel.controller.input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.World;

import com.badlogic.gdx.InputProcessor;

/**
 * Todo: cambiar a input multiplexer.
 * 
 */
public class GameInputProcessor implements InputProcessor {

	public World w;
	//private ArrowButton markedArrowButton;
	
	TouchListener touchListener;

	public GameInputProcessor(World w) {
		this.w = w;
		//markedArrowButton = null;
	}

	
	public void registerTouchListener(TouchListener touchListener) {
		this.touchListener = touchListener;
	}

	//-- Interface ----------------------------
	@Override
	public boolean keyDown (int keycode) {
		return false;
	}

	@Override
	public boolean keyUp (int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped (char character) {
		return false;
	}

	/**
	 * Overrided
	 * Search for a control arrow button in the coordinates
	 * and stores it
	 */
	@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
		/*ArrowButton ab = this.w.getControlArrowButtonWhichContainsPoint(x, y);
		if (ab != null) {
			//System.out.println("Marked button in " + x + ", "+ y);
			this.markedArrowButton = ab;
			return false;
		}
		
		if (this.markedArrowButton != null) {
			//System.out.println("Placing button in " + x + ", "+ y);
			
			this.w.addButtonIn(this.markedArrowButton, x, y);
			this.w.getStats().incArrowsPlaced();
		}
		*/
		if (this.touchListener == null) {
			System.out.println("Error. No listener for GameInputProcess::touchdown()");
			return false;
		}
		this.touchListener.touchDown(x, y);
		return false;
	}

	@Override
	public boolean touchUp (int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged (int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean touchMoved (int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
		return false;
	}

}