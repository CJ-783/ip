package echo.tasks;

<<<<<<< HEAD
public class Todo extends Task {
=======
/**
 * Represents a task (to-do) without any deadline or time constraint.
 */
public class Todo extends Task{
>>>>>>> branch-A-JavaDoc

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the task details for file output.
     *
     * @return A formatted String to be output into the file.
     */
    public String outputToFile() {
        return "T" + " | " + this.getStatusInt() + " | " + this.getDescription();
    }

    /**
     * Returns a string representation of the To-do task.
     *
     * @return A string representing the To-do task.
     */
    @Override
    public String toString() {

        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
