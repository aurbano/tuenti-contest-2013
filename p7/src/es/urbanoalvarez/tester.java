package es.urbanoalvarez;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.ArrayList;


public class tester {
	static LinkedHashMap<Character,ArrayList<Word>> words;
	
	public static void main(String[] args) throws InterruptedException, IOException{
		
		// Load dictionary in memory
		words = new LinkedHashMap<Character,ArrayList<Word>>(); // Words from the dictionary, in memory
		BufferedReader dict = new BufferedReader(new FileReader("boozzle-dict.txt"));
		String s;
		while ((s = dict.readLine()) != null && s.length() != 0){
			if(words.get(s.charAt(0))==null){
				words.put(s.charAt(0), new ArrayList<Word>());
			}
			words.get(s.charAt(0)).add(new Word(s));
			//wordsNum++;
		}
		dict.close();
		
		String scores = "{'A': 1, 'C': 3, 'B': 3, 'E': 1, 'D': 2, 'G': 2, 'F': 4, 'I': 1, 'H': 4, 'K': 5, 'J': 8, 'M': 3, 'L': 1, 'O': 1, 'N': 1, 'Q': 5, 'P': 3, 'S': 1, 'R': 1, 'U': 1, 'T': 1, 'W': 4, 'V': 4, 'Y': 4, 'X': 8, 'Z': 10}";
		Board board = new Board(4, 4, scores);
		board.parseRow("K11 X11 H11 V11",0);
		board.parseRow("K11 F12 R11 S11",1);
		board.parseRow("D11 K11 D11 P12",2);
		board.parseRow("Q11 D11 A11 N11",3);
		int duration = 55;//*/
		
		/*String scores = "{'A': 1, 'C': 3, 'B': 3, 'E': 1, 'D': 2, 'G': 2, 'F': 4, 'I': 1, 'H': 4, 'K': 5, 'J': 8, 'M': 3, 'L': 1, 'O': 1, 'N': 1, 'Q': 5, 'P': 3, 'S': 1, 'R': 1, 'U': 1, 'T': 1, 'W': 4, 'V': 4, 'Y': 4, 'X': 8, 'Z': 10}";
		Board board = new Board(2, 2, scores);
		board.parseRow("B11 B11", 0);
		board.parseRow("I11 P11", 1);
		int duration = 10;//*/
		
		board.print();
		
		FindBestScore finder = new FindBestScore(board, duration);
		
		finder.join();
		System.out.println(finder.points);
	}
}
