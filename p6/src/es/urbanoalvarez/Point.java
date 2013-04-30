package es.urbanoalvarez;

/**
 * Defines a point in the map
 * @author Alex
 *
 */
public class Point {
	boolean ice;	// true means walkable
	int type;		// 0-> ice/rock, 1->start, 2->end
	
	public Point(boolean i, int t){
		ice = i;
		type = t;
	}
	
	public String toString(){
		String ret = "#";
		if(ice) ret = ".";
		if(type>0){
			if(type>1) ret = "O";
			else ret = "X";
		}
		return ret;
	}
}
