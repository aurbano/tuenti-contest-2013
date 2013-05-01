package es.urbanoalvarez;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class FindBestScore extends Thread{
	
	Board board;
	
	int time, points=0;
	
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
			// Calculate all valid words (It returns them nicely sorted by value :D )
			ArrayList<Word> words = validWords(possible);
			debug("All possible words:");
			for(Word word : possible){
				// Assign their value
				System.out.println(word);
			}
			debug("-------");
			debug("All valid words:");
			for(Word word : words){
				// Assign their value
				System.out.println(word);
			}
			debug("--------");
			debug("Start picking");
			int i = 0;
			Word check;
			while(time > 1 && i < words.size()){
				check = words.get(i);
				i++;
				debug("  Time = "+time+", Check: "+check);
				if(!used.contains(check.w)){
					// Check time
					if(time < check.w.length() + 1){
						debug("    Not enough time");
						continue;				
					}
					// Pick the word
					time -= check.w.length() + 1;
					points += check.value;
					used.add(check.w);
					debug("    Picked, Points="+points+", time="+time);
				}else debug("    Already used a better one");
			}
			debug("Final points: "+points);
			
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
	private ArrayList<Word> validWords(Set<Word> words) throws FileNotFoundException, IOException{
		
		//Set<Word> valid = new HashSet<Word>();
		ArrayList<Word> ret = new ArrayList<Word>();
		
		int index;
		Cell[] subcells;
		// Iterate over the words
		for(int i =0;i<P7.wordsNum;i++){
			// For each word, check if it's contained in any of the words found in the board
			for(Word check : words){
				// We must take all occurences, since some might have more value
				index = 0;
				index = check.indexOf(P7.words.get(i), index);
				while(index != -1){
					// The word is contained. I would like to get it's cells
					// and calculate its value. So that duplicate words can be weeded out correctly.
					// Cells for this word:
					subcells = new Cell[P7.words.get(i).w.length()];
					for(int letter=0;letter<P7.words.get(i).w.length();letter++){
						subcells[letter] = check.cells[index + letter];
					}
					ret.add(new Word(P7.words.get(i), subcells, board.wordScore(subcells)));
					
					// Next
					index += P7.words.get(i).w.length();
					index = check.indexOf(P7.words.get(i), index);
				}
			}
		}
		
		// Sort Words by value :D
		Collections.sort(ret, new Word.WordComparator());
		
		return ret;
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