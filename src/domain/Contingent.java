package domain;

import java.util.*;

public class Contingent {
    Map <String, Integer> fees = new HashMap<String, Integer>();
    public Contingent() {

        fees.put("Ungdomssvømmere", 1000);
        fees.put("Seniorsvømmere", 1600);
        fees.put("Pensionist", 1200);
        fees.put("Passivt medlem", 500);
    }
}







