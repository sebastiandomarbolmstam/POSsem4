package se.kth.IV1350.view;

import se.kth.IV1350.controller.Controller;
import se.kth.IV1350.dbHandler.DataBaseFailException;
import se.kth.IV1350.dbHandler.InvalidIdentifierException;
import se.kth.IV1350.model.RevenueObserver;
import se.kth.IV1350.model.SaleDTO;

/**
 *Placeholder for view. Makes calls to the controller.
 */
public class View {
	
	private Controller contr;
	
	/**
	 * Creates a new view using the specified controller.
	 * @param contr controller to be used for all system operations.
	 */
	public View(Controller contr) {
		this.contr = contr;
	}

	/**
	 * Simulates a sample execution containing calls to all system operations. 
	 */
	public void sampleExecution() {
		RevenueObserver observer = new TotalRevenueView();
		CustomerDummy customer = new CustomerDummy();
		int i=0;
		
		while(i < 2) {
			contr.startSale();
			System.out.println("Welcome!\n\n");
			for(ShoppingCartItem item : customer.getShoppingCart()) {
			
			try {
			SaleDTO activeSaleDTO = contr.scanItem(item.getIdentifier(), item.getQuantity());
				System.out.println(activeSaleDTO.toString() + "Running total:\t" + activeSaleDTO.getRunningTotal() + "$\n");
			}
			catch(InvalidIdentifierException inv) {
				System.out.println("No matching items was found in inventory when searching for "
						+ "item no: " + inv.getIdentifier() + " - Please try again!\n");
			}catch(DataBaseFailException dbf) {
				System.out.println(dbf.getMessage() + "\n");
			}
		}
		
		contr.pay(customer.getAmountPaid());
		contr.addRevenueObserver(observer);
		contr.notifyObservers();
		i++;
		}
	}
}
