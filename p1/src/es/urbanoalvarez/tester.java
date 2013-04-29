package es.urbanoalvarez;

import java.util.LinkedList;

public class tester extends Thread {
	LinkedList<Integer> values = new LinkedList<Integer>();
	int budget;
	int test;
	int bitcoins = 0;
	public tester(int b, LinkedList<Integer> val, int bitcoins, int test){
		values = val;
		budget = b;
		this.test = test;
		start();
	}
	
	public void run(){
		// Decide something and send another thread with the other decision
		// Buy?
		if(values.get(0)< budget){
			// Enough money to buy
			bitcoins += Math.floor(budget / values.get(0));
			budget -= bitcoins * values.get(0);
		}else if(bitcoins > 0){ // Sell?
			budget += bitcoins * values.get(0);
			bitcoins = 0;
		}
	}
}
