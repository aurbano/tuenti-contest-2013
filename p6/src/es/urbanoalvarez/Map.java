package es.urbanoalvarez;

/**
 * Data class for P6
 * @author Alex
 *
 */
public class Map {
	Point[][] map;
	Item start,
			   end;
	
	/**
	 * Creates a new Map given a size. Map will be accessed as map[row][column]
	 * @param m
	 * @param n
	 */
	public Map(int h, int w){
		map = new Point[h][w];
	}
	
	/**
	 * Define a row for the map
	 * @param row
	 * @param num Row number where you want to insert
	 */
	public void parseRow(String row, int num){
		System.out.println("Parsing row");
		String[] elem = row.split("");
		Point[] rowPoints = new Point[elem.length];
		// Iterate through the columns
		for(int i=0;i<elem.length;i++){
			System.out.println("  Element "+i+": "+elem[i]);
			int type = 0;
			boolean ice = true;
			switch(elem[i]){
				case "#":
					ice = false;
					break;
				case "X":
					type = 1;
					System.out.println("    Start");
					start = new Item(num, i);
					break;
				case "O":
					System.out.println("    Finish");
					type = 2;
					end = new Item(num, i);
					break;
			}
			rowPoints[i] = new Point(ice, type);
			System.out.println("  "+rowPoints[i].toString());
		}
		map[num] = rowPoints;
	}
	
	public void print(){
		for(int r=0; r<map.length; r++){
			for(int c=0; c<map[0].length;c++){
				System.out.print(map[r][c].toString());
			}
			System.out.println();
		}
	}
}
