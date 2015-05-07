package taxPerson;

import org.testng.Assert;
import org.testng.annotations.Test;

import ComcastTaxPerson.sampleTest.SaleTax;
import ComcastTaxPerson.sampleTest.SaleTax.ItemType;

public class UnitTestingSaleTax {
	
	SaleTax saleTax = new SaleTax();
	
	
  @Test
  public void testSaleTaxForNecessaryItem() {
	  Double finalPrice = saleTax.getGrandTotalPrice(1000, ItemType.NECESSARY, 1);
	  Assert.assertEquals(finalPrice, 1010);  
  }
  
  @Test
  public void testSaleTaxForLuxuryItem() {
	  Double finalPrice = saleTax.getGrandTotalPrice(1000, ItemType.LUXURY, 1);
	  Assert.assertEquals(finalPrice, 1090);  
  }
  
  @Test
  public void testSaleTaxForMoreThanOneItems() {
	  Double finalPrice = saleTax.getGrandTotalPrice(1000, ItemType.LUXURY, 2);
	  Assert.assertEquals(finalPrice, 2180);  
  }
  
  @Test
  public void testSaleTaxForNoItem() {
	  Double finalPrice = saleTax.getGrandTotalPrice(1000, ItemType.LUXURY, 0);
	  Assert.assertEquals(finalPrice, 0);  
  }
  
}
