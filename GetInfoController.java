import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetInfoController implements SystemInOut{

    @Override
    public String getInput() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    @Override
    public void sendOutput(String output) {
        System.out.println(output);
    }

    public Integer getInfo() {
        int num = 0;

        sendOutput("How many amino acids sites would you like to simulate?");
        try {
            String input = getInput();
            num = Integer.valueOf(input);
        } catch (IOException e){
            sendOutput("Something went wrong!");
        }

        return num;
    }

    public void writeUniqueSequence(){}
}
