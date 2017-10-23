import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 6/18/2017
* Description: This program will solve the skyline problem by 
* 				both divide-and-conquer and induction.
 */

public class Program2_Skyline {

	public static void main(String[] args) {
		
		// The following is for debugging
		/*
		ArrayList<Building> buildingList = loadBuildings("sky1.dat");
		for(int i =0; i < buildingList.size(); i++){
			System.out.printf("%2d %2d %2d\n",
					buildingList.get(i).getLeft(),
					buildingList.get(i).getHeight(),
					buildingList.get(i).getRight());
		}*/
		
		/*
		PriorityQueue<Spike> Q = buildingToSpike(buildingList);
		while(!Q.isEmpty()){
			System.out.println(Q.peek().getPoint() + " " + Q.remove().getHeight());
		}*/
		
		System.out.println("================Induction Method====================");
		System.out.println("--------------------sky1.dat--------------------");
		spikeToSkyline(buildingToSpikeQueue(loadBuildings("sky1.dat")));
		System.out.println();
		
		System.out.println("--------------------sky2.dat--------------------");
		spikeToSkyline(buildingToSpikeQueue(loadBuildings("sky2.dat")));
		System.out.println();
		
		System.out.println("--------------------sky3.dat--------------------");
		spikeToSkyline(buildingToSpikeQueue(loadBuildings("sky3.dat")));
		System.out.println();
		
		/*
		System.out.println("--------------------sky4.dat--------------------");
		spikeToSkyline(buildingToSpike(loadBuildings("sky4.dat")));
		System.out.println();
		*/
		
		System.out.println("============Divide-and-Conquer Method==================");
		System.out.println("--------------------sky1.dat--------------------");
		parseSkyline(dcSkyline(loadBuildings("sky1.dat")));
		System.out.println();
		
		System.out.println("--------------------sky2.dat--------------------");
		parseSkyline(dcSkyline(loadBuildings("sky2.dat")));
		System.out.println();
		
		System.out.println("--------------------sky3.dat--------------------");
		parseSkyline(dcSkyline(loadBuildings("sky3.dat")));
		System.out.println();
		
		/*
		// the following is for debugging use.
		ArrayList<Building> buildingList1 = loadBuildings("sky1.dat");
		ArrayList<Spike> skyline = dcSkyline(buildingList1);
		parseSkyline(skyline);
		System.out.println();
		*/
		
	}

	public static ArrayList<Spike> dcSkyline(
			ArrayList<Building> buildingList){
		
		int n = buildingList.size();
		// if there is only one building, we need to convert it to spike
		if(n == 1){
			ArrayList<Spike> spikeList = new ArrayList<Spike>();
			spikeList.add(new Spike(buildingList.get(0).getLeft(), buildingList.get(0).getHeight()));
			spikeList.add(new Spike(buildingList.get(0).getRight(), 0 ));
			return spikeList;
		}
		
		
		ArrayList<Building> buildingListA = new ArrayList<Building>();
		for (int i=0; i < n/2; i++){
			buildingListA.add(buildingList.get(i));
		}
		
		ArrayList<Building> buildingListB = new ArrayList<Building>();
		for (int i=n/2; i < n; i++){
			buildingListB.add(buildingList.get(i));
		}
		
		ArrayList<Spike> spikeListLeft = dcSkyline(buildingListA);
		ArrayList<Spike> spikeListRight = dcSkyline(buildingListB);
		
		return mergeSkyline(spikeListLeft, spikeListRight);
	}
	
	// divide-and-conquer way to build skyline
	public static ArrayList<Spike> mergeSkyline(
			ArrayList<Spike> spikeListA, ArrayList<Spike> spikeListB){
		ArrayList<Spike> newSpikeList = new ArrayList<Spike>();
        
		int indexA = 0;
		int indexB = 0;
		int heightA = 0;
		int heightB = 0;
		int newHeight = 0;
		while(!spikeListA.isEmpty() && !spikeListB.isEmpty()){
			if(spikeListA.get(indexA).getPoint() <= spikeListB.get(indexB).getPoint()){
				heightA = spikeListA.get(indexA).getHeight();
				newHeight = Math.max(heightA, heightB);
				
				Spike newSpike = spikeListA.remove(indexA);
				newSpike.setHeight(newHeight);
				newSpikeList.add(newSpike);
				//indexA++;
			}
			else{
				heightB = spikeListB.get(indexB).getHeight();
				newHeight = Math.max(heightA, heightB);

				Spike newSpike = spikeListB.remove(indexB);
				newSpike.setHeight(newHeight);
				newSpikeList.add(newSpike);
				//indexB++;
			}
		}
		if(spikeListB.isEmpty()){
			newSpikeList.addAll(spikeListA);
		}
		else if(spikeListA.isEmpty()){
			newSpikeList.addAll(spikeListB);
		}

		return newSpikeList;
	}

