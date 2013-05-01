package es.urbanoalvarez;

import java.util.ArrayList;

public class Node {
	int value;
	String word;
	Cell pos;
	ArrayList<Cell> parents; // Visited points (avoid repeating)
	
	// Initial constructor
	public Node(Cell p){
		pos = p;
		parents = new ArrayList<Cell>(); // Empty list
		parents.add(p);
		word = Character.toString(p.letter);
	}
	// Next nodes:
	public Node(Cell p, String w, ArrayList<Cell> parents){
		pos = p;
		this.parents = parents;
		word = w + Character.toString(p.letter);
	}
	
	public Word getWord(){
		return new Word(word, parents);
	}
	
	/**
	 * Test if a given Point is not in the parents
	 * @param g
	 * @return
	 */
	public boolean testPoint(Cell p){
		if(parents.contains(p)) return false;
		return true;
	}
	
	/**
	 * Return a valid next Node for a given Point
	 * the Point won't be validated, make sure it's ok
	 * @param p
	 * @return
	 */
	public Node next(Cell p){
		// int r, Point p, LinkedList<Point> parents
		// Time? Previous time + distance
		ArrayList<Cell> nextParents = new ArrayList<Cell>(parents);
		nextParents.add(p);
		
		return new Node(p, word, nextParents);
	}
	
	
	/**
	 * Debug method
	 */
	public String toString(){
		return "Node "+word+" "+pos.toString()+" Parents="+parents.size();
	}
}
