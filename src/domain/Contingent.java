package domain;

import domain.member.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Contingent {
    HashMap<String, Integer> contingent = new HashMap<>();
    ArrayList<Charge> charges = new ArrayList<>();

    // ****************
    // *
    // * Charge
    // *
    // ****************

    private class Charge {
        private int charge;
        private Member member;
        private LocalDate dueDate;
        private boolean paid = false;

        public Charge(int charge, Member member, LocalDate dueDate) {
            this.charge = charge;
            this.member = member;
            this.dueDate = dueDate;
        }

        public String toCSV() {
            return charge + ";" + member.getMemberID() + ";" + dueDate.toString() + ";" + paid;
        }

        public int getCharge() {
            return charge;
        }

        public void setCharge(int charge) {
            this.charge = charge;
        }

        public Member getMember() {
            return member;
        }

        public void setMember(Member member) {
            this.member = member;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public void setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
        }

        public boolean isPaid() {
            return paid;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
        }
    }

    // ****************
    // *
    // * CSV
    // *
    // ****************

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