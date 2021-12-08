package domain;


import domain.member.Member;

import java.time.LocalDate;
import java.util.HashMap;


public class Contingent {
    HashMap<String, Integer> contingent = new HashMap<String, Integer>();

    public Contingent() {
        contingent.put("Ungdomssvømmere", 1000);
        contingent.put("Seniorsvømmere", 1600);
        contingent.put("Pensionist", 1200);
        contingent.put("Passivt medlem", 500);


    }
}