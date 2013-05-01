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
	public Map(int w, int h){
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
		Item[] rowPoints = new Item[w];
		// Iterate through the columns
		int type, write = 0;
		boolean ice;
		for (int i = 0;i < row.length(); i++){
			type = 0;
			ice = true;
			switch(row.charAt(i)){
				case '#':
					ice = false;
					break;
				case 'X':
					type = 1;
					start = new Point(num, write);
					break;
				case 'O':
					type = 2;
					end = new Point(num, write);
					break;
				default:
					// One of those weird chars...
					i++;
			}
			rowPoints[write] = new Item(ice, type);
			write++;
		}
		map[num] = rowPoints;
	}
	
	/**
	 * Debug function, prints the map
	 */
	public void print(){
		System.out.println("Printing map "+h+" rows and "+w+" cols");
		System.out.println("Start: "+start.toString());
		System.out.println("End: "+end.toString());
		System.out.print("0 ");
		for(int r=0; r<this.h; r++){
			for(int c=0; c<this.w;c++){
				System.out.print(map[r][c].toString());
			}
			System.out.println();
			System.out.print((r+1)+" ");
		}
		System.out.println("------------");
	}
	
	/**
	 * Calculate all possible moves from given position
	 * @param p
	 * @return Top Right Bottom Left next available Points
	 */
	public Point[] possibleMoves(Point p){
		Point[] next = new Point[4];
		// Test boundaries (This should never be true...)
		if(p.c >= w  || p.c < 0 || p.r > h || p.r < 0){
			System.out.println(" Point out of bounds!! "+p.toString());
			System.exit(-1);
		}
		// Assing the current position to all next values, default
		next[0] = new Point(p);
		next[1] = new Point(p);
		next[2] = new Point(p);
		next[3] = new Point(p);
		
		// Find top move
		for(int r=p.r-1; r>=0; r--){
			if(map[r][p.c].isEnd()){
				next[0] = new Point(r, p.c);
				break;
			}
			if(map[r][p.c].isRock()){
				next[0] = new Point(r+1, p.c);
				break;
			}
		}
		// Find right move
		for(int c=p.c+1; c<w; c++){
			if(map[p.r][c].isEnd()){
				next[1] = new Point(p.r, c);
				break;
			}
			if(map[p.r][c].isRock()){
				next[1] = new Point(p.r, c-1);
				break;
			}
		}
		// Find bottom move
		for(int r=p.r+1; r<h; r++){
			if(map[r][p.c].isEnd()){
				next[2] = new Point(r, p.c);
				break;
			}
			if(map[r][p.c].isRock()){
				next[2] = new Point(r-1, p.c);
				break;
			}
		}
		// Find left move
		for(int c=p.c-1; c>=0; c--){
			if(map[p.r][c].isEnd()){
				next[3] = new Point(p.r, c);
				break;
			}
			if(map[p.r][c].isRock()){
				next[3] = new Point(p.r, c+1);
				break;
			}
		}
		
		return next;
	}
}
