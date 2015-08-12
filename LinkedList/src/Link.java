
/*public class Link {

	public String name;
	public int millionSold;
	
	public Link next;
	
	public Link(String name, int millionSold) {
		this.name = name;
		this.millionSold = millionSold;
	}
	
	public void display(){
	System.out.println(name + ": "+ millionSold + ",000,000");
	}
	
	public String toString(){
		return name;
	}

	public static void main(String[] args){
	
		LinkedList l1 = new LinkedList();
		
		l1.insertFirstLink("yoo", 2000);
		l1.insertFirstLink("bsvds",233);
		l1.insertFirstLink("yoo", 2000);
		l1.insertFirstLink("bsvds",233);
		
		l1.display();
		l1.removeFirst();
		l1.display();
		
	}
	
}
	class LinkedList{
		
		public Link firstLink;
		
		public LinkedList()
		{
			firstLink = null;
		}
		
		public boolean isEmpty(){
			return(firstLink==null);
		}
		
		public void insertFirstLink(String name, int million){
			Link newLink = new Link(name,million);
			
			newLink.next = firstLink;
			
			firstLink = newLink;
		}
		
		public Link removeFirst(){
			
			Link linkReference = firstLink;
			
			if(!isEmpty())
			{
				firstLink = firstLink.next;
			}
			else
				System.out.println("Empty Linked list");

			return linkReference;
		}
		
		
		public void display()
		{
			Link theLink = firstLink;
			
			while(theLink !=null)
			{
				theLink.display();
				System.out.println("Next link: " + theLink.next);
				theLink = theLink.next;
			}
		}
		
		public Link find(String name){
			
			Link theLink = firstLink;
			
			if(!isEmpty())
			{
				while(theLink.name != name)
				{
					if(theLink.next == null)
						return null;
					else
						theLink = theLink.next;
				}
			}
			else
				System.out.println("Empty Linked List");
			
			return theLink;
		}
		
		public Link removeLink(String name)
		{
			Link currentLink = firstLink;
			Link previousLink = firstLink;
			
			while(currentLink.name != name)
			{
				if(currentLink.next == null)
					return null;
				else{
					previousLink = currentLink;
					currentLink = currentLink.next;
					
				}
				
			}
			
			if(currentLink == firstLink)
			{
				firstLink =firstLink.next;
			}
			else
			{
				previousLink.next = currentLink.next;
			}
			
		
			return currentLink;
		}
		
	}*/
	



class Link{
	
	public String name;
	
	public int million;
	
	Link next;
	
	public Link(String name, int million)
	{
		this.name = name;
		this.million = million;
		
	}
	
	
	public void display()
	{
		System.out.println(name+ ": "+ million);
	}
	
	
	public static void main(String[] args)
	{
		LinkList l1 = new LinkList();
		
		l1.insertfirst("yoo", 2000);
		l1.insertfirst("bsvds",233);
		l1.insertfirst("yoo", 2000);
		l1.insertfirst("bsvds",233);
		
		l1.display();
		//l1.reverse();
		
		System.out.println(">>>>>>>>>>");
		l1.display();
		//l1.removeFirst();
		
		//l1.display();
		
		
		
	}
	
	
	
	
}


class LinkList{
	
	Link firstlink ;
	
	public LinkList()
	{
		firstlink = null;
	}
	
	public boolean isEmpty(){
		return (firstlink == null);
	}
	
	public void insertfirst(String name, int million)
	{
		Link newlink = new Link(name,million);
		
		newlink.next = firstlink;
		firstlink = newlink;
		
	}
	
	public void insertlast(String name, int million)
	{
		Link nlink = new Link(name,million);
		
		Link thelink = firstlink;
		if(thelink == null)
		{
			nlink.next = firstlink;
			firstlink = nlink;
		}
		else
		{	
			while(thelink.next != null)
				thelink = thelink.next;
			
			nlink.next = thelink.next;
			thelink.next = nlink;
		}		
		
	}
	public void insertafter(String name1,String name, int million)
	{
		Link nlink = new Link(name,million);

		Link thelink = firstlink;
		
		if(thelink == null)
		{
			nlink.next = firstlink;
			firstlink = nlink;
		}
		else
		{
			while(thelink != null)
			{
				if(thelink.name == name1)
				{
					nlink.next = thelink.next;
					thelink.next = nlink;
				}
				else
					thelink = thelink.next;
					
			}
		}
	}
	
	public void removeFirst(){
		
		if(!isEmpty())
		{
			firstlink = firstlink.next; 
		}
		
		else
			System.out.println("Empty Linkedlist");
	}
	
	public void display(){
		Link thelink = firstlink;
		
		while(thelink != null)
		{
			System.out.println(thelink.name +": " +thelink.million);
			thelink = thelink.next;
		}
	}
	
	public Link find(String name)
	{
		Link thelink = firstlink;
		
		if(!isEmpty())
		{
			while(thelink.name != name)
			{
				if(thelink.next == null)
					return null;
				else
					thelink = thelink.next;
			}
		}
		
		return thelink;
	}
	
	public Link remove(String name)
	{
		Link plink = firstlink;
		Link clink = firstlink;
		
		while(clink != null)
		{
			if(clink.next == null)
				return null;
			else
			{
				plink = clink;
				clink = clink.next;
			}
		}
		
		if(clink == firstlink)
			firstlink = firstlink.next;
		else
			plink.next = clink.next;
		
		return clink;
		
		
	}
	
	
	public int length (Link n)
	{
		if(n == null)
			return 0;
		else
			return 1 + length(n.next);
	}
	

	public boolean search(Link n, String na)
	{
		if(n == null)
			return false;
		if(n.name == na)
			return true;
		else 
			return search(n.next,na);
			
	}
	
	public String Nthelement(int n)
	{
		Link thelink = firstlink;
		int count =0;
		
		while(thelink != null)
		{
			if(count ==n)
				return thelink.name;
			else
			{
				count ++;
				thelink = thelink.next;
			}
		}
		
		return "Least Numbers";
		
	}
	
	
	/*
	 *   n.name = n.next.name;
	 *   n.next = n.next.next;
	 *   
	 * 
	 * 
	 * */
	
	
	public void printmiddle()
	{
		Link slink = firstlink;
		Link flink = firstlink;
		
		if(!isEmpty())
		{
			while(flink != null  && flink.next != null)
			{
				slink = slink.next;
				flink = flink.next.next;
			
			}
			System.out.println("the middle element is :"+ slink.name);
		}
		else
			System.out.println("Empty LinkedList");
	}
	
	
	public String NthelementFromLast(int n)
	{
		Link link = firstlink;
		Link nlink = firstlink;
		
		int count = 0;
		
		while(count <= n)
		{	
			nlink = nlink.next;
			count++;
		}
		
		while(nlink.next !=null)
		{	
			nlink = nlink.next;
			link = link.next;
		}
		return link.name;
	}
	
	public void delLinkList()
	{
		firstlink = null;
	}
	
	public int OcurranceNum(int n)
	{
		Link thelink = firstlink;
		int count = 0;
		
		while(thelink!=null)
		{
			if(thelink.million ==n)
				count++;
			
			thelink = thelink.next;
		}
		
		return count;
	}
	
	public void reverse(){
		
		Link p = null;
		Link c = firstlink;
		Link next;
		
		if(!isEmpty())
		{	
			while(c != null)
			{
				next = c.next;
				c.next = p;
				p = c;
				c = next;
				
			}
			
			firstlink = p;
		}
		else
		{
			System.out.println("Empty LinkedList");
		}
	}
	
	
	public void recreverse(){
		
	}
	
	
}





























