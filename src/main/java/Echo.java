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
        parser = new Parser(ui, taskList);

        //TODO: add this into the Ui.java
//        Scanner reader = new Scanner(System.in);
//        String userInput = reader.nextLine();

//        ArrayList<Task> storeTask = new ArrayList<Task>();

//        while (!userInput.equals("bye")) {
////            if (userInput.equals("list")) {
////                for (int i = 0; i < storeTask.size(); i++) {
////                    System.out.println(i + 1 + "." + storeTask.get(i).toString());
////                }
////            }
////            //Check if user input unmark
////            else if (userInput.contains("unmark")) {
////                updateMarkList(false, userInput, storeTask);
////
////            } else if (userInput.contains("mark")) {
////                updateMarkList(true, userInput, storeTask);
//
//            } else {
//                //get the option
////                String option = userInput.split(" ")[0];
//                try {
//                    //check if option is valid
////                    validOption(option);
//
//                    //update the list
//                    updateList(option, storeTask, userInput);
////                } catch (EchoIncorrectOption err) {
////                    System.out.println("Whoops, this option doesn't exist");
////
////                } catch (ArrayIndexOutOfBoundsException err) {
////                    System.out.println("Whoops, the description cannot be empty!!");
////                }
//            }
//            userInput = reader.nextLine();
//        }
//        System.out.println("See you next time");
    }



//    private static void updateMarkList(boolean markStatus, String inputIndex, ArrayList<Task> storeTask) {
////        String element = inputIndex.split(" ")[1];
////        int elementIndex = Integer.parseInt(element) - 1;
////        if (markStatus) {
////            storeTask.get(elementIndex).setDone(true);
////            System.out.println("Nice! Marked!");
////        } else {
////            storeTask.get(elementIndex).setDone(false);
////            System.out.println("OK, I've marked this task as not done yet:");
////        }
//
//        //Generate the output in the right format to be stored into a file
//        String output = generateOutput("replace", storeTask);
//        //store the output into a file
//        saveToFile("replace", output);
////        System.out.println(storeTask.get(elementIndex).toString());
//    }
//
//    private static void validOption(String userInput) throws EchoIncorrectOption {
//        String[] correctOptions = {"delete", "todo", "deadline", "event"};
//
//        for (String correctOption : correctOptions) {
//            if (userInput.equals(correctOption)) {
//                return;
//            }
//        }
//        throw new EchoIncorrectOption();
//    }
//
//    //Method to be used to save text into a file based on the option given
//    private static void saveToFile(String option, String text) {
//        String fullPath = SAVED_FILE.getAbsolutePath();
//        try {
//            if (option.equals("replace")) {
//                //delete last line from textfile
//                FileWriter fw = new FileWriter(fullPath);
//                fw.write(text);
//                fw.close();
//            } else if (option.equals("append")) {
//                //append text to
//                FileWriter fw = new FileWriter(fullPath, true);
//                fw.write(text);
//                fw.close();
//            }
//
//        } catch (IOException e) {
//            System.out.println("An error has occured. " + e.toString());
//        }
//    }
//
//
//    //Output to be generated for saving into a file
//    private static String generateOutput(String option, ArrayList<Task> storeTask) {
//        StringBuilder text = new StringBuilder();
//
//        if (option.equals("append")) {
//            int lastElementInt = storeTask.size() - 1;
//            text.append(storeTask.get(lastElementInt).outputToFile());
//            text.append("\n");
//
//        } else if (option.equals("replace")) {
//            for (Task task : storeTask) {
//                text.append(task.outputToFile());
//                text.append("\n");
//            }
//
//        }
//        return text.toString();
//    }
//
//
//    private static void updateList(String type, ArrayList<Task> storeTask, String userInput) {
//        if (type.equals("delete")) {
////            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
//
//            System.out.println("Noted. I've removed this task:\n" + storeTask.get(index).toString());
////            storeTask.remove(index);
//
//            //Generate the output in the right format
//            String output = generateOutput("replace", storeTask);
//            //store the output into a file
//            saveToFile("replace", output);
//        } else {
////            userInput = userInput.split(" ", 2)[1];
////            String description = userInput.split("/")[0];
//
////            switch (type) {
////                case "todo" -> storeTask.add(new Todo(userInput));
////                case "deadline" -> {
////                    String to = userInput.split("/by ")[1];
////
////                    storeTask.add(new Deadline(description, to));
////                    storeTask.get(storeTask.size() -1).setDateTime(to);
////
////                }
////                case "event" -> {
////                    String from = userInput.split("/")[1].replace("from ", "");
////                    String to = userInput.split("/to ")[1];
////
////                    storeTask.add(new Event(description, from, to));
////                    storeTask.get(storeTask.size() -1).setDateTime(to);
////                }
////            }
//            String output = storeTask.get(storeTask.size() - 1).toString();
//            System.out.println("Got it! I've added this task: \n" + output);
//
//            output = generateOutput("append", storeTask);
//            saveToFile("append", output);
//        }
//        System.out.println("Now you have " + storeTask.size() + " in the list.");
//
//    }
}
