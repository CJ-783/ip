package echo.tasklist;

import java.util.ArrayList;

import echo.tasks.Task;

/**
 * This class is responsible for managing the task list
 */
public class TaskList {

    private int totalTask = 0;

    private ArrayList<Task> storeTask = new ArrayList<Task>();

    /**
     * Default constructor for TaskList
     */
    public TaskList() {

    }

    /**
     * @param task The task the user keyed in
     * @return The task that is found
     */
    public ArrayList<Task> findTask(String task) {
        ArrayList<Task> taskFound = new ArrayList<>();
        String currentTask = "";
        for (int i = 0; i < storeTask.size(); i++) {
            currentTask = storeTask.get(i).getDescription();
            if (currentTask.contains(task)) {
                taskFound.add(storeTask.get(i));
            }
        }
        return taskFound;
    }

    /**
     * Retrieves the total number of task
     * @return  Returns the total number of task in integer
     */
    public int getTotalTask() {
        return totalTask;
    }

    /**
     * Sets the deadline date/time for a specific task
     *
     * @param index     The index of the task in the list.
     * @param dateLine  The deadline date/time to be set.
     */
    public void setDateTime(int index, String dateLine) {
        assert index > -1 : "The index given should not be negative";
        storeTask.get(index).setDeadlineDateTime(dateLine);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        storeTask.add(task);
        totalTask++;
    }

    /**
     * Removes a task from the task list by its index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        storeTask.remove(index);
        totalTask--;
    }

    /**
     * Unmark a task as not done.
     *
     * @param index The index of the task to be unmarked.
     */
    public void setUnmark(int index) {
        storeTask.get(index).setDone(false);
    }

    /**
     * Mark a task as done.
     *
     * @param index The index to be mark from the task list.
     */
    public void setMark(int index) {
        storeTask.get(index).setDone(true);
    }

    /**
     * Prints the String representation of a task.
     *
     * @param index The index of the task to be printed.
     */
    public void printElementString(int index) {
        System.out.println(storeTask.get(index).toString());
    }

    /**
     * Retrieves the string representation of a task.
     *
     * @param index The index of the task from the task list to be returned.
     */
    public String getElementString(int index) {
        return storeTask.get(index).toString();
    }


    /**
     * Retrieves the formatted string representation of a task for file output.
     *
     * @param index The index of the task.
     * @return      The formatted string representation of the task for file output.
     */
    public String getElementOutputToFile(int index) {
        return storeTask.get(index).outputToFile();
    }

    /**
     * Retrieves the formatted string representation of all tasks for file output.
     *
     * @return A formatted string containing all tasks for file output.
     */
    public String getAllOutputToFile() {
        StringBuilder outputList = new StringBuilder();
        String newline = "";
        for (int i = 0; i < totalTask; i++) {
            outputList.append(newline);
            outputList.append(storeTask.get(i).outputToFile());
            outputList.append("\n");
        }
        return outputList.toString();
    }

    /**
     * Returns a string representation of the entire task list.
     *
     * @return A formatted string containing all tasks in the list.
     */
    public String toString() {
        StringBuilder outputList = new StringBuilder();
        String newline = "";
        for (int i = 0; i < totalTask; i++) {
            outputList.append(newline);
            outputList.append(i + 1 + "." + storeTask.get(i).toString());
            newline = "\n";
        }
        return outputList.toString();
    }


}
