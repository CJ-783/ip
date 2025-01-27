import java.util.ArrayList;

public class TaskList {

    public int totalTask = 0;

    ArrayList<Task> storeTask = new ArrayList<Task>();

    TaskList() {

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
