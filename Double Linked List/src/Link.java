import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

//import java.awt.datatransfer.ClipboardOwner;
//
//
//public class Link {
//
//	int data;
//	String name;
//	
//	Link next;
//	Link previous;
//	
//	public Link(int data, String name)
//	{
//		this.data = data;
//		this.name = name;
//	}
//	
//	
//	public void display(){
//		System.out.println(name +" : "+ data);
//	}
//	
//	public static void main(String[] args){
//		
//	}
//	
//}
//
//class doubleLinkedList{
//	Link firstlink;
//	Link lastlink;
//	
//	public doubleLinkedList()
//	{
//		firstlink = null;
//		lastlink = null;
//	}
//	
//	public boolean isEmpty()
//	{
//		return(firstlink==null);
//	}
//	public void InsertFirst(int data,String name){
//		
//		Link newlink = new Link(data,name);
//		
//		if(isEmpty())
//		{
//			lastlink = newlink;
//		}
//		else
//			firstlink.previous = newlink;
//		
//		newlink.next = firstlink;
//		
//		firstlink = newlink;
//		
//	}
//	
//	public void InsertLast(int data,String name)
//	{
//		Link newlink = new Link(data,name);
//		
//		if(isEmpty())
//		{
//			firstlink = newlink;
//		}
//		else
//			lastlink.next = newlink;
//		
//		newlink.previous = lastlink;
//		lastlink = newlink;
//		
//	}
//	
//	
//	public boolean insertAfter(String name,int data, int key){
//		
//		Link newlink = new Link(data,name) ;
//		
//		Link clink = firstlink;
//		
//		while(clink.data != key)
//		{
//				clink = clink.next;
//				if(clink ==null)
//					return false;
//		}
//		
//		if(clink == lastlink)
//		{
//			newlink.next = null ;
//			lastlink = newlink;
//		}
//		else{
//		newlink.next = clink.next;
//		clink.next.previous = newlink;
//		}
//		
//		newlink.previous = clink;
//		clink.next = newlink;
//		
//		return true;
//	}
//	
//	public void display()
//	{
//		Link thelink = firstlink;
//		
//		while(thelink != null)
//		{
//			thelink.display();
//			System.out.println("Next Link : "+thelink.next);
//			thelink = thelink.next;
//		}
//	}
//	
//}

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
		 DoubleLinkedList theLinkedList = new DoubleLinkedList();
			         
			         
		 	       /* theLinkedList.insertFirst("Mark Evans", 7);
		 	        theLinkedList.insertFirst("Piers Polkiss", 9);
		 	        theLinkedList.insertFirst("Doreen Figg", 6);
		 	        theLinkedList.insertFirst("Petunia Dursley", 4);
		 	         */
		 	         
		 	        
		 	        theLinkedList.insertInorder("Mark Evans", 7);
		 	        theLinkedList.insertInorder("Piers Polkiss", 9);
		 	        theLinkedList.insertInorder("Doreen Figg", 6);
		 	        theLinkedList.insertInorder("Petunia Dursley", 4);
		 	        
		 	         
		 	        theLinkedList.display();
		 	         
		 	        theLinkedList.insertAfter(2,"Derek Banas",  6);
		 	         
		 	        theLinkedList.delete(9);
		 	       
		 	        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 	       theLinkedList.display();
		 	       
		 	        theLinkedList.delete(4);
		 	       System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		 	       theLinkedList.display();
		 	       
		 	        theLinkedList.delete(2);

		 	        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 	        theLinkedList.display();
		 	        
		 	       theLinkedList.reverse();
		 	       System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 	       theLinkedList.display();
		 	       
		 	      // http://cslibrary.stanford.edu/109/
		 	       TreeList tr = new TreeList();
		 	      
		 	      tr.insert(4);
		 	      tr.insert(2);
		 	      tr.insert(1);
		 	      tr.insert(3);
		 	      tr.insert(5);
		 	        
		 	      System.out.println("tree:");
		 	      tr.printTree();   // 1 2 3 4 5
		 	       
	}
}


class DoubleLinkedList{
	
	Link firstlink;
	Link lastlink;
	
	public DoubleLinkedList()
	{
		firstlink = null;
		lastlink = null;
	}
	
	public boolean isEmpty(){
		return (firstlink == null);
	}
	
	public void insertFirst(String name, int data)
	{
		Link newlink = new Link(data,name);
		
		if(isEmpty())
		{
			lastlink = newlink;
		}
		else
		{
			firstlink.previous = newlink;
		}
		
		newlink.next = firstlink;
		
		firstlink = newlink;
		
	}
	
	public void insertLast(int data, String name){
		
		Link newlink = new Link(data,name);
		
		if(isEmpty())
		{
			firstlink = newlink;
		}
		else
			lastlink.next = newlink;
			
		newlink.previous = lastlink;
		
		lastlink = newlink;
		
	}
	
