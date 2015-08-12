import java.util.*;
public class tree
{
	Node root;
	public tree(){
	this.root = null;
	}
public void insert(int key)
{
	Node n=new Node(key);
	n.data=key;
	n.right=null;
	n.left=null;
}
public void inoreder(Node root)
{
	if(root==null)
	{
		return;
	}
	inoreder(root.left);
	
	System.out.println(root.data);
	inoreder(root.right);
	
	
}
public void postorder(Node root)
{
	if(root==null)
	{
		return;
	}
	postorder(root.left);
	postorder(root.right);
	System.out.println(root.data);
}

public void preorder(Node root)
{
	if(root == null)
		return;
	
	System.out.println(root.data);
	preorder(root.left);
	preorder(root.right);
}

public int size(Node root)
{
	if(root == null)
		return 0;
	
	else 
		return (1+size(root.left)+size(root.right));
}

public int maxDepth(Node root)
{
	if(root == null)
		return 0;
	
	else
		return (Math.max(maxDepth(root.left),maxdepth(root.right))+1);
}

public int maxdepth(Node root)
{
	if(root==null)
	{
		return 0;
	}
	else 
	{
		int ldepth=maxdepth(root.left);
		System.out.println("l.depth is"+ldepth+"   root.data is"+root.data);
		int rdepth=maxdepth(root.right);
		System.out.println("rdepth is"+rdepth+" root.data is"+root.data);
		if(ldepth>rdepth)
		{
			return(ldepth+1);
		}
		else
		{
			return(rdepth+1);
		}
	}
}

public void delete(Node root)
{
	if(root ==null)
		return;
	
	delete(root.left);
	delete(root.right);
	
	root = null;
}

public void mirrore(Node root)
{
	if(root == null)
		return;
	
	mirrore(root.left);
	mirrore(root.right);
	
	Node temp = root.left;
	root.left = root.right;
	root.right = temp;
}

public int areIdentical(Node n, Node m)
{
	if(n==null && m==null)
		return 1;
	
	if(n!= null && m !=null)
	{
		int l = areIdentical(n.left, m.left);
		int r = areIdentical(n.right, m.right);
		
		if(n.data == m.data && l==1 && r ==1)
			return 1;
		else 
			return 0;	
	}
	else
		return 0;
}

public void PrintPath(Node root)
{
	int[] path = new int[1000];
	printSecure(root,path,0);
}

public void printSecure(Node root, int[] path, int length)
{
	if(root == null)
		return;
	
	path[length] = root.data;
	length++;
	
	if(root.left == null && root.right == null)
	{
		printarry(path, length);
	}
	else
	{
		printSecure(root.left, path, length);
		printSecure(root.right, path, length);
	}
}


public void printarry(int[] path, int len) {
	// TODO Auto-generated method stub
	int i;
	for(i=0;i<len;i++)
	{
		System.out.print(path[i]+"   ");
		
	}
	System.out.println();
	
}


public void printlevelorder(Node root)
{
	int h=maxdepth(root);
	for(int i=1;i<=h;i++)
	{
		levelorder(root,i);
	}
}
public void levelorder(Node root, int level) {
	// TODO Auto-generated method stub
	if(root==null)
	{
		return;
	}
	if(level==1)
	{
		System.out.print(root.data+"   ");
	}
	else if(level>1)
	{
		levelorder(root.left,level-1);
		levelorder(root.right,level-1);
	}
	
}


public void LevelOrder(Node root)
{
	Queue<Node> que = new LinkedList<Node>();

	Node temp = root;
	
	while(temp != null)
	{
		System.out.print(temp.data+" ");
		if(temp.left != null)
			que.add(temp.left);
		
		if(temp.right != null)
			que.add(temp.right);
		
		if(!que.isEmpty())
			temp = que.remove();
		else
			temp = null;
	}
	
}

public int getLeafCount(Node root)
{
	if(root == null)
		return 0;
	
	if(root.left == null && root.right == null)
		return 1;
	else
		return getLeafCount(root.left) + getLeafCount(root.right);
}


public int ToCheckSumProperty(Node root)
{
	if(root == null && (root.left == null) && (root.right == null))
		return 1;
	
	int left=0,right=0;
	
	if(root.left != null)
		left = root.left.data;
	
	if(root.right != null)
		left = root.right.data;
	
	int lcheck = ToCheckSumProperty(root.left);
	int rcheck = ToCheckSumProperty(root.right);
	
	if(root.data == (left+right) && (lcheck==1)&&(rcheck == 1))
		return 1;
	else 
		return 0;
	
}

public int diameter(Node root)
{
	if(root==null)
	{
		return 0;
	}
	int lheight=height(root.left);
	int rheight=height(root.right);
	
	int ldia=diameter(root.left);
	int rdia=diameter(root.right);
	
	return Math.max(lheight+rheight+1,Math.max(diameter(root.left),diameter(root.right)));
}

public int height(Node root) {
	// TODO Auto-generated method stub
	if(root==null)
	{
		return 0;
	}
	
	return(1+Math.max(height(root.left),height(root.right)));
}

public int balance(Node root)
{
	if(root==null)
	{
		return 1;
	}
	int lh=height(root.left);
	int rh=height(root.right);

	int p=balance(root.left);
	int q=balance(root.right);
	
	if (Math.abs(lh-rh)<=1 &&  p==1 && q==1)
		return 1;
	else 
		return 0;
}

public int Diameter(Node root)
{
	if(root == null)
		return 0;
	
	int lh = height(root.left);
	int rh = height(root.right);
	
	int ld = Diameter(root.left);
	int rd = Diameter(root.right);
	
	return Math.max(lh+rh+1, Math.max(ld,rd));
}

public int search(char arr[], int strt, int end, int value)
{
  int i;
  for(i = strt; i <= end; i++)
  {
    if(arr[i] == value)
      return i;
  }
return 0;
}

public Node buildTree(char in[], char pre[], int inStrt, int inEnd)
{
  int preIndex = 0;
 
  if(inStrt > inEnd)
     return null;
  
  Node node = new Node(pre[preIndex++]);
 
   if(inStrt == inEnd)
    return node;
 
  int inIndex = search(in, inStrt, inEnd, node.data);
 
  node.left = buildTree(in, pre, inStrt, inIndex-1);
  node.right = buildTree(in, pre, inIndex+1, inEnd);
 
  return node;
}

public void convertToSumTree(Node root)
{
	if(root == null ||( root.left == null && root.right == null))
		return;
	
	convertToSumTree(root.left);
	convertToSumTree(root.right);
	
	int leftData=0, rightData=0;
	
	if(root.left != null)
		leftData = root.left.data;
	if(root.right != null)
		rightData = root.right.data;
	
	int diff = leftData + rightData - root.data;
	
	if(diff>0)
		root.data+= diff;
	else if(diff <0)
		increment(root,-diff);
	
	
}

public void increment(Node root, int diff)
{
	if(root.left != null)
	{
		root.left.data += diff;
		increment(root.left, diff);
	}
	else if(root.right != null)
	{
		root.right.data += diff;
		increment(root.right, diff);
				
	}
	
	
}

void doubleTree(Node node) 
{
  Node oldLeft;
 
  if (node==null) return;
 
  doubleTree(node.left);
  doubleTree(node.right);
 
  oldLeft = node.left;
  node.left = new Node(node.data);
  node.left.left = oldLeft;
}

public void printKDistant(Node root , int k)    
{
   if(root == null) 
      return;
   if( k == 0 )
   {
      System.out.println(root.data + " ");
      return ;
   }
   else
   {      
      printKDistant( root.left, k-1 ) ;
      printKDistant( root.right, k-1 ) ;
   }
}

public boolean isSubtree(Node T, Node S)
{
    if (S == null)
        return true;
 
    if (T == null)
        return false;
 
    if (areIdentical(T, S)==1)
        return true;
 
    return isSubtree(T.left, S) ||
           isSubtree(T.right, S);
}

public int getMaxWidth(Node root)
{
	int h = height(root);
	
	int[] count = new int[100];
	
	int level = 0;
	
	getMaxWidthRec(root, count , level);
	
	return getMax(count,h);
};

public int getMax(int arr[], int n)
{
	   int max = arr[0];
	   int i;
	   for (i = 0; i < n; i++)
	   {
	       if (arr[i] > max)
	          max = arr[i];
	   }
	   return max;
	}

public void getMaxWidthRec(Node root, int[] count, int level)
{
	if(root!= null)
	{
		count[level]++;
		getMaxWidthRec(root.left, count , level+1);
		getMaxWidthRec(root.right, count , level+1);

	}
}


public boolean printAncestors(Node root, int target)
{
	if(root == null)
		return false;
	
	if(root.data == target)
		return true;
	
	if(printAncestors(root.left, target)|| printAncestors(root.right, target))
	{
		System.out.println(root.data+" ");
		return true;
	}
	return false;
	
}

public int getLevelUtil(Node root,int data,int level)
{
	if(root == null)
		return 0;
	
	
	if(root.data == data)
		return level;
	
	int leftlevel = getLevelUtil(root, data, level+1);
	if(leftlevel !=0)
		return leftlevel;
	 
	leftlevel = getLevelUtil(root, data, level+1);
    return leftlevel;

	    
}


public Node buildTreeFRomInorder(int[] inorder, int start,int end){
 
	if(start>end)
		return null;
 
	int i = max(inorder, start,end);
	
	Node root = new Node(inorder[i]);
	
	if(start==end)
		return root;
	
	root.left = buildTreeFRomInorder(inorder, start, i-1);
	
	root.right = buildTreeFRomInorder(inorder, i+1, end);
	
	return root;
 
}

public int max (int arr[], int strt, int end)
{
    int i, max = arr[strt], maxind = strt;
    for(i = strt+1; i <= end; i++)
    {
        if(arr[i] > max)
        {
            max = arr[i];
            maxind = i;
        }
    }
    return maxind;
}

public int convertToSumeTree(Node root)
{
	if(root == null)
		return 0;
	
	int oldValue = root.data;
	
	root.data = convertToSumeTree(root.left) + convertToSumeTree(root.right);
	
	return root.data + oldValue;
}

public void connect(Node root)
{
	root.next=null;
	connectrecur(root);
}
public void connectrecur(Node root) {
	// TODO Auto-generated method stub
	if(root==null)
		return;
	if(root.left!=null)
		{
		if(root.right!=null)
			{
				root.left.next=root.right;
				root.right.next=getnextright(root);
			}
		else
			root.left.next=getnextright(root);
		
		connectrecur(root);
		}
	else if(root.right!=null)
	{
		root.right.next=getnextright(root);
		connectrecur(root.right);
	}
	else
		connectrecur(getnextright(root));
}

public Node getnextright(Node root) {
	// TODO Auto-generated method stub
	Node temp=root.next;
	while(temp!=null)
	{
		if(temp.left!=null)
		{
			return temp.left;
		}
		if(temp.right!=null)
		{
			return temp.right;
		}
		temp=temp.next;
	}
	return null;
}



public void verticalsum(Node root)
{
	if(root==null)
	{
		return;
	}
	HashMap <Integer,Integer> hm=new HashMap <Integer,Integer>();
	Verticalsumutil(root,0,hm);
	if(hm!=null)
	{
		System.out.println(hm.entrySet());
	}
}
private void Verticalsumutil(Node root, int i, HashMap<Integer, Integer> hm) {
	// TODO Auto-generated method stub
	if(root==null)
	{
		return;
	}
	Verticalsumutil(root.left,i-1,hm);
	int pre=(hm.get(i)==null)?0:i;
	hm.put(i, pre+root.data );
	Verticalsumutil(root.right,i+1,hm);
	
}
}
