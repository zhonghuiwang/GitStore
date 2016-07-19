package com.express.common.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.express.model.FunTree;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ExtHelper {
	
	public static String getXmlFromList(int recordTotal , List beanList) {
		Total total = new Total();
		total.setResults(recordTotal);
		List results = new ArrayList();
		results.add(total);
		results.addAll(beanList);
		XStream sm = new XStream(new DomDriver());
		for (int i = 0; i < results.size(); i++) {
			Class c = results.get(i).getClass();
			String b = c.getName();
			String[] temp = b.split("\\.");
			sm.alias(temp[temp.length - 1], c);
		}
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"	+ sm.toXML(results);
		return xml;
	}
	
	public static String getXmlFromList(List beanList,int Bsize){
		return getXmlFromList(Bsize,beanList);  
	}        
	
	public static String getJsonFromList(long recordTotal , List beanList){
		int start = 0;
		int limit = 10;
		TotalJson total = new TotalJson();
		List Ll = new ArrayList();
		total.setResults(recordTotal);
		total.setItems(beanList);   
		JSONObject JsonObject = JSONObject.fromObject(total);
		return JsonObject.toString();
	}

	public static String getJsonFromList(List beanList){
		return getJsonFromList(beanList.size(),beanList);
	}
	
	public static String getJsonFromBean(Object bean){
		JSONObject JsonObject = JSONObject.fromObject(bean);
		return JsonObject.toString();
	}
	
	public static List<?> getListFromJson(String jsonString,Class<?> tclass){
	    
		  JSONArray jsonArray2= JSONArray.fromObject(jsonString);
		 
		   List<?> tList=JSONArray.toList(jsonArray2, tclass);
		
		return tList;
		
	}

   public static String beanToJson(Object bean) {     
        StringBuilder json = new StringBuilder();     
        json.append("{");     
        PropertyDescriptor[] props = null;     
       try {     
            props = Introspector.getBeanInfo(bean.getClass(), Object.class)     
                    .getPropertyDescriptors();     
        } catch (IntrospectionException e) {     
        }     
       if (props != null) {     
           for (int i = 0; i < props.length; i++) {     
               try {    
                    String name = objectToJson(props[i].getName());     
                    String value = objectToJson(props[i].getReadMethod().invoke(bean));    
                    json.append(name);     
                    json.append(":");     
                    json.append(value);     
                    json.append(",");    
                } catch (Exception e) {     
                }     
            }     
            json.setCharAt(json.length() - 1, '}');     
        } else {     
            json.append("}");     
        }     
       return json.toString();     
    }     
   
  
   public static String objectToJson(Object object) {     
        StringBuilder json = new StringBuilder();     
       if (object == null) {     
            json.append("\"\"");     
        } else if (object instanceof String || object instanceof Integer) {   
            json.append("\"").append(object.toString()).append("\"");    
        } else {     
            json.append(beanToJson(object));     
        }     
       return json.toString();     
    }     
    
   public static String listToJson(List<?> list) {     
        StringBuilder json = new StringBuilder();     
        json.append("[");     
       if (list != null && list.size() > 0) {     
           for (Object obj : list) {     
                json.append(objectToJson(obj));     
                json.append(",");     
            }     
            json.setCharAt(json.length() - 1, ']');     
        } else {     
            json.append("]");     
        }     
       return json.toString();     
    }  
}