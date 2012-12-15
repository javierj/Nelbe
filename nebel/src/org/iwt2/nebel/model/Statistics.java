package org.iwt2.nebel.model;

import java.util.ArrayList;
import java.util.List;

public class Statistics {

	int arrowsPlaced;
	int arrowsUsedBySnake;
	int arrowsErasedByPlayer;
	long initTime;
	long finalTime;
	long timeInLevel;
	float snakeVelocity;
	int deathSnakes;
	int savedSnaked;
	String dificulty;
	private int levels;
	int playedLevels;
	
	public int getArrowsPlaced() {
		return arrowsPlaced;
	}
	public void incArrowsPlaced() {
		this.arrowsPlaced ++;
	}
	public int getArrowsUsedBySnake() {
		return arrowsUsedBySnake;
	}
	public void incArrowsUsedBySnake() {
		this.arrowsUsedBySnake ++;
	}
	public int getArrowsErasedByPlayer() {
		return arrowsErasedByPlayer;
	}
	public void incArrowsErasedByPlayer() {
		this.arrowsErasedByPlayer ++;
	}
	public long getInitTime() {
		return initTime;
	}
	public void setInitTime(long initTime) {
		this.initTime = initTime;
	}
	public long getTimeInLevel() {
		return this.finalTime - this.initTime;
	}
	public void setFinalTime(long timeInLevel) {
		this.finalTime = timeInLevel;
	}
	public float getSnakeVelocity() {
		return snakeVelocity;
	}
	public void setSnakeVelocity(float snakeVelocity) {
		this.snakeVelocity = snakeVelocity;
	}
	public void incDeathSnake() {
		this.deathSnakes++;
	}
	public void incSavedSnake() {
		this.savedSnaked++;
	}
	public int getSavedSnakes() {
		return this.savedSnaked;
	}
	public int getDeathSnakes() {
		return this.deathSnakes;
	}
	public void setDificulty(String dificultyAsString) {
		this.dificulty = dificultyAsString;
	}
	
	public String getDificulty() {
		return this.dificulty;
	}
	
	public void setNumberOfLevels(int size) {
		this.levels = size;
	}

	public int getNumberOfLevels() {
		return this.levels;
	}
	
	public void incPlayedLevel() {
		this.playedLevels++;
	}

	//--------------------------
	
	public List<String> getStatsAsString() {
		List<String> stats = new ArrayList<String>();
		
		stats.add("Arrows placed: " + this.getArrowsPlaced());
		stats.add("Arrows used: " + this.getArrowsUsedBySnake());
		stats.add("Nelbes found: " + this.getSavedSnakes());
		stats.add("Nelbes lost: " + this.getDeathSnakes());
		stats.add("Playing segs: " + this.getTimeInLevel() / 1000f); 
		stats.add("Difficult: " + this.getDificulty());
		stats.add("Levels: " + this.playedLevels + "/"+ this.getNumberOfLevels());
														
		return stats;
		
	}
	
}
