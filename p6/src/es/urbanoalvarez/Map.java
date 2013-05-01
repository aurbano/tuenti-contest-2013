package es.urbanoalvarez;

/**
 * Data class for P6
 * @author Alex
 *
 */
public class Map {
	Item[][] map;
	int h, w;
	Point start, end;
	Item pos;
	
	/**
	 * Creates a new Map given a size. Map will be accessed as map[row][column]
	 * @param m
	 * @param n
	 */
	public Map(int h, int w){
		this.w = w;
		this.h = h;
		map = new Item[h][w];
	}
	
	/**
	 * Define a row for the map
	 * @param row
	 * @param num Row number where you want to insert
	 */
	public void parseRow(String row, int num){
		Item[] rowPoints = new Item[row.length()];
		// Iterate through the columns
		for (int i = 0;i < row.length(); i++){
			int type = 0;
			boolean ice = true;
			switch(row.charAt(i)){
				case '#':
					ice = false;
					break;
				case 'X':
					type = 1;
					start = new Point(num, i);
					break;
				case 'O':
					type = 2;
					end = new Point(num, i);
					break;
			}
			rowPoints[i] = new Item(ice, type);
		}
		map[num] = rowPoints;
	}
	
	/**
	 * Debug function, prints the map
	 */
	public void print(){
		System.out.println("Printing map "+h+" rows and "+w+" cols");
		for(int r=0; r<this.h; r++){
			for(int c=0; c<this.w;c++){
				System.out.print(map[r][c].toString());
			}
			System.out.println();
		}
	}
	
	/**
	 * Calculate all possible moves from given position
	 * @param p
	 * @return Top Right Bottom Left next available Points
	 */
	public Point[] possibleMoves(Point p){
		Point[] next = new Point[4];
		// Test boundaries
		if(p.c >= w  || p.c < 0 || p.r > h || p.r < 0){
			System.out.println(" Point out of bounds!! "+p.toString());
			System.exit(-1);
		}
		/*
		 * Test vertically: Find the closest # to the current position
		 * the column is p.col
		 */
		int first=0, second=0;
		boolean foundPos = false;
		for(int r=0;r<this.h;r++){
			if(p.r == r){
				foundPos = true;
				continue;
			}
			if(!foundPos && map[r][p.c].isRock()){
				first = Math.max(0, Math.min(h-1,r+1));
			}else if(foundPos && map[r][p.c].isRock()){
				second = Math.max(0, Math.min(h-1,r-1));
				continue;
			}
		}
		
		next[0] = new Point(first, p.c);
		next[2] = new Point(second, p.c);
		
		foundPos = false;
		
		// Test horizontally
		for(int c=0;c<this.w;c++){
			if(p.c == c){
				foundPos = true;
				continue;
			}
			if(!foundPos && map[p.r][c].isRock()){
				first = Math.max(0, Math.min(w-1,c+1));
			}else if(foundPos && map[p.r][c].isRock()){
				second = Math.max(0, Math.min(w-1,c-1));
				continue;
			}
		}
		
		next[3] = new Point(p.r, first);
		next[1] = new Point(p.r, second);
		
		return next;
	}
}
