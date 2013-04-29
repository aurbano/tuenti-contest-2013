package es.urbanoalvarez;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.*;

public class P3{
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String s;
		int scripts = 0,
			line = -1;
		String[] scenes;
		String[] subscenes;
		while ((s = in.readLine()) != null && s.length() != 0){
			line++;
			if(line == 0){
				scripts = new Integer(s);
				continue;
			}
			ArrayList<String> reordered = new ArrayList<String>();
			String ret = "";
			int scene=0,index = 0;
			boolean goBack = false;	// Flag to reorder future scenes
			boolean list = true;	// If false, just show value of valid
			boolean valid = false;	// Valid/Invalid
			Matcher m = Pattern.compile("([.|<|>])([^.<>]+)").matcher(s);
			while (m.find()) {
				index = scene;
				switch(m.group().substring(0,1)){
					case ".":
						if(goBack) index --;
						goBack = false;
						break;
					case "<":
						if(scene > 0) index--;
						break;
					case ">":
						goBack = true;
						break;
				}
				// Valid or invalid
				//System.out.println("index="+index);
				reordered.add(index, m.group().substring(1));
				//System.out.println(m.group());
				scene++;
			}
			
			for(int i = 0; i < reordered.size(); i++){
				if(reordered.get(i).length()<1) continue;
				System.out.print(reordered.get(i));
				if(i<reordered.size()-1) System.out.print(",");
			}
			System.out.println();
		}
	}
}