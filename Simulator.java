import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Simulator {
    public static void main(String[] args) throws IOException {
        int num = 1;
        double coverage = 80;
        String type = "aa"; //"dna" or "aa"
        int replicateNum = 1;
        GeneratorController sim = new GeneratorController(num, coverage, type, replicateNum);
        int simNum = sim.generate();
        System.out.println(simNum);
    }


}
