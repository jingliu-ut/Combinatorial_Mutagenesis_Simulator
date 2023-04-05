import java.io.*;
import java.nio.file.Path;
import java.util.*;

/**
 * Contains all the methods for the simulation
 */
public class UseCase {
    private final int num; // Number of amino acids
    public String sequence; // Template sequence
    public List<String> randomDNA = new ArrayList<>(); // List contains all randomly generated DNA sequences
    public List<String> randomAA = new ArrayList<>(); // List contains all randomly generated AA sequences
    double coverage; // Coverage
    public List<String> uniqueDNA; // List contains all unique DNA sequences
    public List<String> uniqueAA; // List contains all unique AA sequences
    public final int DNAsize;
    public final int AAsize;
    public final Map<String, String> aaMap = new HashMap<>();
    private final List<String> aaList = Arrays.asList("F", "L", "I", "M", "V", "S", "P", "T", "A", "Y", "*", "H", "Q", "N", "K", "D",
            "E", "C", "W", "R", "G");
    private final String mutagenesis_scheme;

    /**
     * Initiate an UseCase given number of sites to mutate and mutagenesis scheme to use.
     * @param num number of sites
     * @param mutagenesis_scheme mutagenesis scheme
     */
    public UseCase(int num, String mutagenesis_scheme){
        this.num = num;
        this.mutagenesis_scheme = mutagenesis_scheme;
        this.sequence = createTemplate();
        this.uniqueDNA = createUniqueDNA();
        this.DNAsize = createUniqueDNA().size();
        this.uniqueAA = createUniqueAA();
        this.AAsize = createUniqueAA().size();
        this.aaMap.put("TTT", "F");
        this.aaMap.put("TTC", "F");
        this.aaMap.put("TTA", "L");
        this.aaMap.put("TTG", "L");
        this.aaMap.put("CTT", "L");
        this.aaMap.put("CTC", "L");
        this.aaMap.put("CTA", "L");
        this.aaMap.put("CTG", "L");
        this.aaMap.put("ATT", "I");
        this.aaMap.put("ATC", "I");
        this.aaMap.put("ATA", "I");
        this.aaMap.put("ATG", "M");
        this.aaMap.put("GTT", "V");
        this.aaMap.put("GTC", "V");
        this.aaMap.put("GTA", "V");
        this.aaMap.put("GTG", "V");
        this.aaMap.put("TCT", "S");
        this.aaMap.put("TCC", "S");
        this.aaMap.put("TCA", "S");
        this.aaMap.put("TCG", "S");
        this.aaMap.put("CCT", "P");
        this.aaMap.put("CCC", "P");
        this.aaMap.put("CCA", "P");
        this.aaMap.put("CCG", "P");
        this.aaMap.put("ACT", "T");
        this.aaMap.put("ACC", "T");
        this.aaMap.put("ACA", "T");
        this.aaMap.put("ACG", "T");
        this.aaMap.put("GCT", "A");
        this.aaMap.put("GCC", "A");
        this.aaMap.put("GCA", "A");
        this.aaMap.put("GCG", "A");
        this.aaMap.put("TAT", "Y");
        this.aaMap.put("TAC", "Y");
        this.aaMap.put("TAA", "*");
        this.aaMap.put("TAG", "*");
        this.aaMap.put("CAT", "H");
        this.aaMap.put("CAC", "H");
        this.aaMap.put("CAA", "Q");
        this.aaMap.put("CAG", "Q");
        this.aaMap.put("AAT", "N");
        this.aaMap.put("AAC", "N");
        this.aaMap.put("AAA", "K");
        this.aaMap.put("AAG", "K");
        this.aaMap.put("GAT", "D");
        this.aaMap.put("GAC", "D");
        this.aaMap.put("GAA", "E");
        this.aaMap.put("GAG", "E");
        this.aaMap.put("TGT", "C");
        this.aaMap.put("TGC", "C");
        this.aaMap.put("TGA", "*");
        this.aaMap.put("TGG", "W");
        this.aaMap.put("CGT", "R");
        this.aaMap.put("CGC", "R");
        this.aaMap.put("CGA", "R");
        this.aaMap.put("CGG", "R");
        this.aaMap.put("AGT", "S");
        this.aaMap.put("AGC", "S");
        this.aaMap.put("AGA", "R");
        this.aaMap.put("AGG", "R");
        this.aaMap.put("GGT", "G");
        this.aaMap.put("GGC", "G");
        this.aaMap.put("GGA", "G");
        this.aaMap.put("GGG", "G");
    }


