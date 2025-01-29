package echo.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {

    String from;
    String by;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        super.setDeadlineDateTime(to);
        this.by = super.getDeadlineDateTime().format(DateTimeFormatter.ofPattern("MMM d yyyy"));

    }

    /**
     * Retrieves the end time of the event.
     *
     * @return A string representing the event's end time.
     */
    public String getDeadline() {
        return this.by;
    }

    /**
     * Formats the event task for file output.
     *
     * @return A formatted string representing the event task details for file storage.
     */
    public String outputToFile() {
        return "E" + " | " + this.getStatusInt() + " | " + this.getDescription() + "| " + this.from + " - " + this.by;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (from: " + this.from + " to: " + this.by + ")";
    }
}
