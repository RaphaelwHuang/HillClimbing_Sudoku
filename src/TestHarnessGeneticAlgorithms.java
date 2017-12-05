import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Raphael Huang
 * The program for get the initial state from the ReadFile 
 * and calls the GeneticAlgorithm() function, display the result to screen.
 *
 */

public class TestHarnessGeneticAlgorithms {
	
	private static Queue<Integer> initial = new LinkedList<Integer>();
	
	/**
	 * Get the counts of the total number of conflicts
	 * @param queue
	 * @return checkCount
	 */
	private static int Fitness(Queue<String> queue) {
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
	private static Queue<String> randomSelection(Queue<String> queue){
		Queue<String> newQueue = new LinkedList<String>();
		initial = new LinkedList<Integer>();
		// Set up the number to the blank place
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			String elt = queue.remove();
			String tem = "";
			String num = "1234"; // use to check whether the char is a number
			for (int j = 0; j < elt.length(); j++) {
				// if is not the number, then set up a number
				if (num.indexOf(elt.charAt(j)) == -1) {
					int numElt = (int)(Math.random() * 4 + 1);
					tem += numElt;
					initial.add(numElt);
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
	 * Do the selection and cross-over.
	 * 
	 * @param queue, the initial with blank
	 * @param xPop, one of the parents situation of blanks
	 * @param yPop, one of the parents situation of blanks
	 * @return a child situation
	 */
	private static Queue<String> reproduce(Queue<String> queue, Queue<Integer> xPop, Queue<Integer> yPop) {
		initial = new LinkedList<Integer>();

		Queue<String> newQueue = new LinkedList<String>();

		int n = xPop.size() - 1;
		int c = (int)(Math.random() * n + 1);
		String x = "";
		int sizeX = xPop.size();
		for (int i = 0; i < sizeX; i++) {
			int tem = xPop.remove();
			x += tem;
			xPop.add(tem);
		}
		
		String y = "";
		int sizeY = yPop.size();
		for (int i = 0; i < sizeY; i++) {
			int tem = yPop.remove();
			y += tem;
			yPop.add(tem);
		}
		
		String child = x.substring(0, c) + y.substring(c);
		for (int i = 0; i < child.length(); i++) {
			String tem = "";
			tem += child.charAt(i);
			initial.add(Integer.parseInt(tem));
		}
		
		// generate the new child
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			String elt = queue.remove();
			String tem = "";
			String num = "1234"; // use to check whether the char is a number
			for (int j = 0; j < elt.length(); j++) {
				// if is not the number, then set up a number
				if (num.indexOf(elt.charAt(j)) == -1) {
					int eltNum = initial.remove();
					tem += eltNum;
					initial.add(eltNum);		
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
	 * Do the mutation
	 * 
	 * @param queue
	 * @param childPop the situation of blank
	 * @return a child after mutation
	 */
	private static Queue<String> mutate(Queue<String> queue, Queue<Integer> childPop) {
		Queue<String> newQueue = new LinkedList<String>();

		// mutate the childPop
		int sizeOfChildPop = childPop.size() - 1;
		int c = (int)(Math.random() * sizeOfChildPop + 1);
		for (int i = 0; i < sizeOfChildPop; i++) {
			int tem = childPop.remove();
		
			if (i == c) {
				childPop.add((int)(Math.random() * 4 + 1));
			}
			else{
				childPop.add(tem);
			}
			
		}
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			String elt = queue.remove();
			String tem = "";
			String num = "1234"; // use to check whether the char is a number
			for (int j = 0; j < elt.length(); j++) {
				// if is not the number, then set up a number
				if (num.indexOf(elt.charAt(j)) == -1) {
					int temNum = childPop.remove();
					tem += temNum;
					childPop.add(temNum);
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
	 *  Using the Genetic Algorithm Search to find the solution.
	 * @param queue
	 */
	private static void GeneticAlgorithm(Queue<String> queue) {
		Queue<String> child = new LinkedList<String>();
		Queue<String> childOne = new LinkedList<String>();
		Queue<String> childTwo = new LinkedList<String>();
		Queue<Integer> childPopOne = new LinkedList<Integer>();
		Queue<Integer> childPopTwo =new LinkedList<Integer>();	
		int fitnessCO = 0;
		int fitnessCT = 0;
		boolean check = true;
		boolean checkFirst = true;
		
		Queue<String> x = randomSelection(queue);
		Queue<Integer> xPop = initial;
		int fitnessX = Fitness(x);
		Queue<String> y = randomSelection(queue);
		Queue<Integer> yPop = initial;
		int fitnessY = Fitness(y);

		int checkFit = 0;
		if (fitnessX > fitnessY) {
			checkFit = fitnessY;
		}
		else {
			checkFit = fitnessX;
		}
		System.out.println("Start the Genetic Algorithm...");
		for (int i = 0; i < (int) Math.pow(4, 16) && check; i++) {

			if (checkFirst) {
				childOne = reproduce(queue, xPop, yPop);
			    childPopOne = initial;
				fitnessCO = Fitness(childOne);

				childTwo = reproduce(queue, xPop, yPop);
				childPopTwo = initial;
				fitnessCT = Fitness(childTwo);
				
				checkFirst = false;
			}
			else {
				childOne = reproduce(queue, childPopOne, childPopTwo);
				childPopOne = initial;
				fitnessCO = Fitness(childOne);

				childTwo = reproduce(queue, childPopOne, childPopTwo);
				childPopTwo = initial;
				fitnessCT = Fitness(childTwo);
			}
			// If fitness is not good, then do the mutate function.
			if (fitnessCO >= checkFit) {
				childOne = mutate(queue, childPopOne);
				fitnessCO = Fitness(childOne);
			}
			else{
				checkFit = fitnessCO;
			}
			
			if (fitnessCT >= checkFit) {
				childTwo = mutate(queue, childPopTwo);
				fitnessCT = Fitness(childTwo);
			}
			else{
				checkFit = fitnessCO;
			}
			
			// Choose the best child
			if (fitnessCO < fitnessCT) {
				child = childOne;
			}
			else{
				child = childTwo;
				
			}
			
			// Print the current result
			System.out.println("Current result is: ");
			System.out.println(child);
			System.out.println();
			
			if (fitnessCO == 0 || fitnessCT == 0) {
				check = false;
				// Print the result
				System.out.println("*************************");
				System.out.println("The solution is found: ");
				System.out.println(" ------ ");
				int size1 = queue.size();
				for (int i1 = 0; i1 < size1; i1++) {
					String elt = child.remove();
					System.out.println("| " + elt + " |");
					child.add(elt);
				}
				System.out.println(" ------ ");
				System.out.println("The count of the number of iterations is: " + i);
				System.out.println();		
			}
		}		
	}
	
	/**
	 * Call the Genetic Algorithm function
	 * @param args
	 */
    public static void main(String args[]) {
    	Queue<String> initialState = ReadFile.setUpInitialState(args);
    	
    	//call the GeneticAlgorithm
    	GeneticAlgorithm(initialState);
    }

}
