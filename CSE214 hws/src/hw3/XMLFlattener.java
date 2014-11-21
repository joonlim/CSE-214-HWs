package hw3;

import java.io.BufferedReader;
import java.util.EmptyStackException;
import java.util.Stack;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.annotation.processing.FilerException;

/**Program that uses stacks to "flatten" an XML file. It reads data from an XML
 * file and prints its data out neatly.
 * @author Joon Hyung Lim 
 *   	   e-mail: joonhyung.lim@stonybrook.edu
 *   	   Stony Brook ID: 109558002
 */
public class XMLFlattener {
    FileInputStream fis; // Object that obtains information from XML file.
    InputStreamReader inStream; // Object that reads and decodes information from XML file.
    BufferedReader reader; //  Object that reads the text from the InputStreamReader.
	Stack s1, s2; // Stacks used for parsing algorithm.
	String fileName;  // String that stores the name of the XML file.
	String data = ""; // String that stores the information contained in the XML file.

    /**Returns an instance of <code>XMLFlattener</code>. Prompts the user to enter
     * a file name and stores the information in the file onto a string.
	 * 
	 * <dt>Postcondition:
	 * 		<dd>The file given exists. The data stored on the XML file is now
	 * 		stored on a String. The <code>FileInputStream</code>, 
	 * 		<code>InputStreamReader</code>, and <code>BufferedReader</code> objects
	 * 		are closed.
	 * 
	 * @throws IOException
	 * 		Indicates that there was an error reading the file.
	 *  
	 * @throws FileNotFoundException
	 * 		Indicates that given file name does not exist.
	 */
	public XMLFlattener() throws IOException, FileNotFoundException {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter name of XML file: ");
		fileName = input.next();
		
		try {
			fis = new FileInputStream(fileName);
		} catch(FileNotFoundException e) {
			System.out.println("No such file found.");
		}
		
		inStream = new InputStreamReader(fis);
		reader = new BufferedReader(inStream);
	    s1 = new Stack();
	    s2 = new Stack();
	    
	    String nextLine = reader.readLine();
	    while(nextLine != null) {
	    	data = data + nextLine;
	    	nextLine = reader.readLine();
	    }
	    
	    fis.close();
	    inStream.close();
	    reader.close();
	    
	}

	/**Parses information on a String from XML format to a neat format using
	 * stacks.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>XMLFlattener</code> object has been instantiated and
	 *		the XML file's information was stored on a String.
	 * 
	 * <dt>Postcondition:
	 * 		The data from the XML file is parsed into a neat format and returned
	 * 		as a String.
	 * 
	 * @throws FilerException
	 *  	Indicates that a Filer error was detected.
	 *  
	 * @throws EmptyStackException
	 * 		Indicates a stack is empty.
	 * 
	 * @throws StringIndexOutOfBoundsException
	 * 		Indicates that the attempted character location on a String is out
	 * 		of bounds.
	 * 
	 * @return
	 */
	public String parse() throws FilerException, EmptyStackException, StringIndexOutOfBoundsException {
		String display = "";
		String print = "";
		int x = 0;
		boolean printed = false;
		
		data = data.substring(data.indexOf('<'));
		while(data != null) {
			String tag;
			
			if (data.charAt(0) == '<') { // Indicates Start of a tag.
				tag = data.substring(1, data.indexOf('>'));
				if(tag.charAt(0) == '/') { // If first character in tag is '/' then it is a closing tag.

					tag = tag.substring(1); // Remove '/' from tag.
					
					try {
						if (((String) s1.peek()).equalsIgnoreCase(tag)) { // If closing tag matches the opening tag
						
							if (printed == false) { // Check a new text was stored in <code>print</code>.
								while (!(s1.isEmpty())) { // If not, push all elements from stack 1 onto stack 2.
									s2.push(s1.pop());
								}
								while (!(s2.isEmpty())) { // Then push all elements from stack 2 onto stack 1 and add each element to the string.
								
									s1.push(s2.pop());
							
									if (s2.isEmpty()) { // The last tag is written with out a frontslash after it.
										display += (String)s1.peek();
									}
									else { // Other tags are written with a front slash after.
										display += (String)s1.peek() + "/";
									}
								}
						
								display += print; // The text is then added onto the stack.
								printed = true; // Indicates that the text was added to <code>display</code> and is need of a new text.
							}
							s1.pop(); // Pops the tag off after closing tag was found.
						
						}
						else { // Opening and closing tag do not match.
							
							display += "\nInvalid XML. \"</" + tag + ">\" closes \"<" + s1.peek() + ">\"";  
						}
					} catch(EmptyStackException e) {
						display += "\nInvalid XML. Extra closing tag \"<" + tag + ">\""; // Indicates an extra closing tag.
					}	
				}
				else { // It is an opening tag
					if(!(tag.charAt(tag.length() - 1) == '/')){ 
						if (printed == false) {
							while (!(s1.isEmpty())) { // Push all elements from stack 1 onto stack 2 to store the tags in reverse order.
								s2.push(s1.pop());
							}
							while (!(s2.isEmpty())) { // Push all elements from stack 2 onto stack 1 while adding each element to the string.
								
								s1.push(s2.pop());
							
								if (s2.isEmpty()) {
									display += (String)s1.peek();
								}
								else {
									display += (String)s1.peek() + "/";
								}
							}
						
							display += print;
							printed = true;
						}
						
						s1.push(tag); // 
					}
				}
			}
			else { // Text. Not tag. ex: John.F. Kennedy.
				print = ": " + data.substring(0, data.indexOf('<')) + "\n";
				printed = false;
			}
			try{
				if(data.charAt(0) == '<') {
					data = data.substring(data.indexOf('>') + 1).trim();
				}
				else {
					data = data.substring(data.indexOf('<')).trim();
				}
			} catch (StringIndexOutOfBoundsException e) {
				data = null;
			}
			if (data.trim().isEmpty()) {
				data = null;
			}
		}
		
		if (!(s1.isEmpty())) {// If stack is not empty, write unclosed tag.
			display += "\nInvalid XML: Unclosed tag ";
			int numLeft = 1;
			while(!s1.isEmpty()){
				if (numLeft == 1) {
					display += "\"<" + s1.pop() + ">\"";
					numLeft++;
				}
				else {
					display += ", \"<" + s1.pop() + ">\"";
				}
				
			}
		}
		return display;
	}
	
	
	public static void main(String[] args) throws IOException {
		XMLFlattener program = new XMLFlattener();
		
		System.out.print(program.parse());
	}
	
	
}
