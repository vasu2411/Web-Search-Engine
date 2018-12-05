/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import files.KeywordInfo;
import files.PageRankModel;
import files.Test;
import files.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vasus
 */
public class Search extends HttpServlet {

    Hashtable<String, KeywordInfo> mainHT= new Hashtable<String, KeywordInfo>();
    Trie t;
    
    public static String readFileAsString(String fileName)throws Exception 
    { 
      String data = ""; 
      data = new String(Files.readAllBytes(Paths.get(fileName))); 
      return data; 
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) 
    {
	Comparator<K> valueComparator = new Comparator<K>() 
        {
            public int compare(K k1, K k2) 
            {
                int compare = map.get(k2).compareTo(map.get(k1));
                if (compare == 0) 
                    return 1;
                else 
                    return compare;
            }
         };
	 
        Map<K, V> sortedByValues =  new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
    
    
    public void init(ServletConfig config)
    {
        t = new Trie();
        
        File directory = new File("E:\\javatpoint\\javatpoint\\www.javatpoint.com\\Text");
        String filename;
        
        //get all the files from a directory
        File[] fList = directory.listFiles();
        
        for (File file : fList)
        {
            filename = file.getName();
            String s="";
            try {
                s = readFileAsString("E:\\javatpoint\\javatpoint\\www.javatpoint.com\\Text\\"+filename);
            } catch (Exception ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
            StringTokenizer st = new StringTokenizer(s," , .;:©®â'\"&!?-_\n\t[]{}()@#$%^*/+-    ");
            filename =filename.substring(0, filename.length()-4);
            
            while (st.hasMoreTokens()) 
            {
                String key = st.nextToken().toLowerCase().trim();
	        TreeMap<String, Integer> pageRanking;
	        KeywordInfo k;
	        if(mainHT.get(key)==null)
	        {
                    t.insert(key);
                    k = new KeywordInfo();
                    pageRanking =new TreeMap<String, Integer>();
                    pageRanking.put(filename,1);
                    k.setPageCount(1);
	        }
                else 
                {
                    k = mainHT.get(key);
                    pageRanking =  k.getPageRanking();
                    if(pageRanking.containsKey(filename))
                    {
                        int count = pageRanking.get(filename);
                        pageRanking.put(filename,(count+1));
                    }
                    else 
                    {
                        pageRanking.put(filename,1);
                        int count = k.getPageCount();
                        k.setPageCount(count+1);
                    }
	        }
                k.setPageRanking(pageRanking);
                mainHT.put(key,k);
            }
            
        }
    }
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<String> st;
            if(request.getParameter("suggest")!=null){
               st =t.autocomplete(request.getParameter("suggest"));
               response.setHeader("Content-Type", "text/html");
               for (int i=0;i<st.size();i++){
                   out.print(st.get(i)+",");
               }
            }
            
            String query = request.getParameter("keyword");
            long start,end;
            start = System.currentTimeMillis();
            LinkedHashMap<String, PageRankModel> resultHT= new LinkedHashMap<String, PageRankModel>();
            ArrayList<PageRankModel> pagerRanks = new ArrayList<>();
            TreeMap<String, Integer> wordInPage;
            PageRankModel PM;

            String[] queryArray = query.split(" ");
            int temp=0;
            for(int i=0;i<queryArray.length;i++)
            {
                String s= queryArray[i];

                if(mainHT.get(s)!=null) 
                {
                    if(i==0) 
                    {
                        for(Map.Entry<String,Integer> entry : sortByValues(mainHT.get(s).getPageRanking()).entrySet()) 
                        {
                            String key = entry.getKey();
                            Integer value = entry.getValue();
                            wordInPage = new TreeMap<String, Integer>();
                            PM = new PageRankModel();
                            wordInPage.put(s, value);
                            PM.setWords(wordInPage);
                            PM.setTotalWord(value);
                            PM.setNumberOfWord(1);
                            PM.setPageName(key);
                            //pagerRanks.add(PM);
                            resultHT.put(key, PM);
                            //System.out.println(key+"=>>"+wordInPage);
                        }
                    }

                    else 
                    {
                        for(Map.Entry<String,Integer> entry : sortByValues(mainHT.get(s).getPageRanking()).entrySet()) 
                        {
                            String key = entry.getKey();
                            Integer value = entry.getValue();

                            //for(i=0;i<pagerRanks.size();i++) {

                            if(resultHT.containsKey(key)) 
                            {
                                //if(pagerRanks.get(i).getPageName()==key) {
                                // PM = pagerRanks.get(i);
                                // wordInPage = pagerRanks.get(i).getWords();
                                PM = resultHT.get(key);
                                wordInPage = PM.getWords();
                            }
                            else 
                            {
                                PM = new PageRankModel();
                                wordInPage = new TreeMap<String, Integer>();
                            }  
                            wordInPage.put(s, value);
                            PM.setWords(wordInPage);
                            temp = PM.getTotalWord();
                            PM.setTotalWord(temp+value);
                            temp = PM.getNumberOfWord();
                            PM.setNumberOfWord(temp+1);
                            PM.setPageName(key);
                            //pagerRanks.add(PM);
                            resultHT.put(key, PM);
                             //}		    			 

                        }
                    }
                }
                else 
                {
                    System.out.println("Not Found"); 
                }
            }

            for (String key : resultHT.keySet()) 
            {
                pagerRanks.add(resultHT.get(key));
            }

            Test t = new Test();
            pagerRanks = t.heapSort(pagerRanks, pagerRanks.size());

            ArrayList al = new ArrayList();

            for(int i=0;i<pagerRanks.size();i++)
            {
                al.add(pagerRanks.get(i).pageName);
            }
            end = System.currentTimeMillis();
            float totalTime = (float)(end-start);
            String file= "E:\\javatpoint\\javatpoint\\www.javatpoint.com\\";
            request.setAttribute("file", file); 
            request.setAttribute("totalTime", totalTime);        
            request.setAttribute("query", query);
            request.setAttribute("output", al);

            request.getRequestDispatcher("./result.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}