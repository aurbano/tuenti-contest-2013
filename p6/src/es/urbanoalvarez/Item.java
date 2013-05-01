package es.urbanoalvarez;

/**
 * Defines an element in the map
 * @author Alex
 *
 */
public class Item {
	boolean ice;	// true means walkable
	int type;		// 0-> ice/rock, 1->start, 2->end
	
	public Item(boolean i, int t){
		ice = i;
		type = t;
	}
	
	/**
	 * Test if current block is a rock
	 * @return
	 */
	public boolean isRock(){
		if(!ice && type == 0) return true;
		return false;
	}
	
	/**
	 * Test if current block is the end
	 * @return
	 */
	public boolean isEnd(){
		if(type == 2) return true;
		return false;
	}
	
	/**
	 * Debug function
	 */
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
