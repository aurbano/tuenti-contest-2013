package es.urbanoalvarez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P7{
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String scores = "{'A': 1, 'C': 3, 'B': 3, 'E': 1, 'D': 2, 'G': 2, 'F': 4, 'I': 1, 'H': 4, 'K': 5, 'J': 8, 'M': 3, 'L': 1, 'O': 1, 'N': 1, 'Q': 5, 'P': 3, 'S': 1, 'R': 1, 'U': 1, 'T': 1, 'W': 4, 'V': 4, 'Y': 4, 'X': 8, 'Z': 10}";
		int duration = 9;
		int rows = 2;
		int cols = 2;
		
		Board board = new Board(rows, cols, scores);

		board.parseRow("B11 B11",0);
		board.parseRow("I11 P11",1);
		
		board.print();
	}
}
