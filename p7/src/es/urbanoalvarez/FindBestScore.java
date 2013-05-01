package es.urbanoalvarez;

import java.util.LinkedList;

public class FindBestScore extends Thread{
	
	Board board;
	
	public FindBestScore(Board m){
		board = m;
		
		this.start();
	}
	
	public void run(){
		// Let's start by generating all possible words (All rows, columns and diagonals, max length)
		LinkedList<String> used = new LinkedList<String>();
		LinkedList<String> possible = new LinkedList<String>();
		
	}
	
	/**
	 * Simply display a text if I wanted to (comment out the system out for submit)
	 * @param text
	 */
	public void debug(String text){
		System.out.println(text);
	}
}