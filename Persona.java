package ElencoPersone;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Persona {
    private String nome;
    private String cognome;
    private String DoB;

    public Persona(String nome, String cognome, String date){
        this.nome = nome;
        this.cognome = cognome;
        this.DoB = new String(date);
    }

    public int getAge(String date){
        LocalDate now = LocalDate.now();
        LocalDate dob = LocalDate.parse(this.getDoB());
        int years=Period.between(now, dob).getYears();
        years = years>0 ? years : -years;
        return years;
    }

    //get function
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getDoB() {
        return DoB;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nome, persona.nome) && Objects.equals(cognome, persona.cognome) && Objects.equals(DoB, persona.DoB);
    }

    @Override
    public String toString() {
        return "{" +
                "Nome: " + nome  +
                ", Cognome: " + cognome  +
                ", Date of Birth: " + DoB  +
                ", Eta: " + getAge(DoB) +
                '}';
    }
}
