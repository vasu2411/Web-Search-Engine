package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ListofFiles {
	public File[] List()
	{
		File folder = new File("E:\\javatpoint\\javatpoint\\www.javatpoint.com\\Text");
        File[] listOfFiles = folder.listFiles();
        
        return listOfFiles;
	}
}
