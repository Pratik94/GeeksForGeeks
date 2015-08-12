import java.util.Arrays;
import java.util.Stack;

/*import java.awt.DisplayMode;
import java.util.Arrays;


public class stack {

	private String[] stackArray;
	
	private int stackSize;
	
	private int topOfStack;
	
	stack(int size)
	{
		stackSize = size;
		
		stackArray = new String[size];
		
		Arrays.fill(stackArray, "-1");
	}
	
	public void push(String input)
	{
		if(topOfStack+1 < stackSize)
		{
			topOfStack++;
			stackArray[topOfStack] = input;
		}
		else
			System.out.println("Stack is full");
		
		display();
			
	}
	
	public String pop()
	{
		if(topOfStack >=0)
		{
			display();
			System.out.println("POP "+stackArray[topOfStack] +" was removed");
			
			stackArray[topOfStack] ="-1";
			
			return stackArray[topOfStack--];
		}
		
		else{
			System.out.println("The stack is empty");
			
			return "-1";
		}
		
	}
	
	public String peek(){
		return stackArray[topOfStack];
	}
	
	public void display()
	{
		System.out.println("Display the Stack >>>>>");

		for(int i=1;i<stackSize;i++)
		{
			if(stackArray[i].equals("-1"))
				break;
			else
				System.out.println(stackArray[i]);
		}
	}
	
	public static void main(String[] args)
	{
	
		stack theStack = new stack(10);
		  
		theStack.push("10");
        theStack.push("17");
        theStack.push("13");
         
        theStack.peek();
        theStack.pop();
        theStack.pop();
        
        theStack.display();
        
	}
	
}
*/


public class stack{
	
	private String[] stackArray;
	
	private int stackSize;
	
	private int topOfStack;
	
	private String[] auxiArray;
	
	stack(int size)
	{
		stackSize = size;
				
		stackArray = new String[size];
		auxiArray = new String[size];

		
		Arrays.fill(stackArray, "-1");
		Arrays.fill(auxiArray, "-1");
	}
	
	
	public void display()
	{
		System.out.println("display the stack>>>");
		
		for(int i=1;i<stackSize;i++)
		{
			if(stackArray[i].equals("-1"))
				break;
			else
				System.out.println(stackArray[i]);
		}
	}
	
	public void push(String data)
	{
		if(topOfStack+1<stackSize)
		{
			System.out.println("Push :" +data);
			
			topOfStack++;
			
			stackArray[topOfStack] = data;
			
			display();
			
			if(topOfStack==1)
				auxiArray[topOfStack]=data;
			else
				{
			if(Integer.parseInt(auxiArray[topOfStack-1])<Integer.parseInt(data))
				auxiArray[topOfStack] = auxiArray[topOfStack-1];
			else
				auxiArray[topOfStack] = data;}
		}
		else
			System.out.println("The stack is full");
	}
	
	public String pop()
	{
		if(topOfStack<=0)
		{
			System.out.println("Empty Stack");
			return "-1";
		}
		else
		{
			System.out.println("Pop :"+ stackArray[topOfStack]);
			String temp = stackArray[topOfStack];
			stackArray[topOfStack] = "-1";
			topOfStack--;
			
			return temp;
		}
	
		
	}
	
	public String MinElement(){
		return auxiArray[topOfStack];
	}
	public void reverse()
	{
		reverse1(topOfStack);
	}
	
	public void reverse1(int i)
	{
		String temp;
		
		if(!isEmpty())
		{
			temp = stackArray[topOfStack];
			topOfStack--;
			reverse1(topOfStack);
			
			insertAtBottom(topOfStack,temp);
		}
	}
	
	public void insertAtBottom(int i, String t)
	{
		if(isEmpty())
		{
			topOfStack++;
			stackArray[topOfStack]=t;
		}
		
		else
		{
			
			String temp = stackArray[topOfStack];
			topOfStack--;
			insertAtBottom(i, t);
			
			topOfStack++;
			stackArray[topOfStack]=temp;
			
		}
	}
	
	public String peek()
	{
		return stackArray[topOfStack];
	}
	
	public boolean isEmpty()
	{
		return (topOfStack==0);
	}
	public static void main(String[] args)
	{
		stack theStack = new stack(10);
		
		theStack.push("10");
        theStack.push("17");
        theStack.push("13");
         
        theStack.peek();
      //  theStack.pop();
       // theStack.pop();
        
        theStack.display();
       // nextGreater(new int[]{1,7,4,12,16,75});
        
        char[] ch = "{()}[]".toCharArray();
        System.out.println(checkParenthesis(ch));
        
        theStack.reverse();
        theStack.display();
        System.out.println("min element"+theStack.MinElement());
        
       /* String s1 = "Pratik";
		
		stack s = new stack(s1.length()+1);
		
		char[] ch = s1.toCharArray();
		for(int i=0;i<s1.length();i++)
		{
			s.push(ch[i]+"");
			System.out.println(ch[i]);
		}
		StringBuilder ss = new StringBuilder();
		
		for(int i =0;i<s1.length();i++)
		{
			ss.append(s.pop());
		}
		
		
		System.out.println(ss);*/
		
        
	}
	
	public static void nextGreater(int[] a)
	{
		Stack<Integer> sta = new Stack<Integer>();
		
		int i = 0;
		
		sta.push(a[i]);
		
		for(i=1;i<a.length;i++)
		{
			int next = a[i];
			
			if(!sta.isEmpty())
			{
				int element = sta.pop();
				
				while(element < next)
				{
					System.out.println(element +" >>" +next);
					if(sta.isEmpty())
						break;
					else
						element = sta.pop();
				}
				
				if(element > next)
					sta.push(element);
			}
			
			sta.push(next);
		}
		
		while(!sta.isEmpty())
		{
			int element = sta.pop();
			System.out.println(element +">> -1" );
		}
		
		
	}
	
	public static boolean checkParenthesis(char a[])
	{
		
		Stack<Character> s = new Stack<Character>();
		
		int i=0;
		
		while(i<a.length){
			
			if(a[i] == '{' || a[i] == '(' || a[i] == '[')
		        s.push(a[i]);
			
			if(a[i] == '}' || a[i] == ')' || a[i] == ']')
			{
				if(s.isEmpty())
					return false;
				
				
				if(!isMatchingPair(s.pop(), a[i]))
					return false;
				
				
			}
			i++;
			
		}
		
		if(s.isEmpty())
			return true;
		else
			return false;
		
	}
	
	public static boolean isMatchingPair(char character1, char character2)
	{
	   if(character1 == '(' && character2 == ')')
	     return true;
	   else if(character1 == '{' && character2 == '}')
	     return true;
	   else if(character1 == '[' && character2 == ']')
	     return true;
	   else
	     return false;
	}
}






