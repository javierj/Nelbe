
package org.iwt2.nebel.controller;


import org.iwt2.nebel.NebelGame;
import org.iwt2.nebel.model.World;
import org.iwt2.nebel.view.PhysicScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {
	NebelGame game;
	World world;
	PhysicScreen screen;

	public AbstractScreen (NebelGame game) {
		this.game = game;
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void show () {
	}

	@Override
	public void hide () {
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
		System.out.println("AbstractScree - Someone didn't implemented its dispose method.");
	}
}
