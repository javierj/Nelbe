package org.iwt2.nebel.controller;

import com.badlogic.gdx.math.Rectangle ;
import java.util.List;

import org.iwt2.nebel.NebelGame;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.Statistics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class StatisticsScreen  extends AbstractScreen {

	List<Block> background;
	float time = 0;
	Sizes ss;
	
	public StatisticsScreen(NebelGame game) {
		super(game);
		
		this.screen = game.getPhysicScreen();

	}

	@Override
	public void show () {
		time = 0;
		this.world = game.getWorld();
		ss = this.world.getSizes();
		this.background = ss.getBlocksForColumnsInStatsScreen();		
		

		this.game.getSound().playMusic();
		
	}

	@Override
	public void dispose () {
		//System.out.println("StatisticsScreen - Nothing to dispose.");
	}

	@Override
	public void render(float delta) {
		this.screen.drawStatsBackgroundAndClearScreen(this.background);
		//this.screen.drawListOfBlocks(); -- Called from teh previous one
		
		Statistics stats = this.world.getStats();
		
		int x = ss.getXForBeginningText();
		int y;
		int yinc = 25;
		
		if (this.ss.getHeight() >= 320) {
			y = 10;
			 yinc = 25;

		} else {
			y = 2;
			yinc = 23;
		}
		 
		// draw statistcs
		this.screen.drawText(x, y, "Stats:");
		y += yinc + yinc;
		x += yinc;
		
		for (String s: stats.getStatsAsString()) {
			this.screen.drawText(x, y, s);
			y += yinc;
		}
		
		this.screen.closeGraph();
		
		time += delta;
		//Rectangle r = ss.getExitButtonRectangle();
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
				if ((Gdx.input.getX() > ss.getWidth() - 50) && (Gdx.input.getY() > ss.getHeight() - 50)) {
					game.goToCreditsScreen();
				} else {
					game.goToIntroScreen();
				}
			}
		}
	}
	


}
