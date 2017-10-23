/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 6/5/2017
* Description: This program will perform an event driven simulation. 
*              It may help us decide how many tellers should be placed to balance the efficiency and cost.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Program1_Event_Driven_Simulation {
    public static void main(String args[]) {
    	Random rand = new Random();
    	final int MAX_NUMBER_OF_TELLER = 9;
	    int number_of_teller = 0;
	    int inter_arrival_mean = 0;
	    int inter_arrival_variance = 0;
	    int customer_service_mean = 0;
	    int customer_service_variance = 0;
	    
	    int time_limit = 0;
	    int clock = 0;
	    
	    int total_inter_arrival_time =0;
	    int total_service_time = 0;
	    
	    int customer_count = 0;
	    int wait_time = 0;
	    int max_wait_time =0;
	    int total_wait_time = 0;
		
		boolean neverMeet500 = true;
		boolean neverMeet1000 = true;
		boolean neverMeet1500 = true;
		boolean neverMeet2000 = true;
		
    	
    	
	    // load each input value into each variable respectively
    	try{
    		File inputFile = new File("input.txt");
		    Scanner input = new Scanner(inputFile);
		    
		    number_of_teller = input.nextInt();
		    inter_arrival_mean = input.nextInt();
		    inter_arrival_variance = input.nextInt();
		    customer_service_mean = input.nextInt();
		    customer_service_variance = input.nextInt();
		    time_limit = input.nextInt();
    	}
		catch (IOException e){
		    System.err.println("IOException in reading input file!!!");
		}
    	
    	// create an array of tellers; the tellers are implemented by a TellerQueue.
    	TellerQueue[] tellerArray = new TellerQueue[MAX_NUMBER_OF_TELLER];
    	for(int i=0; i < number_of_teller; i++){
    		tellerArray[i] = new TellerQueue();
    	}
    	
    	// create an Event Queue, which is implemented by a Priority Queue.
        Comparator<EventItem> comparator = new EventItemComparator();
        PriorityQueue<EventItem> eventQueue = new PriorityQueue<EventItem>(10, comparator);
        
        // place an arrival node onto the eventQueue
        int inter_arrival = uniform(inter_arrival_mean, inter_arrival_variance, rand);
        int customer_service = uniform(customer_service_mean, customer_service_variance, rand);
        eventQueue.add(new EventItem(clock + inter_arrival, customer_service, -1));
    	
        // update total inter_arrival and total cutomer_service time
	    total_inter_arrival_time += inter_arrival;
	    total_service_time += customer_service;
    	
	    /*
	    System.out.println(number_of_teller);
	    System.out.println(inter_arrival_mean);
	    System.out.println(inter_arrival_variance);
	    System.out.println(customer_service_mean);
	    System.out.println(customer_service_variance);	    
	    System.out.println(time_limit);
	    */
	    
    	while(clock < time_limit){
    	    
    		//System.out.println(clock);
    		EventItem customer = eventQueue.remove();
    		for(int i = 0; i < number_of_teller; i++){
    			if(tellerArray[i].isEmpty()){
    				//System.out.printf("idle time for teller %d is: %d\n", i, customer.getTimeOfDay() - clock);
    				tellerArray[i].updateIdle(customer.getTimeOfDay() - clock);
    			}
    		}
    		
    		clock = customer.getTimeOfDay();
    		//System.out.println("clock: " + clock);
    		
    		if(customer.getTypeOfEvent() < 0){ // if the dequeued one is an arrival node(-1)
    			
    			// find the shortest queue
    			int shortestQueueIndex = 0;
    	    	for(int i=1; i<number_of_teller; i++){
    	    		if( tellerArray[i].getLength() < tellerArray[shortestQueueIndex].getLength()){
    	    			shortestQueueIndex = i;
    	    		}
    	    	}
    	    	
    	    	// place the customer onto the shortest queue
    	    	tellerArray[shortestQueueIndex].enqueue(customer);
    	    	//System.out.println("queue " + shortestQueueIndex + " length: "+tellerArray[shortestQueueIndex].getLength());
    	    	
    	    	if(tellerArray[shortestQueueIndex].getLength() ==1){ // if the customer is the only customer on the queue
    	    		// then generate a departure node onto the enventQueue
    	    		//System.out.println("generate departure node");
    	    		eventQueue.add(new EventItem(clock + customer.getServiceTime(), customer.getServiceTime(), shortestQueueIndex));
    	    	}
    	    	
    	    	// place the next arrival node onto the event queue
    	        inter_arrival = uniform(inter_arrival_mean, inter_arrival_variance, rand);
    	        customer_service = uniform(customer_service_mean, customer_service_variance, rand);
    	        eventQueue.add(new EventItem(clock + inter_arrival, customer_service, -1));
    	    	
    	        // update total inter_arrival and total cutomer_service time
    		    total_inter_arrival_time += inter_arrival;
    		    total_service_time += customer_service;
    	    	
    	    	// update max queue Length
    	    	tellerArray[shortestQueueIndex].updateMaxLength();
    		} // end if
    		
    		if(customer.getTypeOfEvent() >= 0){ // if the dequeued one is a departure node
    			// remove the customer from the indicated queue
    			EventItem leavingCustomer = tellerArray[customer.getTypeOfEvent()].dequeue();
    			
    			//System.out.println("departure node performed");
    			customer_count++;
    			wait_time = clock - (leavingCustomer.getTimeOfDay() + leavingCustomer.getServiceTime());
    			//System.out.println("wait time:"+wait_time);
    			
    			// update the max customer wait time
    			if(max_wait_time < wait_time){
    				max_wait_time = wait_time;
    			}
    			
    			// update the total wait time
    			total_wait_time += wait_time;
    			

    			
    			// if the indicated queue is not empty
    			if(!tellerArray[customer.getTypeOfEvent()].isEmpty()){
    				// generate a departure node for the next customer
    				eventQueue.add(new EventItem(
    						clock + tellerArray[customer.getTypeOfEvent()].peek().getServiceTime(), 
    						tellerArray[customer.getTypeOfEvent()].peek().getServiceTime(), 
    						customer.getTypeOfEvent()));
    			}
    			
    		} // end if

    		if(clock >= 500 && neverMeet500){
    			System.out.printf("The clock time: %d\n", clock);
    			for(int i=0; i < number_of_teller; i++){
        			System.out.printf("There are %d customer(s) in Teller %d Queue.\n", tellerArray[i].getLength(), i);
    			}
    			System.out.printf("There are %d item(s) in the Event Queue.\n", eventQueue.size());
    			System.out.println();
    			neverMeet500 = false;
    		}
    		
    		if(clock >= 1000 && neverMeet1000){
    			System.out.printf("The clock time: %d\n", clock);
    			for(int i=0; i < number_of_teller; i++){
        			System.out.printf("There are %d customer(s) in Teller %d Queue.\n", tellerArray[i].getLength(), i);
    			}
    			System.out.printf("There are %d item(s) in the Event Queue.\n", eventQueue.size());
    			System.out.println();
    			neverMeet1000 = false;
    		}
    		
    		if(clock >= 1500 && neverMeet1500){
    			System.out.printf("The clock time: %d\n", clock);
    			for(int i=0; i < number_of_teller; i++){
        			System.out.printf("There are %d customer(s) in Teller %d Queue.\n", tellerArray[i].getLength(), i);
    			}
    			System.out.printf("There are %d item(s) in the Event Queue.\n", eventQueue.size());
    			System.out.println();
    			neverMeet1500 = false;
    		}
    		
    		if(clock >= 2000 && neverMeet2000){
    			System.out.printf("The clock time: %d\n", clock);
    			for(int i=0; i < number_of_teller; i++){
        			System.out.printf("There are %d customer(s) in Teller %d Queue.\n", tellerArray[i].getLength(), i);
    			}
    			System.out.printf("There are %d item(s) in the Event Queue.\n", eventQueue.size());
    			System.out.println();
    			neverMeet2000 = false;
    		}
    	} // end while

    	//calculate and print all summary statistics
    	System.out.printf("The total number of customers processed: %d\n", customer_count);
    	System.out.printf("The average inter-arrival time: %f\n", total_inter_arrival_time/1.0/customer_count);
    	System.out.printf("The average service time: %f\n", total_service_time/1.0/customer_count);
    	System.out.printf("The average wait time per customer: %f\n", total_wait_time/1.0/customer_count);
    	
    	for(int i=0; i < number_of_teller; i++){
    		//System.out.println(tellerArray[i].getTotalIdelTime());
			System.out.printf("The percent of idle time for the cashier %d: %.4f%% \n", i, tellerArray[i].getTotalIdelTime()*100.0/clock);
		}
    	System.out.printf("The maximum customer wait time: %d\n", max_wait_time);
    	
		int realMaxLength = 0;
		int index = 0;
    	for(index=0; index < number_of_teller; index++){
    		if(tellerArray[index].getMaxLength() > realMaxLength){
    			realMaxLength = tellerArray[index].getMaxLength();
    		}
		}
    	System.out.printf("The maximum queue length of any customer queue is: %d (happened on Queue %d)\n", realMaxLength , index);
    	
    	
		int totalLeft = 0;
    	for(int i=0; i < number_of_teller; i++){
    		totalLeft += tellerArray[i].getLength();
		}
    	System.out.printf("The total number of customers left in the queues at the end of the simulation: %d\n", totalLeft);
    } //end main
    
    public static int uniform(int mean, int variant, Random rand){
    	// this function can return a uniformly random,
    	// integer in the range of mean +- variant
    	int small = mean - variant;
    	int range = 2 * variant +1;
    	return small + rand.nextInt(range);
    }
}
