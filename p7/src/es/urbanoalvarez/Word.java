package es.urbanoalvarez;

import java.util.ArrayList;

public class Word {
	String w;
	int value;
	Cell[] cells;
	
	public Word(String word){
		w = word;
		cells = new Cell[0];
	}
	
	public Word(String word, ArrayList<Cell> c){
		w = word;
		cells = c.toArray(new Cell[c.size()]);
	}
	
	public String toString(){
		String ret = w;
		if(cells.length>0){
			for(Cell c : cells){
				ret += c.toString()+", ";
			}
		}
		return ret;
	}
	
	public int indexOf(Word word){
		return w.indexOf(word.w);
	}
}
