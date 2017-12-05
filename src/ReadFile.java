import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Raphael Huang
 * The class for read the file and check the row number;
 * and then set up initial state that convert the state be represented as a vector.
 *
 */
public class ReadFile {
	 // File
    private BufferedReader myFile;
    
    /**
     * Creates sudoku object from file
     * @param filename The file that data is read from
     */
    public ReadFile(String filename) {
	try {
	    myFile=new BufferedReader(new FileReader(filename));
	} catch (Exception e) {
	    System.err.println("Ooops!  I can't seem to load the file \""+filename+"\", do you have the file in the correct place?");
	    System.exit(1);
	}
	
    }
    
    /**
     * Creates sudokou object from standard input
     */
    public ReadFile() {
	try {
	    myFile=new BufferedReader(new InputStreamReader(System.in));
	} catch (Exception e) {
	    System.err.println("Ooops!  I can't seem to read from the standard input!");
	    System.exit(1);
	}
    }
    
    /**
     * Read the file and check the row;
     * Convert * to characters.
     * @param args
     * @return queue
     * 		the initial state
     */
    public static Queue<String> setUpInitialState(String args[]) {
   	 	Queue<String> queue = new LinkedList<String>();
    	// Open and read the file
    	if (args.length!= 0 && 
    	    (args.length != 2 ||   (! args[0].equals("-file")))) {
    	    System.err.println("Usage: RoverSampleSensor -file <filename>");
    	    System.exit(1);
    	}
    	ReadFile sudoku=null;

    	if (args.length==0) {
    		sudoku=new ReadFile();
    	} else {
    		sudoku=new ReadFile(args[1]);
    	}
    	
    	try {
    		boolean checkCol = true; // check whether row has 4 number
    		boolean checkFir = true; // to avoid check first row
			String input = sudoku.myFile.readLine();
			
			// check the first number whether is 4
			if(Integer.parseInt(input) != 4){
				System.out.println("Error: we need to do 4-sudoku.");
				
			}
			else{
				while(input != null && checkCol){
					queue.add(input);
					if(checkFir){
						input = sudoku.myFile.readLine();
						checkFir = false;
					}
					else{
						if(input.length() != 4){
							checkCol = false;
							System.out.println("Error: Each row should have 4 elements !");
						}
						input = sudoku.myFile.readLine();
					}
				}
			}
			} catch (IOException e) {
				System.err.println("There some errors for read.");
				e.printStackTrace();
			}
				
    	//close the file
    	try {
			sudoku.myFile.close();
		} catch (IOException e) {
			System.err.println("There some errors for close.");
			e.printStackTrace();
		}

    	// check the size of row
    	int size = Integer.parseInt(queue.remove());
    	if(size != queue.size()){
    		System.out.println("Error: The row number should be 4");
    	}
    	else{
    		String[] arr = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"};
    		int n = 0; // for the arr
        	for (int i =0; i < size; i++) {
        		String elt = queue.remove();
        		String tem = "";
        		for (int j = 0; j < size; j++) {
        			if (elt.charAt(j) == '*') {
        				tem += arr[n];
        				n++;
        			}else{
        				tem += elt.charAt(j);
        			}
        		}
        		queue.add(tem);
        	}
    	}
		return queue;
 
    }

}
