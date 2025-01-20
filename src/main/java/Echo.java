import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        String logo = "#######  #####   #     #  ####### \n"
                    + "#       #     #  #     #  #     # \n"
                    + "#       #        #     #  #     # \n"
                    +"#####   #        #######  #     # \n"
                    +"#       #        #     #  #     # \n"
                    +"#       #     #  #     #  #     # \n"
                    +"#######  #####   #     #  #######";
        System.out.println("Welcome to\n" + logo + "\n" + "What can I do for you?");

        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();

        String[] storeText = new String[100];
        int storeTextAmnt = 0;

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < storeTextAmnt; i++) {
                    System.out.println(i+1 + ". " + storeText[i]);
                }
            }

            storeText[storeTextAmnt] = userInput;
            storeTextAmnt++;

            System.out.println(userInput);
            userInput = reader.nextLine();
        }

        System.out.println("See you next time");

    }


}
