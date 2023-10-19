package JAVAOOPUppgift2;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GymMainTest {

    TidSortering tidSortering = new TidSortering();
    Personuppgift personuppgift = new Personuppgift();
    private String date2 = "2021-11-17";
    Path kundPost = Paths.get("src/JAVAOOPUppgift2/customers.txt");

    GymMainTest() throws IOException {
    }

    @Test
    public final void SortTimeYearTest() {
        String year = "2023-9-15";
        int actualYear = tidSortering.SortTimeYear(year);
        System.out.println(actualYear);
        assertTrue(actualYear == 2023);
        assertFalse(actualYear == 2021);
    }
    @Test
    public final void calculateTest() {
        /*test date2 med 2022-10-15 står det true(som förväntas)
        och med 2022-10-17 står det false(som förväntas).*/
        String date2 = "2021-11-17";
        String date1 = "2023-10-18";

        int actualYear1 = tidSortering.SortTimeYear(date1);
        int actualMonth1 = tidSortering.SortTimeMonth(date1);
        int actualDay1 = tidSortering.SortTimeDay(date1);
        int actualYear2 = tidSortering.SortTimeYear(date2);
        int actualMonth2 = tidSortering.SortTimeMonth(date2);
        int actualDay2 = tidSortering.SortTimeDay(date2);
        boolean isMoreOneYear;

        // testa om årtet 1 och året 2 har bara 1 år skillnad.
        if (actualYear1 > actualYear2 && (actualYear1 - actualYear2) == 1) {
            if (actualMonth1 >= actualMonth2) {
                if (actualDay1 >= actualDay2) {
                    isMoreOneYear = true;
                } else {
                    isMoreOneYear = false;
                }
            } else {
                isMoreOneYear = false;
            }
            // testa om året mellan båda år är mer skillnad än ett år.
        } else if (actualYear1 > actualYear2 && (actualYear1 - actualYear2) > 1) {
            isMoreOneYear = true;
            // hamnar else menar det att båda åren har mindre än 1 år skillnad.
        } else {
            isMoreOneYear = false;
        }
        System.out.println(isMoreOneYear);
        assertTrue(isMoreOneYear);
    }
    @Test
    public final void testanotherCalculator() {
        int actualYear2 = tidSortering.SortTimeYear(date2);
        int actualMonth2 = tidSortering.SortTimeMonth(date2);
        int actualDay2 = tidSortering.SortTimeDay(date2);
        boolean overAYearTest = tidSortering.compareDate(actualYear2, actualMonth2, actualDay2);
        System.out.println(overAYearTest);
        assertTrue(overAYearTest);
    }
    @Test
    public final void nameMatchingTest() {
        String Aromes = "7703021234, Alhambra Aromes";
        String nameChecking1 = personuppgift.checkName(Aromes);
        String nameChecking2 = "";
        String line1 = "";
        String line2 = "";
        boolean isNameExist = false;
        try (Scanner sc = new Scanner(Paths.get("src/JAVAOOPUppgift2/customers.txt"))){
            while (sc.hasNext()) {
                if (sc.hasNext()) {
                    line1 = sc.nextLine();
                    nameChecking2 = personuppgift.checkName(line1);
                    if (sc.hasNext()) {
                        line2 = sc.nextLine();
                        if (nameChecking1.equals(nameChecking2)) {
                            isNameExist = true;
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Finns inte input.");
            e.printStackTrace();
        }
        assertTrue(isNameExist);
    }
    @Test
    public final void idCheckingTest() {
        String elmer = "7605021234, Elmer Ekorrsson";
        String checkElmer = personuppgift.checkPersonnummer(elmer);
        assertTrue(checkElmer.equals("7605021234"));
    }
    @Test
    public final void Matching3() throws IOException {
        Scanner sc = new Scanner(kundPost);
        String nameChecking2 = "";
        String line1 = "";
        String line2 = "";
        while (sc.hasNext()) {
            if (sc.hasNext()) {
                line1 = sc.nextLine();
                System.out.println(line1);
                nameChecking2 = personuppgift.checkName(line1);
            }
            if (sc.hasNext()) {
                line2 = sc.nextLine();
                System.out.println(line2);
            }

        }
        System.out.println("Name and ID: "+line1 + "\nTime: " + line2);

    }
    @Test
    public final void DateTimeFunctionTest() {
        LocalDate ld = LocalDate.now();
        String ldToString = ld.toString();
        System.out.println(ldToString);
        assertTrue(ldToString.equals("2023-10-19"));
    }

    @Test
    public final void DurationAndPeriodTest() {
        Instant då = Instant.parse("2023-10-16T08:20:16.00Z");
        Instant nu = Instant.now();
        System.out.println(Duration.between(då, nu).toHours());
        System.out.println(nu);
        System.out.println(då);
    }
    @Test
    public final void TidSorteringTestUtanRegEx() {
        // om man inte vill ha RegEx för uppgiften.
        String year = "";
        String month = "";
        String day = "";
        boolean fullfil = false;

        for (int i = 0; i < date2.length() ; i++) {
            if (!fullfil) {
                if (date2.charAt(i) == '-') {
                    fullfil = true;
                }  else {
                    year = date2.substring(0, i + 1);
                }
            } else if (fullfil) {
                if (date2.charAt(i) != '-') {
                    month += date2.charAt(i);
                } else {
                    day = date2.substring(i + 1);
                    break;
                }

            }
        }

        System.out.println(year+"/"+month+"/"+day);
        assertTrue(year.equals("2021"));
        assertTrue(month.equals("11"));
        assertTrue(day.equals("17"));
        assertFalse(day.equals("16"));
    }
    @Test
    public final void CheckMemberTest() {
        String Greger = "9902149834, Jicky Juul";
        personuppgift.setNamn(personuppgift.checkName(Greger));
        personuppgift.setPersonnummer(personuppgift.checkPersonnummer(Greger));
        String GregarName = personuppgift.getNamn();
        String GregarID = personuppgift.getPersonnummer();
        GymMain gymMain = new GymMain();
        gymMain.CheckMember(GregarName, GregarID);
    }
}