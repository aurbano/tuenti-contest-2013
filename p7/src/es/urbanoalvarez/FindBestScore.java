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
			//Set<Word> possible = allWords();
			// Calculate all valid words (It returns them nicely sorted by value :D )
			ArrayList<Word> words = validWords();
			
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
				}else debug("    Already used");
			}
			debug("Final points: "+points);
			
			// Now that I have all the valid words, find the combination that provides the most points in the given time
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a list of words found on the dictionary, for a given set of
	 * possible "words"
	 * @param words
	 * @return
	 */
	private ArrayList<Word> validWords() throws FileNotFoundException, IOException{
		
		//Set<Word> valid = new HashSet<Word>();
		ArrayList<Word> ret = new ArrayList<Word>();
		
		LinkedList<Node> currentNodes = new LinkedList<Node>();
		LinkedList<Node> nextNodes;
		Cell[] possibleMoves;
		
		// Setup all starting points
		for(int row = 0; row < board.h; row++){
			for(int col = 0;col < board.w; col++){
				// For each point, create a node
				currentNodes.add(new Node(board.cells[row][col]));
			}
		}
		
		// Now starts the fun part
		
		Node tempNode;
		int availMoves, iter = 0;
		
		while(currentNodes.size() > 0){
			debug("-------");
			debug("Iteration "+iter+": "+currentNodes.size()+" nodes, "+ret.size()+" words");
			nextNodes = new LinkedList<Node>();
			// Iterate the nodes
			for(Node eachNode : currentNodes){
				debug("  "+eachNode.toString());
				
				// Reset the available moves
				availMoves = 0;
				// Test possible moves
				possibleMoves = possibleMoves(eachNode);
				for(Cell testCell : possibleMoves){
					
					// Discard non movable
					if(testCell.status == -1) continue;
					
					// Discard current (Non valid moves)
					if(testCell.equals(eachNode.pos)){
						continue;
					}
					
					// Discard already visited Points
					if(!eachNode.testPoint(testCell)){
						debug("    Already used "+testCell);
						continue;
					}
					
					tempNode = eachNode.next(testCell);
					
					debug("    Valid move:"+testCell.toString());
					
					// Test end
					if(testCell.status == 1){
						Word w = tempNode.getWord();
						debug("      Valid end "+w);
						if(!ret.contains(w)){ // Check if this word was already picked
							// Calculate score and submit
							w.value = board.wordScore(tempNode.parents.toArray(new Cell[tempNode.parents.size()]));
							ret.add(w);
						}
					}
					
					availMoves++;
					
					// Valid node
					nextNodes.add(tempNode);
				}
				
				if(availMoves == 0){
					debug("    No more moves...");
				}
			}
			// If there are any nextNodes update currentNodes
			if(nextNodes.size()==0) break;
			currentNodes = nextNodes;
			iter++;
		}
		
		debug("--------------");
		debug("Sorting...");
		// Sort Words by value :D
		Collections.sort(ret, new Word.WordComparator());
		debug("--------------");
		return ret;
	}
	
	/**
	 * Find possible moves around a Node
	 * it checks the dictionary to validate a point, and the Node's history
	 * The returned values are TL, T, TR, R, BR, B, BL, L
	 * an option, it will return the current node's position
	 * @param pos
	 * @return
	 */
	private Cell[] possibleMoves(Node cur){
		// There are 4 possibilities
		Cell[] moves = new Cell[8];
		// Defaults
		moves[0] = new Cell(cur.pos);
		moves[1] = new Cell(cur.pos);
		moves[2] = new Cell(cur.pos);
		moves[3] = new Cell(cur.pos);
		moves[4] = new Cell(cur.pos);
		moves[5] = new Cell(cur.pos);
		moves[6] = new Cell(cur.pos);
		moves[7] = new Cell(cur.pos);
		
		String add;
		int r,c,type, index=-1;
		
		for(int row=-1; row < 2; row++){
			for(int col=-1; col < 2; col++){
				if(row==col && row == 0) continue;
				index++;
				// Coordinates
				r = cur.pos.row + row;
				c = cur.pos.col + col;
				
				// Test bounds
				if(r >= 0 && c >= 0 && r < board.h && c < board.w){
					add = cur.word+Character.toString(board.cells[r][c].letter);
					// Now test this move:
					type = validWord(add);
					if(type>-1){
						try{
							moves[index] = new Cell(board.cells[r][c], type);
						}catch(Exception e){
							e.printStackTrace();
							System.exit(1);
						}
					}
				}//else debug("  Out of bounds: ("+r+","+c+")");
			}
		}
		
		return moves;
	}
	
	/**
	 * Returns -1 if no word starts or ends with that String
	 * returns 0 if a word starts with that String
	 * returns 1 if a word ends with that String 
	 * @param w
	 * @return
	 */
	private int validWord(String w){
		if(w.length()<1) return 0;
		//for(int i=0; i<P7.wordsNum;i++){
		LinkedList<Word> avail = tester.words.get(w.charAt(0));
		for(Word word : avail){
			if(word.w.equals(w)) return 1; // A word is this
			if(word.w.indexOf(w)==0) return 0; // A word starts by this
		}
		return -1;
	}
	
	/**
	 * Simply display a text if I wanted to (comment out the system out for submit)
	 * @param text
	 */
	public void debug(String text){
		System.out.println(text);
	}
}