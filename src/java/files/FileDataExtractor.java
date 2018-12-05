package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileDataExtractor {
	public String[] Extract() throws IOException
	{
		File folder = new File("E:\\javatpoint\\javatpoint\\www.javatpoint.com\\Text");
        File[] listOfFiles = folder.listFiles();
        
        String[] fileData = new String[listOfFiles.length];
        
        for(int i=0;i<listOfFiles.length;i++)
        {
        	File file = new File("E:\\javatpoint\\javatpoint\\www.javatpoint.com\\Text\\"+listOfFiles[i].getName());
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            
            String str = new String(data, "UTF-8");
            
            fileData[i]=str;
        }
        
        return fileData;
	}
}
