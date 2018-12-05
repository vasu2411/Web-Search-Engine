package files;

import java.util.*;

public class Test {
	

	public static  void main(String args[]) {
//			ArrayList<PageRankModel> pagerRanks = new ArrayList<>();
//			TreeMap<String, Integer> treeMap = new TreeMap<>();
//			treeMap.put("what", 30);
//			treeMap.put("is", 20);
//			pagerRanks.add(new PageRankModel("W3School.com",2,50,treeMap));
//			treeMap.clear();
//			treeMap.put("is",50);
//			pagerRanks.add(new PageRankModel("W3School123.com",1,50,treeMap));
//			treeMap.clear();
//			//treeMap.put("what", 17);
//			treeMap.put("is", 50);
//			//treeMap.put("java",28);
//			pagerRanks.add(new PageRankModel("W3School123456.com",1,50,treeMap));
//			heapSort(pagerRanks,pagerRanks.size());
//			
//			for(int i=0;i<pagerRanks.size();i++) {
//				//System.out.println(pagerRanks.get(i).pageName);
//			}
	}
	
	
	// main function to do heap sort 
	 public ArrayList<PageRankModel> heapSort(ArrayList<PageRankModel> pagerRanks , int n) 
	{ 
	    // Build heap (rearrange array) 
	    for (int i = n / 2 - 1; i >= 0; i--) 
	        heapify(pagerRanks, n, i); 
	  
	    // One by one extract an element from heap 
	    for (int i=n-1; i>=0; i--) 
	    { 
	        // Move current root to end 
	       // swap(i,pagerRanks.size(),pagerRanks); 
	    	
	    	Collections.swap(pagerRanks,0, i);
	    	//System.out.println(pagerRanks.get(i).pageName);
	        // call max heapify on the reduced heap 
	        heapify(pagerRanks, i-1, 0);
	    } 
            
            return pagerRanks;
	}


	public void heapify(ArrayList<PageRankModel> pagerRanks, int n, int i) {
		// TODO Auto-generated method stub	
		
	    int largest = i; // Initialize largest as root 
	    int l = 2*i + 1; // left = 2*i + 1 
	    int r = 2*i + 2; // right = 2*i + 2 
	  
	    // If left child is larger than root 
	    if (l < n) 
	    {
	    	if(pagerRanks.get(l).getNumberOfWord() > pagerRanks.get(largest).getNumberOfWord())
	    	{
	    		largest = l; 	
	    	}
	    	else if((pagerRanks.get(l).getNumberOfWord() == pagerRanks.get(largest).getNumberOfWord()) &&(pagerRanks.get(l).getTotalWord() > pagerRanks.get(largest).getTotalWord())) {
	    		largest = l;
	    	}
	    		
	    		
	    }
	  
	    // If right child is larger than largest so far 
	    if (r < n) {
	    	if(pagerRanks.get(r).getNumberOfWord() > pagerRanks.get(largest).getNumberOfWord()) {
	    		 largest = r; 
	    	}else if((pagerRanks.get(r).getNumberOfWord() == pagerRanks.get(largest).getNumberOfWord()) && (pagerRanks.get(r).getTotalWord() > pagerRanks.get(largest).getTotalWord())) {
	    		 largest = r; 
	    	}
	    }
	       
	    
	    // If largest is not root 
	    if (largest != i) 
	    {
	    	Collections.swap(pagerRanks,i, largest);
	        // Recursively heapify the affected sub-tree 
	        heapify(pagerRanks, n, largest);	
	    } 
		
	}

}
