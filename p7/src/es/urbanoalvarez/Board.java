package es.urbanoalvarez;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Holds the Board information
 * @author Alex
 *
 */
public class Board {
	int w, h;
	Cell[][] cells;
	int[] scores = new int[26];
	
	public Board(int rows, int cols, String scores){
		w = cols;
		h = rows;
		cells = new Cell[rows][cols]; // row-col
		
		// Parse the scores from String to array of ints
		Matcher m = Pattern.compile("'([A-Z])': ([0-9])").matcher(scores);
		while (m.find()) {
			this.scores[(int) m.group(1).charAt(0) - 65] = new Integer(m.group(2));
		}
	}
	
	/**
	 * Process a row in String format from input
	 * @param row
	 * @param num
	 */
	public void parseRow(String row, int num){
		String[] cells = row.split(" ");
		int type, value, col=0;
		for(col=0; col < cells.length; col++){
			type = new Integer(Character.toString(cells[col].charAt(1)));
			value = new Integer(Character.toString(cells[col].charAt(2)));
			this.cells[num][col] = new Cell(cells[col].charAt(0), num, col, type, value);
		}
	}
	
	public int wordScore(Cell[] cells){
		int score = 0,
			multiplier = 1;
		for(Cell each : cells){
			score += letterScore(each.letter) * each.CM();
			multiplier = Math.max(multiplier, each.WM()); // Find the greatest multiplier
		}
		return score * multiplier + cells.length;
	}
	
	private int letterScore(char letter){
		return this.scores[(int) letter - 65];
	}
	
	/**
	 * Debug function, prints the board
	 */
	public void print(){
		System.out.println("Printing board "+h+" rows and "+w+" cols");
		for(int r=0; r<this.h; r++){
			for(int c=0; c<this.w;c++){
				System.out.print(cells[r][c].toString()+" ");
			}
			System.out.println();
		}
		System.out.println("------------");
	}
}
