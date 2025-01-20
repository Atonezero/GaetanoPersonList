package ElencoVeicoliJava;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private ElencoVeicoli _manager;
    private IinputOutput _io;

    private UserInterface(IinputOutput io, ElencoVeicoli manager) {
        this._io = io;
        this.manager = manager;
    }

    public void clearConsole() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing the console.");
        }
    }

    public int callMenu() {
        clearConsole();
        _io.println("\n=== Menu ===");
        _io.println("1. Aggiungi Veicolo");
        _io.println("2. Elimina Veicolo");
        _io.println("3. Mostra lista Veicoli");
        _io.println("4. Modifica Veicolo");
        _io.println("5. Exit");
        _io.print("Seleziona un'opzione: ");

        int choice;
        while (true) {
            try {
                choice = _io.inputInt();
                if (choice >= 1 && choice <= 5) break;
                _io.println("Opzione non valida. Riprova.");
            } catch (NumberFormatException e) {
                _io.println("Input non valido. Inserisci un numero.");
            }
        }
        return choice;
    }

    private void MenuOfVeicoli() {
        _io.println("Lista Tipi di Veicoli:");
        _io.println("1. Quatriciclo");
        _io.println("2. Motociclo");
        _io.println("3. Mezzo Pesante");
    }

    private void AddVeicolo(ElencoVeicoli elencoVeicoli) {
        clearConsole();
        MenuOfVeicoli();
        _io.println("Scegli il tipo di veicolo da aggiungere:");
        int scelta = _io.inputInt();

        _io.println("Inserisci il numero di ruote:");
        String ruote = _io.input();

        _io.println("Inserisci la marca:");
        String marca = _io.input();

        _io.println("Inserisci la targa:");
        String targa = _io.input();
        
        _io.println("Inserisci la Cilindarata:");
        String cilindrata = _io.input();

        String numeroPorte = null, pesoMassimo = null, haBauletto = null;

        if(scelta == 1){
            _io.println("Inserisci il numero di porte:");
            numeroPorte = _io.input();
        }
        else if(scelta == 2) {
            _io.println("Presenza Bauletto (true/false):");
            haBauletto = _io.input();
        }
        else if(scelta == 3){
            _io.println("Peso massimo supportato:");
            pesoMassimo = _io.input();
        }

        try {
            Veicolo veicolo = FactoryVeicoli.creaVeicolo(scelta, ruote, marca, cilindrata, targa, numeroPorte, Boolean.parseBoolean(haBauletto), pesoMassimo);
            elencoVeicoli.AddVeicolo(veicolo);
            _io.println("Veicolo aggiunto con successo.");
        } catch (IllegalArgumentException e) {
            _io.println(e.getMessage());
        }
    }

    private int trovaVeicolo(ElencoVeicoli elencoVeicoli, String targa) {
        for (int i = 0; i < elencoVeicoli.size(); i++) {
            if (elencoVeicoli.getVeicolo(i).getTarga().equalsIgnoreCase(targa)) {
                _io.println("Veicolo trovato: " + elencoVeicoli.getVeicolo(i).toString());
                return i;
            }
        }
        _io.println("Veicolo con targa " + targa + " non trovato.");
        return -1;
    }

    private void DeledeVeicolo(ElencoVeicoli elencoVeicoli) {
        clearConsole();
        _io.println("Inserisci la targa del veicolo da eliminare:");
        String targa = _io.input();

        int index = trovaVeicolo(elencoVeicoli, targa);
        if (index != -1) {
            elencoVeicoli.removeVeicolo(index);
            _io.println("Veicolo eliminato con successo.");
        } else {
            _io.println("Errore: veicolo non trovato.");
        }
    }

    private void ModificaVeicolo(ElencoVeicoli elencoVeicoli) {
        clearConsole();
        _io.println("Inserisci la targa del veicolo da modificare:");
        String targa = _io.input();

        int index = trovaVeicolo(elencoVeicoli, targa);
        if (index != -1) {
            Veicolo veicolo = elencoVeicoli.getVeicolo(index);

            _io.println("Modifica i dati del veicolo (premi Invio per mantenere il valore corrente):");

            _io.println("Marca attuale: " + veicolo.getMarca());
            _io.println("Nuova marca:");
            String nuovaMarca = _io.input();
            if (!nuovaMarca.isEmpty()) veicolo.setMarca(nuovaMarca);

            _io.println("Modello attuale: " + veicolo.getModello());
            _io.println("Nuovo modello:");
            String nuovoModello = _io.input();
            if (!nuovoModello.isEmpty()) veicolo.setModello(nuovoModello);

            _io.println("Targa attuale: " + veicolo.getTarga());
            _io.println("Nuova targa:");
            String nuovaTarga = _io.input();
            if (!nuovaTarga.isEmpty()) veicolo.setTarga(nuovaTarga);

            _io.println("Veicolo modificato con successo.");
        } else {
            _io.println("Errore: veicolo non trovato.");
        }
    }

    public void runMenu(ElencoVeicoli elencoVeicoli) {
        while (true) {
            int choice = callMenu();
            if (choice == 1) {
                AddVeicolo(elencoVeicoli);
            } else if (choice == 2) {
                DeledeVeicolo(elencoVeicoli);
            } else if (choice == 3) {
                elencoVeicoli.stampaVeicoli();
            } else if (choice == 4) {
                ModificaVeicolo(elencoVeicoli);
            } else if(choice == 5) {
                _io.println("Uscita dal programma.");
                return;
            } else { 
                _io.println("Errore: scelta non valida.");
            }
        }
    }
}