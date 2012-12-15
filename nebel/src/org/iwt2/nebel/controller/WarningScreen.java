package org.iwt2.nebel.controller;

import java.util.List;

import org.iwt2.nebel.NebelGame;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.Statistics;
import org.iwt2.nebel.view.PhysicMenuView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * Hideen Credits screen
 * @author Javier
 *
 */
public class WarningScreen  extends AbstractScreen {

	List<Block> background;
	float time = 0;
	Sizes ss;
	
PhysicMenuView screen;
	
		
		
	public WarningScreen(NebelGame game) {
		super(game);
		
		
		ss = Sizes.getInstance();
		this.background = ss.getBlocksForColumnsInMenuScreen();		
		screen = new PhysicMenuView();
		screen.init();
	}

	@Override
	public void show () {
		time = 0;
	}

	@Override
	public void dispose () {
		this.screen.dispose();
	}

	@Override
	public void render(float delta) {
		this.screen.drawStatsBackgroundAndClearScreen(this.background);
		//this.screen.drawListOfBlocks(); -- Called from teh previous one
		
		
		int x = 128;
		int y = 20;
		int yinc = 25;
		
		this.screen.drawText(x, y, "This version of Nelbe has been designed for smaller screens,");
		y += yinc;
		this.screen.drawText(x, y, "as you can see here :(");
		y += yinc;


		this.screen.drawText(x, y, "You can play the game but it might be difficult due the touchable");
		y += yinc;
		this.screen.drawText(x, y, "elements will be very small.");
		y += yinc;
		this.screen.drawText(x, y, "We are so sorry and we are working in a new and improved version.");
		y += yinc;
		this.screen.drawText(x, y, "So, stay tuned for BIG Nelbes in 1024 and beyond.");
		y += yinc;
		this.screen.drawText(x, y, "Thank you !!!");
		y += yinc;
		this.screen.drawText(x, y, "Remember, Nelbe is open source, get new versions and source code ");
		y += yinc;
		this.screen.drawText(x, y, "in github.com/javierj");
		y += yinc;
		
		
		this.screen.closeGraph();
		
		time += delta;
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
				game.goToMenuScreen();
			}
		}
	}
	


}
