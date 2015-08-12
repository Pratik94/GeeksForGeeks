import java.util.Arrays;


public class twoStack {
	
	private int stackArray[];
	
	private int size;
	
    private int top1,top2;
    
    public twoStack(int size){
    	this.size= size;
    	top1 = -1;
    	top2 = size;
    	stackArray = new int[size];
    	Arrays.fill(stackArray, -1);
    }

    
    public void push1(int x)
    {
    	if(top1 < top2-1)
    	{
    		top1++;
    		stackArray[top1] = x;
    	}
    	else
    		System.out.println("stack overflow");
    }
    
    public void push2(int x)
    {
    	if(top1<top2-1)
    	{
    		top2--;
    		stackArray[top2] = x;
    	}
    }
    
    public int pop1()
    {
    	if(top1>=0)
    	{
    		int x = stackArray[top1];
    		top1--;
    		return x;
    	}
    	System.out.println("Empty Stack");
    	return -1;
    }
    
    public int pop2()
    {
    	if(top2<size)
    	{
    		int x = stackArray[top2];
    		top2++;
    		return x;
    	}
    	System.out.println("Stack empty");
    	return -1;
    }
    
}
