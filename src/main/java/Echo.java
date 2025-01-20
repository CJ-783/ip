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

        Task[] storeTask = new Task[100];
        int storeTaskAmt = 0;

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < storeTaskAmt; i++) {
                    System.out.println(i+1 + "." +  storeTask[i].toString() );
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

            updateList(type, storeTask, storeTaskAmt, userInput);

            System.out.println("Got it! I've added this task: \n" + storeTask[storeTaskAmt].toString() );

            storeTaskAmt++;
            userInput = reader.nextLine();

        }

        System.out.println("See you next time");

    }

    private static void updateMarkList(boolean markStatus, String inputIndex, Task[]  storeTask) {
        String element = inputIndex.split(" ")[1];
        int elementIndex = Integer.parseInt(element) - 1;

        if (markStatus) {
            storeTask[elementIndex].setDone(true);

            System.out.println("Nice! Marked!");

        } else {
            storeTask[elementIndex].setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(storeTask[elementIndex].toString());

    }

    private static void updateList(String type, Task[] storeTask, int storeTaskAmt, String userInput) {

        if (type.equals("todo")) {
            userInput = userInput.substring(5);
            storeTask[storeTaskAmt] = new Todo(userInput);
        }
        if (type.equals("deadline")) {
            userInput = userInput.substring(9);
            String description = userInput.split("/")[0];

            String to = userInput.split("/")[1].replace("by ", "");
            storeTask[storeTaskAmt] = new Deadline(description, to);
        }
        if (type.equals("event")) {
            userInput = userInput.substring(6);
            String description = userInput.split("/")[0];

            String from = userInput.split("/")[1].replace("from ", "");
            String to = userInput.split("/")[2].replace("to ", "");
            storeTask[storeTaskAmt] = new Event(description, from, to);
        }


    }

}
