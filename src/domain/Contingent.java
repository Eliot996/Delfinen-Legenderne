package domain;

import domain.member.Member;

import java.time.LocalDate;
import java.util.*;


public class Contingent {
    HashMap<String, Integer> contingent = new HashMap<>();
    ArrayList<Charge> charges = new ArrayList<>();
    Controller controller;

    public Contingent(Controller controller) {
        this.controller = controller;
    }

    // ****************
    // *
    // * Charge
    // *
    // ****************

    public void addCharge(Member member) {
            addCharge(member, contingent.get(member.getMemberType().toLowerCase()));
    }

    public void addCharge(Member member, int amount) {
        charges.add(new Charge(amount,
                member,
                LocalDate.now().plusDays(30)));
    }

    public Charge getCharge(int index) {
        return charges.get(index);
    }

    public int getAmountOfCharges() {
        return charges.size();
    }

    public void initCharges(List<String> csvs) {
        if (csvs.size() > 0)
        csvs.remove(0);// TODO: 09/12/2021 Temp, needs to be redone, to include in Filehandler


        for (String csv: csvs) {
            String[] elements = csv.split(";");
            charges.add(new Charge(Integer.parseInt(elements[0]),
                    controller.getMember(elements[1]),
                    LocalDate.parse(elements[2]),
                    Boolean.parseBoolean(elements[3])));
        }

        checkAndAddYearlyContigentCharge();
    }

    private void checkAndAddYearlyContigentCharge() {
        ArrayList<Charge> latestChargesPerMember = new ArrayList<>();
        sortCharges();

        boolean contains = false;
        for (Charge charge : charges) {
            for (Charge latestChargePerMember : latestChargesPerMember) {
                if (charge.getMember() == latestChargePerMember.getMember()) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                latestChargesPerMember.add(charge);
            }
            contains = false;
        }

        LocalDate now = LocalDate.now();
        for (Charge charge : latestChargesPerMember) {
            if (charge.getDueDate().plusYears(1).isBefore(now)) {
                addCharge(charge.member);
            }
        }
    }

    private void sortCharges() {
        charges.sort(((o1, o2) -> o2.getDueDate().compareTo(o1.getDueDate())));
    }

    public String chargesToCSV() {
        StringBuilder sb = new StringBuilder("At betale;Medlems ID;Dato for betaling;Er Betalt\n");

        for (Charge charge : charges) {
            sb.append(charge.toCSV()).append('\n');
        }

        return sb.toString();
    }

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

        public Charge(int charge, Member member, LocalDate dueDate, Boolean paid) {
            this.charge = charge;
            this.member = member;
            this.dueDate = dueDate;
            this.paid = paid;
        }

        public String toCSV() {
            return charge + ";" + member.getMemberID() + ";" + dueDate.toString() + ";" + paid;
        }

        @Override
        public String toString() {
            return "charge=" + charge +
                    ", medlem=" + member.getMemberID() +
                    ", betalingsdato=" + dueDate +
                    ", betalt=" + paid;
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
        csvs.remove(0); // TODO: 09/12/2021 Temp, needs to be redone, to include in Filehandler
        for (String csv: csvs) {
            String[] elements = csv.split(";");
            contingent.put(elements[0], Integer.parseInt(elements[1]));
        }
    }
}