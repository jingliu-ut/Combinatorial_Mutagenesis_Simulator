import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Simulator {
    public static void main(String[] args) throws IOException {
        GetInfoController info = new GetInfoController();
        int num = info.getInfo();
        GeneratorController sim = new GeneratorController(num);
        sim.generate();
//        UseCase u = new UseCase(num);
//        List l = u.createRandom(u.uniqueSize);
//        System.out.println(l);
//        System.out.println(u.uniqueSize);


    }


}