    /**
     * @return a string of sequence template
     */
    public String createTemplate() {
        int num = this.num * 3;
        int pos = 1;
        StringBuilder tempSequence = new StringBuilder("");
        while (pos <= num) {
            if (!((pos % 3) == 0)) {
                tempSequence.append("N");
                pos += 1;
            } else {
                if (this.mutagenesis_scheme.equals("NNK")) {
                    tempSequence.append("K");
                } else {
                    tempSequence.append("B");
                }
                pos += 1;
            }
        }
        return tempSequence.toString();
    }


    /**
     * Create a list of all unique amino acid sequences
     * @return a list of all unique amino acid sequences
     */
    public List<String> createUniqueAA() {
        List<String> result;
        List<String> temp = new ArrayList<>();
        String sequence = "_".repeat(this.num);
        for (String aa: aaList) {
            temp.add(aa + sequence.substring(1));
        }
        int index = 1;
        while (index < sequence.length()) {
            List<String> temp2 = new ArrayList<>();
            for (String s : temp) {
                String pre = s.substring(0, index);
                String post = s.substring(index + 1);
                for (String i : aaList) {
                    temp2.add(pre + i + post);
                }
            }
            temp = temp2;
            index += 1;
        }
        result = temp;
        return result;
    }

    /**
     * Create a list of all unique dna sequences based on mutagenesis scheme
     * @return a list of all unique dna sequences
     */
    public List<String> createUniqueDNA() {
        List<String> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> n = new ArrayList<String>();
        n.add("A");
        n.add("T");
        n.add("C");
        n.add("G");
        List<String> k = new ArrayList<String>();
        k.add("T");
        k.add("G");
        List<String> b = new ArrayList<String>();
        b.add("C");
        b.add("G");
        b.add("T");
        String sequence = this.sequence;

        if (String.valueOf(sequence.charAt(0)).equals("N")) {
            for (String i : n) {
                temp.add(i + sequence.substring(1));
            }
        }
        else if (String.valueOf(sequence.charAt(0)).equals("K")) {
            for (String i : k) {
                temp.add(i + sequence.substring(1));
            }
        }
        else {
            for (String i : b) {
                temp.add(i + sequence.substring(1));
            }
        }

        int index = 1;
        while (index < (sequence.length() - 1)) {
            List<String> temp2 = new ArrayList<>();
            if (String.valueOf(sequence.charAt(index)).equals("N")) {
                for (String s : temp) {
                    String pre = s.substring(0, index);
                    String post = s.substring(index + 1);
                    for (String i : n) {
                        temp2.add(pre + i + post);
                    }
                }
            }
            else if (String.valueOf(sequence.charAt(index)).equals("K")) {
                for (String s : temp) {
                    String pre = s.substring(0, index);
                    String post = s.substring(index + 1);
                    for (String i : k) {
                        temp2.add(pre + i + post);
                    }
                }
            }
            else {
                for (String s : temp) {
                    String pre = s.substring(0, index);
                    String post = s.substring(index + 1);
                    for (String i : b) {
                        temp2.add(pre + i + post);
                    }
                }
            }
            temp = temp2;
            index += 1;
        }
        if (String.valueOf(sequence.charAt(sequence.length() - 1)).equals("N")) {
            for (String s: temp) {
                for (String i: n) {
                    result.add(s.substring(0, sequence.length() - 1) + i);
                }
            }
        }
        else if (String.valueOf(sequence.charAt(sequence.length() - 1)).equals("K")) {
            for (String s: temp) {
                for (String i: k) {
                    result.add(s.substring(0, sequence.length() - 1) + i);
                }
            }
        }
        else {
            for (String s: temp) {
                for (String i: b) {
                    result.add(s.substring(0, sequence.length() - 1) + i);
                }
            }
        }
        return result;
    }

