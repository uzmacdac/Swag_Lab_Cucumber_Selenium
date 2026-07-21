package stepdefinitions;

import context.Test_Context;
import swag.lab.pages.CartPage;
import swag.lab.pages.CheckoutOverviewPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.ProductDetailsPage;

public class CheckOutOverviewStepDefinition {
	public  Test_Context context;

	public CheckoutOverviewPage checkoutOverviewPage;
	public InventoryPage inventoryPage;
	public ProductDetailsPage pdp;
	public CartPage cartPage;
	
	int beforeCartCount;

	public CheckOutOverviewStepDefinition(Test_Context context) {

		this.context = context;
	}
	
	
	
	
	
	

}
