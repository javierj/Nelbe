package org.iwt2.nebel.controller;

import java.util.ArrayList;
import java.util.List;

import org.iwt2.nebel.NebelGame;
import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.Statistics;
import org.iwt2.nebel.view.PhysicMenuView;
import org.iwt2.nebel.view.PhysicSplashView;
import org.iwt2.nebel.view.SoundSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class SplashScreen  extends AbstractScreen {

	List<Snake> snakes;
	float time = 0;
	Sizes ss;
	PhysicSplashView splashView;
	float xOrigin;
	float xLimit;
	SoundSystem soundSystem;
	float y;


	
	public SplashScreen(NebelGame game) {
		super(game);
		
		soundSystem = this.game.getSound();
		
		ss = Sizes.getInstance();
		splashView = new PhysicSplashView();
		splashView.init();
		splashView.fill(ss);

		snakes = new ArrayList<Snake>();
		//float x = -40;
		
		this.xOrigin = -50f;
		//this.xLimit = 350f;
		this.xLimit = (ss.getWidth() / 4) * 3;
		int snakeLimit = 8;
		this.y = (ss.getHeight() / 3) * 2;
		
		Snake s;
		for (int i = 0; i < snakeLimit; i++) {
			s = new Snake((xOrigin*i), y);
			s.changeDirection(ArrowButton.RIGHT);
			s.setVelocity(40f);
			snakes.add(s);
		}


	}

	@Override
	public void show() {
		this.soundSystem.playMusic();
	}
	
	@Override
	public void render(float delta) {
		this.splashView.drawBackground();
		this.splashView.drawSnakes(this.snakes);
		this.splashView.drawHouseIn(xLimit + 15, y);
		
		for (Snake s: this.snakes) {
			s.update(delta);
			if (s.getX() >= xLimit) {
				s.setXY(xOrigin, y);
				this.soundSystem.playSnakeInHouseSound();
			}
		}
		
		time += delta;
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
				if (ss.unsuportedScreen()) {
					game.goToWarningScreen();
				} else {
					game.goToMenuScreen();
				}
			}
		}
	}
	

	@Override
	public void dispose () {
		this.splashView.dispose();
	}

}
