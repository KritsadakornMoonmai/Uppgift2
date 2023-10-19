package JAVAOOPUppgift2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class TidSortering {
    public int SortTimeYear(String line) {
        String[] timeList = line.split("-");
        return Integer.parseInt(timeList[0]);

    }
    public int SortTimeMonth(String line) {
        String[] timeList = line.split("-");
        return Integer.parseInt(timeList[1]);
    }
    public int SortTimeDay(String line) {
        String[] timeList = line.split("-");
        return Integer.parseInt(timeList[2]);
    }
    public boolean compareDate(int year, int month, int day) {
        LocalDate dateNow = LocalDate.now();
        String datenowFormat = dateNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int actualYear = SortTimeYear(datenowFormat);
        int actualMonth = SortTimeMonth(datenowFormat);
        int actualDay = SortTimeDay(datenowFormat);
        if (actualYear > year && (actualYear - year) == 1) {
            if (actualMonth >= month) {
                if (actualDay >= day) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (actualYear > year && (actualYear - year) > 1) {
            return true;
        } else {
            return false;
        }
    }
}