	public static void parseSkyline(ArrayList<Spike> skyline){
		Spike previous = skyline.get(0);
		Spike current = null;

		for(int j = 0; j < skyline.size(); j++){
			current = skyline.get(j);
			
			if(previous.getPoint() != current.getPoint() & previous.getPoint() != 0){
				if(previous.getHeight() != current.getHeight()){
					printSpike(previous, true);
					previous = current;
				}
			}
			else{
				previous = current;
			}
		}
		System.out.println();
	}
	
	// induction way to build Skyline
	public static void spikeToSkyline(PriorityQueue<Spike> spikeList){		
        Comparator<Spike> comparator = new SpikeComparator();
        PriorityQueue<Spike> newSpikeQueue = new PriorityQueue<Spike>(10, comparator);

        Spike previous;
        Spike current;

        previous = spikeList.remove();
        newSpikeQueue.add(previous);
        	
		while(!spikeList.isEmpty()){
			current = spikeList.remove();
				
			if(previous.getPoint() == current.getPoint()){
				if(previous.getHeight() < current.getHeight()){
					// discard the previous node 
					// since it is not the highest in same position
					// spikeList.
					newSpikeQueue.remove(previous);
					newSpikeQueue.add(current);
					previous = current;
				}
			}
			else{
				newSpikeQueue.add(current);
				previous = current;
			}
		}
		
		// convert to Skyline
        previous = newSpikeQueue.remove();
        
		while(!newSpikeQueue.isEmpty()){
			current = newSpikeQueue.remove();
			
			if(previous.getHeight() == current.getHeight()){
			}
			else if(previous.getHeight() < current.getHeight()){
				printSpike(current, true);
				previous = current;
			}
			else{
				printSpike(current, false);
				previous = current;
			}
		}
		
		System.out.println();
        
        /*// debug use
		while(!newSpikeQueue.isEmpty()){
			System.out.println(newSpikeQueue.peek().getPoint() + " " + newSpikeQueue.remove().getHeight());
		}*/
		

	}
	
	public static void printSpike(Spike aSpike, boolean rise){
		if(rise){
			System.out.printf("%2d %2d\n", aSpike.getPoint(), aSpike.getHeight());
		}
		else{
			System.out.printf("%2d %2d\n", aSpike.getPoint() - 1, aSpike.getHeight());
		}
	}
	
	public static PriorityQueue<Spike> buildingToSpikeQueue(ArrayList<Building> buildingList){
        Comparator<Spike> comparator = new SpikeComparator();
        PriorityQueue<Spike> spikeQueue = new PriorityQueue<Spike>(10, comparator);
        
        // This is where induction used
		for(int i =0; i < buildingList.size(); i++){
			int left = buildingList.get(i).getLeft();
			int right = buildingList.get(i).getRight();
			int height = buildingList.get(i).getHeight();
			for (int position = left; position <= right; position++){
				spikeQueue.add(new Spike(position, height));
			}
		}
        
        return spikeQueue;
	}

	/*
	public static ArrayList<Spike> buildingToSpikeList(ArrayList<Building> buildingList){
        ArrayList<Spike> spikeList = new ArrayList<Spike>();
        
        // This is where induction used
		for(int i =0; i < buildingList.size(); i++){
			int left = buildingList.get(i).getLeft();
			int right = buildingList.get(i).getRight();
			int height = buildingList.get(i).getHeight();
			for (int position = left; position <= right; position++){
				spikeList.add(new Spike(position, height));
			}
		}
        
        return spikeList;
	}*/
	
	public static ArrayList<Building> loadBuildings(String fileName){
	    int farLeft = 9999999;
	    int farRight = -1;
		ArrayList<Building> buildingList = new ArrayList<Building>();
	    // load each input value into the building respectively
    	try{
    		File inputFile = new File(fileName);
		    Scanner input = new Scanner(inputFile);
		    
		    while(input.hasNext()){
		    	int left = input.nextInt();
		    	int height = input.nextInt();
		    	int right = input.nextInt();
		    	
		    	if(farRight < right){
		    		farRight = right;
		    	}
		    	if(farLeft > left){
		    		farLeft = left;
		    	}
		    	buildingList.add(new Building(left, height, right));
		    }
	    	input.close();
    	}
		catch (IOException e){
		    System.err.println("IOException in reading input file!!!");
		}

		// important base building
		buildingList.add(new Building(--farLeft, 0, ++farRight)); 
		
    	return buildingList;
	}
	
	public static ArrayList<Spike> inductSkyline(Building b, ArrayList<Spike> skyline){
		for(int i =0; i < skyline.size(); i++){
			// if the building is in-between two spikes
			if(skyline.get(i).getPoint() <= b.getLeft() &&
					skyline.get(i).getPoint() >= b.getRight()){
				if(skyline.get(i).getHeight() < b.getHeight()){
					skyline.get(i).setHeight(b.getHeight());
				}
			}
		}
		return skyline;
	}
}