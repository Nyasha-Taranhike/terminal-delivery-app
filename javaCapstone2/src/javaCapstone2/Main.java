package javaCapstone2;

import java.io.*;
import java.util.*;

// The Spec says console based only : no import javax.swing.*;

public class Main {
	static BufferedReader driverReader;
	static BufferedWriter invoiceWriter;

	static ArrayList<String[]> driverInfo = new ArrayList<>();
	static HashMap<String, Integer> drivers = new HashMap<>(); // Just to store
	static HashMap<String, Integer> mealsAndQuantities = new HashMap<>();

	static String nameEntered = null;
	static String cityEntered = null;
	static String streetEntered = null;
	static String suburbEntered = null;
	static String addressCustomer = null;
	static String emailEntered = null;
	static String phoneEntered = null;
	static String specialInfo = null;

	static String chosenRestaurantName = null;
	static String existingRestarauntName = null;
	static String restaurantCity = null;

	static int orderNumber = 0;
	static int bill = 0;

	// Pick a driver
	static String yourDriver = null;
	// Pick a driver

	static String companyNumber = null;

	public static void main(String[] args) throws Exception {

		// Build restaurants here
		Restaurant pizzaHut = new Restaurant("Pizza Hut", "Bloemfontein", "012 990 4444");
		Restaurant kfc = new Restaurant("KFC", "Cape Town", "021 545 5759");
		Restaurant mcDonalds = new Restaurant("Mcdonalds", "Durban", "089 234 1839");
		Restaurant nySlice = new Restaurant("NY slice", "Johannesburg", "011 145 9098");
		Restaurant nandos = new Restaurant("Nandos", "Port Elizabeth", "077 322 1733");
		Restaurant burgerKing = new Restaurant("Burger King", "Potchefstroom", "071 876 9876");
		Restaurant steers = new Restaurant("Steers", "Springbok", "022 555 1733");
		Restaurant spur = new Restaurant("Spur", "Witbank", "080 667 9383");

		// Puts entries into the menu
		Restaurant.main(args);

		// Read in driver file
		readDrivers();

		/////////////////////// Read in Customer input///////////////////////////////
		Scanner scanner = new Scanner(System.in);

		System.out.println("Would you like to make a purchase (Y/N)?");
		String answer = scanner.nextLine().toUpperCase();

		orderMeal(scanner, answer);
		/////////////////////// Read in Customer input///////////////////////////////

		// TODO move read in customer to its class
		Customer jill = new Customer(nameEntered, cityEntered, addressCustomer, emailEntered, phoneEntered);

		// Determine the Restaurant and City
		if (cityEntered.equalsIgnoreCase("Bloemfontein")) {
			selectedRestaurantInfo(pizzaHut);

			// Pick a driver
			yourDriver = pickDriver(cityEntered);

			printInvoice(pizzaHut);

		} else if (cityEntered.equalsIgnoreCase("Cape Town")) {
			selectedRestaurantInfo(kfc);

			// Pick a driver
			yourDriver = pickDriver(cityEntered);

			printInvoice(kfc);

		} else if (cityEntered.equalsIgnoreCase("Durban")) {
			selectedRestaurantInfo(mcDonalds);

			// Pick a driver
			yourDriver = pickDriver(cityEntered);

			printInvoice(mcDonalds);

		} else if (cityEntered.equalsIgnoreCase("Johannesburg")) {
			selectedRestaurantInfo(nySlice);

			// Pick a driver
			yourDriver = pickDriver(cityEntered);

			printInvoice(nySlice);

		} else if (cityEntered.equalsIgnoreCase("Port Elizabeth")) {
			selectedRestaurantInfo(nandos);

			// Pick a driver
			yourDriver = pickDriver(cityEntered);

			printInvoice(nandos);

		} else if (cityEntered.equalsIgnoreCase("Potchefstroom")) {
			selectedRestaurantInfo(burgerKing);

			// Pick a driver
			yourDriver = pickDriver(cityEntered);

			printInvoice(burgerKing);

		} else if (cityEntered.equalsIgnoreCase("Springbok")) {
			selectedRestaurantInfo(steers);

			// Pick a driver
			yourDriver = pickDriver(cityEntered);

			printInvoice(steers);

		} else if (cityEntered.equalsIgnoreCase("Witbank")) {
			selectedRestaurantInfo(spur);

			// Pick a driver
			yourDriver = pickDriver(cityEntered);

			printInvoice(spur);

		} else {
			System.out.println("You're probably not in South Africa");
			System.exit(0);
		}

		System.out.println("Your invoice is ready!!!");

	}

	private static void selectedRestaurantInfo(Restaurant chosenRestaurant) {
		// Get restaurant info
		existingRestarauntName = chosenRestaurant.getResName();
		restaurantCity = chosenRestaurant.getResLocation();
		orderNumber = chosenRestaurant.orderNumber();
		bill = chosenRestaurant.calculateToal();
		companyNumber = chosenRestaurant.resPhone();
	}

