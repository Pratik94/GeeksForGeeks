
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root=new Node(5);
		
		root.left=new Node(3);
		root.right=new Node(2);
		root.left.right=new Node(20);
		root.right.right=new Node(2);
		root.left.left=new Node(1);
		root.left.left.left=new Node(1);
		//root.left.left.left.left=new Node(32);
		//System.out.println(root.data);
		//System.out.println(root.left.data);
		//System.out.println(root.right.data);
		tree t=new tree();
		//t.LevelOrder(root);
		
		//t.verticalsum(root);
		//t.printlevelorder(root);
		
		/*int ans=t.balance(root);
		if(ans==1)
		{
			System.out.println("posible");
		}
		else
		{
			System.out.println("no posilble");
		}*/
		
		//int x=t.diameter(root);
		//System.out.println("root is"+x);
	
		//t.convertToSumTree(root);
		
		//t.LevelOrder(root);
		
		//t.inoreder(root);
	//	System.out.println("After mirroe>>>>>>>>>>>");
	//	t.mirrore(root);
	//	t.inoreder(root);
		//t.inoreder(root);
		//int p=t.getleaf(root);
		//System.out.println("size is"+p);
	}

}
