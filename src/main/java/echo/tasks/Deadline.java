package echo.tasks;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }



    public String outputToFile() {
        return "D" + " | " + this.getStatusInt() + " | " + this.getDescription() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " ("+ "by: "  + this.by + ")";
    }

}
