package es.urbanoalvarez;

import java.util.LinkedList;

public class Node {
	int time, speed;
	Point pos;
	LinkedList<Point> parents; // Visited points (avoid repeating)
	
	// Initial constructor
	public Node(int r, Point p, int speed){
		pos = p;
		time = r;
		this.speed = speed;
		parents = new LinkedList<Point>(); // Empty list
	}
	// Next nodes:
	public Node(int r, Point p, LinkedList<Point> parents, int speed){
		pos = p;
		time = r;
		this.speed = speed;
		this.parents = parents;
	}
	
	/**
	 * Test if a given Point is not in the parents
	 * @param g
	 * @return
	 */
	public boolean testPoint(Point p){
		if(parents.contains(p)) return false;
		return true;
	}
	
	/**
	 * Return a valid next Node for a given Point
	 * the Point won't be validated, make sure it's ok
	 * @param p
	 * @return
	 */
	public Node next(Point p){
		// int r, Point p, LinkedList<Point> parents
		// Time? Previous time + distance
		LinkedList<Point> nextParents = new LinkedList<Point>(parents);
		nextParents.add(p);
		
		int nextTime = this.time + (pos.distance(p) * this.speed);
		
		return new Node(nextTime, p, nextParents, this.speed);
	}
	
	
	/**
	 * Debug method
	 */
	public String toString(){
		return "Node in "+pos.toString()+" Time="+time+", Parents="+parents.size();
	}
}
