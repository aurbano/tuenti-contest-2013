package es.urbanoalvarez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    int line = 0;
	    long budget=0, bitcoins=0, current, next;
	    String s;
	    String[] p;
	    while ((s = in.readLine()) != null && s.length() != 0){	
	    	if(s.length() < 0) continue;
	    	if(line==0){
	    		line ++;
	    		continue;
	    	}
	    	if(line%2!=0){
	    		budget = new Long(s);
	    	    bitcoins = 0;
	    		line++;
	    		continue;
	    	}
	    	
	    	p = s.trim().split(" ");
	    	
	    	for (int i = 0; i < p.length; i++) {
	    		
	    		// bounds
	    		if(i < p.length-1){
	    			current = new Long(p[i]);
	    			next = new Long(p[i+1]);
	    			if(next < current){
	    				// Sell
	    				budget += bitcoins * current;
	    				bitcoins = 0;
	    			}else if(next > current && budget > current){
	    				// Buy
	    				bitcoins += Math.floor(budget / current);
	    				budget -= bitcoins * current;
	    			}
	    			continue;
	    		}
	    		if(bitcoins > 0){
	    			budget += bitcoins * new Integer(p[i]);
    				bitcoins = 0;
	    		}
	    	}
	    	
	    	System.out.println(budget);
	    	
	    	line++;
		}
	}
}