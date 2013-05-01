package es.urbanoalvarez;


public class tester {
	public static void main(String[] args){
		Word w1 = new Word("hello");
		Word w2 = new Word("my name is hello sir");
		
		System.out.println(w1.equals(w2));
	}
}
