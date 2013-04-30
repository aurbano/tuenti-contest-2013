package es.urbanoalvarez;

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
}
