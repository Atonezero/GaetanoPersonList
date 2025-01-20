package ElencoVeicoli;

public class UserInterface {
    public static UserInterface _instance = null;
    private IinputOutput _io;

    private UserInterface(IinputOutput io) {
        this._io = io;
    }

    public static UserInterface getInstance(IinputOutput io) {
        if (UserInterface._instance == null) {
            UserInterface._instance = new UserInterface(io);
        }
        return UserInterface._instance;
    }


    /*
    *
    * Fuzioni del Menu
    *
    * */

    public static void clearConsole() {
        try {
            final String os = this._ioProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                this._io.println("\033[H\033[2J");
                this._io.flush();
            }
        } catch (Exception e) {
            this._io.println("Error clearing the console.");
        }
    }

    public int run() {
        Scanner scanner = new Scanner(this._io;
        this._io.println("\n=== Menu ===");
        this._io.println("1. Aaggiungi Veicolo");
        this._io.println("2. Elimina Veicolo");
        this._io.println("3. Show list of Veicoli");
        this._io.println("4. Exit");
        this._io.print("Seleziona un'opzione: ");

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 4) break;
                this._io.println("Opzione non valida. Riprova.");
            } catch (NumberFormatException e) {
                this._io.println("Input non valido. Inserisci un numero.");
            }
        }
        return choice;
    }

    private void MenuOfVeicoli(){
        this._io.println("List of Veicoli : ");
        this._io.println("1. Quatriciclo");
        this._io.println("2. Motociclo");
        this._io.println("3. Mezzo Pesante");
    }

    private void callMenu(ListOfPersone manager) {
        while (true) {
            int choice = showMenu();
            switch (choice) {
                case 1 -> AddVeicolo(manager);
                case 2 -> DelPerson_UserInterface(manager);
                case 3 -> manager.stampaVeicoli();
                case 4 -> {
                    this._io.println("Uscita dal programma.");
                    return;
                }
                default -> this._io.println("Errore: scelta non valida.");
            }
        }
    }

    /*
     *
     *  Funzioni di UserInterface
     *
     * */
    private void AddVeicolo(ElencoVeicoli elencoVeicoli) {
        MenuOfVeicoli();
        this._io.println("Scegli il tipo di veicolo da aggiungere :");
        int scelta = this._io.inputInt();

        this._io.println("Inserisci la marca:");
        String marca = this._io.inputString();
    
        this._io.println("Inserisci il modello:");
        String modello = this._io.inputString();
    
        this._io.println("Inserisci la targa:");
        String targa = this._io.inputString();
    
        String numeroPorte;
        String pesoMassimo;
        String haBauletto;
    

        if (scelta == 1) {
            this._io.println("Inserisci il numero di porte:   ");
            numeroPorte = this._io.inputInt();
        } else if (scelta == 2) {
            this._io.println("Presenza Bauletto (true/false):     ");
            haBauletto = this._io.inputString();
        } else if (scelta == 3) {
            this._io.println("Peso massimo supportato:    ");
            pesoMassimo = this._io.inputString();
        }
    
        try {
            Veicolo veicolo = FactoryVeicoli.creaVeicolo(scelta, marca, modello, targa, numeroPorte, Boolean(haBauletto), pesoMassimo);
            elencoVeicoli.aggiungiVeicolo(veicolo);
            this._io.println("Veicolo aggiunto con successo:");
            veicolo.descrizione();
        } catch (IllegalArgumentException e) {
            this._io.println(e.getMessage());
        }
    }

    private static void DelPerson_UserInterface(ListOfPersone manager) {
        try {
            this._io.print("Inserisci il nome della persona da eliminare: ");
            String nome = reader.readLine();

            this._io.print("Inserisci il cognome della persona da eliminare: ");
            String cognome = reader.readLine();

            this._io.print("Inserisci la data di nascita (YYYY-MM-DD) della persona da eliminare: ");
            String DoB = reader.readLine();

            clearConsole();
            Persona p = new Persona(nome, cognome, DoB);
            if (manager.Del(p)) {
                this._io.println("Persona eliminata con successo!");
            } else {
                this._io.println("Errore: persona non trovata.");
            }
        } catch (IOException e) {
            this._io.println("Errore durante l'inserimento dei dati: ");
        }
    }

    private static void PrintLoP(ListOfPersone manager) {
        clearConsole();
        this._io.println("\n=== Lista delle Persone ===");
        if (manager.isEmpty()) {
            this._io.println("Nessuna persona presente.");
            return;
        }

        for (Persona persona : manager.getAll()) {
            this._io.println(persona.toString());
        }
    }
}


