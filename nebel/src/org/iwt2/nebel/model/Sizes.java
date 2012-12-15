package org.iwt2.nebel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Sizes {

	static final int REFERENCE_width = 480;
	int width;
	int height;
	int blockSizeX;
	int blockSizeY;
	int blocksInButtonZone;
	Vector2 houseSize;
	Random r;
	
	public static Sizes ss = null;
	
	public static Sizes getInstance() {
		if (ss == null) {
			ss = new Sizes();
		}
		return ss;
	}
	
	public Sizes() {
		blocksInButtonZone = 3;
		r = new Random();
	}
	
	public void setWithAndHeight(int i, int j) {
		this.width = i;
		this.height = j;
		//System.out.println("model.Sizes:setWithAndHeight, x=" + i+ ", j="+ j);
		/*
		if (this.width <= REFERENCE_width) {
			this.blocksInButtonZone = 2;
		} else {
			this.blocksInButtonZone = 4;
		}*/
		//this.blocksInButtonZone = 3;
		
		if (i < 640  ) {
			this.setBlockAndArrowButtonSize(16, 16);
			this.setSnakeSize(16, 16);
		} else {
			this.setBlockAndArrowButtonSize(32, 32);
			this.setSnakeSize(32, 32);
		}
		this.setHouseSize(32, 32);
	}

	/**
	 * Not in use diurectly. Called from other method
	 * Blocks and arrows button (in the game zone) have the same size.
	 * @param i
	 * @param j
*/	 
	public void setBlockAndArrowButtonSize(int i, int j) {
		
		this.blockSizeX = i;
		this.blockSizeY = j;
		
		ArrowButton.XSIZE = i;
		ArrowButton.YSIZE = j;
		
		Block.XSIZE = i;
		Block.YSIZE = j;
	}

	public void setSnakeSize(int i, int j) {
		Snake.X = i;
		Snake.Y = j;
	}

	/**
	 * It is always blocksize x 3 + rest of divison
	 * @return
	 */
	public Rectangle getButtonZoneRectangle() {
		//float w =  (this.blockSizeX * blocksInButtonZone) + (this.width  % this.blockSizeX);
		float w =  (this.blockSizeX * 3) + (this.width  % this.blockSizeX);
		
		Rectangle r = new Rectangle(0, 0, w, height);
		return r;
	}

	public Rectangle getGameZoneRectangle() {
		Rectangle r = new Rectangle(this.getButtonZoneRectangle().width, 0, this.width - this.getButtonZoneRectangle().width, height);
		return r;
	}

	public float getBlockSizeX() {
		return this.blockSizeX;
	}

	public float getBlockSizeY() {
		return this.blockSizeY;
	}

	public int getBlocksInWidht() {
		return this.width / (int)this.getBlockSizeX();
	}

	
	public int getBlocksInGameZoneWidht() {
		return this.getBlocksInWidht() - this.blocksInButtonZone;
		//return (this.width - (int)this.getButtonZoneRectangle().width) / (int)this.getBlockSizeX();
	}

	public int getWidth() {
		return width;
	}

	public float getHeight() {
		return this.height;
	}

	public boolean isInGameZone(int i, int j) {
		Rectangle r = this.getGameZoneRectangle();
		return r.contains(i, j);
	}
	
	/**
	 * This method is called when you have a resolution bigger tna 480 x 320
	 * This method increases the value of the vector
	 * @param v
	 
	public void translateVector(Vector2 v) {
		if (v.x <= REFERENCE_width)
			return;
		v.x = v.x + ((this.width - REFERENCE_width) / 10);
		v.y = v.y + ((this.width - 320) / 10);
	}*/
	

	/**
	 * Return true is screen is more than 300 pixels width
	 * This means than two columns of block will be drawed
	 * in the menu screen.
	 * 
	 * @return
	 */
	public boolean hasColumnsInMenuScreen() {
		return this.width >= 300;
	}

	public void setHouseSize(int i, int j) {
		this.houseSize = new Vector2(i, j);
		
	}

	public Vector2 getHouseSize() {
		if (this.houseSize == null) {
			System.out.println("Sizes - Warning ! returning a null house size. Must call setHouseSize first");
		}
		return this.houseSize;
	}

	
	public List<Block> getBlocksForColumnsInMenuScreen() {
		List<Block> background = new ArrayList<Block>();
		if (this.hasColumnsInMenuScreen() ) {
			for (float y = 0f; y < ss.getHeight(); y+= ss.getBlockSizeY()) {
				//for (x = 0f; x < ss.getWidth(); x+= ss.getBlockSizeX()) {
					background.add(new Block(0, y));
					background.add(new Block(ss.getWidth()-ss.getBlockSizeX(), y));
					
					if (this.height > 240) {
						background.add(new Block(ss.getBlockSizeX(), y));
						background.add(new Block(ss.getBlockSizeX() + ss.getBlockSizeX(), y));

						background.add(new Block(ss.getWidth()-ss.getBlockSizeX()-ss.getBlockSizeX(), y));
						background.add(new Block(ss.getWidth()-ss.getBlockSizeX()-ss.getBlockSizeX()-ss.getBlockSizeX(), y));
					}
				//}
			
			}
			}

		return background;
	}

	
	public List<Block> getBlocksForColumnsInStatsScreen() {		
		
		if (this.width < 400) {
		
			List<Block> background = new ArrayList<Block>();
			return background;
		}
		return this.getBlocksForColumnsInMenuScreen();
	}

	/**
	 * Return the widht estimated for the texto of the statistics.
	 * @return
	 */
	public int getTextWight() {
		//return 200;
		//return 250;
		return 310;
	}

	public int getXForBeginningText() {
		return (this.width - this.getTextWight()) / 2;
	}

	public int getInitialYForControlButton() {
		if (this.height <= 400)
			return 10;
		return 30;
	}

	/**
	 * Includes the size of the button
	 * @return
	 */
	public int getSpaceBetweenControlButton() {
		if (this.height > 420)
			return 32 + 30;
		return 32 + 10;
	}

	public Vector2 getRandomCoordinatesInGameZone() {
		Vector2 v = new Vector2();
		
		v.x = r.nextInt(this.width - (32*6)) + (32*4);
		v.y = (r.nextInt(this.height - (this.blockSizeY*3)) + this.blockSizeY);
		
		return v;
	}

	/**
	 * Button is drawed to botton
	 * @return
	 */
	public Rectangle getExitButtonRectangle() {
		float x = ((this.blocksInButtonZone * this.blockSizeX) - 32) / 2;
		return new Rectangle(x, this.height - 32 - (this.blockSizeY / 2), 32, 32);
	}
	
	
	/**
	 * Returns a diferent velocity is the secreen is wider or not
	 * Smallest screens gets a lower velocity.
	 * @return
	 */
	public float getBaseVelocity() {
		if (this.width > 480)
			return 35f;
		return 20f;
	}
	
	public SnakeInitialPosition[] getInitialSnakePos() {
		
		Rectangle gz = this.getGameZoneRectangle();
		if ((this.getWidth() != 320) && (this.getWidth() != 640)) {
		
			SnakeInitialPosition[] pos = { new SnakeInitialPosition(gz.width-60, gz.y+200, ArrowButton.LEFT),
	/*1*/				new SnakeInitialPosition(gz.x + 40, gz.y + 200, ArrowButton.UP),
					new SnakeInitialPosition(gz.x + 40, gz.y + 100, ArrowButton.DOWN),
					new SnakeInitialPosition(gz.x + 60, gz.y + 60, ArrowButton.RIGHT),
					new SnakeInitialPosition(ss.getWidth() - 70, gz.y + 200, ArrowButton.UP),
/*5*/					new SnakeInitialPosition(ss.getWidth() - 70, ss.getHeight() -70, ArrowButton.LEFT),

			};
			return pos;
		}
		
		SnakeInitialPosition[] pos = { new SnakeInitialPosition(gz.width-60, gz.y+200, ArrowButton.LEFT),
				new SnakeInitialPosition(gz.x + 40, gz.y + 200, ArrowButton.UP),
				new SnakeInitialPosition(gz.x + 40, gz.y + 100, ArrowButton.DOWN),
				new SnakeInitialPosition(gz.x + 60, gz.y + 60, ArrowButton.RIGHT),
				new SnakeInitialPosition(ss.getWidth() - 70, gz.y + 200, ArrowButton.UP),
				new SnakeInitialPosition(ss.getWidth() - 70, ss.getHeight() -70, ArrowButton.LEFT),

		};
		return pos;

	}

	/**
	 * Actually, returns true if the widht of the
	 * screen is bigger tan 800
	 * @return
	 */
	public boolean unsuportedScreen() {
		return this.width > 800;
	}

}
