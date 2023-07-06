package javaCapstone2;

import java.util.*;

public class Restaurant {
	// The Menu
	static HashMap<String, Integer> mealsAvailable = new HashMap<String, Integer>();

	// TODO Check if chosen res is in the list
	// Restaurant List: ArrayList<String> stores = new ArrayList();

	String restaurantName;
	String resLocation;
	String resPhone; // No need for integer
	int total = 0;
	int mealPrice = 0;

	public Restaurant(String restaurantName, String resLocation, String resPhone) {
		this.restaurantName = restaurantName;
		this.resLocation = resLocation;
		this.resPhone = resPhone;

	}

	// Helper methods: The main uses them
	String getResName() {
		return restaurantName;
	}

	String getResLocation() {
		return resLocation;
	}

	String resPhone() {
		return resPhone;
	}

	// Pick a random integer from 0-1000 for the order number
	int orderNumber() {
		return (int) (Math.random() * 1000);
	}

	// Return the price for each meal a customer picks
	int getMealPrice(String meal) {
		mealPrice = mealsAvailable.get(meal);
		return mealPrice;
	}

	// Return a customers bill
	int calculateToal() {
		Main.mealsAndQuantities.forEach((food, repeat) -> {
			int mealPrice = mealsAvailable.get(food);
			total += (repeat * mealPrice);

		});
		return total;
	}

	public static void main(String[] args) {

		// MENU: Assume all restaurants have the same menu. /////////////////////
		mealsAvailable.put("Pepperoni pizza", 78);
		mealsAvailable.put("Hawaiian pizza", 82);
		mealsAvailable.put("Meatlovers pizza", 90);
		mealsAvailable.put("BBQ pizza", 85);
		mealsAvailable.put("Grape Juice", 22);
		mealsAvailable.put("Black Label", 35);
		mealsAvailable.put("Beef Burger", 70);
		mealsAvailable.put("Cheese Burger", 50);
		mealsAvailable.put("Cake", 250);

		/////////////////////////// MENU: ////////////////////////////////////
	}

}
