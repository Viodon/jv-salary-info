package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = convertToLocalDate(dateFrom);
        LocalDate localDateTo = convertToLocalDate(dateTo);
        LocalDate localDateTemp;

        int[] salary = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            localDateTemp = convertToLocalDate(data[i]);
            if ((localDateTemp.isAfter(localDateFrom)
                    || localDateTemp.isEqual(localDateFrom))
                    && (localDateTemp.isBefore(localDateTo)
                    || localDateTemp.isEqual(localDateTo))) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(data[i].substring(11, data[i].indexOf(' ',12)))) {
                        salary[j] += getSalary(data[i]);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return result.toString();
    }

    public LocalDate convertToLocalDate(String date) {

        return LocalDate.of(Integer.parseInt(date.substring(6, 10)),
                Integer.parseInt(date.substring(3,5)),
                Integer.parseInt(date.substring(0,2)));
    }

    public int getSalary(String data) {

        int indexSecondWhiteSpace = data.indexOf(' ', 11);
        int indexThirdWhiteSpace = data.indexOf(' ', indexSecondWhiteSpace + 1);

        return Integer.parseInt(data.substring(indexSecondWhiteSpace + 1, indexThirdWhiteSpace))
                * Integer.parseInt(data.substring(indexThirdWhiteSpace + 1));
    }
}
