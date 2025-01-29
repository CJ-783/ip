package echo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a generic task with a description, completion status, and optional deadline.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected LocalDateTime deadlineDateTime;

    /**
     * Parses and sets the deadline date/time for the task from a String.
     *
     * @param date The string representing the deadline in various formats.
     */
    public void setDeadlineDateTime(String date) {
        List<String> formatDateString = Arrays.asList(
                "yyyy/M/dd", "dd/M/yyyy", "yyyy/M/dd HHmm", "dd/M/yyyy HHmm",
                "yyyy-M-dd", "dd-M-yyyy", "yyyy-M-dd HHmm", "dd-M-yyyy HHmm",
                "yyyy/M/d", "d/M/yyyy", "yyyy/M/d HHmm", "d/M/yyyy HHmm");

        for (int i = 0; i < formatDateString.size(); i++) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDateString.get(i));
                this.deadlineDateTime = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
    }

    /**
     * Constructs a task with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the deadline date/time of the task.
     *
     * @return The deadline in LocalDateTime format.
     */
    public LocalDateTime getDeadlineDateTime() {
        return this.deadlineDateTime;
    }

    /**
     * Retrieves the integer status of the task (1 for complete, 0 for not complete).
     *
     * @return A string representing the status.
     */
    public String getStatusInt() {
        return isDone ? "1" : "0";
    }


    /**
     * Formats the task details for file output.
     *
     * @return A formatted string representing the task details for file.
     */
    public String outputToFile() {
        return "T" + " | " + this.getStatusInt() + " | " + this.getDescription();
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return True if the task is completed, else false.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return "X" if completed, else a space.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done True to mark the task as done, else false.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Updates the description of the task.
     *
     * @param description The description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
