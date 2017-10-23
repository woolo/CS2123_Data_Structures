/**
* @author Zimo Chai (Jerry)
* Student ID: 1495687
* Lab Section: 1
* Date: 6/9/2017
* Description: 
 */

import java.util.Random;

public class Program3_Hash_Table {

	public static void main(String[] args) {
		final int TABLE_SIZE = 1019;
		final int NUMBER_OF_KEY_60 = 611;
		final int NUMBER_OF_KEY_80 = 815;
		final int range = 10000+1;
		
		int total_successful_probing_time_1 = 0;
		int total_successful_probing_time_2 = 0;
		int total_successful_probing_time_3 = 0;
		int total_successful_probing_time_4 = 0;
		
		int total_unsuccessful_probing_time_1 = 0;
		int total_unsuccessful_probing_time_2 = 0;
		int total_unsuccessful_probing_time_3 = 0;
		int total_unsuccessful_probing_time_4 = 0;
		
		int successful_search_1 = 0;
		int unsuccessful_search_1 = 0;
		
		int successful_search_2 = 0;
		int unsuccessful_search_2 = 0;
		
		int successful_search_3 = 0;
		int unsuccessful_search_3 = 0;
		
		int successful_search_4 = 0;
		int unsuccessful_search_4 = 0;
		
		int[] table1 = new int[TABLE_SIZE];
		int[] table2 = new int[TABLE_SIZE];
		int[] table3 = new int[TABLE_SIZE];
		int[] table4 = new int[TABLE_SIZE];
				
		Random rand = new Random();
		
		/*
		int counter = 0;
		for(int i = 0; i < NUMBER_OF_KEY_60; i++){
			if(table1[i] > 0){
				counter++;
			}
		}
		System.out.println("counter: " + counter);
		*/
		
		// Case 1: 60% full, linear probing
		// Step 1: enter the random values into the Hash Table

		System.out.println("-------------Case 1: 60% full, linear probing-------------");
		for(int i = 0; i < NUMBER_OF_KEY_60; i++){
			int random_number = rand.nextInt(range);  // Generate random integers from 1 to 10,000
			int index = random_number % TABLE_SIZE;  // Use "Division Method" as the hash function
			
			boolean placed = false;
			while(!placed){
				if (table1[index] == 0){  // if the found place is empty(0)
					table1[index] = random_number;  // place the value
					placed = true;
				}
				else if(table1[index] == random_number){  // if the value is repetitive
					// do nothing, do not place the value
					placed = true;  // Although it is not successfully placed, we still say it's placed
					i--; // since it's not really place, the counter should be decreased by 1
				}
				else{  // do linear probing
					index ++;
					index = index % TABLE_SIZE;  // in case index out of bound
				}
			}
		}
		
		/*
		counter = 0;
		for(int i = 0; i < TABLE_SIZE; i++){
			if(table1[i] > 0){
				counter++;
			}
		}
		System.out.println("counter: " + counter);
		*/
		
		// Step 2: search keys and record probing times
		for(int key = 1; key < range; key++){
			int index = key % TABLE_SIZE;  // Use "Division Method" as the hash function
			int probing = 1;  // Starting at 1 because h(key) is considered the first probe
			
			boolean success = false;
			while(!success && table1[index] !=0){
				probing++;
				if(table1[index] == key){
					success = true;
				}
				else{
					index++;  // linear probing
					index = index % TABLE_SIZE;  // in case index out of bound
				}
			}
			
			if(success){
				successful_search_1++;
				total_successful_probing_time_1 += probing;
			}
			else{
				unsuccessful_search_1++;
				total_unsuccessful_probing_time_1 += probing;
			}

		}
		//System.out.println(total_successful_probing_time_1);
		//System.out.println(total_unsuccessful_probing_time_1);
		
		System.out.println("successful_search: " + successful_search_1);
		System.out.println("unsuccessful_search: " + unsuccessful_search_1);
		
		System.out.printf("Average number of probes for a successful search: %.4f\n", 1.0*total_successful_probing_time_1/successful_search_1);
		System.out.printf("Average number of probes for an unsuccessful search: %.4f\n", 1.0*total_unsuccessful_probing_time_1/unsuccessful_search_1);

		System.out.println();
		
		// Case 2: 80% full, linear probing
		// enter the random values into the Hash Table
		System.out.println("-------------Case 2: 80% full, linear probing-------------");
		for(int i = 0; i < NUMBER_OF_KEY_80; i++){
			int random_number = rand.nextInt(range);  // Generate random integers from 1 to 10,000
			int index = random_number % TABLE_SIZE;  // Use "Division Method" as the hash function
			
			boolean placed = false;
			while(!placed){
				if (table2[index] == 0){  // if the found place is empty(0)
					table2[index] = random_number;  // place the value
					placed = true;
				}
				else if(table2[index] == random_number){  // if the value is repetitive
					// do nothing, do not place the value
					placed = true;  // Although it is not successfully placed, we still say it's placed
					i--; // since it's not really place, the counter should be decreased by 1
				}
				else{  // do linear probing
					index ++;
					index = index % TABLE_SIZE;  // in case index out of bound
				}
			}
		}
		
		/*
		counter = 0;
		for(int i = 0; i < TABLE_SIZE; i++){
			if(table2[i] > 0){
				counter++;
			}
		}
		System.out.println("counter: " + counter);
		*/
		
		// Step 2: search keys and record probing times
		for(int key = 1; key < range; key++){
			int index = key % TABLE_SIZE;  // Use "Division Method" as the hash function
			int probing = 1;  // Starting at 1 because h(key) is considered the first probe
			
			boolean success = false;
			while(!success && table2[index] !=0){
				probing++;
				if(table2[index] == key){
					success = true;
				}
				else{
					index++;  // linear probing
					index = index % TABLE_SIZE;  // in case index out of bound
				}
			}
			
			if(success){
				successful_search_2++;
				total_successful_probing_time_2 += probing;
			}
			else{
				unsuccessful_search_2++;
				total_unsuccessful_probing_time_2 += probing;
			}

		}
		//System.out.println(total_successful_probing_time_2);
		//System.out.println(total_unsuccessful_probing_time_2);
		
		System.out.println("successful_search: " + successful_search_2);
		System.out.println("unsuccessful_search: " + unsuccessful_search_2);
		
		System.out.printf("Average number of probes for a successful search: %.4f\n", 1.0*total_successful_probing_time_2/successful_search_2);
		System.out.printf("Average number of probes for an unsuccessful search: %.4f\n", 1.0*total_unsuccessful_probing_time_2/unsuccessful_search_2);

		System.out.println();
		
		// Case 3: 60% full, quadratic probing
		// enter the random values into the Hash Table
		System.out.println("-------------Case 3: 60% full, quadratic probing-------------");
		for(int i = 0; i < NUMBER_OF_KEY_60; i++){
			int random_number = rand.nextInt(range);  // Generate random integers from 1 to 10,000
			int index = random_number % TABLE_SIZE;  // Use "Division Method" as the hash function
			int index_first_probe = index;  // Use "Division Method" as the hash function

			int j = 1;  // 1 <= j < (b-1)/2, where b is the size of the table
			
			boolean placed = false;
			boolean add = true;
			while(!placed && j < (TABLE_SIZE-1)/2){
				if (table3[index] == 0){  // if the found place is empty(0)
					table3[index] = random_number;  // place the value
					placed = true;
				}
				else if(table3[index] == random_number){  // if the value is repetitive
					// do nothing, do not place the value
					placed = true;  // Although it is not successfully placed, we still say it's placed
					i--; // since it's not really place, the counter should be decreased by 1
				}
				else{  // do quadratic probing
					if(add){  // +(j*j)
						index = (index_first_probe + (j*j)) % TABLE_SIZE;
						add = !add;  // toggle for next time
					}
					else{
						index = (TABLE_SIZE + (index_first_probe - (j*j))) % TABLE_SIZE;
						add = !add;  // toggle for next time
					}
					j++;
				}
			}
		}
		
		/*
		counter = 0;
		for(int i = 0; i < TABLE_SIZE; i++){
			if(table3[i] > 0){
				counter++;
			}
		}
		System.out.println("counter: " + counter);
		*/
		
		// Step 2: search keys and record probing times
		for(int key = 1; key < range; key++){
			int index = key % TABLE_SIZE;  // Use "Division Method" as the hash function
			int index_first_probe = index;  // Serve as a static "backup"
			int probing = 1;  // Starting at 1 because h(key) is considered the first probe
			int j = 1;  // 1 <= j < (b-1)/2, where b is the size of the table
			
			boolean success = false;
			boolean add = true;
			while(!success && table3[index] !=0 && j < (TABLE_SIZE-1)/2){
				probing++;
				if(table3[index] == key){
					success = true;
				}
				else{  // do quadratic probing
					if(add){  // +(j*j)
						index = (index_first_probe + (j*j)) % TABLE_SIZE;
						add = !add;  // toggle for next time
					}
					else{
						index = (index_first_probe - (j*j)) % TABLE_SIZE;
						if(index < 0){
							index = TABLE_SIZE + index;
						}
						add = !add;  // toggle for next time
					}
					j++;
				}
			}
			
			if(success){
				successful_search_3++;
				total_successful_probing_time_3 += probing;
			}
			else{
				unsuccessful_search_3++;
				total_unsuccessful_probing_time_3 += probing;
			}

		}
		//System.out.println(total_successful_probing_time_3);
		//System.out.println(total_unsuccessful_probing_time_3);
		
		System.out.println("successful_search: " + successful_search_3);
		System.out.println("unsuccessful_search: " + unsuccessful_search_3);
		
		System.out.printf("Average number of probes for a successful search: %.4f\n", 1.0*total_successful_probing_time_3/successful_search_3);
		System.out.printf("Average number of probes for an unsuccessful search: %.4f\n", 1.0*total_unsuccessful_probing_time_3/unsuccessful_search_3);

		System.out.println();
		
		// Case 4: 80% full, quadratic probing
		// enter the random values into the Hash Table
		System.out.println("-------------Case 4: 80% full, quadratic probing-------------");
		for(int i = 0; i < NUMBER_OF_KEY_80; i++){
			int random_number = rand.nextInt(range);  // Generate random integers from 1 to 10,000
			int index = random_number % TABLE_SIZE;  // Use "Division Method" as the hash function
			int index_first_probe = index;  // Use "Division Method" as the hash function

			int j = 1;  // 1 <= j < (b-1)/2, where b is the size of the table
			
			boolean placed = false;
			boolean add = true;
			while(!placed && j < (TABLE_SIZE-1)/2){
				if (table4[index] == 0){  // if the found place is empty(0)
					table4[index] = random_number;  // place the value
					placed = true;
				}
				else if(table4[index] == random_number){  // if the value is repetitive
					// do nothing, do not place the value
					placed = true;  // Although it is not successfully placed, we still say it's placed
					i--; // since it's not really place, the counter should be decreased by 1
				}
				else{  // do quadratic probing
					if(add){  // +(j*j)
						index = (index_first_probe + (j*j)) % TABLE_SIZE;
						add = !add;  // toggle for next time
					}
					else{
						index = (TABLE_SIZE + (index_first_probe - (j*j))) % TABLE_SIZE;
						add = !add;  // toggle for next time
					}
					j++;
				}
			}
		}
		
		/*
		counter = 0;
		for(int i = 0; i < TABLE_SIZE; i++){
			if(table4[i] > 0){
				counter++;
			}
		}
		System.out.println("counter: " + counter);
		*/
		
		// Step 2: search keys and record probing times
		for(int key = 1; key < range; key++){
			int index = key % TABLE_SIZE;  // Use "Division Method" as the hash function
			int index_first_probe = index;  // Serve as a static "backup"
			int probing = 1;  // Starting at 1 because h(key) is considered the first probe
			int j = 1;  // 1 <= j < (b-1)/2, where b is the size of the table
			
			boolean success = false;
			boolean add = true;
			while(!success && table4[index] !=0 && j < (TABLE_SIZE-1)/2){
				probing++;
				if(table4[index] == key){
					success = true;
				}
				else{  // do quadratic probing
					if(add){  // +(j*j)
						index = (index_first_probe + (j*j)) % TABLE_SIZE;
						add = !add;  // toggle for next time
					}
					else{
						index = (index_first_probe - (j*j)) % TABLE_SIZE;
						if(index < 0){
							index = TABLE_SIZE + index;
						}
						add = !add;  // toggle for next time
					}
					j++;
				}
			}
			
			if(success){
				successful_search_4++;
				total_successful_probing_time_4 += probing;
			}
			else{
				unsuccessful_search_4++;
				total_unsuccessful_probing_time_4 += probing;
			}

		}
		//System.out.println(total_successful_probing_time_2);
		//System.out.println(total_unsuccessful_probing_time_2);
		
		System.out.println("successful_search: " + successful_search_4);
		System.out.println("unsuccessful_search: " + unsuccessful_search_4);
		
		System.out.printf("Average number of probes for a successful search: %.4f\n", 1.0*total_successful_probing_time_4/successful_search_4);
		System.out.printf("Average number of probes for an unsuccessful search: %.4f\n", 1.0*total_unsuccessful_probing_time_4/unsuccessful_search_4);

		System.out.println();
		
	}

}
