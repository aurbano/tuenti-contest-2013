package es.urbanoalvarez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P5{
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String s;
		int tests;
		
		// Number of tests
		tests = new Integer(in.readLine());
		FindBestPath[] finder = new FindBestPath[tests];
		// Variables per test
		String[] p;
		Point pos;
		int Z, gemNum;
		String gemVals;
		LinkedList<Gem> gems;
		
		for(int i=0; i< tests;i++){
			s = in.readLine(); // MxN
			p = in.readLine().split(","); // X,Y
			pos = new Point(new Integer(p[0]),new Integer(p[1]));
			Z = new Integer(in.readLine());
			gemNum = new Integer(in.readLine());
			gemVals = in.readLine();
			gems = parseGems(gemVals);
			
			// Launch tester
			finder[i] = new FindBestPath(pos, gemNum, Z, gems);
		}
		
		try{
			// Wait for them
			for(int i=0; i< tests;i++){
				finder[i].join();
				System.out.println(finder[i].reward);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Return LinkedList with gems 
	 * @param data
	 * @return
	 */
	private static LinkedList<Gem> parseGems(String data){
		String[] p = data.split("#");
		String[] gemData;
		LinkedList<Gem> ret = new LinkedList<Gem>();
		for(int i=0; i < p.length; i++){
			gemData = p[i].split(",");
			ret.add(new Gem(new Integer(gemData[2]), new Integer(gemData[0]), new Integer(gemData[1])));
		}
		return ret;
	}
}