package hw3;

import javax.annotation.processing.FilerException;
import java.util.Stack;

public class Sample {

	public static void main(String[] args) throws FilerException {
		Stack stack = new Stack();
		
		String a = "A";
		String b = "B";
		stack.push(a);
		
		
		System.out.println(stack.peek());
	}
}
