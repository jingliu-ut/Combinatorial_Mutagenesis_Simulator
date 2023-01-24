import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
    private final String mutagenesis_scheme;


    public GeneratorController(int num, double coverage, String type, int replicateNum, String mutagenesis_scheme) {
        this.useCase = new UseCase(num, mutagenesis_scheme);
        this.coverage = coverage;
        this.type = type;
        this.replicateNum = replicateNum;
        this.mutagenesis_scheme = mutagenesis_scheme;
    }

    public int generate() throws IOException {
        sendOutput("Simulating sampling requirement using " + this.mutagenesis_scheme + "mutagenesis scheme for "
                + this.coverage + " " + "percent coverage (replicate " + this.replicateNum + ")");

        if (type.equals("dna")) {
            this.useCase.writeFile(this.useCase.uniqueDNA, "uniqueDNA_" + this.mutagenesis_scheme);
        } else if (this.type.equals("aa")) {
            this.useCase.writeFile(this.useCase.uniqueAA, "uniqueAA_"  + this.mutagenesis_scheme);
        }

        int simNum = 0;
        double simCoverage = 0;
        if (this.type.equals("dna")) {
            while (simCoverage < this.coverage) {
                simCoverage = useCase.calDNACoverage();
                simNum += 1;
                sendOutput("Current coverage: " + simCoverage + "\nCurrent number of random sequences: " + simNum);
            }
            this.useCase.writeFile(this.useCase.randomDNA, this.mutagenesis_scheme + "_randomDNA_DNA_" + coverage +
                    "_" + replicateNum);
            this.useCase.writeFile(this.useCase.randomAA, this.mutagenesis_scheme + "_randomDNA_AA_"  + coverage +
                    "_" + replicateNum);

        } else if (this.type.equals("aa")) {
            while (simCoverage < this.coverage) {
                simCoverage = useCase.calAACoverage();
                simNum += 1;
            }
            this.useCase.writeFile(this.useCase.randomAA, this.mutagenesis_scheme + "_randomAA_AA_"  + coverage +
                    "_" + replicateNum);
            this.useCase.writeFile(this.useCase.randomDNA, this.mutagenesis_scheme + "_randomAA_DNA_"  + coverage +
                    "_" + replicateNum);
        }

        return simNum;
    }

}
