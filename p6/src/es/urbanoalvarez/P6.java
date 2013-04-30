package es.urbanoalvarez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P6{
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// Manual mode
		String s = "4 5 1 3";
		String[] data = s.split(" ");
		int w = new Integer(data[0]),
			h = new Integer(data[1]),
			speed = new Integer(data[2]),
			wait = new Integer(data[3]);
		
		Map map = new Map(h,w);
		map.parseRow("####", 0);
		map.parseRow("#X·#", 1);
		map.parseRow("#..#", 2);
		map.parseRow("#..O", 3);
		map.parseRow("####", 4);
		
		// Data parsed.
		//System.out.println(map.start.toString());
		//System.out.println(map.end.toString());
		map.print();
	}
}