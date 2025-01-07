package ElencoPersone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListOfPersone {
    private List<Persona> people;

    public ListOfPersone() {
        this.people = new ArrayList<>();
    }

    public boolean Add(Persona persona) {
        return people.add(persona);
    }

    public boolean Del(Persona persona) {
        return people.remove(persona);
    }

    public List<Persona> getAll() {
        return people;
    }

    public boolean isEmpty() {
        return people.isEmpty();
    }

    // Sort by Name
    public void sortByName() {
        Collections.sort(people, Comparator.comparing(Persona::getNome));
    }

    // Sort by Surname
    public void sortBySurname() {
        Collections.sort(people, Comparator.comparing(Persona::getCognome));
    }

    // Sort by Date of Birth
    public void sortByDob() {
        Collections.sort(people, Comparator.comparing(Persona::getDoB));
    }
}