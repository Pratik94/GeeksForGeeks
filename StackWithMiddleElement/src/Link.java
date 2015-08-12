public class Link{
	
	int data;
	String name;
	
	Link next = null;
	Link previous = null;
	
	public Link(int data, String name)
	{
		this.data = data;
		this.name = name;
	}
	
	public void display()
	{
		System.out.println(name +" : "+data);
	}
	
	
	public static void main(String[] args)
	{
		 
		 	       
	}
}

class stack{
	
	Link firstnode;
	Link Middlenode;
	int count;
	
	public stack(int data)
	{
		this.count = data;
		firstnode = null;
		Middlenode = null;
	}
	
	public boolean isEmpty(){
		return (firstnode == null);
	}
	
	public void push()
	{
		
	}
	
	
}