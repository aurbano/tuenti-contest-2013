package es.urbanoalvarez;

import java.io.*;
<<<<<<< HEAD
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
=======
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Challenge explanation
 * 
 * I first decided to split the big integers file in smaller files, each with 999999 integers.
 * Each file was named g0, g1, g2... g2147, containing each group.
 * After that, I checked the filesizes, seeing that there were only integers missing in the first and last groups
 * But the first group was packed with duplicates, so I removed all the duplicates, and then I sorted the results
 * Another class took the sorted groups g0 and g2147 and looked for missing integers, and stored the missing ones in a file
 * called "final" (attached in the bin folder)
 * Lastly it was as easy as reading the missing integers into an arraylist and accessing the correct indexes.
 * @author Alex
 *
 */
public class P4 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader inData = new BufferedReader(new InputStreamReader(System.in)); 
		String s;
		int line = -1, current;
		// Read file
		DataInputStream in = null;
		ArrayList<Integer> missing = new ArrayList<Integer>();
		try{
			//in = new DataInputStram(new File("integers"));
			in = new DataInputStream(new BufferedInputStream(new FileInputStream("final")));
			while(true){
				current = in.readInt();
				missing.add(current);
				//System.out.println(current);
			}
			
		}catch(EOFException e){
			//System.out.println("End Of File");
			in.close();
		}
		//System.out.println("Waiting for input");
		// Read input and return data
		while ((s = inData.readLine()) != null && s.length() != 0){
			line++;
			if(line < 1) continue;
			System.out.println(missing.get(new Integer(s)-1));
		}
		inData.close();	
	}
}
>>>>>>> P4
