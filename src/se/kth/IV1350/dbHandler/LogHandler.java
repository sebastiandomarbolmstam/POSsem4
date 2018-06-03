package se.kth.IV1350.dbHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class to handle writing and reading files. Used to log exception information, and to save information about 
 * the sales to be used when summarizing a number of instances of sales.
 */
public class LogHandler {
	private FileWriter fw;
	private Scanner fileScanner;
	private double preTotalRevenue = 0;
	private FileWriter rw;
	
	/**
	 * Logs exception information stored in parameter message to file "log.txt".
	 * @param message Message to be written to file.
	 */
	public void logInFile(String message) {
		try {
			File logFile = new File("exceptionLog.txt");
			fw = new FileWriter(logFile,true);
			fw.write(message);
			fw.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * Converts the stack trace of an exception into a printable string. 
	 * @param dbf Thrown exception with stack trace.
	 * @return Stack trace converted into string
	 */
	public String stackTraceToString(DataBaseFailException dbf) {
		String stackTrace = Arrays.toString(dbf.getStackTrace());
		stackTrace = stackTrace.replace(",", "\n");
		return stackTrace;
	}

	/**
	 * Reads text stored in file to get total revenue before current sale.
	 * @return Total revenue before current sale.
	 */
	public double readRevenueLog() {
		try {
			File logRevenue = new File("revenueLog.txt");
			fileScanner = new Scanner(logRevenue);
			preTotalRevenue = Double.parseDouble(fileScanner.next());
		}catch(IOException e) {
			e.printStackTrace();
		}
		return preTotalRevenue;
	}
	
	/**
	 * Writes total revenue after completed sale into file.
	 * @param newTotalRevenue Total revenue after completed sale.
	 */
	public void writeRevenueLog(double newTotalRevenue) {
		try {
			File logFile = new File("revenueLog.txt");
			rw = new FileWriter(logFile,false);
			rw.write(Double.toString(newTotalRevenue));
			rw.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}

	/**
	 * Creates file to store total revenue. Initiated with a sting containing zero.
	 */
	public void initiateRevenue() {
		try {
			File logFile = new File("revenueLog.txt");
			rw = new FileWriter(logFile,false);
			rw.write("0");
			rw.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
}
