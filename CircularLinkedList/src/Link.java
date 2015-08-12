import java.text.CharacterIterator;

import javax.crypto.CipherSpi;


public class Link {

	int data;
	
	Link next;
	
	
	public Link(int data)
	{
		this.data = data;
		next = null;
	}
	
	public void display()
	{
		System.out.println("data :"+data);
	}
	
	public static void main(String[] args)
	{
		CircularLinkedList cr = new CircularLinkedList();
		
		cr.sortedInsert(3);
		cr.sortedInsert(2);
		cr.sortedInsert(1);	
		System.out.println(">>>>>>>>>>>>>>>>");
		cr.display();
		//System.out.println(cr.firstlink.next.next.next.data);
	}
}

class CircularLinkedList{
	
	Link firstlink ;
	
	public CircularLinkedList() {
		// TODO Auto-generated constructor stub
		firstlink = null;	
	}

	public boolean isEmpty(){
		return (firstlink==null);
	}
	public void insert(int data)
	{
		Link newlink = new Link(data);
		
		Link thelink = firstlink;
		if(isEmpty())
		{
			firstlink = newlink;
			firstlink.next = firstlink;
		}
		
		else
		{

			while(thelink.next!= firstlink)
			{
				thelink = thelink.next;
			}
			newlink.next = firstlink;
			thelink.next = newlink;

		}
	}
	
	public void sortedInsert(int data)
	{
		Link thelink = firstlink;
		
		Link newlink = new Link(data);
		
		if(isEmpty())
		{
			firstlink = newlink;
			firstlink.next = firstlink;
		}
		else if(thelink.data >= data)
		{
			while(thelink.next != firstlink)
			{
				thelink = thelink.next;
			}
			newlink.next = firstlink;
			firstlink = newlink;
			thelink.next = firstlink;
		}
		else 
		{
			while(thelink.data <= data)
			{
				System.out.println("data :"+thelink.data);
				thelink = thelink.next;

				if(thelink == firstlink)
					return;
				
			}
			newlink.next = firstlink;
			thelink.next = newlink;
		}
		
	}
	
	
	public void display()
	{
		Link thelink = firstlink;
		
		if(!isEmpty())
		{
			System.out.println("data: "+ thelink.data);
			while(thelink.next != firstlink)
			{
				System.out.println("print");
				thelink = thelink.next;
				System.out.println("data: "+thelink.data);
			}
			//System.out.println("data: "+ thelink.data);
		}
	}
	
	public Link[] splite()
	{
		Link thelink = firstlink.next;
		Link slink = firstlink;
		Link flink = firstlink.next;
		
		Link l1 = null;
		
		Link l2 = null;
		
		while(thelink.next != firstlink && thelink.next.next != firstlink)
		{
			slink = thelink.next;
			flink = thelink.next.next;
			thelink = thelink.next.next;
		}
		
		if(flink.next.next == firstlink)
			flink = flink.next;
		
		l1 = firstlink;
		
		if(firstlink.next != firstlink)
			l2 = slink.next;
		
		flink.next = slink.next;
		
		slink.next = firstlink;
		
		return new Link[]{l1,l2};
		
	}
}