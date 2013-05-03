package es.urbanoalvarez;

import java.util.ArrayList;
import java.util.Comparator;

public class Word {
	String w;
	int value=0;
	Cell[] cells;
	
	/**
	 * Simple constructor
	 * @param word
	 */
	public Word(String word){
		w = word;
		cells = new Cell[0];
	}
	
	/**
	 * Normal constructor, but containing cells
	 * @param word
	 * @param c
	 * @param value
	 */
	public Word(String word, ArrayList<Cell> c){
		w = word;
		cells = c.toArray(new Cell[c.size()]);
	}
	
	/**
	 * Kind of copy
	 * @param w1
	 * @param c
	 */
	public Word(Word w1, Cell[] c, int value){
		w = w1.w;
		cells = c;
		this.value = value;
	}
	
	/**
	 * Display
	 */
	public String toString(){
		String ret = w;
		if(cells.length>0){
			ret += ". Cells: ";
			for(Cell c : cells){
				ret += c.toString()+", ";
			}
		}
		if(value > 0) ret += ". Value="+value;
		return ret;
	}
	
	/**
	 * indexOf
	 * @param word
	 * @param i
	 * @return
	 */
	public int indexOf(Word word, int i){
		return w.indexOf(word.w, i);
	}
	
	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Word))return false;
		Word w = (Word)other;
		// To be the same two words must have the same cells
		if(this.w.length() != w.w.length()) return false;
		// Compare Cells
		for(int i=0;i<cells.length;i++){
			if(!cells[i].equals(w.cells[i])) return false;
		}
			
		return true;
	}
	
	@Override
    public int hashCode() {
        int result = 7;
		for(int i=0; i<this.cells.length; i++){
        	result += this.cells[i].hashCode();
        }
		return result;
    }
	
	/**
	 * Custom comparator
	 * @param o
	 * @return
	 */
	public int compareTo(Object o) {
		if (!(o instanceof Word)) throw new ClassCastException();
		Word e = (Word) o;
		return w.compareTo(e.w);
	}
}
