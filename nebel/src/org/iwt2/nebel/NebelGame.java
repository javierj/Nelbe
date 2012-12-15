package org.iwt2.nebel;

import java.util.HashMap;
import java.util.Map;

import org.iwt2.nebel.controller.AbstractScreen;
import org.iwt2.nebel.controller.CreditsScreen;
import org.iwt2.nebel.controller.MenuScreen;
import org.iwt2.nebel.controller.NebelGameScreen;
import org.iwt2.nebel.controller.SplashScreen;
import org.iwt2.nebel.controller.WarningScreen;

import org.iwt2.nebel.controller.StatisticsScreen;
import org.iwt2.nebel.model.World;
import org.iwt2.nebel.view.PhysicDevice;
import org.iwt2.nebel.view.PhysicScreen;
import org.iwt2.nebel.view.SoundSystem;
import org.iwt2.nebel.view.TexturePool;

import com.badlogic.gdx.Game;

public class NebelGame extends Game  {
	
	/*
	@Override
	public void render() {		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	*/
	
	AbstractScreen intro;
	AbstractScreen menu;
	AbstractScreen game;
	AbstractScreen stats;
	AbstractScreen credits;
	AbstractScreen warning;
	PhysicDevice device;
	//Sizeator sizer;
	World world;
	PhysicScreen screen;
	Map<String, Object> session;
	SoundSystem sound;

	
	@Override
	public void create () {
		// 1. Create PhysicDevice
		device = new PhysicDevice();
		
		session = new HashMap<String, Object>();
		
		sound = new SoundSystem();
		sound.init();
		
		//intro = new IntroScreen(this);
		menu = new MenuScreen(this);
		intro = new SplashScreen(this);
		game = new NebelGameScreen(this);
		
		setScreen(intro);
		
	}

	@Override
	public void dispose() {
		//System.out.println("NebelGame - Dispose. Calling soundsystem");
		this.sound.dispose();
		this.intro.dispose();
		this.menu.dispose();
		this.game.dispose();
		if (stats != null)
			stats.dispose();
		if (credits != null)
			credits.dispose();
		if (warning != null)
			warning.dispose();

		TexturePool.getInstance().dispose();
		
	}

	public void goToIntroScreen() {
		setScreen(intro);
		
	}

	public void goToGameScreen() {	
		setScreen(game);
	}

	public void goToStatisticScreen() {
		if (stats == null) {
			stats = new StatisticsScreen(this);
		}
		setScreen(stats);
		//this.goToCreditsScreen();
	}
	
	public void goToMenuScreen() {
		setScreen(menu);
	}

	public void goToCreditsScreen() {
		if (credits == null) {
			credits = new CreditsScreen(this);
		}
		setScreen(credits);
	}
	
	public void goToWarningScreen() {
		if (this.warning == null)
			this.warning = new WarningScreen(this);
		//System.out.println("Show warning screen.");
		setScreen(this.warning);
			
	}
	
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public PhysicScreen getPhysicScreen() {
		return screen;
	}

	public void setPhysicScreen(PhysicScreen screen) {
		this.screen = screen;
	}

	public void setInSession(String string, Object val) {
		this.session.put(string, val);
	}
	

	public Object getFromSession(String string) {
		Object val = this.session.get(string);
		if (val == null) {
			System.out.println("NebelGame - Warning, no value in sessión for key: " + string);
		}
		return val;
	}

	public SoundSystem getSound() {
		return this.sound;
	}



}
