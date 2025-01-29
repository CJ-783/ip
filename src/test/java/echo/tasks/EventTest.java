package echo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testBasicInput() {
        String expectedAnswer = "[E][ ] Weekly meeting (from: 28/1/2025 1500 to: Jan 28 2025)";
        Event e = new Event("Weekly meeting", "28/1/2025 1500", "28/1/2025 1700");
        assertEquals(expectedAnswer, e.toString());
    }

}
