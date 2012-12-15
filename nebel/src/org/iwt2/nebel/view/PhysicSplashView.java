package org.iwt2.nebel.view;

import java.util.List;

import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PhysicSplashView extends AbstractView {

	TexturePool pool;
	float hdes;
	Texture background;
	List<Sprite> snakeSprites;
	private Texture house;

	
	public PhysicSplashView() {
		super();
	}
	
	public void init() {
		w = Gdx.graphics.getWidth(); // 475
		h = Gdx.graphics.getHeight(); // 320
		
	
		camera = this.setCamera(w, h);
		batch = new SpriteBatch();
		
		this.pool = TexturePool.getInstance();
		//this.blocks = this.pool.getBlocks();
		hdes = this.pool.getHDes();

		snakeSprites = this.pool.getSnakeSprites();
		
		/** 
		if (w < 640) {
			this.load16x16Images();
		} else {
			System.out.println("PhysicMenuView. " + w + ", " + h + ". Loading 32x32.");
			this.load32x32Images();
			//this.load16x16Images();
		}
		this.buttonHeight = this.easyButton.getHeight();
		this.buttonWeight = this.easyButton.getWidth();

		//font = new BitmapFont(Gdx.files.internal("fonts/font16.fnt"), Gdx.files.internal("fonts/font16_magenta.png"), false);
		exitButton = new Texture(Gdx.files.internal("data/exitButton.png"));
	**/
		background = new Texture(Gdx.files.internal("data/mainScreen.jpg"));
		this.house = this.pool.getHouseTexture();

	}

	public void dispose() {
		this.background.dispose();
		this.batch.dispose();
		//System.out.println("PhisycSplashView - Disposed. ");
	}
	
	public void drawBackground() {
		int bHeight = this.background.getHeight();
		int bWidth = this.background.getWidth();

		float desY = 0f;
		float desX = 0f;
		if (h > bHeight) {
			desY = (h - bHeight )/ 2;
		}
		if (bWidth > w) {
			desX = (bWidth - w) /2;
			desX = -desX;
		} else {
			desX = (w - bWidth) /2;
		}
		
		
		this.clearScreen();
		
		camera.update();
		//batch.setProjectionMatrix(camera.invProjectionView);
		batch.setProjectionMatrix(camera.combined);
		batch.disableBlending();
		batch.begin();
		
		batch.draw(this.background, desX, desY);
		
	}

	public void drawSnakes(List<Snake> snakes) {
		//float r;
		//float x;
		//float y;
		Sprite snakeSprite;
		for (Snake snake: snakes) {
			/*
			x = snake.getX() ;
			y = snake.getY() ;
			batch2.draw(this.serpiente, x,  hdes-y);
			*/
			snakeSprite = this.snakeSprites.get((snake.getId() % this.snakeSprites.size()));
			snakeSprite.setX(snake.getX());
			snakeSprite.setY(hdes - snake.getY());
			snakeSprite.draw(batch);
	
		}
	}

	public void drawHouseIn(float i, float j) {
		batch.draw(house, i, hdes - j);
		batch.end();
		
	}

	public void fill(Sizes ss) {
		ss.setWithAndHeight((int)w, (int)h);
		ss.setSnakeSize(this.pool.getSnakeSize(), this.pool.getSnakeSize());
	}


}
