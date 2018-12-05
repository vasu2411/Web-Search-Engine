package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlToText {
	public int convert() throws IOException
	{
		File folder = new File("E:\\javatpoint\\javatpoint\\www.javatpoint.com");
        File[] listOfFiles = folder.listFiles();
        
        int c=0;
        
        for (int i = 0; i < listOfFiles.length; i++) 
        {
            if(listOfFiles[i].isDirectory())
            {
                if(listOfFiles[i].getName().equals("Text"))
                {
                    c=1;
                }
            }
        }
        
        if(c==0)
        {
            File file = new File("E:\\javatpoint\\javatpoint\\www.javatpoint.com\\Text");
            file.mkdir();
        }
        
        for (int i = 0; i < listOfFiles.length; i++) 
        {
            if (listOfFiles[i].isFile()) 
            {
                File file = new File("E:\\javatpoint\\javatpoint\\www.javatpoint.com\\"+listOfFiles[i].getName());
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();

                String str = new String(data, "UTF-8");
                str=str.replaceAll("\\<.*?>","");
                
                String fileName = listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-4)+".txt";
                
                FileWriter fwr =new FileWriter("E:\\javatpoint\\javatpoint\\www.javatpoint.com\\Text\\"+fileName);
                fwr.write(str);
            }
        }
        
        return 1;
	}

}