package echo.tasklist;

import echo.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private int totalTask = 0;

    ArrayList<Task> storeTask = new ArrayList<Task>();

    public TaskList() {

    }

    public int getTotalTask() {
        return totalTask;
    }

    public void setDateTime(int index, String dateLine) {
        storeTask.get(index).setDateTime(dateLine);
    }

    public void addTask(Task task) {
        storeTask.add(task);
        totalTask++;
    }

    public void removeTask(int index) {
        storeTask.remove(index);
        totalTask--;
    }

    public void setUnmark(int index) {
        storeTask.get(index).setDone(false);
    }

    public void setMark(int index) {
        storeTask.get(index).setDone(true);
    }

    public void printElementString(int index) {
        System.out.println(storeTask.get(index).toString());
    }

    public String getElementString(int indext) {
        return storeTask.get(indext).toString();
    }

    public String getElementOutputToFile(int index) {
        return storeTask.get(index).outputToFile();
    }

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
