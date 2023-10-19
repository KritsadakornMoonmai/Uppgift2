package JAVAOOPUppgift2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GymMain {
    Path kundPost = Paths.get("src/JAVAOOPUppgift2/customers.txt");
    Path iMedlem = Paths.get("src/JAVAOOPUppgift2/onmember");
    Path väntaPåAvgift = Paths.get("src/JAVAOOPUppgift2/waitingforannualfee");
    Path Besök = Paths.get("src/JAVAOOPUppgift2/lastvisited");
    TidSortering tidSortering = new TidSortering();
    Personuppgift personuppgift = new Personuppgift();
    private String line1;
    private String line2;
    public String Status(boolean overYear) {
        if (!overYear) {
            return "Status: På medlem";
        } else {
            return "Status: Väntar på årsavgift";
        }
    }
    public void MemberCheck2(Scanner sc, PrintWriter wIMedlem, PrintWriter vAvgift) throws NullPointerException {
        while (sc.hasNext()) {
            if (sc.hasNext()) {
                line1 = sc.nextLine();
                line2 = sc.nextLine();
                int year = tidSortering.SortTimeYear(line2);
                int month = tidSortering.SortTimeMonth(line2);
                int day = tidSortering.SortTimeDay(line2);
                // Skriv in separat filer till kund eller f.d.kund.
                if (!tidSortering.compareDate(year, month, day)) {
                    System.out.println(line1 + line2);
                    wIMedlem.append(personuppgift.checkPersonnummer(line1)).append(", ").append(personuppgift.checkName(line1).trim()).
                            append("\n").append(line2).append("\n");
                } else {
                    System.out.println(line1 + line2);
                    vAvgift.append(personuppgift.checkPersonnummer(line1)).append(", ").append(personuppgift.checkName(line1).trim()).
                            append("\n").append(line2).append("\n");
                }
            }
        }
    }
    public String checkStatus(String namn, String personnummer) {
        if (namn.equals(personuppgift.checkName(line1))) {
            if (personnummer.equals(personuppgift.checkPersonnummer(line1))) {
                return "Namn: " + namn +
                        "\nPersonnummer: " + personnummer +
                        "\nDatum: " + line2 + "\n" + Status(tidSortering.compareDate(tidSortering.SortTimeYear(line2),
                        tidSortering.SortTimeMonth(line2),
                        tidSortering.SortTimeDay(line2)));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    public void ChecklistDialog(String input, Scanner iMedlem, Scanner wtp, Scanner besökningen) {
        if (input.equalsIgnoreCase("Medlem")) {
            sortTypeOfMemeber(iMedlem);
        } else if (input.equalsIgnoreCase("Avgift")) {
            sortTypeOfMemeber(wtp);
        } else if (input.equalsIgnoreCase("Besök")) {
            sortTypeOfMemeber(besökningen);
        }

    }

    protected void sortTypeOfMemeber(Scanner files) {
        while (files.hasNext()) {
            if (files.hasNext()) {
                line1 = files.nextLine();
                line2 = files.nextLine();
                System.out.println(line1 + "\n" + line2);
            }
        }
    }
    public void CheckMember(String namn, String personnummer) {
        String status ="Personen finns inte i kundlistan.";
        try (Scanner scOnMem = new Scanner(iMedlem); Scanner scWait = new Scanner(väntaPåAvgift)) {
            while (scOnMem.hasNext()) {
                if (scOnMem.hasNext()) {
                    line1 = scOnMem.nextLine();
                    line2 = scOnMem.nextLine();
                    if (checkStatus(namn, personnummer) != null) {
                        status = checkStatus(namn, personnummer);
                        break;
                    } else {
                        status = "Personuppgiften matchas inte";
                    }
                    while (scWait.hasNext()) {
                        if (scWait.hasNext()) {
                            line1 = scWait.nextLine();
                            line2 = scWait.nextLine();
                            if (checkStatus(namn, personnummer) != null) {
                                status = checkStatus(namn, personnummer);
                                break;
                            } else {
                                status = "Personuppgiften matchas inte";
                            }
                        }
                    }
                    if (checkStatus(namn, personnummer) != null) {
                        status = checkStatus(namn, personnummer);
                        break;
                    } else {
                        status = "Personuppgiften matchas inte";
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(status);
    }
    public void GymMainDialog() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try (Scanner scMedlem = new Scanner(iMedlem); Scanner pMedlem = new Scanner(väntaPåAvgift);
            Scanner sc = new Scanner(kundPost); Scanner besökPath = new Scanner(Besök)) {
                System.out.println("Väljer alternativ: ");
                System.out.println("1 Ladda ned fil");
                System.out.println("2 Visa status");
                System.out.println("3 Skriva in Kundens sista besök");
                System.out.println("4 Visa Medlemlistor");
                System.out.println("5 Stänger av");
                System.out.print("Input: ");
                String alt = input.nextLine();
                if (alt.equals("1")) {
                    try (PrintWriter wIMedlem = new PrintWriter(Files.newBufferedWriter(iMedlem));
                         PrintWriter vAvgift = new PrintWriter(Files.newBufferedWriter(väntaPåAvgift));) {
                        MemberCheck2(sc, wIMedlem, vAvgift);
                    }
                } else if (alt.equals("2")) {
                    System.out.println("Anger Personuppgifter för att kolla status.");
                    System.out.print("Namn: ");
                    String namn = input.nextLine();
                    System.out.print("Personnummer: ");
                    String personnummer = input.nextLine();
                    CheckMember(namn, personnummer);
                } else if (alt.equals("3")) {
                    try (Scanner lastvisitlist = new Scanner(iMedlem)) {
                        System.out.println("Anger Personuppgifter.");
                        System.out.print("Namn: ");
                        String namn = input.nextLine();
                        personuppgift.setNamn(namn);
                        System.out.print("Personnummer: ");
                        String personnummer = input.nextLine();
                        personuppgift.setPersonnummer(personnummer);
                        personuppgift.CheckLastVisited(namn, personnummer, lastvisitlist);
                    } catch (Exception e) {
                        System.out.println("Ospecifierat felsökande");
                    }
                } else if (alt.equals("4")) {
                    System.out.print("Vil du kolla på Medlem Avgift(Kund som vänta på årsavgift) eller sista besökningen: ");
                    String writein = input.nextLine();
                    ChecklistDialog(writein, scMedlem, pMedlem, besökPath);
                } else if (alt.equals("5")) {
                    System.exit(0);
                }
            } catch (InputMismatchException e){
                System.out.println("Anger rätt typ av input.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Input saknas.");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Ospecifierat felsökandet.");
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
       GymMain gymMain = new GymMain();
       gymMain.GymMainDialog();
    }

}
