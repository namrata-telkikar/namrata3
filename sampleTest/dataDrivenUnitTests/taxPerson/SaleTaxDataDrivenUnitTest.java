package taxPerson;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import ComcastTaxPerson.sampleTest.SaleTax;
import ComcastTaxPerson.sampleTest.SaleTax.ItemType;

public class SaleTaxDataDrivenUnitTest extends DataDrivenTestDataSetup {

	SaleTax saleTax = new SaleTax();
	ItemType itemType;

	@BeforeSuite
	public void setUp() throws Exception {
		SpreadsheetName = "TestData.xlsx";
		SpreadsheetTabName = "Sheet1";
	}

	@Test(dataProvider = "runner")
	public void testSaleTax(String[] obj) throws Exception {

		/**
		 * reading data from spreadsheet
		 */
		String TestCaseName = obj[0];
		double ItemBasePrice = Double.parseDouble(obj[1]);	
		String ItemTypeFromSpreadsheet =  obj[2];	
		double ItemQuantity = Double.parseDouble(obj[3]);	
		double GrandTotal = Double.parseDouble(obj[4]);

		if(ItemTypeFromSpreadsheet.contains("NECESSARY")){
			itemType = ItemType.NECESSARY;
		}
		else if(ItemTypeFromSpreadsheet.contains("LUXURY")){
			itemType = ItemType.LUXURY;
		}

		Double finalPrice = saleTax.getGrandTotalPrice(ItemBasePrice, itemType, ItemQuantity);

		System.out.println("Executing Test Case " + TestCaseName);
		System.out.println("**********************************************");

		assertEquals(finalPrice, GrandTotal); 	
	}

	@AfterSuite
	public void tearDown() throws Exception {
	}
}