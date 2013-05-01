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
			System.out.println("Iteration "+iter+": "+currentNodes.size()+" nodes");
			nextNodes = new LinkedList<Node>();
			// Iterate the nodes
			for(Node eachNode : currentNodes){
				System.out.println("  "+eachNode.toString());
				// Reset the available moves
				availMoves = 0;
				// Test possible moves
				possibleMoves = map.possibleMoves(eachNode.pos);
				for(Point testPoint : possibleMoves){
					// Discard current
					if(testPoint.equals(eachNode.pos)) continue;
					
					// Discard already visited Points
					if(!eachNode.testPoint(testPoint)) continue;
					
					// Discard tested points
					if(testedPoints.contains(testPoint)){
						System.out.println("    Already tested "+testPoint.toString());
						continue;
					}
					
					System.out.println("    Valid move:"+testPoint.toString());
					availMoves++;
					
					tempNode = eachNode.next(testPoint);
					
					// Is it the end?
					if(testPoint.equals(map.end)){
						time = Math.min(time,  tempNode.time);
						System.out.println("    End reached in "+time+"s!");
						continue;
					}
					// Store as a tested point
					testedPoints.add(testPoint);
					// Still didn't reach the end, increment the wait and continue
					tempNode.time += wait;
					
					// Discard if it's already longer than a valid time
					if(tempNode.time > time){
						System.out.println("     Too long, already found a better option");
						continue;
					}
					
					// Valid node
					nextNodes.add(tempNode);
				}
				
				if(availMoves == 0){
					// No more moves, did you reach the end?
					System.out.println("    No more moves...");
					//reward = Math.max(reward, eachNode.reward);
				}
			}
			// If there are any nextNodes update currentNodes
			if(nextNodes.size()==0) break;
			currentNodes = nextNodes;
			iter++;
			//if(iter > 5) break;
		}
	}
}