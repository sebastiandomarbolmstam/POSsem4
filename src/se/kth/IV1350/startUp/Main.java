package se.kth.IV1350.startUp;

import se.kth.IV1350.controller.Controller;
import se.kth.IV1350.dbHandler.AccountingSystemHandler;
import se.kth.IV1350.dbHandler.ItemInventory;
import se.kth.IV1350.dbHandler.LogHandler;
import se.kth.IV1350.dbHandler.Printer;
import se.kth.IV1350.model.CompleteSale;
import se.kth.IV1350.view.View;

/**
 * Starts the entire application.
 */
public class Main {

	/**
	 * 
	 * @param args The program does not take any command line parameters. 
	 */
	public static void main(String[] args) {
		
		LogHandler logHandler = new LogHandler();
		logHandler.initiateRevenue();
		AccountingSystemHandler accSys = new AccountingSystemHandler();
		Printer printer = new Printer();
		ItemInventory inventory = new ItemInventory();
		CompleteSale completedSale = new CompleteSale(logHandler);
		Controller contr = new Controller(inventory, printer, accSys, logHandler, completedSale);
		View view = new View(contr);
		
		view.sampleExecution();
	}

}
