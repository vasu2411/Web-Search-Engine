package files;

import java.util.*;

public class KeywordInfo {
	
	// TODO Auto-generated method stub
//	ArrayList<String> pageUrl= new ArrayList();
//	ArrayList<String> pageCount=new ArrayList();
	TreeMap<String, Integer> pageRanking= new TreeMap<String, Integer>();
	private int pageCount;
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public TreeMap<String, Integer> getPageRanking() {
		return pageRanking;
	}

	public void setPageRanking(TreeMap<String, Integer> pageRanking) {
		this.pageRanking = pageRanking;
	}
}
