import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        //Print welcoming messages
        welcomeMessage();

        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();

        ArrayList<Task> storeTask = new ArrayList<Task>();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < storeTask.size(); i++) {
                    System.out.println(i + 1 + "." + storeTask.get(i).toString());
                }
            }
            //Check if user input unmark
            else if (userInput.contains("unmark")) {
                updateMarkList(false, userInput, storeTask);

            } else if (userInput.contains("mark")) {
                updateMarkList(true, userInput, storeTask);

            } else {
                //get the option
                String option = userInput.split(" ")[0];
                try {
                    //check if option is valid
                    validOption(option);

                    //update the list
                    updateList(option, storeTask, userInput);
                } catch (EchoIncorrectOption err) {
                    System.out.println("Whoops, this option doesn't exist");

                } catch (ArrayIndexOutOfBoundsException err) {
                    System.out.println("Whoops, the description cannot be empty!!");
                }
            }
            userInput = reader.nextLine();
        }
        System.out.println("See you next time");
    }

    private static void welcomeMessage() {
        String logo = """
                #######  #####   #     #  #######\s
                #       #     #  #     #  #     #\s
                #       #        #     #  #     #\s
                #####   #        #######  #     #\s
                #       #        #     #  #     #\s
                #       #     #  #     #  #     #\s
                #######  #####   #     #  #######""";
        System.out.println("Welcome to\n" + logo + "\n" + "What can I do for you?");
    }

    private static void updateMarkList(boolean markStatus, String inputIndex, ArrayList<Task> storeTask) {
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

    private static void validOption(String userInput) throws EchoIncorrectOption {
        String[] correctOptions = {"delete", "todo", "deadline", "event"};

        for (String correctOption : correctOptions) {
            if (userInput.equals(correctOption)) {
                return;
            }
        }
        throw new EchoIncorrectOption();
    }


    private static void updateList(String type, ArrayList<Task> storeTask, String userInput) {
        if (type.equals("delete")) {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;

            System.out.println("Noted. I've removed this task:\n" + storeTask.get(index).toString());
            storeTask.remove(index);
        } else {
            userInput = userInput.split(" ", 2)[1];
            String description = userInput.split("/")[0];

            switch (type) {
                case "todo" -> storeTask.add(new Todo(userInput));
                case "deadline" -> {
                    String to = userInput.split("/")[1].replace("by ", "");
                    storeTask.add(new Deadline(description, to));
                }
                case "event" -> {
                    String from = userInput.split("/")[1].replace("from ", "");
                    String to = userInput.split("/")[2].replace("to ", "");

                    storeTask.add(new Event(description, from, to));
                }
            }
            System.out.println("Got it! I've added this task: \n" + storeTask.get(storeTask.size() - 1).toString());
        }
        System.out.println("Now you have " + storeTask.size() + " in the list.");
    }


}
