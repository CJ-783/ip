package echo.ui;

import java.util.ArrayList;
import java.util.Scanner;

import echo.tasklist.TaskList;
import echo.tasks.Task;

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

    public String findTask (ArrayList<Task> tasklists) {
        if (tasklists.isEmpty()) {
            return "No Task found!";
        }
        String startSentence = "Here are the matching tasks in your list:";
        String tasks = "";
        for (int i = 0; i < tasklists.size(); i++) {
            tasks += tasklists.get(i).toString() + "\n";
        }
        return startSentence + "\n" + tasks;
    }


    /**
     * Display a message stating that the task the user input has been deleted
     *
     * @param index
     * @param taskList
     */
    public String deleteFromList(int index, TaskList taskList) {
        String startSentence = "Gotcha! The following has been deleted!";
        assert index > -1: "The index given should not be negative";
        return startSentence + "\n" + taskList.getElementString(index);
    }


    /**
     * Display a message indicating a task has been added to the list.
     *
     * @param taskList The task list containing the task
     */
    public String addToList(TaskList taskList) {
        String startSentence = "Gotcha! I've added this task:";

        return startSentence + "\n" + taskList.getElementString(taskList.getTotalTask() - 1);
    }

    /**
     * Display the task list
     *
     * @param taskList The Task list to be printed
     */
    public String printList(TaskList taskList) {
        if (Integer.valueOf(taskList.getTotalTask()).equals(0)) {
            return "The list is empty! Add in something!";
        } else {
            return taskList.toString();
        }
    }

    /**
     * Unmarks a task
     *
     * @param index     The index of the task to be unmarked.
     * @param taskList  The task list containing the tasks.
     */
    public String unmark(int index, TaskList taskList) {
        String startSentence = "OK, I've marked this task as not done yet:";
        assert index > -1: "The index given should not be negative";
        return startSentence + "\n" + taskList.getElementString(index);
    }

    /**
     * Marks a task.
     *
     * @param index     The index of the task to be marked.
     * @param taskList  The task list containing the tasks.
     */
    public String mark(int index, TaskList taskList) {
        String startSentence = "Nice! Marked!";
        assert index > -1: "The index given should not be negative";
        return startSentence + "\n" + taskList.getElementString(index);
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
