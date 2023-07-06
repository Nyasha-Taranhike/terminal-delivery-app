package javaCapstone2;

public class Customer {
	String customerName;
	String customerCity;
	String customerAddress;
	String customerEmail;
	String customerPhone;

	// Customer Info in constructor
	public Customer(String customerName, String customerCity, String customerAddress, String customerEmail,
			String customerPhone) {
		this.customerName = customerName;
		this.customerCity = customerCity;
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
	}

	// Helper Methods
	String getCustName() {
		return customerName;
	}

	String custLocation() {
		return customerCity;
	}

	String custAddress() {
		return customerAddress;
	}

	String custEmail() {
		return customerEmail;
	}

	String custPhone() {
		return customerPhone;
	}

}
