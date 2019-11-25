import java.util.*;

public class Driver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		menu();

		System.out.println("Enter your order: ");
		String user = input.nextLine();

		while (!(user.contentEquals("QUIT"))) {
			// split user input
			String[] array = userSplit(user);

			// Verify user request
			String request = order(array[0]);

			String player, league, position, file, expression;

			// All the print statements are for testing purposes
			switch (request) {
			case "ODRAFT": {
				player = array[1];
				league = array[2];
				System.out.println(request + player + league);
				break;
			}

			case "IDRAFT": {
				player = array[1];
				System.out.println(request + player);
				break;
			}

			case "OVERALL": {
				if (array.length > 1) {
					position = array[1];
					System.out.println(request + position);
				}
				break;
			}

			case "POVERALL": {
				System.out.println(request);
				break;
			}

			case "TEAM": {
				league = array[1];
				System.out.println(request + league);
				break;
			}

			case "STARS": {
				league = array[1];
				System.out.println(request + league);
				break;
			}

			case "SAVE": {
				file = array[1];
				System.out.println(request + file);
				break;
			}

			case "RESTORE": {
				file = array[1];
				System.out.println(request + file);
				break;
			}

			case "EVALFUN": {
				expression = array[1];
				System.out.println(request + expression);
				break;
			}

			case "PEVALFUN": {
				expression = array[1];
				System.out.println(request + expression);
				break;
			}

			}

			menu();

			System.out.println("Enter your order: ");
			user = input.nextLine();

		}

		System.out.println("Program is terminated.");
	}

	// method will return user input as an array of strings
	public static String[] userSplit(String str) {
		String[] userArray = str.split("\\s+");
		return userArray;
	}

	// method will return the order/request
	public static String order(String userInput) {
		String str = " ";
		String[] orders = { "ODRAFT", "IDRAFT", "OVERALL", "POVERALL", "TEAM", "STARS", "SAVE", "QUIT", "RESTORE",
				"EVALFUN", "PEVALFUN" };

		for (int i = 0; i < orders.length; i++) {

			if (userInput.equals(orders[i]))
				return str = orders[i];
			else
				str = "Invalid Input";
		}
		return str;
	}

	public static void menu() {
		System.out.println("-ODRAFT\n-IDRAFT\n-OVERALL\n-POVERALL\n-TEAM\n-STARS\n-SAVE\n-QUIT"
				+ "\n-RESTOR\n-EVALFUN\n-PEVALFUN\n");
	}

}
