package es.urbanoalvarez;

public class Tester {
	public static void main(String[] args){
		// Test maps manually
		int speed = 5,
			wait = 7;
		Map map = new Map(10, 10);
		
		map.parseRow("##########", 0);
		map.parseRow("#X·······#", 1);
		map.parseRow("#·########", 2);
		map.parseRow("#·······##", 3);
		map.parseRow("#·····#··#", 4);
		map.parseRow("#···#····#", 5);
		map.parseRow("##····#··#", 6);
		map.parseRow("#··#·····#", 7);
		map.parseRow("#···#····O", 8);
		map.parseRow("##########", 9);
		
		/*map.parseRow("##########", 0);
		map.parseRow("#X#···#··#", 1);
		map.parseRow("#········#", 2);
		map.parseRow("#········#", 3);
		map.parseRow("#········#", 4);
		map.parseRow("#········#", 5);
		map.parseRow("#········#", 6);
		map.parseRow("#········#", 7);
		map.parseRow("#···#··#·O", 8);
		map.parseRow("##########", 9);*/
		
		/*map.parseRow("######", 0);
		map.parseRow("#X#··#", 1);
		map.parseRow("#····#", 2);
		map.parseRow("#····#", 3);
		map.parseRow("##···#", 4);
		map.parseRow("#··###", 5);
		map.parseRow("#····O", 6);
		map.parseRow("######", 7);*/
		
		map.print();
		
		FindBestPath finder = new FindBestPath(map, map.start, speed, wait);
		
		try {
			finder.join();
			System.out.println(finder.time);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		
	}
}
