package es.urbanoalvarez;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

public class ReadMissing {
	public static void main(String[] args) throws IOException{
		/*BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String s;
		while ((s = in.readLine()) != null && s.length() != 0){
			
		}*/
		int nth = 1;
		DataInputStream in = null;
		DataOutputStream out = new DataOutputStream(new FileOutputStream("ordered/g2147-inorder"));
		ArrayList<Integer> reorder = new ArrayList<Integer>();
		// Read from file
		try{
			//in = new DataInputStram(new File("integers"));
			in = new DataInputStream(new BufferedInputStream(new FileInputStream("ordered/g2147")));
			int current, last = 0, dif = 0;
			long counted = 0;
			while(true){
				
				current = in.readInt();
				//System.out.println(current);
				reorder.add(current);
				
				/*if(current > last){
					missing.add(current-dif);
				}
				last = current;
				System.out.println(current);
				*/
				//counted++;
				//if(counted > 100) break;
				
			}
		}catch(EOFException e){
			System.out.println("End Of File");
			in.close();
		}
		in.close();
		System.out.println("Sorting");
		Collections.sort(reorder);
		for(int i=0;i<reorder.size();i++){
			//System.out.println(missing.get(i));
			out.writeInt(reorder.get(i));
		}
		System.out.println("Reordered: "+reorder.size());
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