package JAVAOOPUppgift2;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Personuppgift{
    private String namn;
    private String personnummer;
    private boolean isNameExist;

    public String checkName(String line) {
        String[] checkNameFromFile = line.split(", ");
        return checkNameFromFile[1];
    }

    public String checkPersonnummer(String personnummerCheck) {
        String[] checkIDFromFile = personnummerCheck.split(", ");
        return  checkIDFromFile[0];
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public void setPersonnummer(String personnummer) {
        this.personnummer = personnummer;
    }

    public String getNamn() {
        return namn;
    }
    public String getPersonnummer() {
        return personnummer;
    }
    public void setNameExist(boolean isNameExist) {
        this.isNameExist = isNameExist;
    }

    public void CheckLastVisited(String namn, String personnummer, Scanner sc) {
        String line1;
        String line2;
        String result = "";
        LocalDate ld = LocalDate.now();
        String ldwf = ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        while (sc.hasNext()) {
            if (sc.hasNext()) {
                line1 = sc.nextLine();
                if (sc.hasNext()) {
                    line2 = sc.nextLine();
                    if (namn.equals(checkName(line1))) {
                        if (personnummer.equals(checkPersonnummer(line1))) {
                            try (FileWriter fw = new FileWriter("src/JAVAOOPUppgift2/lastvisited", true)) {
                                fw.append(checkPersonnummer(line1)).append(", ").append(checkName(line1).trim())
                                        .append("\n").append(ldwf);
                                result = "Personuppgiften är skriven";
                                break;
                            } catch (IOException e) {
                                System.out.println("Input saknas.");
                                e.printStackTrace();
                            } catch (Exception e) {
                                System.out.println("Ospecifierat felsökning.");
                                e.printStackTrace();
                            }
                        }

                    } else {
                       result = "Har inte uppnått nästa process.";
                    }
                }
            } else {
                result = "Information saknas.";
                break;
            }
        }
        System.out.println(result);
    }
    public boolean nameMatching() {
        return isNameExist;
    }
}
