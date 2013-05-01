package es.urbanoalvarez;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P7{
	static ArrayList<Word> words;
	static int wordsNum;
	
	public static void main(String[] args) throws IOException{
		// Load dictionary in memory
		words = new ArrayList<Word>(); // Words from the dictionary, in memory
		BufferedReader dict = new BufferedReader(new FileReader("boozzle-dict.txt"));
		String s;
		while ((s = dict.readLine()) != null && s.length() != 0){
			words.add(new Word(s));
			wordsNum++;
		}
		dict.close();
		
		// Now read input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int tests = new Integer(in.readLine()),
			duration, rows, cols;
		String scores;
		Board board;
		
		// Finders
		FindBestScore[] finder = new FindBestScore[tests];
				
		for(int i=0; i<tests; i++){
			scores = in.readLine();
			duration = new Integer(in.readLine());
			rows = new Integer(in.readLine());
			cols = new Integer(in.readLine());
			
			board = new Board(rows, cols, scores);
			
			// Read board data
			for(int row = 0; row<rows; row++){
				board.parseRow(in.readLine(),row);
			}
			
			finder[i] = new FindBestScore(board, duration);
		}
		
		// Display results
		for(int t = 0; t<tests; t++){
			try{
				finder[t].join();
				System.out.println(finder[t].points);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
}
