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

        Task[] storeText = new Task[100];
        int storeTaskAmt = 0;

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < storeTaskAmt; i++) {
                    System.out.println(i+1 + "." +  storeText[i].toString() );
                }
                userInput = reader.nextLine();
                continue;
            }

            if (userInput.contains("unmark")) {

                updateMarkList(false, userInput, storeText);

                userInput = reader.nextLine();
                continue;

            } else if (userInput.contains("mark")) {

                updateMarkList(true, userInput, storeText);

                userInput = reader.nextLine();
                continue;
            }

                storeText[storeTaskAmt] = new Task(userInput);
                storeTaskAmt++;

                System.out.println("added: " + userInput);
                userInput = reader.nextLine();

        }

        System.out.println("See you next time");

    }

    private static void updateMarkList(boolean markStatus, String inputIndex, Task[]  storeText) {
        String element = inputIndex.split(" ")[1];
        int elementIndex = Integer.parseInt(element) - 1;

        if (markStatus) {
            storeText[elementIndex].setDone(true);

            System.out.println("Nice! Marked!");

        } else {
            storeText[elementIndex].setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(storeText[elementIndex].toString());

    }

}
