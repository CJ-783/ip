package echo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class Task {

    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    public void setDateTime(String date) {
        List<String> formatDateString = Arrays.asList(
                "yyyy/M/dd", "dd/M/yyyy", "yyyy/M/dd HHmm", "dd/M/yyyy HHmm",
                "yyyy-M-dd", "dd-M-yyyy", "yyyy-M-dd HHmm", "dd-M-yyyy HHmm",
                "yyyy/M/d", "d/M/yyyy", "yyyy/M/d HHmm", "d/M/yyyy HHmm");

        for (int i = 0; i < formatDateString.size(); i++) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDateString.get(i));
                this.dateTime = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getStatusInt() {
        return isDone ? "1" : "0";
    }

    public String outputToFile() {
        return "T" + " | " + this.getStatusInt() + " | " + this.getDescription();
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
