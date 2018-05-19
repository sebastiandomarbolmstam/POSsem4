package se.kth.IV1350.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.IV1350.dbHandler.LogHandler;

/**
 *Handles all information and functionality regarding the end of the sale, when all items has been added
 *to the sale.
 */
public class CompleteSale {

	private List<RevenueObserver> revenueObservers = new ArrayList<>();
	private double change;
	private String infoToPrint;
	private double totalWithTaxes;
	private double TAX_RATE = 1.10;
	private double totalRevenue = 0;
	private LogHandler logHandler;
	private double preTotalRevenue;
	
	/**
	 * Creates an empty instance of {@link Complete Sale} with specified instance logHandler.
	 * @param logHandler To be used when reading or writing files.
	 */
	public CompleteSale(LogHandler logHandler) {
		this.logHandler = logHandler;
	}
	
	/**
	 * Puts together all information concerning the sale.
	 * 
	 * @param activeSaleDTO Contains all information about the sale. Is used to calculate change,
	 * totalWithTaxes and to provide overall information about the sale.
	 * @param paidAmount Amount paid by customer. Is used to calculate change.
	 * @return Instance of EndOfSaleDTO containing complete information about the sale.
	 */
	public EndOfSaleDTO assembleSaleInformation(SaleDTO activeSaleDTO, double paidAmount) {
		totalWithTaxes = calculateFinalTotal(activeSaleDTO);
		change = calculateChange(totalWithTaxes, paidAmount);
		preTotalRevenue = logHandler.readRevenueLog();
		totalRevenue = preTotalRevenue + activeSaleDTO.getRunningTotal();;
		logHandler.writeRevenueLog(totalRevenue);
		EndOfSaleDTO saleInformation = new EndOfSaleDTO(activeSaleDTO, change, paidAmount, totalWithTaxes);
		return saleInformation;
	}
	
	/**
	 * Calculates total cost with taxes at end of sale.
	 * @param activeSaleDTO Information about sale.
	 * @return Total with taxes
	 */
	private double calculateFinalTotal(SaleDTO activeSaleDTO) {
		return activeSaleDTO.getRunningTotal() * TAX_RATE;
	}
	
	/**
	 * Calculates change to be returned to customer given amount paid and total cost.
	 * @param price Total with taxes.
	 * @param paidAmount Amount of money paid by customer.
	 * @return Amount of money to be returned to customer.
	 */
	private double calculateChange(double price, double paidAmount) {
		return paidAmount - price;
	}
	
	/**
	 * Creates a printable string representing a receipt.
	 * @param saleInformation All information that is to be printed
	 * @return Receipt as a printable string. 
	 */
	public String createReceipt(EndOfSaleDTO saleInformation) {
		Receipt receipt = new Receipt();
		infoToPrint = receipt.toString(saleInformation);
		return infoToPrint;	
	}
	
	/**
	 * Notifies all observers in observer list about an updated revenue.
	 */
	public void notifyObservers() {
		for(RevenueObserver obs: revenueObservers) {
			obs.updateRevenue(totalRevenue);
		}
	}
	
	/**
	 * Adds a observer to list of observers.
	 * @param obs Observer to be added.
	 */
	public void addRevenueObserver(RevenueObserver obs) {
		revenueObservers.add(obs);
	}
}
