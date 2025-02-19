package echo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import echo.exceptions.DateFormatError;
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
     * This method retrieves the task list from the file and load into the program task list.
     */
    public void loadData() {
        try {
            Scanner s = new Scanner(filePath);
            while (s.hasNext()) {
                processTask(s.nextLine());
            }
        } catch (FileNotFoundException err) {
            setUpFile();
        }
    }

    /**
     * Process a task string and adds to the task list
     *
     * @param task the string containing the task
     */
    private void processTask(String task) {
        String[] taskArray = task.split("\\|");
        String type = taskArray[0].replaceAll("\\s", "");
        boolean isDone = taskArray[1].replaceAll("\\s", "").equals("1");
        String description = taskArray[2].trim();

        switch (type) {
        case "T":
            addTodoTask(description, isDone);
            break;
        case "D":
            addDeadlineTask(taskArray, description, isDone );
            break;
        case "E":
            addEventTask(taskArray, description, isDone);
            break;
        default:

        }
    }

    /**
     * Adds a "to-do" task to the task list and marks as completed if it's completed.
     *
     * @param description the description of the to-do task
     * @param isDone a boolean flag checking whether the to-do task list is marked as complete
     */
    private void addTodoTask(String description, boolean isDone) {
        taskList.addTask(new Todo(description));
        markTask(isDone);
    }

    private void addDeadlineTask(String[] taskArray, String description, boolean isDone) {
        String dueDate = taskArray[3].trim();
        try {
            DateFormat originalFormat = new SimpleDateFormat("MMM d yyyy HHmm");
            Date date = originalFormat.parse(dueDate);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            String formattedDueDate = dateFormat.format(date);

            taskList.addTask(new Deadline(description, formattedDueDate));
            markTask(isDone);
        } catch (DateFormatError err) {
            System.out.println("Invalid date format for dateline: " + dueDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds an "event" task to the task list and marks as completed if it's completed
     *
     * @param taskArray the array containing all the tasks
     * @param description the description of the event task
     * @param isDone a boolean flag indicating whether the event task is marked as completed
     */
    private void addEventTask(String[] taskArray, String description, boolean isDone) {
        String duration = taskArray[3].trim();
        String fromDate = duration.split("-")[0].trim();
        String dueDate = duration.split("-")[1].trim();
        taskList.addTask(new Event(description, fromDate, dueDate));
        markTask(isDone);
    }

    /**
     * Marks a task as completed if it is done
     *
     * @param isDone is a boolean flag checking whether the task is completed
     */
    private void markTask(boolean isDone) {
        if (isDone) {
            taskList.setMark(taskList.getTotalTask() - 1);
        }
    }

    /**
     * Sets up the file for storing task data by creating a new file if it does not exist.
     */
    private void setUpFile() {
        System.out.println("Creating Echo.txt file...");
        File echoFile = new File("Data/Echo.txt");
        echoFile.getParentFile().mkdir();
        System.out.println("File created!");
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
     *
     * @param option Determines whether to replace the entire task list or to just append into the file
     * @param text   The text to replace or append into the file
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
     * @param option Determines whether to generate the entire list, or just an additional task to be added
     * @return The entire list String to be added into the file
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
