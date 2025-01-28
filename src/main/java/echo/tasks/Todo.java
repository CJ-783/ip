package echo.tasks;

public class Todo extends Task{


    public Todo(String description) {
        super(description);
    }

    public String outputToFile() {
        return "T" + " | " + this.getStatusInt() + " | " + this.getDescription();
    }
    @Override
    public String toString() {

        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
