package es.urbanoalvarez;

/**
 * Just a class to store x/y pairs
 * @author Alex
 *
 */
public class Item {
	int r, c;
	
	public Item(int r, int c){
		this.r = r;
		this.c = c;
	}
	
	public String toString(){
		return "("+r+","+c+")";
	}
}
