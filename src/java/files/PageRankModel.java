package files;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author uttam
 *
 */
public class PageRankModel {
	public String pageName;
	public Integer numberOfWord=0;
	public Integer totalWord=0;
	public TreeMap<String,Integer> words;
	

	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public Integer getNumberOfWord() {
		return numberOfWord;
	}
	public void setNumberOfWord(Integer numberOfWord) {
		this.numberOfWord = numberOfWord;
	}
	public Integer getTotalWord() {
		return totalWord;
	}
	public void setTotalWord(Integer totalWord) {
		this.totalWord =  totalWord;
	}
	public TreeMap<String, Integer> getWords() {
		return words;
	}
	public void setWords(TreeMap<String, Integer> words) {
		this.words = words;
	}
	
}
