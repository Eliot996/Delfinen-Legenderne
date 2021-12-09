package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Contingent {
    HashMap<String, Integer> contingent = new HashMap<>();

    public String ContigentToCSV() {
        StringBuilder sb = new StringBuilder("Navn(MÅ IKKE ÆNDRES);Pris\n");

        for (Map.Entry<String, Integer> entry: contingent.entrySet()) {
            sb.append(entry.getKey()).append(';');
            sb.append(entry.getValue()).append('\n');
        }

        return sb.toString();
    }

    public void initContingents(List<String> csvs) {
        csvs.remove(0);
        for (String csv: csvs) {
            String[] elements = csv.split(";");
            contingent.put(elements[0], Integer.parseInt(elements[1]));
            System.out.println(contingent.get(elements[0]));
        }
    }
}