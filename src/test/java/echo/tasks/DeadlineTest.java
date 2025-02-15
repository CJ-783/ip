package echo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import echo.exceptions.DateFormatError;

public class DeadlineTest {

    @Test
    public void testBasicInput() {
        String expectedAnswer = "[D][ ] Weekly meeting (by: Jan 28 2025)";
        try {
            Deadline d = new Deadline("Weekly meeting", "28/1/2025 1500");
            assertEquals(expectedAnswer, d.toString());
        } catch (DateFormatError err) {

        }

    }



}
