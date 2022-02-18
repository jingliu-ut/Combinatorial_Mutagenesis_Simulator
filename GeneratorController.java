import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private final double coverage;
    private final String type;
    private final int replicateNum;


    public GeneratorController(int num, double coverage, String type, int replicateNum) {
        this.useCase = new UseCase(num);
        this.coverage = coverage;
        this.type = type;
        this.replicateNum = replicateNum;
    }

    public int generate() throws IOException {
        sendOutput("Calculating simulator number for " + this.coverage + " percent coverage......");
        if (type.equals("dna")) {
            this.useCase.writeFile(this.useCase.uniqueDNA, "uniqueDNA");
        } else if (this.type.equals("aa")) {
            this.useCase.writeFile(this.useCase.uniqueAA, "uniqueAA");
        }
        int deciNum;
        int decIndex = String.valueOf(this.coverage).indexOf(".");
        deciNum = String.valueOf(this.coverage).length() - decIndex - 1;
        this.useCase.setDeciNum(deciNum);


        int simNum = 0;
        double simCoverage = 0;
        if (this.type.equals("dna")) {
            while (simCoverage < this.coverage) {
                simCoverage = useCase.calDNACoverage();
                simNum += 1;
            }
            this.useCase.writeFile(this.useCase.randomDNA, "randomDNA_" + coverage + "_" + replicateNum);
        } else if (this.type.equals("aa")) {
            while (simCoverage < this.coverage) {
                simCoverage = useCase.calAACoverage();
                simNum += 1;
            }
            this.useCase.writeFile(this.useCase.randomAA, "randomAA_"  + coverage + "_" + replicateNum);
        }

        return simNum;
    }

}
