package es.urbanoalvarez;

import java.io.*;
import java.util.*;

public class Search extends Thread{
	String dict, w;
	char[] word;
	
	ArrayList<String> found = new ArrayList<String>();
	
	public Search(String d, String w){
		//System.out.println("Search: dict="+d+"; word="+w);
		this.w = w;
		dict = d;
		word = w.toCharArray();
		Arrays.sort(word);
		this.start();
	}
	
	public void run(){
		// Open dictionary
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File (dict)));
			String line;
			
			// Create char array for searched word
			
			while((line = br.readLine()) != null){
				if(!line.equals(w) && sameChars(line)){
					//System.out.println("Found: "+line);
					found.add(line);
				}
			}
			
			// Sort
			Collections.sort(found);
			
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Compare two strings' characters
	private boolean sameChars(String check) {
	  char[] first = check.toCharArray();
	  Arrays.sort(first);
	  return Arrays.equals(first, word);
	}
}
