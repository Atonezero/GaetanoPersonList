package ElencoVeicoliJava;

import java.util.ArrayList;
import java.util.List;

public class ElencoVeicoli {
    private List<Veicolo> veicoli;
    private IinputOutput _io;

    public ElencoVeicoli(IinputOutput io) {
        this.veicoli = new ArrayList<>();
        this._io = io;
    }

    public void AddVeicolo(Veicolo veicolo) {
        veicoli.add(veicolo);
        _io.println("Veicolo aggiunto con successo!");
    }

    public void removeVeicolo(int indice) {
        if (indice >= 0 && indice < veicoli.size()) {
            Veicolo rimosso = veicoli.remove(indice);
            _io.println("Veicolo rimosso: " + rimosso.getClass().getSimpleName());
        } else {
            _io.println("Indice non valido.");
        }
    }

    public void stampaVeicoli() {
        if (veicoli.isEmpty()) {
            _io.println("Nessun veicolo nell'elenco.");
        } else {
            _io.println("");
            _io.println("Elenco dei veicoli:");
            for (int i = 0; i < veicoli.size(); i++) {
                Veicolo v = veicoli.get(i);
                _io.println("");
                _io.println((i + 1) + ". " + v.getClass().getSimpleName());
                _io.print(v.toString());
            }
            _io.println("");
            _io.print("Premi invio per continuare ...");
            _io.input();
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

    public void setVeicoli(List<Veicolo> veicoli) {
        this.veicoli = veicoli;
    }

    public List<Veicolo> getVeicoli() {
        return this.veicoli;
    }
}