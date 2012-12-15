package org.iwt2.nebel.controller;

import java.util.List;

import org.iwt2.nebel.NebelGame;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.Statistics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * Hideen Credits screen
 * @author Javier
 *
 */
public class CreditsScreen  extends AbstractScreen {

	List<Block> background;
	float time = 0;
	Sizes ss;
	
	public CreditsScreen(NebelGame game) {
		super(game);
		
		this.screen = game.getPhysicScreen();
		
		ss = game.getWorld().getSizes();
		this.background = ss.getBlocksForColumnsInMenuScreen();		

	}

	@Override
	public void show () {
		//this.game.getSound().playMusic();
		time = 0;
	}

	@Override
	public void dispose () {
		//System.out.println("StatisticsScreen - Nothing to dispose.");
	}

	@Override
	public void render(float delta) {
		this.screen.drawStatsBackgroundAndClearScreen(this.background);
		//this.screen.drawListOfBlocks(); -- Called from teh previous one
		
		
		int x = ss.getXForBeginningText();
		int y;
		int yinc = 25;
		
		if (ss.getWidth() <= 400 ) {
			x -= 15;
		}
		if (this.ss.getHeight() >= 320) {
			y = 10;
			 yinc = 25;

		} else {
			y = 2;
			yinc = 23;
		}
		
		if (ss.getWidth() > 320) {
		
		// draw credits
		this.screen.drawText(x, y, "Nelbe is for Belen");
		y += yinc;
		this.screen.drawText(x, y, "By Javier J. Gutierrez");
		y += yinc;
		this.screen.drawText(x, y, "IWT2, Sevilla, Spain");
		y += yinc;
		this.screen.drawText(x, y, "In Android and LibGDX");
		y += yinc;
		this.screen.drawText(x, y, "Free sounds used.");
		y += yinc;
		this.screen.drawText(x, y, "Get the source code and");
		y += yinc;
		this.screen.drawText(x, y, "more info in javierj's");
		y += yinc;
		this.screen.drawText(x, y, "github");
		y += yinc;
		
			if (ss.getWidth() >= 640) {
				y += yinc;
				this.screen.drawText(x, y, "Tested and improved by:");
				y += yinc;
				this.screen.drawText(x, y, "Ricardo Padiel, Juan M.");
				y += yinc;
				this.screen.drawText(x, y, "Martin, Javi Aguadero, Javi ");
				y += yinc;
				this.screen.drawText(x, y,"Delgado");
				y += yinc;
				
			}
		} else {
			x += 35;
			// draw credits
			this.screen.drawText(x, y, "Nelbe is for");
			y += yinc;
			this.screen.drawText(x, y, "Belen");
			y += yinc;
			this.screen.drawText(x, y, "By Javier IWT2");
			y += yinc;
			this.screen.drawText(x, y, "Android + LibGDX");
			y += yinc;
			this.screen.drawText(x, y, "Get the code in");
			y += yinc;
			this.screen.drawText(x, y, "javierj's github");
		}
		
		
		
		this.screen.closeGraph();
		
		time += delta;
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
				game.goToIntroScreen();
			}
		}
	}
	


}
