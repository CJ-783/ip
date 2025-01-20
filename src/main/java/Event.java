public class Event extends Task {

    String from;
    String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {

        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + "(from: " + this.from + "to: " + this.to + ")";
    }
}
