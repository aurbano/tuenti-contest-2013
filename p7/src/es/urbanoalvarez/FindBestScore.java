package es.urbanoalvarez;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class FindBestScore extends Thread{
	
	Board board;
	
	int time;
	
	public FindBestScore(Board m, int time){
		board = m;
		this.time = time;
		
		this.start();
	}
	
	public void run(){
		try{
			// Let's start by generating all possible words (All rows, columns and diagonals, max length)
			LinkedList<String> used = new LinkedList<String>();
			// All possible words
			Set<Word> possible = allWords();
			// Calculate all valid words
			Set<Word> words = validWords(possible);
			
			
			debug("All valid words:");
			for(Word word : words){
				System.out.println(word);
			}
			
			// Now that I have all the valid words, find the combination that provides the most points in the given time
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Simply display a text if I wanted to (comment out the system out for submit)
	 * @param text
	 */
	public void debug(String text){
		System.out.println(text);
	}
	
	/**
	 * Returns a list of words found on the dictionary, for a given set of
	 * possible "words"
	 * @param words
	 * @return
	 */
	private Set<Word> validWords(Set<Word> words) throws FileNotFoundException, IOException{
		
		Set<Word> valid = new HashSet<Word>();
		
		int index;
		// Iterate over the words
		for(int i =0;i<P7.wordsNum;i++){
			// For each word, check if it's contained in any of the words found in the board
			for(Word check : words){
				index = check.indexOf(P7.words.get(i));
				if(index > -1){
					valid.add(P7.words.get(i));
				}
			}
		}
		
		return valid;
	}
	
	/**
	 * Find all possible words in the board, in all directions
	 * without repeating. By words I mean max length, without checking the dictionary.
	 * This could probably be done much more efficiently, with less loops...
	 * @return
	 */
	private Set<Word> allWords(){
		Set<Word> possible = new HashSet<Word>();
		
		String tempRow, tempCol, tempDiag;
		ArrayList<Cell> cells; // Container with the cells used by each word
		
		// Horizontal
		for(int r=0; r<board.h;r++){
			cells = new ArrayList<Cell>();
			tempRow = "";
			for(int c=0; c<board.w; c++){
				tempRow += board.cells[r][c].letter;
				cells.add(board.cells[r][c]);
			}
			possible.add(new Word(tempRow, cells));
		}
		
		// Vertical
		for(int c=0; c<board.w; c++){
			cells = new ArrayList<Cell>();
			tempCol = "";
			for(int r=0; r<board.h;r++){
				tempCol += board.cells[r][c].letter;
				cells.add(board.cells[r][c]);
			}
			possible.add(new Word(tempCol, cells));
		}
	
		int r=0, c=0;
		try{
			// Diagonals starting on the first column and going down
			for(int start=0; start<board.h-1; start++){
				cells = new ArrayList<Cell>();
				// For each starting cell, loop over the cells in its corresponding diagonal
				r = start;
				c = 0;
				tempDiag = "";
				while(r < board.h && c < board.w){
					tempDiag += board.cells[r][c].letter;
					cells.add(board.cells[r][c]);
					r++;
					c++;
				}
				possible.add(new Word(tempDiag, cells));
			}
			
			
			// Diagonals starting on the first column and going up
			for(int start=1; start<board.h; start++){
				cells = new ArrayList<Cell>();
				// For each starting cell, loop over the cells in its corresponding diagonal
				r = start;
				c = 0;
				tempDiag = "";
				while(r >= 0 && c < board.w){
					tempDiag += board.cells[r][c].letter;
					cells.add(board.cells[r][c]);
					r--;
					c++;
				}
				possible.add(new Word(tempDiag, cells));
			}
			
			// Diagonals starting on the bottom row going up
			for(int start=0; start<board.w-1; start++){
				cells = new ArrayList<Cell>();
				// For each starting cell, loop over the cells in its corresponding diagonal
				c = start;
				r = board.h-1;
				tempDiag = "";
				while(c < board.w && r >=0){
					tempDiag += board.cells[r][c].letter;
					cells.add(board.cells[r][c]);
					r--;
					c++;
				}
				possible.add(new Word(tempDiag, cells));
			}
			
			// Diagonals starting on the top row going down
			for(int start=0; start<board.w-1; start++){
				cells = new ArrayList<Cell>();
				// For each starting cell, loop over the cells in its corresponding diagonal
				c = start;
				r = 0;
				tempDiag = "";
				while(c < board.w && r < board.h){
					tempDiag += board.cells[r][c].letter;
					cells.add(board.cells[r][c]);
					r++;
					c++;
				}
				possible.add(new Word(tempDiag, cells));
			}
		}catch(Exception e){
			debug("Out of bounds: r="+r+", c="+c);
			e.printStackTrace();
			System.exit(-1);
		}
		return possible;
	}
}