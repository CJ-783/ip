import java.util.ArrayList;
import java.util.Arrays;
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

        ArrayList<Task> storeTask = new ArrayList<Task>();

        int storeTaskAmt = 0;
        int printedStoreTaskAmt = 0;

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < storeTaskAmt; i++) {
                    System.out.println(i+1 + "." +  storeTask.get(i).toString());

                }
                userInput = reader.nextLine();
                continue;
            }

            if (userInput.contains("unmark")) {

                updateMarkList(false, userInput, storeTask);

                userInput = reader.nextLine();
                continue;

            } else if (userInput.contains("mark")) {

                updateMarkList(true, userInput, storeTask);

                userInput = reader.nextLine();
                continue;
            }

            String type = userInput.split(" ")[0];

            try {
                updateList(type, storeTask, storeTaskAmt, userInput);
            } catch (EchoIncorrectOption err) {
                System.out.println("Whoops, this option doesn't exist");

                userInput = reader.nextLine();
                continue;
            } catch (ArrayIndexOutOfBoundsException err) {
                System.out.println("Whoops, the description cannot be empty!!");

                userInput = reader.nextLine();
                continue;
            }

            printedStoreTaskAmt++;
            System.out.println("Got it! I've added this task: \n" + storeTask.get(storeTaskAmt).toString() + "\n" +
                    "Now you have " + printedStoreTaskAmt  + " tasks in the list.");

            storeTaskAmt++;
            userInput = reader.nextLine();

        }

        System.out.println("See you next time");
    }

    private static void updateMarkList(boolean markStatus, String inputIndex, ArrayList<Task>  storeTask) {
        String element = inputIndex.split(" ")[1];
        int elementIndex = Integer.parseInt(element) - 1;

        if (markStatus) {
            storeTask.get(elementIndex).setDone(true);

            System.out.println("Nice! Marked!");

        } else {
            storeTask.get(elementIndex).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(storeTask.get(elementIndex).toString());

    }

    private static void updateList(String type, ArrayList<Task> storeTask, int storeTaskAmt, String userInput) throws
            EchoIncorrectOption{


        if (type.equals("todo")) {
            userInput = userInput.split(" ", 2)[1];

            storeTask.add(new Todo(userInput));


        } else if (type.equals("deadline")) {
            userInput = userInput.split(" ", 2)[1];

            String description = userInput.split("/")[0];

            String to = userInput.split("/")[1].replace("by ", "");

            storeTask.add(new Deadline(description, to));

        } else if (type.equals("event")) {
            userInput = userInput.split(" ", 2)[1];

            String description = userInput.split("/")[0];

            String from = userInput.split("/")[1].replace("from ", "");
            String to = userInput.split("/")[2].replace("to ", "");

            storeTask.add(new Event(description, from, to));

        } else {
            throw new EchoIncorrectOption();
        }

    }

}
