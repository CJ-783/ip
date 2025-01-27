import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private File filePath;

    private TaskList taskList;

    Storage(String filePath, TaskList taskList) {
        this.filePath = new File(filePath);
        this.taskList = taskList;
    }

    public void loadData()  {

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

        }
    }

    public void saveData(String option) {
        String text = generateOutput(option);
        saveToFile(option, text);
    }

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


    //Output to be generated for saving into a file
    private String generateOutput(String option) {
        StringBuilder text = new StringBuilder();

        if (option.equals("append")) {
            text.append(taskList.getElementOutputToFile(taskList.getTotalTask() - 1));
            text.append("\n");
            return text.toString();
        }
        return taskList.getAllOutputToFile() ;
    }
}
