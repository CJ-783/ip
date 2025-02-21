package echo.parser;

import java.util.ArrayList;

import echo.exceptions.DateFormatError;
import echo.exceptions.EchoDuplicateTask;
import echo.exceptions.EchoIncorrectOption;
import echo.storage.Storage;
import echo.tasklist.TaskList;
import echo.tasks.Deadline;
import echo.tasks.Event;
import echo.tasks.Task;
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

    private Boolean isValidCommand(String input, String[] commandParts) {
        if (input.equals("list") || input.equals("bye")) {
            return true;
        }

        if (commandParts.length > 1) {
            return true;
        }

        return false;
    }

    /**
     * Determines the action based on the user input command.
     *
     * @param userInput The user input.
     * @return a response message indicating the result of the command execution
     */
    public String getOption(String userInput) {
        String[] commandParts = userInput.split(" ");
        String userCommand = commandParts[0].trim().toLowerCase();

        if (!isValidCommand(userInput, commandParts)) {
            return "Please add the description";
        }

        switch (userCommand) {
        case "bye":
            return "See you next time!";
        case "list":
            return ui.printList(taskList);
        case "unmark":
            return unMark(userInput);
        case "mark":
            return mark(userInput);
        case "find":
            String userDesc = commandParts[1].trim().toLowerCase();
            return findTask(userDesc);
        default:
            return addRemoveList(userInput);
        }
    }

    /**
     * Searches for tasks in the task list that match the given description.
     *
     * @param userDesc the description to search for in the task list.
     * @return a formatted string containing the matching tasks if found, or a message indicating that it doesn't exist
     */
    private String findTask(String userDesc) {
        ArrayList<Task> tasksFound = this.taskList.findTask(userDesc);
        return ui.findTask(tasksFound);
    }

    /**
     * This method is responsible for un-marking the user task on the task list
     *
     * @param userInput The index of the task in the task list to un-mark.
     * @return a message confirming that the task has been unmarked
     */
    private String unMark(String userInput) {
        String index = userInput.split(" ")[1];
        int unmarkIndex = Integer.parseInt(index) - 1;
        //check the index user key in is within the range
        if (unmarkIndex > taskList.getTotalTask() - 1 || unmarkIndex < 0 ) {
            return "The task doesn't exist!";
        }

        taskList.setUnmark(unmarkIndex);
        storage.saveData("replace");
        return ui.unmark(unmarkIndex, taskList);
    }

    /**
     * This method is responsible for marking the user task on the task list
     *
     * @param userInput The index of the task in the task list to mark.
     * @return a message confirming that the task has been marked
     */
    private String mark(String userInput) {
        String index = userInput.split(" ")[1];
        int markIndex = Integer.parseInt(index) - 1;
        //check the index user key in is within the range
        if (markIndex > taskList.getTotalTask() -1 || markIndex < 0 ) {
            return "The task doesn't exist!";
        }
        taskList.setMark(markIndex);
        storage.saveData("replace");
        return ui.mark(markIndex, taskList);
    }

    /**
     * This method is used to determine if the user entered an incorrect input.
     *
     * @param userInput The index of the task in the task list to add or remove.
     * @return a message confirming the operation, or an error message if the command is incorrect.
     */
    private String addRemoveList(String userInput) {
        String option = userInput.split(" ")[0];

        try {
            validOption(option);
            return updateList(userInput);

        } catch (EchoIncorrectOption err) {
            ui.errorMessage("This option doesn't exist! Please try again");
            return "This option doesn't exist! Please try again";
        } catch (ArrayIndexOutOfBoundsException err) {
            ui.errorMessage("Do remember to add the description");
            return "Do remember to add the description!";
        } catch (EchoDuplicateTask err) {
            ui.errorMessage("Duplicate!!");
            return "This is a duplicate!!!";
        } catch (DateFormatError err) {
            ui.errorMessage("Please enter the correct date format (dd/MM/yyyy HHmm)!!");
            return "Please enter the correct date format (dd/MM/yyyy HHmm)!!";
        }
    }

    private String handleDelete(String[] commandParts) {
        int index = Integer.parseInt(commandParts[1]) - 1;
        //check the index user key in is within the range
        if (index > taskList.getTotalTask() - 1 || index < 0) {
            return "The task doesn't exist!";
        }
        String returnString = ui.deleteFromList(index, taskList);
        taskList.removeTask(index);
        storage.saveData("replace");

        return returnString;
    }


    /**
     * This method is to update the task list (i.e. delete/add).
     *
     *
     * @param userInput the full command string entered by the user.
     *
     * @return a message indicating the result of the update operation.
     * @throws EchoDuplicateTask if an attempt is made to add a duplicate task.
     * @throws DateFormatError if the provided date format is incorrect for deadline tasks.
     */
    private String updateList(String userInput) throws EchoDuplicateTask, DateFormatError {
        String[] commandParts = userInput.trim().split(" ", 2);
        String option = commandParts[0];

        if (option.equals("delete")) {
            return handleDelete(commandParts);
        } else {
            return handleAdd(option, commandParts[1]);
        }
    }


    private String handleAdd(String option, String details) throws EchoDuplicateTask, DateFormatError {



        switch (option) {
        case "todo": {
            String description = details;
            try {
                checkDuplicate(new Todo(description));
            } catch (EchoDuplicateTask e) {
                throw new EchoDuplicateTask();
            }
            taskList.addTask(new Todo(description));
            break;
        }

        case "deadline": {
            String[] deadlineParts = details.split("/by");

            String description = deadlineParts[0].trim();
            String deadlineDate = deadlineParts[1].trim();
            try {
                Deadline newDateline = new Deadline(description, deadlineDate);
                checkDuplicate(newDateline);
                taskList.addTask(newDateline);
            } catch (EchoDuplicateTask e) {
                throw new EchoDuplicateTask();
            } catch (DateFormatError err) {
                throw new DateFormatError();
            }
            break;
        }

        case "event": {
            String description = details.split("/from")[0];
            String fromDate = details.split("/from")[1];
            fromDate = fromDate.split("/to")[0].trim();
            String deadlineDate = details.split("/to ")[1].trim();
            try {
                checkDuplicate(new Event(description, fromDate, deadlineDate));
            } catch (EchoDuplicateTask e) {
                throw new EchoDuplicateTask();
            }
            taskList.addTask(new Event(description, fromDate, deadlineDate));
            break;
        }
        default:
            return "Error: unknown command";
        }
        storage.saveData("append");
        return ui.addToList(taskList);
    }



    public void checkDuplicate(Task task) throws EchoDuplicateTask {
        for (int i = 0; i < taskList.getTotalTask(); i++) {
            if (task.getDescription().equals(taskList.getDescription(i))) {
                throw new EchoDuplicateTask();
            }
        }
    }

    /**
     * This method decides whether the option that the user key in is valid.
     *
     * @param userInput The user option that the user keyed in
     * @throws EchoIncorrectOption Throws an incorrect option exception when the user enter an invalid option
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
