package es.urbanoalvarez;

import java.util.LinkedList;

public class FindBestPath extends Thread{
	
	Point pos;
	
	int speed, wait, time;
	Map map;
	
	public FindBestPath(Map m, Point pos, int speed, int wait){
		map = m;
		this.speed = speed;
		this.wait = wait;
		time = 99999; // Best time found
		this.pos = pos; // Starting position
		
		this.start();
	}
	
	public void run(){
		/*
		 * This will run similarly to my P5 solution
		 * Testing all possible moves, but discarding moves that are obviously worse/tested
		 */
		LinkedList<Point> testedPoints = new LinkedList<Point>();
		LinkedList<Node> currentNodes = new LinkedList<Node>();
		LinkedList<Node> nextNodes;
		Point[] possibleMoves;
		
		Node tempNode;
		int availMoves, iter = 0;
		
		currentNodes.add(new Node(wait, pos, speed)); // Initial node (Including possible gem)
		
		while(currentNodes.size() > 0){
			debug("Iteration "+iter+": "+currentNodes.size()+" nodes");
			nextNodes = new LinkedList<Node>();
			// Iterate the nodes
			for(Node eachNode : currentNodes){
				debug("  "+eachNode.toString());
				// Discard if it's already longer than a valid time
				if(eachNode.time >= time){
					debug("      Too long");
					continue;
				}
				// Reset the available moves
				availMoves = 0;
				// Test possible moves
				possibleMoves = map.possibleMoves(eachNode.pos);
				for(Point testPoint : possibleMoves){
					// Discard current and start
					if(testPoint.equals(eachNode.pos) || testPoint.equals(map.start)) continue;
					
					// Discard already visited Points
					if(!eachNode.testPoint(testPoint)) continue;
					
					// Discard tested points
					if(testedPoints.contains(testPoint)){
						debug("    Already tested "+testPoint.toString());
						continue;
					}
					
					tempNode = eachNode.next(testPoint);
					
					debug("    Valid move:"+testPoint.toString()+", distance="+testPoint.distance(eachNode.pos)+", time="+tempNode.time);
					availMoves++;
					
					// Is it the end?
					if(testPoint.equals(map.end)){
						time = Math.min(time,  tempNode.time);
						debug("      --{{End"+testPoint.toString()+" reached in "+tempNode.time+"s!}}--\n");
						continue;
					}
					// Store as a tested point
					testedPoints.add(testPoint);
					// Still didn't reach the end, increment the wait and continue
					tempNode.time += wait;
					
					// Discard if it's already longer than a valid time
					if(tempNode.time >= time){
						debug("      Too long");
						continue;
					}
					
					// Valid node
					nextNodes.add(tempNode);
				}
				
				if(availMoves == 0){
					// No more moves, did you reach the end?
					debug("    No more moves...");
				}
			}
			// If there are any nextNodes update currentNodes
			if(nextNodes.size()==0) break;
			currentNodes = nextNodes;
			iter++;
		}
	}
	
	/**
	 * Simply display a text if I wanted to (comment out the system out for submit)
	 * @param text
	 */
	public void debug(String text){
		//System.out.println(text);
	}
}