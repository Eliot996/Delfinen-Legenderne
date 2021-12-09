package domain.member;

import domain.Discipline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Author Mathias og Sofia
public class Competitor extends Member {

    private List<Discipline> disciplines = new ArrayList<>();

    public Competitor(boolean isPassiveMember,
                      String name,
                      LocalDate dateOfBirth,
                      String phoneNumber,
                      String email) {
        super(isPassiveMember, name, dateOfBirth, phoneNumber, email);
    }

    public Competitor(String csv) {
        super(csv);

        String discpinesToAdd = csv.substring(csv.lastIndexOf(";") + 1);
        if (!discpinesToAdd.equals("")) {
            String[] elements = discpinesToAdd.split(":");

            for (String element: elements) {
                addDisciplines(Discipline.valueOf(element));
            }
        }

    }

    public List<Discipline> getDisciplines() {
        return disciplines;
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

        // appends the delimiter
        sb.append(";");
        // if there are any disciplines on the competitor, then add the first one
        if (disciplines.size() > 1) {
            sb.append(disciplines.get(0));
            //
            for(int i = 1; i < disciplines.size(); i++) {
                sb.append(":").append(disciplines.get(i));
            }
        } else if (disciplines.size() == 1) {
            sb.append(disciplines.get(0));
        }

        return sb.toString();
    }
}

