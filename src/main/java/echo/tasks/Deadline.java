package echo.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline task with a description and a deadline.
     *
     * @param description The task description.
     * @param by          The deadline of the task in string format.
     */

    public Deadline(String description, String by) {
        super(description);
        super.setDeadlineDateTime(by);
        this.by = super.getDeadlineDateTime().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return A string representing the deadline.
     */
    public String getDeadline() {
        return this.by;
    }

    /**
     * Formats the task for file output.
     *
     * @return A formatted string for file storage.
     */
    public String outputToFile() {
        return "D" + " | " + this.getStatusInt() + " | " + this.getDescription() + " | " + this.by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (" + "by: " + this.by + ")";
    }

}
