import java.io.IOException;


public class Simulator {
    public static void main(String[] args) throws IOException {
        int num = 2;
        double coverage = 1;
        String type = "dna"; //"dna" or "aa"
        int replicateNum = 1;
        String mutagenesis_scheme = "NNB"; //"NNK" or "NNB"
        GeneratorController sim = new GeneratorController(num, coverage, type, replicateNum, mutagenesis_scheme);
        sim.generate();
    }


}
