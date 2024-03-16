package com.chetan.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ItemLogging {
	public static void writeLog(String logmsg) {

		try {
			Logger logger=Logger.getLogger("Hotel Management");
//			FileHandler fh=new FileHandler("C:\\Users\\vispute.chetanulhas\\eclipse-workspace\\JAVA_FULLSTACK\\ChetanUlhasVispute_MiniProject\\src\\itemlog.txt");
			FileHandler fh=new FileHandler("E:\\itemlog.txt");
			logger.addHandler(fh);

			SimpleFormatter sm=new SimpleFormatter();
			fh.setFormatter(sm);
			
			
			LocalDateTime dateTime=LocalDateTime.now();
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String date="Time Stamp : "+dateTime.format(dtf);
			logger.info(date+" " +logmsg);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
