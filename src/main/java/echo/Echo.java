package echo;

import echo.parser.Parser;
import echo.storage.Storage;
import echo.tasklist.TaskList;
import echo.ui.Ui;


/**
 * The Echo class serves as the main entry point for the Echo Chat bot
 * @author Cheng Jing
 */

public class Echo {

    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    /**
     * Initializes the ui, tasklist, storage, and parser object.
     */
    public Echo() {
        ui = new Ui();
        taskList = new TaskList();

        storage = new Storage("Data/Echo.txt", taskList);

        parser = new Parser(ui, taskList, storage);
        storage.loadData();
    }

    public static void main(String[] args) {
        new Echo().run();
    }

    /**
     * The entrypoint for running the program.
     */
    public String getResponse(String userInput) {
        String returnValue = parser.getOption(userInput);
        return returnValue;
    }
    public void run() {
    }

}
