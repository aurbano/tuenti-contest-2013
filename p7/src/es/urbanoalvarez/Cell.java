package es.urbanoalvarez;

/**
 * Cell type, contains the info (letter, multiplier type, multiplier value)
 * @author Alex
 *
 */
public class Cell {
	char letter;
	int type, value;
	
	public Cell(char l, int t, int v){
		letter = l;
		type = t;
		value = v;
	}
	
	public String toString(){
		return Character.toString(letter)+Integer.toString(type)+Integer.toString(value);
	}
	
	/**
	 * Returns the character multiplier
	 * @return
	 */
	public int CM(){
		if(type == 2) return 1;
		return value;
	}
	
	/**
	 * Returns the word multiplier
	 * @return
	 */
	public int WM(){
		if(type == 1) return 1;
		return value;
	}
}
