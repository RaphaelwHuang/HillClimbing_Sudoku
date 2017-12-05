import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Raphael Huang
 * The program for get the initial state from the ReadFile 
 * and calls the hillClimber() function, display the result to screen.
 *
 */

public class TestHarnessHillClimbing {
	
	/**
	 * Get the counts of the total number of conflicts
	 * @param queue
	 * @return checkCount
	 */
	private static int evaluate(Queue<String> queue) {
		int checkCount = 0;
		int size = queue.size();
		
		// CHECK THE ROW
		for (int i = 0; i < size; i++) {
			String elt = queue.remove();
			for (int j = 0; j < elt.length(); j++) {
				char charElt = elt.charAt(j);
				for (int n = j + 1; n < elt.length(); n++) {
					if(charElt == elt.charAt(n)){
						checkCount++;
					}
				}
			}
			queue.add(elt);
		}
	
		
		String eltA = queue.remove();
		String eltB = queue.remove();
		String eltC = queue.remove();
		String eltD = queue.remove();
		// CHECK THE COLUMN 
		for (int i = 0; i < size; i++) {
			if (eltA.charAt(i) == eltB.charAt(i)){
				checkCount++;
			}
			if (eltA.charAt(i) == eltC.charAt(i)){
				checkCount++;
			}
			if (eltA.charAt(i) == eltD.charAt(i)){
				checkCount++;
			}
			if (eltB.charAt(i) == eltC.charAt(i)){
				checkCount++;
			}
			if (eltB.charAt(i) == eltD.charAt(i)){
				checkCount++;
			}
			if (eltC.charAt(i) == eltD.charAt(i)){
				checkCount++;
			}
		}
		
		// CHECK THE BOX
		int index = 0;
		while(index < size) {
			if (eltA.charAt(index) == eltA.charAt(index + 1)){
				checkCount++;
			}
			if (eltA.charAt(index) == eltB.charAt(index)){
				checkCount++;
			}
			if (eltA.charAt(index) == eltB.charAt(index + 1)){
				checkCount++;
			}
			if (eltA.charAt(index + 1) == eltB.charAt(index)){
				checkCount++;
			}
			if (eltA.charAt(index + 1) == eltB.charAt(index + 1)){
				checkCount++;
			}
			if (eltB.charAt(index) == eltB.charAt(index + 1)){
				checkCount++;
			}
			index += 2;
		}
		
		int index2 = 0;
		while (index2 < size) {
			if (eltC.charAt(index2) == eltC.charAt(index2 + 1)){
				checkCount++;
			}
			if (eltC.charAt(index2) == eltD.charAt(index2)){
				checkCount++;
			}
			if (eltC.charAt(index2) == eltD.charAt(index2 + 1)){
				checkCount++;
			}
			if (eltC.charAt(index2 + 1) == eltD.charAt(index2)){
				checkCount++;
			}
			if (eltC.charAt(index2 + 1) == eltD.charAt(index2 + 1)){
				checkCount++;
			}
			if (eltD.charAt(index2) == eltD.charAt(index2 + 1)){
				checkCount++;
			}
			index2 += 2;
		}
		
		queue.add(eltA);
		queue.add(eltB);
		queue.add(eltC);
		queue.add(eltD);
		
		return checkCount;
	}
	
	/**
	 * Set up the value to the blank index
	 * @param queue
	 * @return the newQueue after set up
	 */
	private static Queue<String> setState(Queue<String> queue){
		Queue<String> newQueue = new LinkedList<String>();

		// Set up the number to the blank place
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			String elt = queue.remove();
			String tem = "";
			String num = "1234"; // use to check whether the char is a number
			for (int j = 0; j < elt.length(); j++) {
				// if is not the number, then set up a number
				if (num.indexOf(elt.charAt(j)) == -1) {
					tem += (int)(Math.random() * 4 + 1);
				}
				else{
					tem += elt.charAt(j);
				}
			}
			queue.add(elt);
			newQueue.add(tem);
		}
		return newQueue;		
	}
	
	/**
	 *  Using the Hill-climbing Search to find the solution.
	 * @param queue
	 */
	private static void hillClimber(Queue<String> queue) {
		
		Queue<String> newQueue = setState(queue);
		
		// call the evaluation
		boolean check = true;
		int numOfConflict = evaluate(newQueue);
		int count = 0;
		while (check) {
			newQueue = setState(queue);
			int numOfC = evaluate(newQueue);
			
			if (numOfC < numOfConflict) {
				count++;
				// 	Minimize the number of conflicts
				numOfConflict = numOfC;
				
				// Print the result
				System.out.println("The current result is: ");
				System.out.println(" ------ ");
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					String elt = newQueue.remove();
					System.out.println("| " + elt + " |");
					newQueue.add(elt);
				}
				System.out.println(" ------ ");
				System.out.println("The total number of conflicts in a board is : " + numOfConflict);
				System.out.println("The count of the number of iterations is: " + count);
				System.out.println();
			}
			// When the number of conflicts is zero, then break the loop
			if(numOfC == 0){
				check = false;
				// Print the final result
				System.out.println("*************************");
				System.out.println("The solution is found!");
				System.out.println(" ------ ");
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					String elt = newQueue.remove();
					System.out.println("| " + elt + " |");
					newQueue.add(elt);
				}
				System.out.println(" ------ ");
				System.out.println("The total number of conflicts in a board is : " + numOfConflict);
				System.out.println("The count of the number of iterations is: " + count);
			}
		}	
	}
	
	
	/**
	 * Call the hillClimber function
	 * @param args
	 */
    public static void main(String args[]) {
    	Queue<String> initialState = ReadFile.setUpInitialState(args);
    	
    	//call the hillClimber
    	hillClimber(initialState);
    }

}
