package ElencoVeicoli;

import java.util.ArrayList;
import java.util.List;

public class ElencoVeicoli {
    private List<Veicolo> veicoli;

    public ElencoVeicoli() {
        this.veicoli = new ArrayList<>();
    }

    public void aggiungiVeicolo(Veicolo veicolo) {
        veicoli.add(veicolo);
        System.out.println("Veicolo aggiunto con successo!");
    }

    public void rimuoviVeicolo(int indice) {
        if (indice >= 0 && indice < veicoli.size()) {
            Veicolo rimosso = veicoli.remove(indice);
            System.out.println("Veicolo rimosso: " + rimosso.getClass().getSimpleName());
        } else {
            System.out.println("Indice non valido.");
        }
    }

    public void stampaVeicoli() {
        if (veicoli.isEmpty()) {
            System.out.println("Nessun veicolo nell'elenco.");
        } else {
            System.out.println("Elenco dei veicoli:");
            for (int i = 0; i < veicoli.size(); i++) {
                Veicolo v = veicoli.get(i);
                System.out.println((i + 1) + ". " + v.getClass().getSimpleName());
                v.descrizione();
            }
        }
    }

    public Veicolo getVeicolo(int indice) {
        if (indice >= 0 && indice < veicoli.size()) {
            return veicoli.get(indice);
        } else {
            System.out.println("Indice non valido.");
            return null;
        }
    }

    public int numeroVeicoli() {
        return veicoli.size();
    }
}