package es.urbanoalvarez;

import java.util.LinkedList;

/**
 * Hold Gems information
 * @author Alex
 *
 */
public class Gem {
	int value = 0;
	Point pos;
	
	public Gem(int v, int x, int y){
		value = v;
		pos = new Point(x,y);
	}
	
	/**
	 * Debug info about the Gem
	 */
	public String toString(){
		return "Gem " + this.value + "("+pos.x+","+pos.y+")";
	}
	
	/**
	 * Distance to another gem
	 * @param g
	 * @return
	 */
	public int distance(Gem g){
		return pos.distance(g.pos);
	}
	
	/**
	 * Distance to another point
	 * @param p
	 * @return
	 */
	public int distance(Point p){
		return pos.distance(p);
	}
	
	public boolean equals(Gem g){
		return this.pos == g.pos;
	}
	
	public boolean isBehind(Point pos, LinkedList<Gem> gems, LinkedList<Gem> used){
		// Check if current gem is behind another one
		for(Gem eachGem : gems){
			if(eachGem.pos.equals(pos)) continue;
			if(used.contains(eachGem)) continue;
			/*
			 * The only needed check is if they are on the same
			 * quadrant relative to the current position
			 */
			if((pos.x - this.pos.x)*(pos.x - eachGem.pos.x) >= 0 && (pos.y - this.pos.y)*(pos.y - eachGem.pos.y) >= 0){
				// I think they are in the same quadrant
				// now check if distance is greater, and if so, skip
				if(this.pos.distance(pos) > eachGem.pos.distance(pos)){
					//System.out.println("    Behind. Node="+pos.toString()+", Me="+this.pos.toString()+", "+eachGem.toString());
					return true;
				}
			}
			/*
			int distanceCurrent = (pos.x - this.pos.x) + (pos.y - this.pos.y);
			int distanceOther = (pos.x - eachGem.pos.x) + (pos.y - eachGem.pos.y);
			
			// Now skip if same row, same col, or diagonal
			if(pos.x == eachGem.pos.x) return true;
			if(pos.y == eachGem.pos.y) return true;
			if(pos.x - eachGem.pos.x == pos.y - eachGem.pos.y) return true;*/
		}
		return false;
	}
}
