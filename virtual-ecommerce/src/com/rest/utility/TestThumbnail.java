package com.rest.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TestThumbnail {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//ImageUtil.saveScaledImage(ConstantsUtil.IMAGE_LOCATION+"nilson.jpeg",ConstantsUtil.IMAGE_LOCATION+"nilsonthumb.jpg");
		//System.out.println(DateTimeUtil.getTimeStampInMiliseconds()); 
		/*String activtiyStart   = "25-10-2017 05:05 PM";
		SimpleDateFormat df    = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		try {
						
			Date activtiyStartDate = df.parse(activtiyStart);
			System.out.println(activtiyStartDate);
			
			Calendar c = Calendar.getInstance();
	        c.setTime(activtiyStartDate);
	        c.add(Calendar.DATE, 8); 
	        
	        Date currentDatePlusOne = c.getTime();

	        System.out.println(df.format(currentDatePlusOne));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//15, 10, 19, 8, 19, 7, 16, 18, 2,11,2,19
		
		 
		 
		 Map<Integer, Integer> allocation = new LinkedHashMap<Integer, Integer>();
		 int n = 5000;
		 for(int k=1; k<=n; k++) {
			 int i = Rnd.get(1, 5000);   
			 int j = Rnd.get(1, 50000);  
			 Set<Integer> keys = allocation.keySet(); 
			 List<Integer> vals = new ArrayList<Integer>(allocation.values());
			 if(!keys.contains(i) && !vals.contains(j)) {
				 allocation.put(i, j);
			 }else {
				 n=n+1;
			 }			 
		 }
		 		
		 String str = "";
		 String str1 = "";
		 String str2 = "";
		 for (Map.Entry<Integer, Integer> entry : allocation.entrySet())
		 {
			str = str+ "flat No.: " + entry.getKey() + "; Form No.: " + entry.getValue()+System.lineSeparator();	
			str1 = str1+ entry.getKey() +System.lineSeparator();
			str2 = str2+ entry.getValue() +System.lineSeparator();	
		 }
		 
		 BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Nilesh\\Documents\\test.txt"));
	     writer.write(str);	     
	     writer.close();
		 
		 BufferedWriter writer1 = new BufferedWriter(new FileWriter("C:\\Users\\Nilesh\\Documents\\test1.txt"));
	     writer1.write(str1);	     
	     writer1.close();
	     
	     BufferedWriter writer2 = new BufferedWriter(new FileWriter("C:\\Users\\Nilesh\\Documents\\test2.txt"));
	     writer2.write(str2);	     
	     writer2.close();
	}

}
