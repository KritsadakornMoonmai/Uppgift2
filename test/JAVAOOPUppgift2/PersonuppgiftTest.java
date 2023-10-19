package JAVAOOPUppgift2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PersonuppgiftTest {


    Path kundPost = Paths.get("src/JAVAOOPUppgift2/customers.txt");



    @Test
    public final void SetnameExistTest() {
        Personuppgift pu = new Personuppgift();
        pu.setNamn("Krits");
        System.out.println(pu.getNamn());
        pu.setNameExist(true);
        System.out.println(pu.nameMatching());
    }
    @Test
    public final void MemberCheck2Test() throws IOException {
        Personuppgift pu = new Personuppgift();
        GymMain gm = new GymMain();
        String Greger = "7512166544, Greger Ganache";
        try (Scanner sc = new Scanner(kundPost)) {
            pu.setNamn(pu.checkName(Greger));
            pu.setPersonnummer(pu.checkPersonnummer(Greger));
            gm.MemberCheck2(sc, null, null);
        }
    }
    @Test
    public final void test2() {
        Personuppgift pu = new Personuppgift();
        String Greger = "7512166544, Greger Ganache";
        try (Scanner sc = new Scanner(Paths.get("src/JAVAOOPUppgift2/onmember"))){
            pu.setNamn(pu.checkName(Greger));
            pu.setPersonnummer(pu.checkPersonnummer(Greger));
            pu.CheckLastVisited(pu.getNamn(), pu.getPersonnummer(), sc);
        } catch (IOException e) {
            System.out.println("Input saknas.");
            e.printStackTrace();
        }
    }
}