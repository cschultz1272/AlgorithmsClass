
// By Chris Schultz

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StableMarriage {

	public static void main(String[] args) throws FileNotFoundException {
		 Scanner in = new Scanner(System.in);
		 String file = in.nextLine();
		 in.close();
		 new StableMarriage2().checkMatch(file);
	 }
	
	public void checkMatch(String file) throws FileNotFoundException{
		 Scanner sc = new Scanner(new FileReader(file)); //read in the file
		 List<Integer> indList = new ArrayList<Integer>(); //list of each person's preference list individually
		 List<List<Integer>> manList = new ArrayList<List<Integer>>(); //list of all of the mens preference lists
		 List<List<Integer>> womanList = new ArrayList<List<Integer>>(); //list of all of the womens preference lists
		 List<Integer> unstableSingle = new ArrayList<Integer>(); //a single set of (m,w) that are unstable
		 List<List<Integer>> unstableAll = new ArrayList<List<Integer>>(); //the set of all instabilities
		 List<List<Integer>> theMatching = new ArrayList<List<Integer>>(); //the matching
		 int personCount = 1; //put the number of the person in the array
		 boolean stable = true; //whether the match is stable or not
		 int numPeople = sc.nextInt(); //the first number read in
		 
		 
		 for (int i = 0; i < numPeople*numPeople*2+numPeople; i++) { //fill the men and women lists
			 int preference = sc.nextInt(); 
			 indList.add(preference);
			 if(indList.size()%numPeople == 0 ){ //if the list % numPeople is 0 then thats all their preferences
				 indList.add(personCount);
				 if(i<=numPeople*numPeople){ //if i is in the first half it's a man's preference list
					 manList.add(indList);
				 }
				 else if(i>=numPeople*numPeople && i<numPeople*numPeople*2){ //i is in the second half it's a woman's preference list
					 womanList.add(indList); 
				 }
				 else{ //its the last one which is the matching
					 theMatching.add(indList); //it's the last one and is the matching
				 }
				 indList = new ArrayList<Integer>();
				 personCount++;
				 if(personCount>numPeople){
					 personCount=1; //goes up to man number 1,2...n then resets and goes up women 1,2...n last match will always have a 1
				 }
			 }
		  } //end for
		  sc.close();
		  
		//check the mens preferences
		  for(int i=0; i<manList.size();i++){ 
			  for(int j=0; j<manList.get(i).indexOf(theMatching.get(0).get(i));j++){
				  //add the men and women numbers to unstable list if there is any indexes lower than their current match's index
					  unstableSingle.add(manList.get(i).get(numPeople)); //the man number
					  unstableSingle.add(manList.get(i).get(j)); //the woman number he prefers
					  unstableAll.add(unstableSingle);
					  unstableSingle = new ArrayList<Integer>();
			  }
		  }
		  
		  //check the womens preferences
		  outerLoop:
		  for(int i=0; i<womanList.size();i++){
			  for(int j=0; j<womanList.get((theMatching.get(0).get(i) )-1).indexOf(i+1);j++){
				  //add the men and women numbers to unstable list if there is any indexes lower than their current match's index
					  unstableSingle.add(womanList.get(theMatching.get(0).get(i) -1).get(j)); //the man she prefers
					  unstableSingle.add(theMatching.get(0).get(i)); //the woman number
					  if(unstableAll.contains(unstableSingle)){ //if that pairing is already their from the mens instabilities
						  stable=false;
						  System.out.println("Not stable. (M" +womanList.get(theMatching.get(0).get(i) -1).get(j)+ 
								  ",W"+theMatching.get(0).get(i)+") is one instability.");
						  break outerLoop; //we found the droid we were looking for
					  }
					  unstableAll.add(unstableSingle);
					  unstableSingle = new ArrayList<Integer>();
			  }
		  } //end for
		  
		  if(stable==true){ //if stable never got changed to false
			  System.out.println("Stable.");
		  }
	}
}













