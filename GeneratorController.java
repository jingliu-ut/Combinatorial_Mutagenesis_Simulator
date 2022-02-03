import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneratorController implements SystemInOut{
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

    private final UseCase useCase;


    public GeneratorController(int num) {
        this.useCase = new UseCase(num);
    }

    public int generate() {
        sendOutput("What percent of coverage do you want to achieve:");
        double coverage = 0;
        int deciNum = 0;
        try {
            String input = getInput();
            coverage = Double.parseDouble(input);
            int decIndex = String.valueOf(coverage).indexOf(".");
            deciNum = String.valueOf(coverage).length() - decIndex - 1;
            this.useCase.setDeciNum(deciNum);
        } catch (IOException e) {
            sendOutput("Something went wrong.");
        }

        int simNum = 0;
        double simCoverage = 0;
        while (simCoverage < coverage) {
            simCoverage = useCase.calCoverage();
            simNum += 1;
            sendOutput(String.valueOf(simNum));
        }
        return simNum;
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
