package es.urbanoalvarez;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

public class ReadGroup {
	public static void main(String[] args) throws IOException{
		/*BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String s;
		while ((s = in.readLine()) != null && s.length() != 0){
			
		}*/
		int nth = 1;
		DataInputStream in = null;
		DataOutputStream out = new DataOutputStream(new FileOutputStream("ordered/g2147"));
		//ArrayList<Integer> missing = new ArrayList<Integer>();
		// Missing as hashset
		///Set<Integer> missng = new Set<Integer>();
		Set<Integer> missing = new HashSet<Integer>();
		// Read from file
		try{
			//in = new DataInputStram(new File("integers"));
			in = new DataInputStream(new BufferedInputStream(new FileInputStream("G:/P4/groups/g2147")));
			int current, last = 0, dif = 0;
			long counted = 0;
			while(true){
				// Try adding all to the set, to remove the dupes
				missing.add(in.readInt());
				/*
				// Try grouping numbers by size
				// Remove consecutive dupes
				current = in.readInt();
				
				if(last > 0){
					dif = current - last;
					// sample: 2 missing
					// last = 1, current = 4, dif = 4-1=3, missing? 3 - 1 = 2
					if(dif==2){
						missing.add(current-1);
						// Check if last was missing
						//System.out.println("Cons dupe");
						//break;
					}
				}
				last = current;
				//System.out.println(next);
				
				//counted++;
				//if(counted > 10000) break;
				//*/
			}
		}catch(EOFException e){
			System.out.println("End Of File");
			in.close();
		}
		in.close();
		//System.out.println("Sorting");
		//Collections.sort(missing);
		/*for(int i=0;i<missing.size();i++){
			//System.out.println(missing.get(i));
			out.writeInt(missing.get(i));
		}*/
		for (int each : missing) {
			//System.out.println(each);
			out.writeInt(each);
		}
		System.out.println("Found "+missing.size()+" missing");
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