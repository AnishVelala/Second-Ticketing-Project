package movie;


import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.LinkedList;

public class movieclass {
	 private static final String File_Name  = "A1.txt";   // holds name of reservation file
	 private static char[][] auditorium = new char[10][26]; // To store the seating arrangement
	/*   private static double totalSales = 0.0;
	    private static int totalSeats = 0;
	    private static int totalAdultTickets = 0;
	    private static int totalChildTickets = 0;
	    private static int totalSeniorTickets = 0;
	 */
	 
	    
	  public static  void loadAuditorium ( String fileName ) {
		  BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		
		 String line;
		        int row = 0;
	            while ((line = reader.readLine()) != null) {
	                char[] rowChars = line.toCharArray();
	                for (int col = 0; col < rowChars.length; col++) {
	                    auditorium[row][col] = rowChars[col];
	                }
	                row++;
	            }
	            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    	
	    	
	    	
	    	
	  public static  void initializeArray ( char[][] auditorium ) {
		  
		  
		  char defaultChar = '.';
	        for (int row = 0; row < 10; row++) {
	            for (int col = 0; col < 26; col++) {
	                auditorium[row][col] = defaultChar;
	            }
	        }
	        
		  
		  
	  }
	    	
	    	
	    
	  
 public static  void displaySeats ( char[][] auditorium ) {
		  
	 System.out.println(" ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		  char defaultChar = '.';
	        for (int row = 0; row < 10; row++) {
	        	System.out.println(row + 1);
	            for (int col = 0; col < 26; col++) {
	            	System.out.println(auditorium[row][col]);
	            }
	        }
	        
		  
		  
	  }
	    

	    
	 
	  public static void main(String[] args) {
	    	        
	    	        //////initializeArray ( auditorium );
	    	        Scanner scanner = new Scanner(System.in);
	    	        System.out.print("Enter the filename for the auditorium: ");
	    	        String filename = scanner.nextLine();
	    	        
	    	        // Load the auditorium data from the file
	    	        loadAuditorium(filename);
	    	        
	    	        
	    	        int choice;
	    	        do {
	    	            displayMenu();
	    	            choice = scanner.nextInt();
	    	            scanner.nextLine(); // Consume the newline character
	    	            
	    	            switch (choice) {
	    	                case 1:
	    	                    reserveSeats(scanner);
	    	                    break;
	    	                case 2:
	    	                    // Exit the program
	    	                    break;
	    	                default:
	    	                    System.out.println("Invalid choice. Please try again.");
	    	            }
	    	        } while (choice != 2);
	    	        
	    	        // Generate and display the report
	    	        
	    	        // Write the updated auditorium data back to the file
	    	        
	    	        scanner.close();
	    	    }
	    	  
	    	    
	    	    private static void displayMenu() {
	    	        // Display the main menu
	    	        // Implement this function
	    	        System.out.println("1. Reserve Seats");
	    	        System.out.println("2. Exit");

	    	    }
	    	    
	    	    private static void reserveSeats(Scanner scanner) {
	    	        // Implement the seat reservation logic here
	    	    	
	    	    	
	    	    	

	    	        // Prompt for user input
	    	        System.out.print("Row number");
	    	        int rowNum = scanner.nextInt() - 1; // Adjust for 0-based indexing
	    	        scanner.nextLine(); // Consume the newline character

	    	        if (rowNum < 0 || rowNum >= auditorium.length) {
	    	            System.out.println("Invalid row number.");
	    	            return;
	    	        }

	    	        System.out.print(" Starting seat letter ");
	    	        char startSeat = scanner.nextLine().charAt(0);

	    	        // Validate the seat letter
	    	        if (startSeat < 'A' || startSeat >= 'A' + auditorium[0].length) {
	    	            System.out.println("Invalid seat letter.");
	    	            return;
	    	        }

	    	        System.out.print(" Number of adult tickets  ");
	    	        int numAdult = scanner.nextInt();
	    	        scanner.nextLine(); // Consume the newline character

	    	        if (numAdult < 0) {
	    	            System.out.println("Invalid number of adult tickets.");
	    	            return;
	    	        }

	    	        System.out.print(" Number of child tickets  ");
	    	        int numChild = scanner.nextInt();
	    	        scanner.nextLine(); // Consume the newline character

	    	        if (numChild < 0) {
	    	            System.out.println("Invalid number of child tickets.");
	    	            return;
	    	        }

	    	        System.out.print(" Number of senior tickets  ");
	    	        int numSenior = scanner.nextInt();
	    	        scanner.nextLine(); // Consume the newline character

	    	        if (numSenior < 0) {
	    	            System.out.println("Invalid number of senior tickets.");
	    	            return;
	    	        }

	    	        // Implement seat reservation logic here
	    	        
	    	        
	    	        
	    	        if (AreSeatsAvailable( rowNum,  startSeat, numAdult + numChild +  numSenior ))
	    	        	
	    	        	bookAvailableSeats( rowNum,  startSeat,  numAdult,  numChild,  numSenior);
	    	        	
	    	        	
	    	        	
	    	        	else
	    	        		
	    	        		bestAvailabeSeats( auditorium,  rowNum,  numAdult,  numChild,  numSenior,  scanner);
	    	       
	    	      

	    	        displayReport( auditorium);
	    	        
	    	        
	    	        writeToFile(auditorium);
	    	    	
	    	    }
	    	    
	    	    
	    	    public static  boolean AreSeatsAvailable(int rowNum, char startSeatLetter, int numSeats ) {
	    	   	 
	    	   	int column = convertSeatLettertoIndex(startSeatLetter);
	    	    rowNum = rowNum -1;
	    	    for (int i = column; i < column + numSeats; i++) {
	    	    	if  ( auditorium[rowNum][column] == 'A'|| auditorium[rowNum][column] ==  'C'|| auditorium[rowNum][column] == 'S')
	    	    			return false;
	    	    }
	    	    return true;
	    	    
	    	    }
	    	    
	    	    
	    	    public static void bookAvailableSeats(int rowNum, char startSeatLetter, int numAdult, int numChild, int numSenior) {
	    	        int column = convertSeatLettertoIndex(startSeatLetter);
	    	        rowNum = rowNum - 1;
	    	        
	    	        int numSeats = numAdult + numChild + numSenior;
	    	        
	    	        // Check if there are enough available seats in the row
	    	        if (column + numSeats > auditorium[rowNum].length) {
	    	            System.out.println("Not enough seats available in this row.");
	    	            return;
	    	        }

	    	        for (int i = column; i < column + numSeats; i++) {
	    	            if (auditorium[rowNum][i] == '.') { // Check if the seat is available
	    	                if (numAdult > 0) {
	    	                    auditorium[rowNum][i] = 'A'; // Reserve an adult seat
	    	                    numAdult--;
	    	                } else if (numChild > 0) {
	    	                    auditorium[rowNum][i] = 'C'; // Reserve a child seat
	    	                    numChild--;
	    	                } else if (numSenior > 0) {
	    	                    auditorium[rowNum][i] = 'S'; // Reserve a senior seat
	    	                    numSenior--;
	    	                }
	    	            }
	    	        }
	    	        
	    	        // Handle cases where there are more adults, children, or seniors than available seats
	    	        if (numAdult > 0 || numChild > 0 || numSenior > 0) {
	    	            System.out.println("Not enough available seats for all ticket types.");
	    	        }
	    	    }
	    	    
	    	    
	    	    
	    	    public static void displayReport(char[][] auditorium) {
	    	    	
	    	    	int totalSeats= auditorium.length * auditorium[0].length;
	    	    	int totalTickets=0;
	    	    	int totalAdultTickets=0;
	    	    	int totalChildTickets=0;
	    	    	int totalSeniorTickets=0;
	    	    	
	    	    	for (int row = 0; row < 10; row++) {
	    	            for (int col = 0; col < 26; col++) {
	    	                if (auditorium[row][col] == 'A'|| auditorium[row][col] ==  'C'|| auditorium[row][col] == 'S')
	    	                	totalTickets++;
	    	                if (auditorium[row][col] == 'A')
	    	                	totalAdultTickets++;
	    	                if (auditorium[row][col] ==  'C')
	    	                	totalChildTickets++;
	    	                if (auditorium[row][col] == 'S')
	    	                	totalSeniorTickets++;
	    	                		
	    	            }
	    	            
	    	        }
	    	    	System.out.println("Total Seats: " + totalSeats);
	    	    	System.out.println("Total Tickets: " + totalTickets);
	    	    	System.out.println("Adult Tickets: "  + totalAdultTickets);
	    	    	System.out.println("Child Tickets: " + totalChildTickets);
	    	    	System.out.println("Senior Tickets: " + totalSeniorTickets);
	    	    	System.out.println("Total Sales: " + (10  * totalAdultTickets) + (5 * totalChildTickets) + (7.50 * totalSeniorTickets ));
	    	
	    	    }
	    	    
	    	    
	    	    
	    	    
	    	    
	    	    public static void writeToFile(char[][] auditorium) {
	    	    	String filename = "A1.txt.";
	    	    	BufferedWriter writer;
					try {
						writer = new BufferedWriter(new FileWriter(filename));
					
	    	    	for (int row = 0; row < 10; row++) {
	    	            for (int col = 0; col < 26; col++) {
	    	            	writer.write(auditorium[row][col]);           
	    	            	
	    	            }
	    	            writer.newLine();
	    	            
	    	    	 }
	    	    	writer.close();
	    	    	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	
					}
	    	    	
	    	    }
	    	    

	    	    private static int convertSeatLettertoIndex (char startSeatLetter) {
	    	    	return(startSeatLetter - 'A');
	    	    }
	    	    
	    	    private static char convertIndextoSeatLetter (int index ) {
	    	    	return(char)(index + 'A');
	    	    }
	    	    
	    	    
	    	    private static void bestAvailabeSeats(char[][] auditorium, int rowNum, int numAdult, int numChild, int numSenior, Scanner scanner) {
	    	    	int numSeats= numAdult + numChild + numSenior;
	    	    	  int numColumns = auditorium[rowNum].length;
	    	    	  boolean seatsAvailable = false;
	    	    	    int middleColumn = numColumns / 2; // Find the middle column (assuming numColumns is odd)
	    	    	    
	    	    	    // Check if the middle column is valid
	    	    	    if (middleColumn >= 0 && middleColumn < numColumns) {
	    	    	        int startColumn = middleColumn - (numSeats / 2);
	    	    	        int endColumn = startColumn + numSeats - 1;

	    	    	        // Ensure the seat range is within the row boundaries
	    	    	        if (startColumn >= 0 && endColumn < numColumns) {
	    	    	            seatsAvailable = true;
	    	    	            
	    	    	            // Check if seats in the specified range are available
	    	    	            for (int col = startColumn; col <= endColumn; col++) {
	    	    	                if (auditorium[rowNum][col] != '.') {
	    	    	                    seatsAvailable = false;
	    	    	                    break;
	    	    	                }
	    	    	            }
	    	    	            
	    	    	            // If seats are available, reserve them
	    	    	            if (seatsAvailable) {
	    	    	            	
	    	    	            	System.out.println(rowNum + convertIndextoSeatLetter(startColumn) + " - " + rowNum + convertIndextoSeatLetter(endColumn)) ;
	    	    	            	
	    	    	            	
	    	    	            	System.out.println("Do you want to reserve ? Y/N");
	    	    	            	
	    	    	            	String choice = scanner.next();
	    	    	            	
	    	    	            	if (choice =="Y" )
	    	    	            		bookAvailableSeats( rowNum, convertIndextoSeatLetter(startColumn),  numAdult, numChild, numSenior);
	    	    	            	
	    	    	            	
	    	    	            	else 
	    	    	            		
	    	    	            		return;
	    	    	            	
	    	    	            	
	    	    	    
	    	    	            }
	    	    	        }
	    	    	    }

	    	      
	    	    }
	    	    
}    	   
	    	