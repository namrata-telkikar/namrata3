package ComcastTaxPerson.sampleTest;

public class SaleTax{

	public enum ItemType {NECESSARY, LUXURY};

	public static void main(String []args){
		SaleTax saleTax = new SaleTax();
		Double itemFinalPrice = saleTax.getGrandTotalPrice(1000, ItemType.LUXURY, 1);
		System.out.println("shopping bill : " + itemFinalPrice );
	}

	public Double getGrandTotalPrice(double itemBasePrice,  ItemType itemType, double itemQuantity){
		Double taxPercentage = null;
		Double GrandTotal = 0.00;

		switch (itemType) {
		case NECESSARY:
			taxPercentage = 1.00;
			break;
		case LUXURY:
			taxPercentage = 9.00;
			break;
		}

		if(itemBasePrice <=0 || itemQuantity <= 0){
			System.out.println("Quantity OR Price is zero OR negative !!!");
		}
		else{
			Double saleTax = itemBasePrice * taxPercentage / 100 ;
			Double TotalPrice = (double) (itemBasePrice + saleTax);
			GrandTotal = itemQuantity * TotalPrice;
		}
		return GrandTotal;
	}
}