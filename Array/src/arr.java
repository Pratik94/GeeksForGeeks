import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;


public class arr {
	
	public static void main(String[] args)
	{
		 int a[] = {1,3,4,5,5,5,4,3,2,2};
	//	 printMajorityElement(a,a.length);
		// printCombination(a, a.length, 3);
		 
		 //sortElementFrequency(a);
		 
		 System.out.println(maxRepeating(a, a.length, 8));
	}

	public static void printMajorityElement(int[] a,int length)
	{
	
		int cand = findCandidate(a,length);
		
		if(isValidCandidate(a,cand,length))
			System.out.println("Majority Element is :" +cand);
		else
			System.out.println("No majaority element");
		
	}
	
	
	public static boolean isValidCandidate(int[] ar,int a, int size)
	{
		int count =0;
		
		for(int i=0;i<size;i++)
		{
			if(ar[i]==a)
				count++;
		}
		
		if(count>size/2)
			return true;
		else
			return false;
	}
	
	public static int findCandidate(int[] a,int size){
		
		int major_index =0;
		int count =1 ;
		
		for(int i=1;i<size;i++)
		{
			if(a[major_index] == a[i])
				count++;
			else
				count--;
			
			if(count==0)
			{
				major_index=i;
				count =1;
			}
		}
		
		return a[major_index];
		
	}
	
	 
	public static void printCombination(int arr[], int n, int r)
	{
	    // A temporary array to store all combination one by one
		int data[] = new int [r];
		
	    // Print all combination using temprary array 'data[]'
	    combinationUtil(arr, data, 0, n-1, 0, r);
	}
	
	
	    public static void combinationUtil(int arr[], int data[], int start, int end, int index, int r)
		{
		    // Current combination is ready to be printed, print it
		    if (index == r)
		    {
		        for (int j=0; j<r; j++)
		        	 System.out.print(data[j]);
		        
		        System.out.println("");
		        return;
		    }
	 
	    // When no more elements are there to put in data[]
	    for (int i=start; i<=end ; i++)
	    {
	    	
	        data[index] = arr[i];
	        combinationUtil(arr, data, i+1, end, index+1, r);
	    }
	}
	
	    public static int median(int a[],int n)
	    {
	    	
	    	if(n%2==0)
	    		return (a[n/2]+a[n/2+1])/2;
	    	else 
	    		return a[n/2];
	    }
	    
	    public static int getMedian(int a[], int b[], int n)
	    {
	    	int m1,m2;
	    	
	    	if(n<=0)
	    		return -1;
	    	
	    	if(n==1)
	    		return (a[0]+b[0])/2;
	    	
	    	if(n==2)
	    		return (Math.max(a[0],b[0])+Math.min(a[1],b[1]))/2;
	    	
	    	m1 = median(a, n);
	    	m2 = median(b, n);
	    	
	    	if(m1==m2)
	    		return m1;
	    
	    
	    	if(m1<m2)
	    	{
	    	
	    		if(n%2==0)
	    		{	
	    			for(int i=0;i<n-(n/2)+1;i++)
	    				a[i] = a[i+(n/2)-1];
	    			return getMedian(a, b, n-(n/2)+1);
	    		}else
	    			
	    		{	
	    			for(int i=0;i<n-(n/2);i++)
	    				a[i] = a[i+(n/2)];
	    			return getMedian(a, b, n-(n/2));
	    	
	    		}
	    	}
	    		
	    	else
	    	{

	    		if(n%2==0)
	    		{	
	    			for(int i=0;i<n-(n/2)+1;i++)
	    				b[i] = b[i+(n/2)-1];
	    			return getMedian(b, a, n-(n/2)+1);
	    		}else
	    			
	    		{	
	    			for(int i=0;i<n-(n/2);i++)
	    				b[i] = b[i+(n/2)];
	    			return getMedian(b, a, n-(n/2));
	    	
	    		}
	    	}
	    }
	    
	    public static void sortElementFrequency(int a[])
	    {
	    	
	    	HashMap<Integer, Integer> map = new HashMap<>();
	    	
	    	for(int i=0;i<a.length;i++)
	    	{
	    		if(map.containsKey(a[i]))
	    			map.put(a[i], map.get(a[i])+1);
	    		else
	    			map.put(a[i],1);
	    	}
	    	
	    	
			
			List key = new ArrayList(map.keySet());
			
			final Map mapi = map;
			
			Collections.sort(key,
					new Comparator(){
				
				public int compare(Object left, Object right)
				{
					Integer leftkey = (Integer)left;
					Integer rightkey = (Integer)right;
					
					Integer leftvalue = (Integer)mapi.get(leftkey);
					Integer rightvalue=(Integer)mapi.get(rightkey);
					
					if(leftvalue < rightvalue)
						return 1;
					else if(leftvalue>rightvalue)
						return -1;
					else 
						return 0;
					
				}
				
			});
			
			//List the key value
			for(Iterator i=key.iterator(); i.hasNext();){
				Object k = i.next();
				System.out.println(k + " " + map.get(k));
			}
	    	
	    }

	    public static int maxRepeating(int[] arr, int n, int k)
	    {
	        // Iterate though input array, for every element
	        // arr[i], increment arr[arr[i]%k] by k
	        for (int i = 0; i< n; i++)
	        {
	        	System.out.println(arr[i]+" arr[i] "+k+" k "+arr[i]%k +" arr[i]%k");
	            arr[arr[i]%k] += k;
	        }
	        // Find index of the maximum repeating element
	        int max = arr[0], result = 0;
	        for (int i = 1; i < n; i++)
	        {
	            if (arr[i] > max)
	            {
	                max = arr[i];
	                result = i;
	            }
	        }
	     
	        /* Uncomment this code to get the original array back
	           for (int i = 0; i< n; i++)
	              arr[i] = arr[i]%k; */
	     
	        // Return index of the maximum element
	        return result;
	    }
	    
}
