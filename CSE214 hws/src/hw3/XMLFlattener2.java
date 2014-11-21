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

public class XMLFlattener2 {
    FileInputStream fis;
    InputStreamReader inStream;
    BufferedReader reader;
	Stack s1, s2;
	String fileName;
	String data = "";

	public XMLFlattener2() throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter name of XML file: ");
		fileName = input.next();
		
		fis = new FileInputStream(fileName);
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
	
	public String parse() throws FilerException {
		String display = "";
		String print = "";
		int x = 0;
		boolean printed = false;
		
		

		data = data.substring(data.indexOf('<'));
		while(data != null) {
			/*
			System.out.println(++x);
			System.out.println(data);
			*/
			String tag;
			
			if (data.charAt(0) == '<') { // ex: <Name>
				tag = data.substring(1, data.indexOf('>'));
				if(tag.charAt(0) == '/') { // if first character in tag is '/' then it is a closing tag.

					tag = tag.substring(1); // remove '/' from tag.
					
					try {
						if (((String) s1.peek()).equalsIgnoreCase(tag)) { // if closing tag matches the opening tag
						
							if (printed == false) {
								while (!(s1.isEmpty())) { // push all elements from stack 1 onto stack 2.
									s2.push(s1.pop());
								}
								while (!(s2.isEmpty())) { // push all elements from stack 2 onto stack 1 and add each element to the string.
								
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
							s1.pop();
						
						}
						else { // opening and closing tag do not match.
							
							display += "\nInvalid XML. \"</" + tag + ">\" closes \"<" + s1.peek() + ">\"";  
						}
					} catch(EmptyStackException e) {
						display += "\nInvalid XML. Extra closing tag \"<" + tag + ">\"";
					}
					
					
				}
				
				else { // it is an opening tag
					if(!(tag.charAt(tag.length() - 1) == '/')){ // if last character is not '/' then push tag into stack. if the last character is '/' then do nothing.
						if (printed == false) {
							while (!(s1.isEmpty())) { // push all elements from stack 1 onto stack 2.
								s2.push(s1.pop());
							}
							while (!(s2.isEmpty())) { // push all elements from stack 2 onto stack 1 and add each element to the string.
								
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
						
						s1.push(tag);
						
						
					}
				}
				
				
				
			}
			
			
			
			
			
			
			else { // text. not tag. ex: John.F. Kenndy
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
		
		if (!(s1.isEmpty())) {// is stack is not empty, write unclosed tag
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
		XMLFlattener2 program = new XMLFlattener2();
		
		System.out.print(program.parse());
	}
	
	
}