	// Extract info from the driver files
	private static void readDrivers() {
		try {
			// A buffer to scan and another to (over)write a new file
			driverReader = new BufferedReader(new FileReader("drivers.txt"));

			String line = "";

			// Put drivers in a hashMap.
			while ((line = driverReader.readLine()) != null) {
				// Split each line of driver.txt into an Integer[3] array
				// name, location and load are the indexes from 0 to 2 respectively
				String[] driver = line.split(", ");

				String[] driverValue = new String[2];
				driverValue[0] = driver[0];
				driverValue[1] = driver[2];

				// Key for each driver
				String driverName = driver[0];

				// Value of each individual Driver
				int load = Integer.parseInt(driver[2]);

				drivers.put(driverName, load);
				driverInfo.add(driver);

			}

			driverReader.close();

		} catch (IOException e) {
			System.out.println("Error NO file");
		}

	}

	// Enter details and order a meal
	private static void orderMeal(Scanner scanner, String answer) {
		if (answer.equalsIgnoreCase("Y")) {
			System.out.println("What is your name: ");
			nameEntered = scanner.nextLine();

			System.out.println("What is your city: eg Cape Town ");
			cityEntered = scanner.nextLine();

			System.out.println("What is your street number and street name eg: 12 Kim street ");
			streetEntered = scanner.nextLine();

			System.out.println("What is your suburb eg: Bloomberg ");
			suburbEntered = scanner.nextLine();

			addressCustomer = streetEntered + "\n" + suburbEntered;

			System.out.println("What is your email: ");
			emailEntered = scanner.nextLine();

			System.out.println("What is your phone number: ");
			phoneEntered = scanner.nextLine();

			// Meals
			System.out.println("What Resteraunt would you like to order from: ");
			chosenRestaurantName = scanner.nextLine();

			takeOrders(scanner);

			System.out.println("Any serving suggestions for any of your meals: ");
			specialInfo = scanner.nextLine();

		} else {
			System.out.println("Thank you for coming. Feel free to come back when you're not broke.");
			System.exit(0);

		}
	}

	// Print invoice
	private static void printInvoice(Restaurant firstRestaurant) {
		try {
			invoiceWriter = new BufferedWriter(new FileWriter("invoice.txt"));

			invoiceWriter.write("Order number " + orderNumber + "\n");
			invoiceWriter.write("Customer: " + nameEntered + "\n");
			invoiceWriter.write("Email: " + emailEntered + "\n");
			invoiceWriter.write("Phone number: " + phoneEntered + "\n");
			invoiceWriter.write("Location: " + cityEntered + "\n\n");

			invoiceWriter.write("You have ordered the following from " + chosenRestaurantName + " in "
					+ restaurantCity + ": " + "\n\n");

			mealsAndQuantities.forEach((key, value) -> {
				try {
					int amountEach = firstRestaurant.getMealPrice(key);
					invoiceWriter.write(value + " x " + key + " (R" + amountEach + ".00)\n");

				} catch (IOException e) {
					System.out.println("Could print list of meals");
					e.printStackTrace();
				}
			});

			invoiceWriter.write("\nSpecial instructions: " + specialInfo + "\n\n");

			invoiceWriter.write("Total: R" + bill + ".00\n\n");

			invoiceWriter.write(yourDriver
					+ " is nearest to the restaurant and so he will be delivering your order to you at: " + "\n\n");

			invoiceWriter.write(addressCustomer + "\n\n");

			invoiceWriter.write("If you need to contact the restaurant, their number is " + companyNumber);

			invoiceWriter.close();

		} catch (Exception e) {
			System.out.println("Could not write into the file.");
		}
	}

	// Select a Driver
	private static String pickDriver(String cityEntered) {

		// Check that the Customer city and the restaurant city match
		boolean restMatchCity = restaurantCity.equalsIgnoreCase(cityEntered);

		// Check that the Customer city and the driver city match
		String yourDriver = null;
		boolean foundCity = false;
		int lowestLoad = Integer.MAX_VALUE;
		for (String[] x : driverInfo) {
			int load = Integer.parseInt(x[2]);

			if (cityEntered.equalsIgnoreCase(x[1])) {
				foundCity = true;
				if (lowestLoad > load) {
					lowestLoad = load;
					yourDriver = x[0];
				}

			}

		}

		if (!foundCity || !restMatchCity) {

			System.out.println("Sorry! Our drivers are too far away from you to be able to deliver to your location.");
			System.out.println("Please also check if you spelt your city correctly: " + cityEntered);

			System.exit(0);
		}
		return yourDriver;
	}

	// Recursive algorithm for each order
	private static void takeOrders(Scanner scanner) {
		int mealQuantity;
		String wantsMore;

		String mealName = checkMenu(scanner);

		System.out.println("How many " + mealName + "s would you like? eg 3");
		mealQuantity = Integer.parseInt(scanner.nextLine());

		mealsAndQuantities.put(mealName, mealQuantity);

		System.out.println("Anything else? (Y/N)");
		wantsMore = scanner.nextLine();

		if (wantsMore.equalsIgnoreCase("Y")) {
			takeOrders(scanner);
		}

	}

	private static String checkMenu(Scanner scanner) {
		System.out.println("Heres the Menu: ");
		printHashTable(Restaurant.mealsAvailable);

		String mealName;
		System.out.println("What would you like to order: ");
		mealName = scanner.nextLine();

		if (!Restaurant.mealsAvailable.containsKey(mealName)) {
			System.out.println("Sorry " + mealName + " is not on the menu");

			checkMenu(scanner);

		}
		return mealName;
	}

	// Function to print HashTable
	public static void printHashTable(HashMap<?, ?> map) {
		System.out.println();

		map.forEach((key, value) -> {
			System.out.println(key + ": " + value);
		});
		System.out.println();

	}

}
