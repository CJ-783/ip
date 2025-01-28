package echo.tasks;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testBasicInput() {
        String expectedAnswer = "[T][ ] Finish up CS2103 Weekly Quiz.";
        Todo t = new Todo("Finish up CS2103 Weekly Quiz.");
        assertEquals(expectedAnswer,t.toString());
    }


}
