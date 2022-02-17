import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class UseCase {
    private final int num;
    public String sequence;
    public int deciNum;
    public List<String> randomDNA = new ArrayList<>();
    public List<String> randomAA = new ArrayList<>();
    double coverage;
    public List<String> uniqueDNA;
    public List<String> uniqueAA;
    public final int DNAsize;
    public final int AAsize;
    public final Map<String, String> aa = new HashMap<>();
    private final List<String> aaList = Arrays.asList("F", "L", "I", "M", "V", "S", "P", "T", "A", "Y", "*", "H", "Q", "N", "K", "D",
            "E", "C", "W", "R", "G");

    public UseCase(int num){
        this.num = num;
        this.sequence = createTemplate();
        this.uniqueDNA = createUniqueDNA();
        this.DNAsize = createUniqueDNA().size();
        this.uniqueAA = createUniqueAA();
        this.AAsize = createUniqueAA().size();
        this.aa.put("TTT", "F");
        this.aa.put("TTC", "F");
        this.aa.put("TTA", "L");
        this.aa.put("TTG", "L");
        this.aa.put("CTT", "L");
        this.aa.put("CTC", "L");
        this.aa.put("CTA", "L");
        this.aa.put("CTG", "L");
        this.aa.put("ATT", "I");
        this.aa.put("ATC", "I");
        this.aa.put("ATA", "I");
        this.aa.put("ATG", "M");
        this.aa.put("GTT", "V");
        this.aa.put("GTC", "V");
        this.aa.put("GTA", "V");
        this.aa.put("GTG", "V");
        this.aa.put("TCT", "S");
        this.aa.put("TCC", "S");
        this.aa.put("TCA", "S");
        this.aa.put("TCG", "S");
        this.aa.put("CCT", "P");
        this.aa.put("CCC", "P");
        this.aa.put("CCA", "P");
        this.aa.put("CCG", "P");
        this.aa.put("ACT", "T");
        this.aa.put("ACC", "T");
        this.aa.put("ACA", "T");
        this.aa.put("ACG", "T");
        this.aa.put("GCT", "A");
        this.aa.put("GCC", "A");
        this.aa.put("GCA", "A");
        this.aa.put("GCG", "A");
        this.aa.put("TAT", "Y");
        this.aa.put("TAC", "Y");
        this.aa.put("TAA", "*");
        this.aa.put("TAG", "*");
        this.aa.put("CAT", "H");
        this.aa.put("CAC", "H");
        this.aa.put("CAA", "Q");
        this.aa.put("CAG", "Q");
        this.aa.put("AAT", "N");
        this.aa.put("AAC", "N");
        this.aa.put("AAA", "K");
        this.aa.put("AAG", "K");
        this.aa.put("GAT", "D");
        this.aa.put("GAC", "D");
        this.aa.put("GAA", "E");
        this.aa.put("GAG", "E");
        this.aa.put("TGT", "C");
        this.aa.put("TGC", "C");
        this.aa.put("TGA", "*");
        this.aa.put("TGG", "W");
        this.aa.put("CGT", "R");
        this.aa.put("CGC", "R");
        this.aa.put("CGA", "R");
        this.aa.put("CGG", "R");
        this.aa.put("AGT", "S");
        this.aa.put("AGC", "S");
        this.aa.put("AGA", "R");
        this.aa.put("AGG", "R");
        this.aa.put("GGT", "G");
        this.aa.put("GGC", "G");
        this.aa.put("GGA", "G");
        this.aa.put("GGG", "G");
    }

    public void setDeciNum(int deciNum) {
        this.deciNum = deciNum;
    }

    public String createTemplate() {
        int num = this.num * 3;
        int pos = 1;
        StringBuilder tempSequence = new StringBuilder("");
        while (pos <= num) {
            if (!((pos % 3) == 0)) {
                tempSequence.append("N");
                pos += 1;
            } else {
                tempSequence.append("K");
                pos += 1;
            }
        }
        return tempSequence.toString();
    }

    public List<String> createUniqueAA() {
        List<String> result = new ArrayList<>();
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
        String sequence = this.sequence;

        if (String.valueOf(sequence.charAt(0)).equals("N")) {
            for (String i : n) {
                temp.add(i + sequence.substring(1));
            }
        }
        else {
            for (String i : k) {
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
            else {
                for (String s : temp) {
                    String pre = s.substring(0, index);
                    String post = s.substring(index + 1);
                    for (String i : k) {
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
        else {
            for (String s: temp) {
                for (String i: k) {
                    result.add(s.substring(0, sequence.length() - 1) + i);
                }
            }
        }
        return result;
    }

    public String oneRandom() {
        List<String> n = new ArrayList<String>();
        n.add("A");
        n.add("T");
        n.add("C");
        n.add("G");
        List<String> k = new ArrayList<String>();
        k.add("T");
        k.add("G");

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
        }
        this.randomDNA.add(String.valueOf(sequence));
        this.randomAA.add(dna2aa(String.valueOf(sequence)));
        return String.valueOf(sequence);
    }

    public String stringAt(String string, int index) {
        return String.valueOf(string.charAt(index));
    }

    public String random(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public void writeFile(List<String> sequences, String name) throws IOException {
        File file = new File(name + ".txt");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (String sequence: sequences) {
            bw.write(sequence);
            bw.newLine();
        }
        bw.close();
    }

    public double calDNACoverage() {
        String seq = oneRandom();
        if (this.uniqueDNA.size() != 0) {
            if (this.uniqueDNA.contains(seq)) {
                this.uniqueDNA.remove(seq);
                int num = this.DNAsize - this.uniqueDNA.size();
                this.coverage = round(( (double) num / this.DNAsize) * 100, this.deciNum);
            }
        }
        return this.coverage;
    }

    public double calAACoverage() {
        String seq = oneRandom();
        String aa = dna2aa(seq);
        if (this.uniqueAA.size() != 0) {
            if (this.uniqueAA.contains(aa)) {
                this.uniqueAA.remove(aa);
                int num = this.AAsize - this.uniqueAA.size();
                this.coverage = round(( (double) num / this.AAsize) * 100, this.deciNum);
            }
        }
        return this.coverage;
    }

    public String dna2aa(String dna) {
        return this.aa.get(dna);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
