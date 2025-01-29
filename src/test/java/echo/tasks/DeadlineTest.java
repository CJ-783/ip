package echo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testBasicInput() {
        String expectedAnswer = "[D][ ] Weekly meeting (by: 28/1/2025 1500)";
        Deadline d = new Deadline("Weekly meeting", "28/1/2025 1500");
        assertEquals(expectedAnswer, d.toString());
    }



}
