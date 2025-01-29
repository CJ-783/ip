package echo.tasks;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testDateTimeDeadline() {
        String expectedAnswer = "Jan 28 2025";
        Deadline d = new Deadline("Weekly meeting", "28/1/2025 1500");

        String dayOfWeek = d.getDeadline();
        assertEquals(dayOfWeek, expectedAnswer);
    }

    @Test
    public void testDateTimeDeadline2() {
        String expectedAnswer = "Dec 28 2025";
        Deadline d = new Deadline("Weekly meeting", "28/12/2025 1500");


        String dayOfWeek = d.getDeadline();
        assertEquals(dayOfWeek, expectedAnswer);
    }


}
