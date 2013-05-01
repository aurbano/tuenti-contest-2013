package es.urbanoalvarez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P6{
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int tests = new Integer(in.readLine());
		// Prepare finders
		FindBestPath[] finder = new FindBestPath[tests];
		
		// Variables
		String s;
		String[] data;
		int w,h, speed, wait;
		Map map;
		
		for(int t = 0; t<tests; t++){
			s = in.readLine();
			data = s.split(" ");
			w = new Integer(data[0]);
			h = new Integer(data[1]);
			speed = new Integer(data[2]);
			wait = new Integer(data[3]);
			
			map = new Map(w,h);
			// Now read the map
			for(int row=0; row < h; row++){
				map.parseRow(in.readLine(), row);
			}
			
			// Start the finder
			finder[t] = new FindBestPath(map, map.start, speed, wait);
		}
		
		// Display results
		for(int t = 0; t<tests; t++){
			try{
				finder[t].join();
				System.out.println(finder[t].time);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}