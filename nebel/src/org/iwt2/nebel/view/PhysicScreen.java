package org.iwt2.nebel.view;

import java.util.*;

import org.iwt2.nebel.model.ArrowButton;
import org.iwt2.nebel.model.Block;
import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PhysicScreen extends AbstractView {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	float hdes;
	
	List<Sprite> snakeSprites;
	Texture house;
	Texture background;
	Texture statBackgroud;
	
	Map<Integer, Texture> controlArrows;
	Sprite s;
	//List<Texture> blocks;
	List<Sprite> blocks;
	int blockIndex;
	//int spriteMid;
	
	World world;
	
	//float w, h;
	
	BitmapFont font;
	Texture exitButton;
	int blockSize;
	
	//PhysicDevice imgs = new PhysicDevice();
/*
	Color[] colors = {Color.WHITE, Color.CLEAR, Color.CYAN, Color.GRAY, Color.GREEN};
	int colorIndex = 0;
	int colorLimit;
	*/
	TexturePool pool;
	private float scaleArrow;
	private HashMap<Integer, Sprite> gameArrows;
	private Texture transitionBanner;
	
	public PhysicScreen() {
		//this.colorIndex = 0;
		//this.colorLimit = this.colors.length;
	}
	
	
	public void init() {
		w = Gdx.graphics.getWidth(); // 475
		h = Gdx.graphics.getHeight(); // 320
		//hdes = h-16f;
		
		this.camera = setCamera(w, h); // From abstract view
		batch = new SpriteBatch();
		
		controlArrows = new HashMap<Integer, Texture>() ;
		gameArrows = new HashMap<Integer, Sprite>() ;
		
		//markedArrows = new HashMap<Integer, Texture>();
		//blocks = new ArrayList<Texture>();

		//System.out.println("PhysicScreen. "+ w + ", " + h + ". ");
		
		/*
		//serpiente = new Sprite(new Texture(Gdx.files.internal("data/snake01_32.png")));
		snakeSprites = new ArrayList<Sprite>();
		snakeSprites.add(new Sprite(new Texture(Gdx.files.internal("data/snake01_32.png"))));
		snakeSprites.add(new Sprite(new Texture(Gdx.files.internal("data/snake02_32.png"))));
		snakeSprites.add(new Sprite(new Texture(Gdx.files.internal("data/snake03_32.png"))));
		snakeSprites.add(new Sprite(new Texture(Gdx.files.internal("data/snake04_32.png"))));

		float snakeScale = 1.0f;
		*/
		if (w < 640) {
			this.load16x16Images();
			this.scaleArrow = 0.5f;
			//snakeScale = 0.5f;
			blockSize = 16;
		} else {
			this.load32x32Images();
			this.scaleArrow = 1f;
			blockSize = 32;
		}
		
		/*
		for (Sprite s: this.snakeSprites) { 
			s.setScale(snakeScale);
			s.setOrigin(0f, 0f);
		}
		*/
		this.blockIndex = 0;
		Texture arrow;

		arrow = new Texture(Gdx.files.internal("data/abj32.png"));
		controlArrows.put(ArrowButton.DOWN, arrow);
		this.gameArrows.put(ArrowButton.DOWN, createSprite(arrow, scaleArrow));

		arrow= new Texture(Gdx.files.internal("data/arr32.png"));
		controlArrows.put(ArrowButton.UP, arrow);
		this.gameArrows.put(ArrowButton.UP, createSprite(arrow, scaleArrow));
		
		arrow = new Texture(Gdx.files.internal("data/izq32.png"));
		controlArrows.put(ArrowButton.LEFT, arrow);
		this.gameArrows.put(ArrowButton.LEFT, createSprite(arrow, scaleArrow));
		
		arrow = new Texture(Gdx.files.internal("data/der32.png"));
		controlArrows.put(ArrowButton.RIGHT, arrow);
		this.gameArrows.put(ArrowButton.RIGHT, createSprite(arrow, scaleArrow));
		
		this.pool = TexturePool.getInstance();
		/*
		background = new Texture(Gdx.files.internal("data/background.jpg"));
		font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"), Gdx.files.internal("fonts/default.png"), false);
		statBackgroud = new Texture(Gdx.files.internal("data/statsBackground.jpg"));
	*/
		background = this.pool.getBackground(); // Check height
		font = this.pool.getFont();
		statBackgroud = this.pool.getStatBackground();
		this.blocks = this.pool.getBlocks();
		hdes = this.pool.getHDes();
		//System.out.println("PhisicScree hdes = " + hdes);
		
		this.snakeSprites = this.pool.getSnakeSprites();
		
		//house = new Texture(Gdx.files.internal("data/house.png"));
		house= pool.getHouseTexture();
		
		this.transitionBanner = new Texture(Gdx.files.internal("data/moreNelbe.png"));
		exitButton = pool.getExitButton();

	}
	

	private Sprite createSprite(Texture t, float scale) {
		Sprite s = new Sprite(t);
		s.setScale(scale);
		s.setOrigin(0f, 0f);
		return s;
	}
	
	private void load16x16Images() {
/*
		arrows.put(ArrowButton.DOWN, new Texture(Gdx.files.internal("data/FlechaMiniAbj.png")));
		arrows.put(ArrowButton.UP, new Texture(Gdx.files.internal("data/FlechaMiniArr.png")));
		arrows.put(ArrowButton.LEFT, new Texture(Gdx.files.internal("data/FlechaMiniIzq.png")));
		arrows.put(ArrowButton.RIGHT, new Texture(Gdx.files.internal("data/FlechaMiniDer.png")));
	*/	
		//serpiente = new Texture(Gdx.files.internal("data/snake01.png"));
		
		//house = new Texture(Gdx.files.internal("data/house.png"));
	/*
		blocks.add(new Texture(Gdx.files.internal("data/bloque01.png")));
		blocks.add(new Texture(Gdx.files.internal("data/bloque02.png")));
*/
		
	}

	private void load32x32Images() {
/*
		arrows.put(ArrowButton.DOWN, new Texture(Gdx.files.internal("data/FlechaMiniAbj_32.png")));
		arrows.put(ArrowButton.UP, new Texture(Gdx.files.internal("data/FlechaMiniArr_32.png")));
		arrows.put(ArrowButton.LEFT, new Texture(Gdx.files.internal("data/FlechaMiniIzq_32.png")));
		arrows.put(ArrowButton.RIGHT, new Texture(Gdx.files.internal("data/FlechaMiniDer_32.png")));
	*/	
		//serpiente = new Texture(Gdx.files.internal("data/snake01_32.png"));
		
		//house = new Texture(Gdx.files.internal("data/house.png"));
	/*
		blocks.add(new Texture(Gdx.files.internal("data/bloque01_32.png")));
		blocks.add(new Texture(Gdx.files.internal("data/bloque02_32.png")));
*/	
	}

	/**
	 * This method also disposes the texture pool
	 */
	public void dispose() {
		batch.dispose();
		this.transitionBanner.dispose();
		for (Texture t: this.controlArrows.values()) {
			t.dispose();
		}
		for (Sprite s: this.gameArrows.values()) {
			// s.dispose(); -- Undefined
		}
		//System.out.println("PhysicScreen::dispose called.");
	}


	public void draw(ArrowButton markedButton, Vector2 bannerPos) {
		clearScreen(); // From abstractView

		camera.update();
		//batch.setProjectionMatrix(camera.invProjectionView);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(this.background, 0, 0);
		
		this.drawControlArrowButtonsAndExitButton(batch, markedButton, this.world.getControlArrowButtons());
		this.drawArrowButtons();
		
		batch.disableBlending();
		
		drawBlocks(this.world.getBlocks());
		drawSnakes(batch, this.world.getSnakes());
		drawHouse(batch, this.world.getHouse());
		batch.enableBlending();

		
		if (bannerPos != null) {
			drawTransitionBanner(bannerPos);
		}
		batch.end();
	 
	}

	private void drawTransitionBanner(Vector2 bannerPos) {
		batch.draw(transitionBanner, bannerPos.x, hdes- bannerPos.y);
	}

	/**
	 * This method also draws the exit button
	 * @param batch2
	 * @param marked
	 * @param controlArrowButtons
	 */
	private void drawControlArrowButtonsAndExitButton(SpriteBatch batch2,ArrowButton marked,
			List<ArrowButton> controlArrowButtons) {
		
		float oldHdes = this.hdes;
		if (blockSize == 16)
			this.hdes -= 16;
		
		Texture t;
		for (ArrowButton b: this.world.getControlArrowButtons()) {
			//System.out.println("PhysiscScree - " + b.getRectangle().width + ", " + b.getRectangle().getHeight());
			//s = this.gameArrows.get(b.getArrowDirection());
			t = this.controlArrows.get(b.getArrowDirection());
			
			if (b.equals(marked)) {
				//batch.draw(this.markedArrows.get(b.getArrowDirection()), b.getX(), hdes- b.getY());
				batch2.setColor(Color.GRAY);
				batch2.draw(t, b.getX(), hdes- b.getY());
				//batch.draw(this.arrows.get(b.getArrowDirection()), b.getX(), hdes- b.getY());
				batch2.setColor(Color.WHITE);
			} else {
				//batch.draw(this.arrows.get(b.getArrowDirection()), b.getX(),  hdes-b.getY());
				//s.setX(b.getX());
				//s.setY(hdes- b.getY());
				//System.out.println("x = " + b.getX());
				batch2.draw(t, b.getX(), hdes- b.getY());
				//s.draw(batch2);
			}
		}
		
		// Exit button in a fix position
		Rectangle exit = this.world.getSizes().getExitButtonRectangle();
		batch.draw(this.exitButton, exit.x, hdes - exit.y);

		this.hdes = oldHdes;

	}


	void drawArrowButtons() {
		for (ArrowButton b: this.world.getArrowButtonList()) {
			//batch.draw(this.controlArrows.get(b.getArrowDirection()), b.getX(),  hdes-b.getY());
			s = this.gameArrows.get(b.getArrowDirection());
			s.setX(b.getX() /*-this.spriteMid*/);
			s.setY(hdes- b.getY() /*+ this.spriteMid*/);
			//System.out.println("x = " + b.getX());
			s.draw(batch);
		}
	}
	
	/**
	 * The size is constant so it doe snot use HDES.
	 * @param batch2
	 * @param house
	 */
	private void drawHouse(SpriteBatch batch2, Block house) {
		if (house != null) {
			batch2.draw(this.house, house.getX(),  this.h-house.getY() -32);
		}
		
	}

	public void drawListOfBlocks(List<Block> blocks2) {
		//batch.begin();
		batch.disableBlending();
		drawBlocks(blocks2);
		batch.enableBlending();
		//batch.end();
	}

	/**
	 * Diseable blending for drawing blocks
	 * @param blocks2
	 */
	private void drawBlocks(List<Block> blocks2) {
		for (Block b: blocks2) {
			//batch.setColor(colors[this.colorIndex]);
			
			//batch.draw(this.blocks.get(this.blockIndex), b.getX(), hdes-b.getY());
			s = this.blocks.get( (b.getId() % this.blocks.size()) );
			s.setX(b.getX() /*- 8*/ /*+ this.spriteMid */  );
			s.setY(hdes - (b.getY() /*- this.spriteMid*/));
			s.draw(batch);
			/*this.blockIndex++;
			if (this.blockIndex == this.blocks.size())
				this.blockIndex = 0;*/
		}
		//batch.setColor(Color.WHITE);
		
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


	private void drawSnakes(SpriteBatch batch2, List<Snake> snakes) {
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
			snakeSprite.draw(batch2);
	
		}
	}

	public void fillWirhSizes(Sizes ss) {
		ss.setWithAndHeight((int)this.w, (int)this.h);
		//ss.setBlockAndArrowButtonSize((int)this.pool.getBlockSize(), (int)this.pool.getBlockSize());
		//ss.setSnakeSize(this.pool.getSnakeSize(), this.pool.getSnakeSize());
		//ss.setHouseSize(this.house.getWidth(), this.house.getHeight());
		//this.spriteMid = Block.XSIZE / 2;
		
	}

	public void drawStatsBackgroundAndClearScreen(List<Block> blocks) {
		clearScreen(); // From abstractView
		
		int backgroundWidht = this.statBackgroud.getWidth();
		int numOfCopies = (int)w / backgroundWidht ;
		
		
		//.combined
		camera.update();
		
		//
		//batch = new SpriteBatch();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.disableBlending();
		
		for (int i = 0; i < (numOfCopies+1); i++) 
			batch.draw(this.statBackgroud, (backgroundWidht * i),  0);

		//float oldHDes = this.hdes;
		//hdes = h- 16;
		this.drawBlocks(blocks);
		//hdes = oldHDes;
		//batch.enableBlending();
		//batch.end();
	}

	/**
	 * External dependence
	 * 
	 * @param world2
	 */
	public void setWorld(World world2) {
		this.world = world2;
	}
	
	public void closeGraph() {
		batch.end();
	}
}
