Name: Raphael Huang
OSU email: huang.2339@osu.edu
CSE3521 Lab 3

How to compiles and run:
	My lab3 is in an Archive file, you can import it by Eclipse.
	So, for the GAs and HillClimbing, they both use the input from the ReadFile.java, 
	which return a queue with the state vector.
	ex:
	State vector: [ a, b, c, d, e, f, g, h, i, j ]
	24ab c3de fg4h ij31
	
	For the HillClimbing, using the random numbers to create the initial state, 
	every changes also use the random numbers.
	For the GAs, also using the random numbers to create the initial state, 
	the REPRODUCE function uses the pseudo code on books:
	function REPRODUCE(x , y) returns an individual
		inputs: x , y, parent individuals
		n←LENGTH(x ); c←random number from 1 to n
		return APPEND(SUBSTRING(x, 1, c), SUBSTRING(y, c + 1, n))
		
	You can click Run Configuration, then in the arguments, type -file <FILENAME> 
	to run both if them
	ex: -file hw3-data1.txt
		-file hw3-data2.txt (text case made by myself)
		-file hw3-data3.txt (text case made by myself)

		


