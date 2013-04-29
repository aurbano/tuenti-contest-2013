package es.urbanoalvarez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String s;
		int line = -1,
			suggestions = 0;
		String dict = "";
		Search[] searchers = new Search[0];
		
		while ((s = in.readLine()) != null && s.length() != 0){	
			 line++;
			 //System.out.println("Reading line "+line);
			 if(line == 0) continue;
			 if(line == 1){
				 dict = s;
				 //System.out.println("Using dict="+dict);
				 continue;
			 }
			 if(line == 3){
				 suggestions = new Integer(s);
				 searchers = new Search[suggestions];
				 //System.out.println("Suggest="+suggestions);
				 continue;
			 }
			 if(line < 5){
				 //System.out.println("line<5 cont");
				 continue;
			 }
			 //System.out.println("launching "+(line-5));
			 searchers[line-5] = new Search(dict, s); // Launch the searchers
		}
		for(int i=0; i < suggestions; i++){
			 try{
				 searchers[i].join();
				 System.out.print(searchers[i].w+" ->");
				 for(int a=0; a<searchers[i].found.size(); a++){
					 System.out.print(" "+searchers[i].found.get(a));
				 }
				 System.out.println();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }
	}
}
