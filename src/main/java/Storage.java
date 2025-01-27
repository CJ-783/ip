import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private File filePath;

    private TaskList taskList;

    Storage(String filePath, TaskList taskList) {
        this.filePath = new File(filePath);
        this.taskList = taskList;
    }

    public void loadData() {
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