    /**
     * Create one random sequence and add to random list
     * @return One random sequence
     */
    public String oneRandom() {
        List<String> n = new ArrayList<String>();
        n.add("A");
        n.add("T");
        n.add("C");
        n.add("G");
        List<String> k = new ArrayList<String>();
        k.add("T");
        k.add("G");
        List<String> b = new ArrayList<String>();
        b.add("C");
        b.add("G");
        b.add("T");

        int index = 0;
        int max = this.sequence.length();
        StringBuilder sequence = new StringBuilder();
        while (index < max) {
            if (stringAt(this.sequence, index).equals("N")) {
                sequence.append(random(n));
                index += 1;
            }
            else if (stringAt(this.sequence, index).equals("K")) {
                sequence.append(random(k));
                index += 1;
            }
            else {
                sequence.append(random(b));
                index += 1;
            }
        }
        this.randomDNA.add(String.valueOf(sequence));
        this.randomAA.add(dna2aa(String.valueOf(sequence)));
        return String.valueOf(sequence);
    }

    /**
     * helper method to find char at specified index of a string
     * @param string string to be searched in
     * @param index index
     * @return char at index in a string
     */
    public String stringAt(String string, int index) {
        return String.valueOf(string.charAt(index));
    }

    /**
     * helper method to generate a random string from a list
     * @param list a list contains all the options
     * @return a string contains a single char randomly draw from the list
     */
    public String random(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    /**
     * helper method to write output file int .txt
     * @param sequences list of sequences to be written in the file
     * @param name name of the file
     * @throws IOException
     */
    public void writeFile(List<String> sequences, String name) throws IOException {
        File file = new File("Outputs", name + ".txt");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (String sequence: sequences) {
            bw.write(sequence);
            bw.newLine();
        }
        bw.write(String.valueOf(sequences.size()));
        bw.close();
    }

    /**
     * calculate current dna coverage if dna simulation is chosen
     * @return a double reflects the current dna coverage
     */
    public double calDNACoverage() {
        String seq = oneRandom();
        if (this.uniqueDNA.size() != 0) {
            if (this.uniqueDNA.contains(seq)) {
                this.uniqueDNA.remove(seq);
                int num = this.DNAsize - this.uniqueDNA.size();
                this.coverage = ((double) num / this.DNAsize) * 100;
            }
        }
        return this.coverage;
    }

    /**
     * calculate current aa coverage if aa simulation is chosen
     * @return a double reflects the current aa coverage
     */
    public double calAACoverage() {
        String seq = oneRandom();
        String aa = dna2aa(seq);
        if (this.uniqueAA.size() != 0) {
            if (this.uniqueAA.contains(aa)) {
                this.uniqueAA.remove(aa);
                int num = this.AAsize - this.uniqueAA.size();
                this.coverage = ((double) num / this.AAsize) * 100;
            }
        }
        return this.coverage;
    }

    /**
     * helper method to convert dna into aa sequence
     * @param dna
     * @return
     */
    public String dna2aa(String dna) {
        StringBuilder seq = new StringBuilder("");
        int index = 0;
        while (index < dna.length()) {
            if ((index % 3) == 0) {
                String temp = dna.substring(index, index + 3);
                seq.append(this.aaMap.get(temp));
            }
            index += 1;
        }
        return String.valueOf(seq);
    }


}
