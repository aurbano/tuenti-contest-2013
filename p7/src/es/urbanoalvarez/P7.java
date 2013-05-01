package es.urbanoalvarez;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class P7{
	static ArrayList<Word> words;
	static int wordsNum;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String scores = "{'A': 1, 'C': 3, 'B': 3, 'E': 1, 'D': 2, 'G': 2, 'F': 4, 'I': 1, 'H': 4, 'K': 5, 'J': 8, 'M': 3, 'L': 1, 'O': 1, 'N': 1, 'Q': 5, 'P': 3, 'S': 1, 'R': 1, 'U': 1, 'T': 1, 'W': 4, 'V': 4, 'Y': 4, 'X': 8, 'Z': 10}";
		int duration = 9;
		int rows = 3;
		int cols = 4;
		
		Board board = new Board(rows, cols, scores);
		
		// Load dictionary in memory
		words = new ArrayList<Word>(); // Words from the dictionary, in memory
		BufferedReader dict = new BufferedReader(new FileReader("boozzle-dict.txt"));
		String s;
		while ((s = dict.readLine()) != null && s.length() != 0){
			words.add(new Word(s));
			wordsNum++;
		}
		dict.close();
		
		System.out.println("Loaded "+wordsNum+" words");

		board.parseRow("C11 B11 C11 D11",0);
		board.parseRow("E11 A11 G11 H11",1);
		board.parseRow("I11 J11 R11 L11",2);
		
		board.print();
		
		FindBestScore finder = new FindBestScore(board, duration);
	}
}