	public boolean insertAfter(int data, String name, int key)
	{
		Link newlink = new Link(data, name);
		 Link clink = firstlink;
		 if(isEmpty())
		 {
			 firstlink = newlink;
			 lastlink = newlink;
		 }
		 else{
			 while(clink.data != key)
			 {
				 clink = clink.next;
				 if(clink == null)
					 return false;
			 }
			 
			 if(clink == lastlink)
			 {
				 newlink.next = null;
				 lastlink = newlink;
						 
			 }
			 else
			 {
				 newlink.next = clink.next;
				 clink.next.previous = newlink;
			 }
			 
			 newlink.previous = clink;
			 clink.next = newlink;
		 }
		return true;
	}
	
	public void insertInorder(String name, int data)
	{
		Link newlink = new Link(data,name);
		
		Link plink = null;
		Link clink = firstlink;
		
		while((clink !=null) && (clink.data<data))
		{
			plink = clink;
			clink = clink.next;
		}
		if(plink == null)
		{
			firstlink = newlink;
		}
		else
		{	
			plink.next = newlink;
			newlink.previous = plink;
		}
		newlink.next = clink;
		
	}
	
	public void display()
	{
		Link thelink = firstlink;
		
		while(thelink!=null)
		{
			System.out.println(thelink.data+" : "+thelink.name);
			thelink = thelink.next;
		}
	}
	
	public void delete(int data)
	{
		Link thelink = firstlink;
		
		while(thelink.data!=data)
		{
			thelink = thelink.next;
		}
		
		if(thelink == firstlink)
		{
			firstlink = firstlink.next;
			firstlink.previous = null;
		}
		else if(thelink == lastlink)
		{
			lastlink = lastlink.previous;
			lastlink.next = null;
		}
		if(thelink.previous !=null)
			thelink.previous.next = thelink.next;
		
		if(thelink.next != null)
			thelink.next.previous = thelink.previous;
				
		
	}
	
	public void reverse()
	{
		Link plink = null;
		Link clink = firstlink;
		Link temp = null;
		
		while(clink!=null)
		{
			temp = clink.previous;
			clink.previous = clink.next;
			clink.next = temp;
			clink = clink.previous;
		}
		
		firstlink = temp.previous;
		
	}	
	

	public DoubleLinkedList clon()
	{
		Link currlink = firstlink;
		
		Link clonelink = null;
		
		Map<Link,Link> map = new HashMap<Link,Link>();
		
		while(currlink != null)
		{
			clonelink = new Link(currlink.data,"");
			map.put(currlink,clonelink);
			currlink = currlink.next;
		}
		
		currlink = firstlink;
		
		while(currlink != null)
		{
			clonelink = map.get(currlink);
			clonelink.next = map.get(currlink.next);
			clonelink.previous = map.get(currlink.previous);
			currlink = currlink.next;
		}
		
		DoubleLinkedList dl = new DoubleLinkedList();
		
		dl.firstlink = map.get(this.firstlink);
		
		return dl;
		
	}
	
	public void quicksort()
	{
		_quicksort(firstlink,lastlink);	
	}
	public void _quicksort(Link l,Link h)
	{
		if(l!=null && h!= null && l != h.next)
		{
			Link p = partition(l,h);
			_quicksort(l, p.previous);
			_quicksort(p.next, h);
		}
	}
	
	public Link partition(Link l, Link h)
	{
		int x = h.data;
		
		Link i = l.previous;
		
		for(Link j = l;j!= h.next;j=j.next)
		{
			if(j.data<=x){
				i = (i==null)?l:i.next;
				int temp = i.data;
				
				i.data = j.data;
				j.data = temp;
			}

		}
		
		i= (i==null)?l:i.next;
		
		int temp = i.data;
		i.data = h.data;
		h.data = temp;
		
		return i ;
	}
}



class TreeList{
	
	DoubleLinkedList d = new DoubleLinkedList();
	
	Link root;
	public TreeList(){
		root = null;
	}
	
	public void insert(int data){
		
		if(root == null)
			root = new Link(data,"");
		else
			treeinsert(root, data);
	}
	
	public void treeinsert(Link root, int data)
	{
		if(data <= root.data)
		{
			if(root.previous != null)
				treeinsert(root.next,data);
			else
				root.previous = new Link(data,"");
		}
		else
		{
			if(root.next != null)
				treeinsert(root.next,data);
			else
				root.next = new Link(data,"");
		}
	}
	
	public void printTree()
	{
		printTree1(root);
	}
	
	public void printTree1(Link root){
		
		if(root == null)
			return;
		printTree1(root.previous);
		System.out.println(root.data+" ");
		d.insertLast(root.data,"");
		printTree1(root.next);
	}
	
	public void printList(Link head){
		
		Link thelink = head;
		
		while(thelink != null)
		{
			System.out.println(thelink.data +" ");
			thelink = thelink.next;
			if(thelink == null)
				break;
		}
		System.out.println("");
		
	}
	
	public void join(Link a, Link b)
	{
		a.next = b;
		b.previous = a;
	}
	
	/*public Link append(Link a,Link b)
	{
		
	}*/
	
	
}