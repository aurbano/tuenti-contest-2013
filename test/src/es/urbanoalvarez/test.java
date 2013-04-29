package es.urbanoalvarez;

import java.io.*;
import java.math.*;

public class test {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    String s;
	    String[] p;
	    BigInteger sum = new BigInteger("0");
	    while ((s = in.readLine()) != null && s.length() != 0){
	      // Split and add the elements
	    	p = s.trim().split(" ");
	    	sum = new BigInteger("0");
	    	for (int i = 0; i < p.length; i++) {
	    		if(p[i].length()>0)
	    			sum = sum.add(new BigInteger(p[i]));
	    	}
	    	System.out.println(sum);
		}
	}
}