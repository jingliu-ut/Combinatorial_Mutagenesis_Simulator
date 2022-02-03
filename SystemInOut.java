import java.io.IOException;

public interface SystemInOut {
    String getInput() throws IOException;

    void sendOutput(String output);

}
