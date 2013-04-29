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
			ArrayList<String> future = new ArrayList<String>();
			String sceneText = "";
			int scene=0, index = 0, between = 0, futureIndex = -1;
			boolean list = true;	// If false, just show value of valid
			boolean valid = false;	// Valid/Invalid
			Matcher m = Pattern.compile("([.|<|>])([^.<>]+)").matcher(s);
			while (m.find() && list) {
				sceneText = m.group().substring(1);
				index = scene;
				switch(m.group().substring(0,1)){
					case ".":
						// Check for invalid
						if(reordered.contains(sceneText)){
							list = false;
							valid = false;
							continue;
						}
						futureIndex = future.indexOf(sceneText); 
						if(futureIndex > -1){
							// Remove that element
							future.remove(futureIndex);
							// Do nothing really...
						}else if(!future.isEmpty()){
							between++;
						}
						break;
					case "<":
						// Check if its in future
						futureIndex = future.indexOf(sceneText); 
						if(futureIndex>-1){
							if(between > 1){
								list = false;
								valid = true;
								continue;
							}else{
								between = 0;
								// This is fine, remove from future and index--
								future.remove(futureIndex);
							}
						}
						if(scene > 0) index--;
						break;
					case ">":
						// Ensure that this didn't already happen
						if(reordered.contains(sceneText)){
							valid = false;
							list = false;
						}
						future.add(sceneText);
						continue;
				}
				// Valid or invalid
				//System.out.println("index="+index);
				reordered.add(index, sceneText);
				//System.out.println(m.group());
				scene++;
			}
			if(!future.isEmpty()){
				for(int i=0; i<future.size();i++){
					// There are some elements we didnt insert
					reordered.add(future.get(i));
				}
			}
			if(list){
				for(int i = 0; i < reordered.size(); i++){
					if(reordered.get(i).length()<1) continue;
					System.out.print(reordered.get(i));
					if(i<reordered.size()-1) System.out.print(",");
				}
				System.out.println();
			}else{
				if(valid) System.out.println("valid");
				else System.out.println("invalid");
			}
		}
	}
}