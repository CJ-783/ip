import java.util.ArrayList;
import java.util.Scanner;

//for file management
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Echo {

    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    private final static File SAVED_FILE = new File("Data/Echo.txt");

    public static void main(String[] args) {
        new Echo().run();
    }

    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInputInt = ui.readCommand();
                int userOption = parser.getOption(userInputInt);
//
                if (userOption == 0) {
                    isExit = true;
                    System.out.println("BYEEE");
                }


            } catch (Exception e) {

            }
        }
    }

    public Echo() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage("Data/Echo.txt", taskList);
        parser = new Parser(ui, taskList, storage);
        storage.loadData();
    }

}
