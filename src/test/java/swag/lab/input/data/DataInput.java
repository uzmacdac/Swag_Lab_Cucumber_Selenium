package swag.lab.input.data;


import org.testng.annotations.DataProvider;

public class DataInput {
	
	public enum TestType {

	    TITLE,
	    THANK_MESSAGE,
	    DISPATCH_TEXT,
	    BACK_HOME

	}
	
	
	@DataProvider(name = "loginData")
	public Object[][] getData() {
	    return new Object[][] {
	        {"standard_user", "secret_sauce"},
	        {"locked_out_user", "secret_sauce"},
	        {"problem_user", "secret_sauce"},
	        {"locked_out_user", "secret_sauce"},
	        {"performance_glitch_user", "secret_sauce"},
	        {"error_user", "secret_sauce"},
	        {"error_user", "secret_sauce"},
	        {"Priyanka", "Patil"},
	        
	    };
	}
	
	@DataProvider(name = "checkoutData")
	public Object[][] getCheckoutData() {
	    return new Object[][] {
	    	{"standard_user", "secret_sauce", "401148"}
	        
	    };
	}

	



	@DataProvider(name = "checkoutCompleteData")
	public Object[][] checkoutCompleteData() {

	       return new Object[][] {

	          { "TITLE", "Checkout: Complete!" },

	          { "THANK_MESSAGE", "Thank you for your order!" },

              { "DISPATCH_TEXT", "Your order has been dispatched" },

	          { "BACK_HOME", "inventory.html" }

	      };

	 }
	
	
	
	@DataProvider(name = "checkoutCompleteInputData")
	public Object[][] checkoutCompleteInputData() {

	    return new Object[][] {

	        { TestType.TITLE, "Checkout: Complete!" },

	        { TestType.THANK_MESSAGE, "Thank you for your order!" },

	        { TestType.DISPATCH_TEXT, "Your order has been dispatched" },

	        { TestType.BACK_HOME, "inventory.html" }

	    };
	}
	
	
	
	
	
}
