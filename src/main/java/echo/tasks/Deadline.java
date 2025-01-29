package echo.tasks;

import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        super.setDeadlineDateTime(by);
        this.by = super.getDeadlineDateTime().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getDeadline() {
        return this.by;
    }

    public String outputToFile() {
        return "D" + " | " + this.getStatusInt() + " | " + this.getDescription() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " ("+ "by: "  + this.by + ")";
    }

}
