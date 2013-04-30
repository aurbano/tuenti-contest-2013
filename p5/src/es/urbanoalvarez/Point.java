package es.urbanoalvarez;

/**
 * Store coordinates
 * @author Alex
 *
 */
public class Point {
	int x, y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p){
		x = p.x;
		y = p.y;
	}
	
	/**
	 * Get distance to another Point
	 * @param p2
	 * @return
	 */
	public int distance(Point p2){
		return Math.abs(p2.x - this.x) + Math.abs(p2.y - this.y);
	}
	
	public boolean equals(Point p2){
		return x == p2.x && y == p2.y;
	}
	
	/**
	 * Debug method
	 */
	public String toString(){
		return "("+x+","+y+")";
	}
}
