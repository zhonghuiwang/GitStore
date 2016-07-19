 package com.express.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

//import org.apache.log4j.Logger;


public class PropertiesTool
{
    private Properties propertie;
    private FileInputStream inputFile;
    private FileOutputStream outputFile;
//    private static final Logger log = Logger.getLogger(PropertiesTool.class);
    
  
    public PropertiesTool() {
		propertie = new Properties();
	}
    
    
   
    public PropertiesTool(String filePath)
    {
        propertie = new Properties();
        try {
            inputFile = new FileInputStream(filePath);
            propertie.load(inputFile);
            inputFile.close();
        } catch (FileNotFoundException ex) {

            ex.printStackTrace();
        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }
    
    
    
   
    public String getValue(String key)
    {
        if(propertie.containsKey(key)){
            String value = propertie.getProperty(key);
            return value;
        }
        else
            return "";
    }//end getValue()
    
  
    public String getValue(String fileName, String key)
   {
        try {
            String value = "";
            inputFile = new FileInputStream(fileName);
            propertie.load(inputFile);
            inputFile.close();
            if(propertie.containsKey(key)){
                value = propertie.getProperty(key);
                return value;
            }else
                return value;
        } catch (FileNotFoundException e) {
//        	log.error(e);
            e.printStackTrace();
            return "";
        } catch (IOException e) {
//        	log.error(e);
            e.printStackTrace();
            return "";
        } catch (Exception ex) {
//        	log.error(ex);
            ex.printStackTrace();
            return "";
        }
    }//end getValue()
    
    
    public void clear()
    {
        propertie.clear();
    }//end clear();
    
    
   
    public void setValue(String key, String value)
    {
        propertie.setProperty(key, value);
    }//end setValue()
    
    
    
    public void saveFile(String fileName, String description)
  {
        try {
            outputFile = new FileOutputStream(fileName);
            propertie.store(outputFile, description);
            outputFile.close();
        } catch (FileNotFoundException e) {
//        	log.error(e);
            e.printStackTrace();
        } catch (IOException ioe){
//        	log.error(ioe);
            ioe.printStackTrace();
        }
    }//end saveFile()
    
    
    public static void main(String[] args)
    {
    	URL urlString=PropertiesTool.class.getResource("/treeNode.properties");
    	String pathString=urlString.getPath().replace("%20", " ");
    	System.out.println(pathString);
    	PropertiesTool rc = new PropertiesTool(pathString);
        String ip = rc.getValue("CFAAA");

        System.out.println("ip = " + ip);
        PropertiesTool cf = new PropertiesTool(pathString);
//        cf.clear();
        cf.setValue("min", "9996");
        cf.setValue("max", "99922");
        cf.saveFile(pathString, "test");

    }//end main()
}//end class ReadConfigInfo