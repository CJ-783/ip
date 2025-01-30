package echo.tasks;

import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String from;
    private String by;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        super.setDeadlineDateTime(to);
        this.by = super.getDeadlineDateTime().format(DateTimeFormatter.ofPattern("MMM d yyyy"));

    }

    public String getDeadline() {
        return this.by;
    }

    public String outputToFile() {
        return "E" + " | " + this.getStatusInt() + " | " + this.getDescription() + "| " + this.from + " - " + this.by;
    }

    @Override
    public String toString() {

        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (from: " + this.from + " to: "
                + this.by + ")";
    }
}
