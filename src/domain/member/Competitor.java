package domain.member;

import domain.Discipline;

import java.time.LocalDate;
import java.util.List;

public class Competitor extends Member {

    private List<Discipline> disciplines;

    public Competitor(boolean isPassiveMember,
                      String name,
                      LocalDate dateOfBirth,
                      String phoneNumber,
                      String email) {
        super(isPassiveMember, name, dateOfBirth, phoneNumber, email);
    }

    public Competitor(String competitorString) {
        super(competitorString);
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public void addDisciplines(Discipline discipline) {
        this.disciplines.add(discipline);
    }

    public void removeDisciplines(Discipline discipline) {
        this.disciplines.remove(discipline);
    }

    @Override
    public String toCSV() {
        StringBuilder sb = new StringBuilder(super.toCSV());

        if (disciplines.size() > 1) {
            sb.append(disciplines);
            for (int i = 1; i < disciplines.size(); i++) {
                sb.append(":").append(disciplines.get(i));
            }
        }else if (disciplines.size() == 1){
            sb.append(disciplines.get(0));
        }

        return  sb.toString();
    }
}

