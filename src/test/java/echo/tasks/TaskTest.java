package echo.tasks;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import echo.exceptions.DateFormatError;

public class TaskTest {
    @Test
    public void testDateTimeDeadline() {
        String expectedAnswer = "Jan 28 2025";
        try {
            Deadline d = new Deadline("Weekly meeting", "28/1/2025 1500");
            String dayOfWeek = d.getDeadline();
            assertEquals(dayOfWeek, expectedAnswer);
        } catch (DateFormatError err) {

        }


    }

    @Test
    public void testDateTimeDeadline2() {
        String expectedAnswer = "Dec 28 2025";
        try {
            Deadline d = new Deadline("Weekly meeting", "28/12/2025 1500");
            String dayOfWeek = d.getDeadline();
            assertEquals(dayOfWeek, expectedAnswer);
        } catch (DateFormatError err) {

        }



    }


}
