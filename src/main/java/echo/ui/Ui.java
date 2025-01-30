package echo.ui;

import java.util.Scanner;

import echo.tasklist.TaskList;

/**
 * This class takes care of user interaction.
 */
public class Ui {

    /**
     * Constructs a Ui object
     */
    public Ui() {

    }

    /**
     * Displays the welcome message.
     */
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

    /**
     * Reads the user input.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }


    /**
     * Display a message stating that the task the user input has been deleted
     *
     * @param index
     * @param taskList
     */
    public void deleteFromList(int index, TaskList taskList) {
        System.out.println("Gotcha! The following has been deleted!");
        taskList.printElementString(index);
    }


    /**
     * Display a message indicating a task has been added to the list.
     *
     * @param taskList The task list containing the task
     */
    public void addToList(TaskList taskList) {
        System.out.println("Got it! I've added this task:");
        taskList.printElementString(taskList.getTotalTask() - 1);
    }

    /**
     * Display the task list
     *
     * @param taskList The Task list to be printed
     */
    public void printList(TaskList taskList) {
        if (Integer.valueOf(taskList.getTotalTask()).equals(0)) {
            System.out.println("The list is empty! Add in something!");
        } else {
            System.out.println(taskList);
        }
    }

    /**
     * Unmarks a task
     *
     * @param index     The index of the task to be unmarked.
     * @param taskList  The task list containing the tasks.
     */
    public void unmark(int index, TaskList taskList) {
        System.out.println("OK, I've marked this task as not done yet:");
        taskList.printElementString(index);
    }

    /**
     * Marks a task.
     *
     * @param index     The index of the task to be marked.
     * @param taskList  The task list containing the tasks.
     */
    public void mark(int index, TaskList taskList) {
        System.out.println("Nice! Marked!");
        taskList.printElementString(index);
    }

    /**
     * Display an exit message when the user exits
     */
    public void exitMessage() {
        System.out.println("See you next time!");
    }

    /**
     * Display an error message.
     *
     * @param err The error message to be displayed.
     */
    public void errorMessage(String err) {
        System.out.println("Error!: " + err);
    }

}
