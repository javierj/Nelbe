package org.iwt2.nebel.model;


import org.iwt2.nebel.model.Snake;
import org.iwt2.nebel.model.World;

public class OpenWorld extends World {
	
	
	public OpenWorld(World w) {
		super(w.sizes);
		//this.snake = w.snake;
		this.arrowButtons = w.arrowButtons;
		this.house = w.house;
		
	}

	/*
	public void setSnake(Snake s) {
		this.snake = s;
	}*/
	
	
	public void createArrows() {
		super.createArrows();
	}

	public void setHouse(House house) {
		this.house = house;
		
	}
	
	public void clear() {
		super.clear();
	}


}
