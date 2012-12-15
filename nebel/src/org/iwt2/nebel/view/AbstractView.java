package org.iwt2.nebel.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * No objects are created in this class No need for disposing.
 * @author Javier
 *
 */
public class AbstractView {

	OrthographicCamera camera;
	SpriteBatch batch;
	float w, h;

	OrthographicCamera setCamera(float w, float h) {
		OrthographicCamera camera = new OrthographicCamera(0, 0);
		camera.setToOrtho(false, w, h);
		return camera;
	}
	
	void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

}
