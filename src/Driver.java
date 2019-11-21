import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your order: ");
        String user = input.nextLine();

        String[] array = userSplit(user);

        for(int i=0; i<array.length; i++)
            System.out.println(array[i]);

        String request = order(array[0]);
        System.out.println(request);
    }

    public static String[] userSplit(String str) {
        String[] userArray = str.split("\\s+");
        return userArray;
    }

    public static String order(String userInput) {
        String str = " ";
        String[] orders = {"ODRAFT", "IDRAFT", "OVERALL",
                "POVERALL", "TEAM", "STARS",
                "SAVE","QUIT", "RESTORE",
                "EVALFUN", "PEVALFUN"};

        for(int i=0; i<orders.length; i++) {

            if(userInput.equals(orders[i]))
                return str = orders[i];
            else
                str = "Invalid Input";
        }
        return str;
    }
}
