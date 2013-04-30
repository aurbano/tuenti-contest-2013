package es.urbanoalvarez;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

public class FindMissing {
	public static void main(String[] args) throws IOException{
		/*BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String s;
		while ((s = in.readLine()) != null && s.length() != 0){
			
		}*/
		int nth = 1;
		DataInputStream in = null;
		DataOutputStream[] out = new DataOutputStream[2148];
		// Read from file
		try{
			//in = new DataInputStram(new File("integers"));
			in = new DataInputStream(new BufferedInputStream(new FileInputStream("integers")));
			int next;
			long counted = 0;
			while(true){
				// Try grouping numbers by size
				next = read(in);
				int output = (int) (next / 1000000);
				String name = String.valueOf(output);
				//System.out.println(next);
				//System.out.println(name);
				
				// Write to group file
				if(out[output] == null){
					out[output] = new DataOutputStream(new FileOutputStream("groups/g"+name));
				}	
				out[output].writeInt(next);
				//counted++;
				//if(counted > 4) break;
			}
		}catch(EOFException e){
			System.out.println("End Of File");
			in.close();
		}finally{
			System.out.println("Finally");
			in.close();
			//out.close();
		}
		System.out.println("After try/catch");
	}
	
	/**
	 * Custom reading method for LITTLE_ENDIAN input
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static int read(DataInputStream in) throws IOException{
		byte[] first = new byte[4];
		in.read(first);
		ByteBuffer wrapped = ByteBuffer.wrap(first); // big-endian by default
		wrapped.order(ByteOrder.LITTLE_ENDIAN);
		return wrapped.getInt(); // 1
	}
}
