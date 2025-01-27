/*
This class takes care of UNDERSTANDING user's input
 */

public class Parser {
    private TaskList taskList;
    private Ui ui;

    private Storage storage;
    Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }


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

    private void unMark(String userInput) {
        String index = userInput.split(" ")[1];
        int unmarkIndex = Integer.parseInt(index) - 1;
        taskList.setUnmark(unmarkIndex);
        ui.unmark(unmarkIndex, taskList);
        storage.saveData("replace");
    }

    private void mark(String userInput) {
        String index = userInput.split(" ")[1];
        int markIndex = Integer.parseInt(index) - 1;
        taskList.setMark(markIndex);
        ui.mark(markIndex, taskList);
        storage.saveData("replace");
    }

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

    private void updateList(String userInput) {
        String option = userInput.split(" ")[0];

        if (option.equals("delete")) {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            ui.deleteFromList(index, taskList);
            taskList.removeTask(index);
            storage.saveData("replace");

        } else {
            userInput = userInput.split(" ", 2)[1];
            String description = userInput.split("/")[0];

            switch (option) {
                case "todo" -> {
                    taskList.addTask(new Todo(userInput));


                }
                case "deadline" -> {
                    String to = userInput.split("/by ")[1];

                    taskList.addTask(new Deadline(description, to));
                    taskList.setDateTime(taskList.getTotalTask() - 1, to);

                }
                case "event" -> {
                    String from = userInput.split("/")[1].replace("from ", "");
                    String to = userInput.split("/to ")[1];
                    taskList.addTask(new Event(description, from, to));
                    taskList.setDateTime(taskList.getTotalTask() - 1, to);

                }
            }
            ui.addToList(taskList);
            storage.saveData("append");
        }
    }

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
