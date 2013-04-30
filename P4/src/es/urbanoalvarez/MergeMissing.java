package es.urbanoalvarez;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

public class MergeMissing {
	public static void main(String[] args) throws IOException{
		DataInputStream in = null;
		DataOutputStream out = new DataOutputStream(new FileOutputStream("ordered/final"));
		// Merge missing integers in one file
		int next = 0;
		try{
			//in = new DataInputStram(new File("integers"));
			in = new DataInputStream(new BufferedInputStream(new FileInputStream("ordered/missing")));
			while(true){
				next = in.readInt();
				System.out.println(next);
				out.writeInt(next);				
			}
		}catch(EOFException e){
			System.out.println("End Of File 1");
			in.close();
		}
		// Other file
		try{
			in = new DataInputStream(new BufferedInputStream(new FileInputStream("ordered/missing-G2147")));
			while(true){
				next = in.readInt();
				System.out.println(next);
				out.writeInt(next);
			}
		}catch(EOFException e){
			System.out.println("End Of File 2");
			in.close();
		}
		System.out.println("Merged");
		out.close();
	}
}