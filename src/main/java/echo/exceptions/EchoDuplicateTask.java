package echo.exceptions;

public class EchoDuplicateTask extends Exception{

    public EchoDuplicateTask() {

    }

    public EchoDuplicateTask(String msg) {
        super(msg);
    }
}
