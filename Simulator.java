import java.io.IOException;


public class Simulator {
    public static void main(String[] args) throws IOException {
        int num = Integer.parseInt(args[0]);
        String type = args[1]; //"dna" or "aa"
        String mutagenesis_scheme = args[2]; //"NNK" or "NNB"
        double coverage = Integer.parseInt(args[3]);
        int replicateNum = Integer.parseInt(args[4]);

        GeneratorController sim = new GeneratorController(num, coverage, type, replicateNum, mutagenesis_scheme);
        sim.generate();
    }


}
