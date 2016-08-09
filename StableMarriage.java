import java.io.*;
import java.util.*;
class StableMarriage {
	

	
 public static void main(String[] args) {
   
	 new StableMarriage().checkMatch();
	 
 }
 
 public void checkMatch(){
	 Scanner sc = new Scanner(System.in);
	 int numPeople = sc.nextInt();
	 List<Integer> indList = new ArrayList<Integer>();
	 int unhappyCount = 0;
	 List<List<Integer>> prefList = new ArrayList<List<Integer>>();
	 List<List<Integer>> unhappyManList = new ArrayList<List<Integer>>();
	 List<List<Integer>> unhappyWomanList = new ArrayList<List<Integer>>();
	 int personCount = 1; //put the number of the person in the array
	//i< numPeople * num preferences each person has * 2 for men and women + numPeople for the last row(the matching)
	 for (int i = 0; i < numPeople*numPeople*2+numPeople; i++) { 
		 
		 int preference = sc.nextInt();
		 indList.add(preference);
		 
		 if(indList.size()%numPeople == 0 ){
			 //need to add man number and woman number to each group so i can compare them later
			 indList.add(personCount);
			 prefList.add(indList);
			 indList = new ArrayList<Integer>();
			 personCount++;
			 if(personCount>numPeople){
				 personCount=1; //goes up to man number 1,2...n then resets and goes up women 1,2...n last match will always have a 1
				 
			 }
		 }
	  } //end for
	  sc.close();
	  System.out.println("prefList: " + prefList.toString());
	  
	  List<Integer> stableMatch = new ArrayList<Integer>(); //the matching
	  
	  for(int i=0; i<numPeople ;i++){ //for the number of people
		  int personNum = 1 +i; //the persons actual number like 1 , 2 , 3
	
		  //System.out.println("Man number= " + personNum + " and his first preference =" + prefList.get(i).get(0) );
		  if(personNum != prefList.get(prefList.size()-1).get(i) ) { //if mani+1 != his match in the last array
			  unhappyCount++;
			  
			  //System.out.println("preflist again: " +prefList.toString());
			  //System.out.println("mans prefList if( " + prefList.get(i).get(i) + " != " + prefList.get(prefList.size()-1).get(i) + " )" );
			 // System.out.println("adding man" + personNum + " to unhappyManList and his stats are:" + prefList.get(i).toString());
			  unhappyManList.add(prefList.get(i));
		  }
		  //if (get(last index).get(that woman).get(that woman's index).get(her first preference) != M of i
		  //the woman number is the last array so we want index 1 2 or 3 of that and that is the woman number
		  //who's preference list we want to check, so check her index 0 pick
		  System.out.println("woman number= " + personNum + " and her first preference =" + prefList.get(3+i).get(0) );
		  int woman = prefList.get(prefList.size()-1).get(i);
		  System.out.println("WOMAN NUMBER: " + woman);
		  System.out.println("womans prefList if( " + prefList.get(woman+numPeople-1).get(i) + " != " + personNum + " )" );
		  if(prefList.get(woman+numPeople-1).get(0) != personNum){ 
			  
			  unhappyCount++;
			  
			  System.out.println("-------W" + prefList.get(prefList.size()-1).get(i) + " is not happy with M" + personNum);
			  unhappyWomanList.add(prefList.get(woman+numPeople-1));
		  }
	  } 
	  System.out.println("unhappy man and unhappy woman list: " +unhappyManList.toString() + " : " + unhappyWomanList.toString());
	  if(unhappyManList.size() >= 1 && unhappyWomanList.size() >=1){
		  for(int i=1; i<unhappyManList.size(); i++){
			  if(unhappyManList.get(i-1).get(i) == unhappyWomanList.get(i-1).get(4) );
		  }
	  }
	  
	  //thinking something like if at least 2 men and at least 2 women are unhappy then check
	  //so put the unhappy men with the man number and the preference number into a list
	  // and put the unhappy women with the woman number and preference list
	  //then check all combinations to see if there would be a better match
	  
 } // end checkMatch()
} //end class














