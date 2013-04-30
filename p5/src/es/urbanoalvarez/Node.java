package es.urbanoalvarez;

import java.util.LinkedList;

public class Node {
	int reward;
	int ttl = 0;
	Point pos;
	LinkedList<Gem> parents;
	
	// Initiall constructor
	public Node(int r, Point p){
		pos = p;
		reward = r;
		parents = new LinkedList<Gem>();
	}
	// Next nodes:
	public Node(int r, int ttl, Point p, LinkedList<Gem> parents){
		pos = p;
		reward = r;
		this.ttl = ttl;
		this.parents = parents;
	}
	
	/**
	 * Test if a given Gem is not in the parents
	 * @param g
	 * @return
	 */
	public boolean testGem(Gem g){
		if(parents.contains(g)) return false;
		return true;
	}
	
	/**
	 * Tries to move the node to another Gem
	 * returns false if the Gem was already visited
	 * @param g
	 * @return
	 */
	public Node next(Gem g){
		LinkedList<Gem> next = new LinkedList<Gem>(parents);
		next.add(g);
		//System.out.println("{Copying Node. Parents = "+parents.size()+", Next = "+next.size()+"}");
		// Return the next node
		return new Node(reward + g.value, ttl + g.distance(pos), g.pos, next);
	}
	
	/**
	 * Debug method
	 */
	public String toString(){
		return "Node in "+pos.toString()+" Reward="+reward+", TTL="+ttl+", Parents="+parents.size();
	}
}
