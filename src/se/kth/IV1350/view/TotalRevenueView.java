package se.kth.IV1350.view;

import se.kth.IV1350.model.RevenueObserver;

/**
 * View class to display total revenue of multiple sales. Does not have calls to controller, but implements
 * observer pattern to update.
 */
public class TotalRevenueView implements RevenueObserver{

	/**
	 * Prints total revenue to console after complete sale. Called by observer when observer is notified.
	 */
	@Override
	public void updateRevenue(double revenue) {
		System.out.println("\n***********************************\n"
				+ "Total revenue after sale: " + String.format("%.2f", revenue) + 
				"$\n***********************************\n\n\n");
	}
}
