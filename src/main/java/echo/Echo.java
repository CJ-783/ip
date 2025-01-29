package echo;

//for file management
import echo.parser.Parser;
import echo.storage.Storage;
import echo.tasklist.TaskList;
import echo.ui.Ui;

import java.io.File;

public class Echo {

    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    private final static File SAVED_FILE = new File("Data/echo.Echo.txt");

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

                if (userOption == 0) {
                    isExit = true;
                }

            } catch (Exception e) {

            }
        }
    }

    public Echo() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage("Data/echo.Echo.txt", taskList);
        parser = new Parser(ui, taskList, storage);
        storage.loadData();
    }

}
