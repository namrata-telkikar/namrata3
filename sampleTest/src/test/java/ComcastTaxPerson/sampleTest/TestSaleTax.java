package ComcastTaxPerson.sampleTest;

import org.testng.annotations.Test;
import ComcastTaxPerson.sampleTest.SaleTax.ItemType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestSaleTax {

	SaleTax saleTax = new SaleTax();

	@Test
	public void testSaleTax(){

		Scanner input = new Scanner(System.in);

		try{
			System.out.println("Please enter the number of items : ");
			int NoOfItems = input.nextInt();

			if(NoOfItems <= 0)
				System.out.println("Error! Zero or Negative value entered..");
			else{
  
				int[] arrrayOfItems = new int[NoOfItems];
				Double[] arrayOfItemCost = new Double[NoOfItems];
				int itemTypeInput;

				for(int i = 0; i < NoOfItems; i++){

					System.out.println("Enter the price of item no " + (i+1) + " in cents");
					arrrayOfItems[i] = input.nextInt();

					boolean flagDone = false;

					while(!flagDone){
						System.out.println("Please tell if this item is luxury or a necessary.");
						System.out.println("Enter 1 - For luxury item");
						System.out.println("Enter 2 - For Necessary item");
						itemTypeInput = input.nextInt();

						if(itemTypeInput == 1){
							arrayOfItemCost[i] = saleTax.getGrandTotalPrice(arrrayOfItems[i], ItemType.LUXURY, 1);
							flagDone = true; 
						}else if(itemTypeInput == 2){
							arrayOfItemCost[i] = saleTax.getGrandTotalPrice(arrrayOfItems[i], ItemType.NECESSARY, 1);
							flagDone = true; 
						}else{
							System.out.println("Invalid input! Please enter either 1 or 2");
						}						
					}
				}

				Double totalPrice = 0.0;
				for(int i = 0 ; i < NoOfItems; i++){
					System.out.println("The cost of item no. "+(i+1)+" is: " + arrayOfItemCost[i]);
					totalPrice = totalPrice + arrayOfItemCost[i];
				}

				System.out.println("The total cost of the items is: " + totalPrice);				
			}

		}catch(InputMismatchException e){
			System.out.println("Error! The input should be an integer. E.g. 1, 2, 10, 100..");

		}finally{
			input.close();
		}
	}
}