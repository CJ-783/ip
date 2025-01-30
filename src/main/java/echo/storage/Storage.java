package echo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import echo.tasklist.TaskList;
import echo.tasks.Deadline;
import echo.tasks.Event;
import echo.tasks.Todo;

/**
 * This class is responsible for the storing and retrieving of the task list.
 */
public class Storage {

    private File filePath;

    private TaskList taskList;

    /**
     * A constructor that assign the file-path and task-list into the variable.
     *
     * @param filePath the file path to store the task list.
     * @param taskList the taskList object to manage the task lists.
     */
    public Storage(String filePath, TaskList taskList) {
        this.filePath = new File(filePath);
        this.taskList = taskList;
    }

    /**
     * This method retrieves the task list from the file.
     */
    public void loadData()   {
        String[] taskArray;
        String type;
        String description;
        String from;
        String to;
        String isDone;
        try {
            Scanner s = new Scanner(filePath);
            while (s.hasNext()) {
                taskArray = s.nextLine().split("\\|");

                type = taskArray[0].replaceAll("\\s", "");
                isDone = taskArray[1].replaceAll("\\s", "");
                description = taskArray[2].trim();

                if (type.equals("T")) {
                    taskList.addTask(new Todo(description));

                } else if (type.equals("D")) {
                    to = taskArray[3].trim();
                    taskList.addTask(new Deadline(description, to));

                } else if (type.equals("E")) {
                    from = taskArray[3].trim();
                    to = taskArray[4].trim();
                    taskList.addTask(new Event(description, from, to));
                }

                if (isDone.equals("1")) {
                    taskList.setMark(taskList.getTotalTask() - 1);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * This method generates the correct text, then either append or replaces the entire file
     *
     * @param option Determines whether to replace the entire task list or to just append into the file.
     */
    public void saveData(String option) {
        String text = generateOutput(option);
        saveToFile(option, text);
    }

    /**
     * This method write to the file
     * @param option    Determines whether to replace the entire task list or to just append into the file
     * @param text      The text to replace or append into the file
     */
    private void saveToFile(String option, String text) {
        String fullPath = filePath.getAbsolutePath();
        try {
            if (option.equals("replace")) {
                FileWriter fw = new FileWriter(fullPath);
                fw.write(text);
                fw.close();
            } else if (option.equals("append")) {
                FileWriter fw = new FileWriter(fullPath, true);
                fw.write(text);
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("An error has occured. " + e.toString());
        }
    }


    /**
     * This method generates the correct output format to be added into the file.
     *
     * @param option    Determines whether to generate the entire list, or just an additional task to be added
     * @return          The entire list String to be added into the file
     */
    private String generateOutput(String option) {
        StringBuilder text = new StringBuilder();

        if (option.equals("append")) {
            text.append(taskList.getElementOutputToFile(taskList.getTotalTask() - 1));
            text.append("\n");
            return text.toString();
        }
        return taskList.getAllOutputToFile();
    }
}
