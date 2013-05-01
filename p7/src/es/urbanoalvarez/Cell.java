package es.urbanoalvarez;

import java.util.Arrays;

/**
 * Cell type, contains the info (letter, multiplier type, multiplier value)
 * @author Alex
 *
 */
public class Cell {
	char letter;
	int type, value, row, col, status=-1; // Status is just for moves in nodes....
	
	public Cell(char l, int r, int c, int t, int v){
		letter = l;
		type = t;
		value = v;
		row = r;
		col = c;
	}
	
	/**
	 * Copy
	 * @param c
	 */
	public Cell(Cell c){
		letter = c.letter;
		type = c.type;
		value = c.value;
		row = c.row;
		col = c.col;
	}
	
	/**
	 * Copy
	 * @param c
	 */
	public Cell(Cell c, int s){
		letter = c.letter;
		type = c.type;
		value = c.value;
		row = c.row;
		col = c.col;
		status = s;
	}
	
	public String toString(){
		return "("+Integer.toString(row)+","+Integer.toString(col)+") "+Character.toString(letter)+Integer.toString(type)+Integer.toString(value);
	}
	
	/**
	 * Returns the character multiplier
	 * @return
	 */
	public int CM(){
		if(type == 2) return 1;
		return value;
	}
	
	/**
	 * Returns the word multiplier
	 * @return
	 */
	public int WM(){
		if(type == 1) return 1;
		return value;
	}
	
	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Cell))return false;
		Cell c = (Cell)other;
		// Two cells are the same if their coordinates are
		if(this.row != c.row) return false;
		if(this.col != c.col) return false;
			
		return true;
	}
	
	@Override
    public int hashCode() {
		return Arrays.hashCode( new Object[] { status, letter, type, value, row, col } );
    }
}
