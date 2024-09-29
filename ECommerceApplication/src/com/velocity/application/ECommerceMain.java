package com.velocity.application;

import java.sql.SQLException;
import java.util.Scanner;

import com.velocity.add.product.item.AddProductImpl1;
import com.velocity.buy.product.BuyProductImpl;
import com.velocity.calculate.bill.CalculatebillImpl;
import com.velocity.check.quantity.CheckQuantityImpl;
import com.velocity.check.registered.user.UserInfoImpl;
import com.velocity.display.amount.DisplayAmountImp;
import com.velocity.particular.user.history.UserHistoryImpl;
import com.velocity.purchase.item.PurchaseImpl;
import com.velocity.user.login.UserLoginImpl;
import com.velocity.user.registration.UserRegistrationImpl;
import com.velocity.user.view.product.item.sorted.order.ViewProductImpl;
import com.velocity.view.cart.ViewCartImpl;
import com.velocity.view.product.item.ViewProductItemImpl;

public class ECommerceMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		System.out.println("Welcome to E-Commerce based application");
		System.out.println("*****************************************");
		System.out.println("User Operation\r\n"
						+ "1. User Registration\r\n"
						+ "2. User Login\r\n"
						+ "3. User view Product item as Sorted Order\r\n"
						+ "4. Buy Product\r\n"
						+ "5. View Cart \r\n"
						+ "6. Purchase the item \r\n\n"
						+ "Admin Operation\r\n"
						+ "7. Add product item\r\n"
						+ "8. Calculate Bill\r\n"
						+ "9. Display amount to End User\r\n"
						+ "10. Check Quantity\r\n"
						+ "11. Check registered user\r\n"
						+ "12. Check the particular user history\r\n\n"
						+ "Guest Operation\r\n"
						+ "13. View product item\r\n"
						+ "14. Exit E-Commerce Application\r\n");
		System.out.println("*****************************************");
		
		Scanner scanner = new Scanner(System.in);
	
		while(true)
		{
			System.out.println("Enter your choice - ");
			int choice = scanner.nextInt();
			
			switch (choice) {
			case 1:
				UserRegistrationImpl userRegistrationImpl = new UserRegistrationImpl();
				userRegistrationImpl.getUserInputData();
				break;
				
			case 2:
				UserLoginImpl userLoginImpl = new UserLoginImpl();
				userLoginImpl.getUserLogin();
				break;
				
			case 3:
				ViewProductImpl viewProductImpl = new ViewProductImpl();
				viewProductImpl.getProductInfo();
				break;	
				
			case 4:
				BuyProductImpl buyProduct = new BuyProductImpl();
				buyProduct.getBuyProduct();
				break;
			
			case 5:
				ViewCartImpl AddProduct = new ViewCartImpl();
		        AddProduct.getViewCart();
				break;
				
			case 6:
				PurchaseImpl purchaseImpl = new PurchaseImpl();
		        purchaseImpl.getPurchase();
				break;
		
			case 7:
				AddProductImpl1 addProductImpl = new AddProductImpl1();
				addProductImpl.setAddProduct();
				break;
				
			case 8:
				CalculatebillImpl calculatebillImpl = new CalculatebillImpl();
				calculatebillImpl.calculate();
				break;
				
			case 9:
				DisplayAmountImp displayAmountImp = new DisplayAmountImp();
		    	displayAmountImp.amount();
				break;
				
			case 10:
				CheckQuantityImpl checkQuantity = new CheckQuantityImpl();
		        checkQuantity.userInput();
		        checkQuantity.jdbc();
				break;
				
			case 11:
				UserInfoImpl us= new UserInfoImpl();
				us.userInput();
				us.jdbc();
				break;
				
			case 12:
				UserHistoryImpl userHistory = new UserHistoryImpl();
				userHistory.userInput();
				userHistory.fetchUserHistors();
				break;
				
			case 13:
				ViewProductItemImpl viewProductItemImpl = new ViewProductItemImpl();
				viewProductItemImpl.productInput();
				viewProductItemImpl.productCheck();
				break;
				
			case 14:
				System.out.println("Thank you for using E-Commerce Application... ");
				scanner.close();
				System.exit(0);
				break;
			
			default:
				System.out.println("Please enter valid choice, please try again ...");
				break;		
			}
		}
	}
}