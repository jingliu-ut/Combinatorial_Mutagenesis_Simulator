import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UseCase {
    private final int num;
    public String sequence;
    public int deciNum;
    public List<String> random = new ArrayList<>();
    double coverage;
    public List<String> unique;
    public final int size;

    public UseCase(int num){

        this.num = num * 3;
        this.sequence = createTemplate();
        this.unique = createUnique();
        this.size = createUnique().size();
    }

    public void setDeciNum(int deciNum) {
        this.deciNum = deciNum;
    }

    public String createTemplate() {
        int pos = 1;
        StringBuilder tempSequence = new StringBuilder("");
        while (pos <= this.num) {
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


    public List<String> createUnique() {
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

//    public List<String> createRandom(int simNum) {
//        List<String> result = new ArrayList<>();
//        List<String> n = new ArrayList<String>();
//        n.add("A");
//        n.add("T");
//        n.add("C");
//        n.add("G");
//        List<String> k = new ArrayList<String>();
//        k.add("T");
//        k.add("G");
//
//        for (int i = 0; i < simNum; i++) {
//            int index = 0;
//            int max = this.sequence.length();
//            StringBuilder sequence = new StringBuilder();
//            while (index < max) {
//                if (stringAt(this.sequence, index).equals("N")) {
//                    sequence.append(random(n));
//                    index += 1;
//                }
//                else if (stringAt(this.sequence, index).equals("K")) {
//                    sequence.append(random(k));
//                    index += 1;
//                }
//            }
//            result.add(String.valueOf(sequence));
//        }
//
//        return result;
//    }

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
        this.random.add(String.valueOf(sequence));
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

    public double calCoverage() {
        int max = this.size;
        String seq = oneRandom();
        if (this.unique.size() != 0) {
            if (this.unique.contains(seq)) {
                this.unique.remove(seq);
                int num = this.size - this.unique.size();
                this.coverage = round(( (double) num / max) * 100, this.deciNum);
            }
        }
        return this.coverage;
    }

//    public double calCoverage() {
//        List<String> uniSeq = createUnique();
//        List<String> temp = new ArrayList<>();
//        int count = 0;
//        int total = uniSeq.size();
//        for (String seq: random) {
//            if (uniSeq.contains(seq)) {
//                if (!temp.contains(seq)) {
//                    temp.add(seq);
//                    count += 1;
//                }
//            }
//        }
//        return ( (double) count / total) * 100;
//    }


//    public double getCoverage() {
//        return calCoverage(random);
//    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
