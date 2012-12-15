package org.iwt2.nebel.controller;

import java.util.ArrayList;
import java.util.List;

import org.iwt2.nebel.NebelGame;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.level.LevelPool;
import org.iwt2.nebel.view.PhysicMenuView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;

public class MenuScreen  extends AbstractScreen {

	List<Block> background;
	float time = 0;
	
	PhysicMenuView menuView;
	
	public MenuScreen(NebelGame game) {
		super(game);
		
		//this.world = game.getWorld();
		//this.screen = game.getPhysicScreen();
		
		menuView = new PhysicMenuView();
		createBlocks();

	}
	
	@Override
	public void dispose() {
		this.menuView.dispose();
	}

	private void createBlocks() {
		
		/*float x = 0;
		float y = 0;*/
		
		menuView.init();

		Sizes ss = Sizes.getInstance();
		this.menuView.fillWirhSizes(ss);
		
		this.background = ss.getBlocksForColumnsInMenuScreen();		
	}

	@Override
	public void show () {
	}


	@Override
	public void render(float delta) {
		this.menuView.drawListOfBlocks(this.background);

		this.menuView.drawMenu();
		List<Rectangle> lr = this.menuView.getListOfButtonBounds();
		if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
			int rectangleIndex = this.buttonPressed(lr);
			/*System.out.println("MenuScreen Touched " 
					+ Gdx.input.getX() + ", " 
					+ Gdx.input.getY() + ":" + rectangleIndex 
					+ " / Exit Button: " + lr.get(3).x + ", "
					+ lr.get(3).y );
			*/
			if (rectangleIndex == -1) {
				return;
			}
			
			if (rectangleIndex == 3) {
				//System.out.println("MenuScreen - Exit pressed.");
				Gdx.app.exit();
				return;
				
			}
				this.game.setInSession("dificulty", LevelPool.Dificulty.values()[rectangleIndex]);
				//System.out.println("MenuScreen - No infinite level.");
				//this.game.setInSession("dificulty", LevelPool.Dificulty.values()[0]);
				this.game.getSound().stopMusic();
				this.game.goToGameScreen();
			
		}
	}

	
	private int buttonPressed(List<Rectangle> buttonBounds) {
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			Rectangle r;
			for (int i = 0; i < buttonBounds.size(); i++) {
				r = buttonBounds.get(i);
				if (r.contains(x, y))
					return i;
			}
		

		return -1;
	}
	

}
