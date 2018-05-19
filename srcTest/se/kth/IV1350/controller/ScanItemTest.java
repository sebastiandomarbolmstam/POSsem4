package se.kth.IV1350.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import se.kth.IV1350.dbHandler.AccountingSystemHandler;
import se.kth.IV1350.dbHandler.DataBaseFailException;
import se.kth.IV1350.dbHandler.InvalidIdentifierException;
import se.kth.IV1350.dbHandler.ItemDTO;
import se.kth.IV1350.dbHandler.ItemInventory;
import se.kth.IV1350.dbHandler.LogHandler;
import se.kth.IV1350.dbHandler.Printer;
import se.kth.IV1350.model.Sale;
import se.kth.IV1350.model.SaleDTO;

class ScanItemTest {

	@Test
	void ItemMatchTest() {
		ItemInventory testInventory = new ItemInventory();
		Printer testPrinter = new Printer();
		LogHandler lh = new LogHandler();
		AccountingSystemHandler testAccSys = new AccountingSystemHandler();
		Controller testContr = new Controller(testInventory, testPrinter, testAccSys, lh);
		Sale sale = new Sale();
		
		String itemIdentifier = "12345";
		int quantity = 3;
		ItemDTO returnItem = new ItemDTO("banana", 1.5, "12345", 3);
		List<ItemDTO> itemList = new ArrayList<ItemDTO>();
		itemList.add(returnItem);
		double runningTotal = 3*1.5;
		SaleDTO expected = new SaleDTO(returnItem, itemList, runningTotal, sale.getTime());
		try {
		SaleDTO result = testContr.scanItem(itemIdentifier, quantity);
		assertEquals(expected, result);
		}catch(InvalidIdentifierException e) {
			e.printStackTrace();
		}catch(DataBaseFailException e) {
			e.printStackTrace();
		}
		
	}

}
