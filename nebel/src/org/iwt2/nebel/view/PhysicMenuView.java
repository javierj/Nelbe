package org.iwt2.nebel.view;

import java.util.*;

import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class PhysicMenuView extends AbstractView
{
	float hdes;
	
	Texture easyButton, normalButton, infiniteButton, exitButton;
	Rectangle easyBound, normalBound, infiniteBound, exitBound;
	Texture menuBackgroud;
	
	
	float w, h, r;
	int buttonWeight, buttonHeight;
	
	int blockIndex;
	
	//private BitmapFont font;
	
	//List<Texture> blocks;
	List<Sprite> blocks;
	TexturePool pool;

	private int block;
	BitmapFont font;

	
	public PhysicMenuView() {
	}
	
	
	public void init() {
		w = Gdx.graphics.getWidth(); // 475
		h = Gdx.graphics.getHeight(); // 320
		
	
		camera = new OrthographicCamera(0, 0);
		camera.setToOrtho(false, w, h);
		
		batch = new SpriteBatch();
		
		this.pool = TexturePool.getInstance();
		this.blocks = this.pool.getBlocks();
		hdes = this.pool.getHDes();

		
		/** **/
		block = 16;
		if (w < 640) {
			this.load16x16Images();
		} else {
			//System.out.println("PhysicMenuView. " + w + ", " + h + ". Loading 32x32.");
			this.load32x32Images();
			//this.load16x16Images();
			block = 32;
		}
		this.buttonHeight = this.easyButton.getHeight();
		this.buttonWeight = this.easyButton.getWidth();

		menuBackgroud = new Texture(Gdx.files.internal("data/menuBackground.jpg"));
		//font = new BitmapFont(Gdx.files.internal("fonts/font16.fnt"), Gdx.files.internal("fonts/font16_magenta.png"), false);
		//exitButton = new Texture(Gdx.files.internal("data/exitButton.png"));
		exitButton = pool.getExitButton();
		font = pool.getFont();

	}
	
	/**
	 * Disposie all elements create only in this class
	 */
	public void dispose() {
		batch.dispose();
		easyButton.dispose();
		normalButton.dispose();
		infiniteButton.dispose();
		//exitButton.dispose(); -- Not from this class
		this.menuBackgroud.dispose();
		//System.out.println("PhysicMenuScreen::dispose - Disposed");
	}

	
	private void load16x16Images() {
		this.easyButton = new Texture(Gdx.files.internal("data/easyButton_Small.png"));
		this.normalButton = new Texture(Gdx.files.internal("data/normalButton_Small.png"));
		this.infiniteButton = new Texture(Gdx.files.internal("data/infiniteButton_Small.png"));
		r = 50f;
	}

	private void load32x32Images() {
		this.easyButton = new Texture(Gdx.files.internal("data/easyButton_Big.png"));
		this.normalButton = new Texture(Gdx.files.internal("data/normalButton_Big.png"));
		this.infiniteButton = new Texture(Gdx.files.internal("data/infiniteButton_Big.png"));
		r = 100f;
	}


	/**
	 * Code is dierent from Phisic screen
	 * @param blocks2
	 */
	public void drawListOfBlocks(List<Block> blocks2) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//.combined
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		drawBackground();

		drawBlocks(blocks2);
		//batch.end();
	 
	}
	
	private void drawBlocks(List<Block> blocks2) {
		float oldHdes = hdes;
		hdes = h- 32;
		for (Block b: blocks2) {
			//batch.setColor(colors[this.colorIndex]);
			batch.draw(this.blocks.get(this.blockIndex), 
					b.getX(), hdes-b.getY());
			this.blockIndex++;
			if (this.blockIndex == this.blocks.size())
				this.blockIndex = 0;
		}
		
		hdes = oldHdes;
	}

	
	public void drawMenu() {
		//float x = 0;
		float y = this.buttonHeight / 2;
		float x = this.w / 2;
		float dis = 10;
		
		x -= this.buttonWeight / 2;
		
		float tam = (this.buttonHeight * 3) + (dis * 2);
		float dif = h - tam;
		y += (dif / 2);
		

		 //20f; //this.buttonHeight;//(dif/2); //50f;
		
		batch.draw(this.easyButton, x, hdes-y);
		this.easyBound = new Rectangle(x, y-r, this.buttonWeight, this.buttonHeight);
		y+= this.buttonHeight + dis;

		batch.draw(this.normalButton, x, hdes-y);
		this.normalBound = new Rectangle(x, y-r, this.buttonWeight, this.buttonHeight);
		y+= this.buttonHeight + dis;

		batch.draw(this.infiniteButton, x, hdes-y);
		this.infiniteBound = new Rectangle(x, y-r, this.buttonWeight, this.buttonHeight);

		if (this.h < 320) {
			x = 32 * 2;
		} else {
			x = /*this.w -*/ (32 * 4) + 16;
		}
		y = 16;
		batch.draw(this.exitButton, this.w - x, y);
		if (block == 16)
			this.exitBound = new Rectangle(this.w - x, hdes-y-16,32, 32);
		else
			this.exitBound = new Rectangle(this.w - x, hdes-y,32, 32);
		
		batch.end();
	}


	/**
	 * Draw a background image as a mosaic
	 */
	void drawBackground() {

		int backgroundWidht = this.menuBackgroud.getWidth();
		int numOfCopies = (int)w / backgroundWidht ;
		
	
		batch.disableBlending();
		
		for (int i = 0; i < (numOfCopies+1); i++) 
			batch.draw(this.menuBackgroud, (backgroundWidht * i),  0);

		batch.enableBlending();
		
	}

	
	public List<Rectangle> getListOfButtonBounds() {
		List<Rectangle> l = new ArrayList<Rectangle>();
		
		l.add(this.easyBound);
		l.add(this.normalBound);
		l.add(this.infiniteBound);
		l.add(this.exitBound);
		
		return l; 
	}


	public void fillWirhSizes(Sizes ss) {
		ss.setWithAndHeight((int)this.w, (int)this.h);
		//ss.setBlockAndArrowButtonSize( (int)this.blocks.get(0).getWidth() , (int)this.blocks.get(0).getWidth());
		//ss.setSnakeSize(this.serpiente.getWidth(), this.serpiente.getHeight());
		//ss.setHouseSize(this.house.getWidth(), this.house.getHeight());
	}

	public void drawStatsBackgroundAndClearScreen(List<Block> blocks) {
		clearScreen(); // From abstractView
		
		int backgroundWidht = this.menuBackgroud.getWidth();
		int numOfCopies = (int)w / backgroundWidht ;
		
		
		//.combined
		camera.update();
		
		//
		//batch = new SpriteBatch();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.disableBlending();
		
		for (int i = 0; i < (numOfCopies+1); i++) 
			batch.draw(this.menuBackgroud, (backgroundWidht * i),  0);

		//float oldHDes = this.hdes;
		//hdes = h- 16;
		this.drawBlocks(blocks);
		//hdes = oldHDes;
		//batch.enableBlending();
		//batch.end();
	}
	
	/**
	 * Expected the batch open
	 * @param i
	 * @param j
	 * @param string
	 */
	public void drawText(int i, int j, String string) {
		batch.enableBlending();
		font.draw(batch, string, i, hdes - j);
		batch.disableBlending();
	}

	public void closeGraph() {
		batch.end();
	}


}
