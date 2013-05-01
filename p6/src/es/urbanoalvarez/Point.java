package es.urbanoalvarez;

/**
 * Just a class to store Map coordinates
 * @author Alex
 *
 */
public class Point {
	int r, c; // Row and column
	
	/**
	 * Normal
	 * @param r
	 * @param c
	 */
	public Point(int r, int c){
		this.r = r;
		this.c = c;
	}
	
	/**
	 * Copy
	 * @param p
	 */
	public Point(Point p){
		this.r = p.r;
		this.c = p.c;
	}
	
	/**
	 * Get distance to another Point
	 * @param p2
	 * @return
	 */
	public int distance(Point p2){
		return Math.abs(p2.r - this.r) + Math.abs(p2.c - this.c);
	}
	
	public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Point))
            return false;
        
		//System.out.println("  {Comparing "+toString()+", with "+obj.toString()+"}");
		Point p2 = (Point) obj;
		return r == p2.r && c == p2.c;
	}
	
	/**
	 * Debug function
	 */
	public String toString(){
		return "("+r+","+c+")";
	}
}
