package es.urbanoalvarez;

import java.io.*;
import java.util.*;

public class P4 {
	public static void main(String[] args) throws IOException{
		/*BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String s;
		while ((s = in.readLine()) != null && s.length() != 0){
			
		}*/
		int nth = 1;
		DataInputStream in = null;
		// Read from file
		try{
			in = new DataInputStream(new BufferedInputStream(new FileInputStream("integers")));
			int next;
			while(true){
				next = in.readInt();
				System.out.println(next);
				break;
			}
		}catch(EOFException e){
			in.close();
		}finally{
			in.close();
		}//*/
		
		/*Scanner in = null;
		try{
			in = new Scanner(new File("integers"));
			System.out.println(in.nextInt());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			in.close();
		}//*/
		
		/*StreamTokenizer st = new StreamTokenizer(new FileReader("integers"));
		int next = 0;
		while(next == 0){
			st.nextToken();
			next = (int) st.nval;
			System.out.println(next);
		}*/
	}
}
