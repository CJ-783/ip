package echo.parser;

import echo.exceptions.EchoIncorrectOption;
import echo.storage.Storage;
import echo.tasklist.TaskList;
import echo.tasks.Deadline;
import echo.tasks.Event;
import echo.tasks.Todo;
import echo.ui.Ui;

/**
 * The Parser class is meant as a way to understand the user input
 */

public class Parser {
    private TaskList taskList;
    private Ui ui;

    private Storage storage;

    /**
     * @param ui       The ui class meant for responding to the user input
     * @param taskList The task list that contains all the task that the user has created
     * @param storage  The storage class meant to handle retrieving and storing of the task list
     */
    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }


    /**
     * This method determines the option that the user has chosen.
     *
     * @param userInput The user input.
     * @return          An integer for the Echo class to determine if the user has chosen to exit the program
     */
    public int getOption(String userInput) {
        if (userInput.equals("bye")) {
            ui.exitMessage();
            return 0;
        } else if (userInput.equals("list")) {
            ui.printList(taskList);
            return 1;
        } else if (userInput.contains("unmark")) {
            unMark(userInput);
            return 1;
        } else if (userInput.contains("mark")) {
            mark(userInput);
            return 1;
        } else {
            return addRemoveList(userInput);
        }
    }

    /**
     * This method is responsible for un-marking the user task on the task list
     *
     * @param userInput The index of the task in the task list to un-mark.
     */
    private void unMark(String userInput) {
        String index = userInput.split(" ")[1];
        int unmarkIndex = Integer.parseInt(index) - 1;
        taskList.setUnmark(unmarkIndex);
        ui.unmark(unmarkIndex, taskList);
        storage.saveData("replace");
    }

    /**
     * This method is responsible for marking the user task on the task list
     *
     * @param userInput The index of the task in the task list to mark.
     */
    private void mark(String userInput) {
        String index = userInput.split(" ")[1];
        int markIndex = Integer.parseInt(index) - 1;
        taskList.setMark(markIndex);
        ui.mark(markIndex, taskList);
        storage.saveData("replace");
    }

    /**
     * This method is used to determine if the user entered an incorrect input.
     *
     * @param userInput The index of the task in the task list to add or remove.
     * @return          An integer based on whether the user entered an incorrect input.
     */
    private int addRemoveList(String userInput) {
        String option = userInput.split(" ")[0];

        try {
            validOption(option);
            updateList(userInput);
            return 1;

        } catch (EchoIncorrectOption err) {
            ui.errorMessage("This option doesn't exist! Please try again");
            return -1;
        } catch (ArrayIndexOutOfBoundsException err) {
            ui.errorMessage("Do remember to add the description");
            return -2;
        }
    }

    /**
     * This method is to update the task list (i.e. delete/add).
     *
     * @param userInput The index of the task in the task list.
     */
    private void updateList(String userInput) {
        String option = userInput.split(" ")[0];

        if (option.equals("delete")) {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            ui.deleteFromList(index, taskList);
            taskList.removeTask(index);
            storage.saveData("replace");

        } else {
            userInput = userInput.split(" ", 2)[1].trim();
            String description = userInput.split("/")[0].trim();

            switch (option) {
                case "todo" -> {
                    taskList.addTask(new Todo(userInput));

                }
                case "deadline" -> {
                    String to = userInput.split("/by ")[1].trim();
                    taskList.addTask(new Deadline(description, to));
//                    taskList.setDateTime(taskList.getTotalTask() - 1, to);

                }
                case "event" -> {
                    String from = userInput.split("/from")[1];
                    from = from.split("/to")[0].trim();
                    String to = userInput.split("/to ")[1].trim();
                    taskList.addTask(new Event(description, from, to));
                    taskList.setDateTime(taskList.getTotalTask() - 1, to);

                }
            }
            ui.addToList(taskList);
            storage.saveData("append");
        }
    }

    /**
     * This method decides whether the option that the user key in is valid.
     *
     * @param userInput             The user input that the user keyed in
     * @throws EchoIncorrectOption  Throws an incorrect option exception when the user enter an invalid option
     */
    private void validOption(String userInput) throws EchoIncorrectOption {
        String[] correctOptions = {"delete", "todo", "deadline", "event"};

        for (String correctOption : correctOptions) {
            if (userInput.equals(correctOption)) {
                return;
            }
        }
        throw new EchoIncorrectOption();
    }

}
