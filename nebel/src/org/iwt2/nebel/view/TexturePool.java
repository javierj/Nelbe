package org.iwt2.nebel.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.iwt2.nebel.model.ArrowButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TexturePool {

	float w, h;
	List<Sprite> blocks;
	//List<Texture> blocks;
	List<Sprite> snakeSprites;

	Texture background;
	Texture statBackgroud;
	Texture exitButton;
	
	BitmapFont font;
	float hdes;
	private Texture house;
	private int snakeSize;
	private int blockSize;

	
	
	static TexturePool pool;
	
	public static TexturePool getInstance() {
		if (pool == null) {
			pool = new TexturePool();
			pool.init();
		}
		return pool;
	}
	
	public void init() {
		w = Gdx.graphics.getWidth(); // 475
		h = Gdx.graphics.getHeight(); // 320
	
		blocks = new ArrayList<Sprite>();

		blocks.add(new Sprite(new Texture(Gdx.files.internal("data/block01.png"))));
		blocks.add(new Sprite(new Texture(Gdx.files.internal("data/block02.png"))));
		//blocks.add(new Sprite(new Texture(Gdx.files.internal("data/block01.png"))));
		//blocks.add(new Sprite(new Texture(Gdx.files.internal("data/block03.png"))));

		snakeSprites = new ArrayList<Sprite>();
		snakeSprites.add(new Sprite(new Texture(Gdx.files.internal("data/snake01_32.png"))));
		snakeSprites.add(new Sprite(new Texture(Gdx.files.internal("data/snake02_32.png"))));
		snakeSprites.add(new Sprite(new Texture(Gdx.files.internal("data/snake03_32.png"))));
		snakeSprites.add(new Sprite(new Texture(Gdx.files.internal("data/snake04_32.png"))));

		float snakeScale = 1.0f;
		float blockScale = 1.0f;

		if (w < 640) {
			hdes = h-16;
			snakeScale = 0.5f;
			blockScale = 0.5f;
			this.blockSize = 16;
			this.snakeSize = 16;
		} else {
			hdes = h-32;
			this.snakeSize = 32;
			this.blockSize = 32;
		}
		for (Sprite s: this.snakeSprites) { 
			s.setScale(snakeScale);
			s.setOrigin(0f, 0f);
		}
		for (Sprite s: blocks) {
			s.setScale(blockScale);
			s.setOrigin(0f, 0f);
		}
		
/*
	if (w < 640) {
		this.load16x16Images();
	} else {
		this.load32x32Images();
		System.out.println("TexturePool. "+ w + ", " + h + ". Loading 32x32.");
	}
	*/
	if (w > 512) {
		background = new Texture(Gdx.files.internal("data/background.jpg"));
		
	} else {
		background = new Texture(Gdx.files.internal("data/background512.jpg"));
		
	}


	//font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"), Gdx.files.internal("fonts/default.png"), false);
	font = new BitmapFont(Gdx.files.internal("fonts/font16.fnt"), Gdx.files.internal("fonts/font16.png"), false);

		statBackgroud = new Texture(Gdx.files.internal("data/statsBackground.jpg"));
		//hdes = h-this.blocks.get(0).getHeight();
	
		house = new Texture(Gdx.files.internal("data/house.png"));
		exitButton = new Texture(Gdx.files.internal("data/exitButton.png"));


	}
/*
	private void load16x16Images() {
		blocks.add(new Texture(Gdx.files.internal("data/bloque01.png")));
		blocks.add(new Texture(Gdx.files.internal("data/bloque02.png")));

		
	}

	private void load32x32Images() {
		blocks.add(new Texture(Gdx.files.internal("data/block01.png")));
		blocks.add(new Texture(Gdx.files.internal("data/block02.png")));

		
	}

*/
	public void dispose() {
		background.dispose();
		font.dispose();
		this.statBackgroud.dispose();
		house.dispose();
		exitButton.dispose();
		//System.out.println("TexturePool::dispose - Called. not disposing textures in sprites :(");
	}
	
	public Texture getBackground() {
		return this.background;
	}

	public BitmapFont getFont() {
		return this.font;
	}

	public Texture getStatBackground() {
		return this.statBackgroud;
	}

	//public List<Texture> getBlocks() {
	public List<Sprite> getBlocks() {
		return this.blocks;
	}

	public float getHDes() {
		return this.hdes;
	}

	public List<Sprite> getSnakeSprites() {
		return this.snakeSprites;
	}

	public Texture getHouseTexture() {
		return this.house;
	}

	public Texture getExitButton() {
		return exitButton;
	}

	public int getSnakeSize() {
		return this.snakeSize;
	}

	public int getBlockSize() {
		return this.blockSize;
	}

}
