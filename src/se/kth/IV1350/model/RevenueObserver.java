package se.kth.IV1350.model;

/**
 * Listener interface for receiving notifications about updated revenue. Classes using total revenue implements 
 * the interface. 
 */
public interface RevenueObserver {

	void updateRevenue(double revenue);
}
