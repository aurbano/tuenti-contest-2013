package es.urbanoalvarez;

import java.util.LinkedList;

public class FindBestPath extends Thread{
	Point pos;
	
	int m, n, gemNum, Z, reward;
	
	LinkedList<Gem> gems;
	
	public FindBestPath(Point p, int gemNum, int Z, LinkedList<Gem> gems){
		pos = p;
		//this.m = m;
		//this.n = n;
		this.gemNum = gemNum;
		this.Z = Z;
		this.gems = gems;
		
		this.start();
	}
	
	public void run(){
		// Check if any gem is already in pos
		for(int i=0;i < gems.size(); i++){
			if(gems.get(i).pos == pos){
				reward = gems.get(i).value;
				gems.remove(i);
				break;
			}
		}
		
		LinkedList<Node> currentNodes = new LinkedList<Node>();
		LinkedList<Node> nextNodes;
		
		Node tempNode;
		int availGems;
		
		currentNodes.add(new Node(reward, pos)); // Initial node (Including possible gem)
		
		while(currentNodes.size() > 0){
			//System.out.println("Iteration "+currentNodes.size()+" nodes");
			nextNodes = new LinkedList<Node>();
			// Iterate the nodes
			for(Node eachNode : currentNodes){
				//System.out.println("  "+eachNode.toString());
				// Reset the available gems
				availGems = 0;
				// Now calculate all other possible nodes and add them to Next nodes
				for(Gem eachGem : gems){
					if(eachNode.pos.distance(eachGem.pos) > Z) continue;
					/*
					 * Discard gems that are behind other gems
					 * To be an invalid gem the distance without abs has to be greater and same sign
					 * and the column/row has to be also the same, or multiple
					 */
					if(eachGem.isBehind(gems)){
						continue;
					}
					if(eachNode.testGem(eachGem)){
						//System.out.println("    "+eachGem.toString());
						// Add Tested gem
						availGems++;
						// Valid next Gem
						tempNode = eachNode.next(eachGem);
						if(tempNode.ttl >= Z){
							//System.out.println("      Max TTL");
							// Reached end of line, don't add to next nodes
							// update reward
							if(tempNode.ttl == Z){
								//System.out.println("      Reward="+tempNode.reward);
								reward = Math.max(reward, tempNode.reward);
							}
							continue;
						}
						// Discard nodes with small rewards
						if(tempNode.reward < reward/2 && tempNode.ttl > Z/2){
							// Discard this node
							continue;
						}
						// Still didnt reach the end
						nextNodes.add(tempNode);
					}
				}
				if(availGems == 0){
					// No more gems, update reward
					reward = Math.max(reward, eachNode.reward);
				}
			}
			// If there are any nextNodes update currentNodes
			if(nextNodes.size()==0) break;
			currentNodes = nextNodes;
		}
	}
}
