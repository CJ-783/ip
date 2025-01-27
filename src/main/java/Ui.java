import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class takes care of user inputs + displaying messages to the user
 */
public class Ui {

    Ui() {

    }

    public void welcomeMessage() {
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

    public String readCommand() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }


    public void deleteFromList(int index, TaskList taskList) {
        System.out.println("Gotcha! The following has been deleted!");
        taskList.printElementString(index);
    }


    public void addToList(TaskList taskList) {
        System.out.println("Got it! I've added this task:");
        taskList.printElementString(taskList.getTotalTask() - 1);
    }

    public void printList(TaskList taskList) {
        if (Integer.valueOf(taskList.getTotalTask()).equals(0)) {
            System.out.println("The list is empty! Add in something!");
        } else {
            System.out.println(taskList);
        }
    }

    public void unmark(int index, TaskList taskList) {
        System.out.println("OK, I've marked this task as not done yet:");
        taskList.printElementString(index);
    }

    public void mark(int index, TaskList taskList) {
        System.out.println("Nice! Marked!");
        taskList.printElementString(index);
    }

    public void exitMessage() {
        System.out.println("See you next time!");
    }

    public void errorMessage(String err) {
        System.out.println("Error!: " + err);
    }

}
