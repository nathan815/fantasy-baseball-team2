import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		menu();

		System.out.println("Enter your order: ");
		String user = input.nextLine();
		League league = new League();

		while (!(user.contentEquals("QUIT"))) {
			// split user input
			String[] userInput = userSplit(user);

			// Verify user request
			String request = order(userInput[0]);

			String playerName, teamName, position, file, expression;

			switch (request) {
				case "ODRAFT":
				case "IDRAFT":
					if(userInput.length <= 1) {
						System.out.println("Error: Must provide player last name. " +
								"If ambiguous last name, also provide first name/first initial in format: Last, First OR Last, F");
					} else if(request.equals("ODRAFT") && userInput.length < 3) {
						System.out.println("Error: Must provide league member name");
					} else {
						playerName = userInput[1];
						teamName = request.equals("IDRAFT") ? "A" : userInput[2];
						try {
							league.draftPlayerToTeam(playerName, teamName);
						} catch (PlayerDraftException e) {
							System.out.println("Error: " + e.getMessage());
						}
					}
				break;

			case "OVERALL": {
				if(userInput.length > 1) {
					position = userInput[1];
					System.out.println(request + position);
				}
				break;
			}

			case "POVERALL": {
				System.out.println(request);
				break;
			}

			case "TEAM": {
				teamName = userInput[1];
				System.out.println(request + teamName);
				break;
			}

			case "STARS": {
				teamName = userInput[1];
				System.out.println(request + teamName);
				break;
			}

			case "SAVE": {
				file = userInput[1];
				System.out.println(request + file);
				break;
			}

			case "RESTORE": {
				file = userInput[1];
				System.out.println(request + file);
				break;
			}

			case "EVALFUN": {
				expression = userInput[1];
				System.out.println(request + expression);
				break;
			}

			case "PEVALFUN": {
				expression = userInput[1];
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
