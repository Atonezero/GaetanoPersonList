package ElencoPersone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInterface {
    public static UserInterface _instance = null;
    private int id;

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private UserInterface() {
        this.id = 0;
    }

    public static UserInterface getInstance() {
        if (UserInterface._instance == null) {
            UserInterface._instance = new UserInterface();
        }
        return UserInterface._instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
    *
    * Fuzioni del Menu
    *
    * */

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing the console.");
        }
    }

    public int showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Menu ===");
        System.out.println("1. Add person");
        System.out.println("2. Delete person");
        System.out.println("3. Show list of people");
        System.out.println("4. Exit");
        System.out.print("Seleziona un'opzione: ");

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 4) break;
                System.out.println("Opzione non valida. Riprova.");
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisci un numero.");
            }
        }
        return choice;
    }

    public void callMenu(ListOfPersone manager) {
        while (true) {
            int choice = showMenu();
            switch (choice) {
                case 1 -> AddPerson_UserInterface(manager);
                case 2 -> DelPerson_UserInterface(manager);
                case 3 -> PrintLoP(manager);
                case 4 -> {
                    System.out.println("Uscita dal programma.");
                    return;
                }
                default -> System.out.println("Errore: scelta non valida.");
            }
        }
    }

    /*
     *
     *  Funzioni di UserInterface
     *
     * */
    private static void AddPerson_UserInterface(ListOfPersone manager) {
        try {
            System.out.print("Inserisci un nome: ");
            String nome = reader.readLine();

            System.out.print("Inserisci un cognome: ");
            String cognome = reader.readLine();

            String DoB = Utilities.readValidDate();

            clearConsole();
            Persona p = new Persona(nome, cognome, DoB);
            if (manager.Add(p)) {
                System.out.println("Persona aggiunta con successo!");
            } else {
                System.out.println("Errore: impossibile aggiungere la persona.");
            }
        } catch (IOException e) {
            System.out.println("Errore durante l'inserimento dei dati: ");
        }
    }

    private static void DelPerson_UserInterface(ListOfPersone manager) {
        try {
            System.out.print("Inserisci il nome della persona da eliminare: ");
            String nome = reader.readLine();

            System.out.print("Inserisci il cognome della persona da eliminare: ");
            String cognome = reader.readLine();

            System.out.print("Inserisci la data di nascita (YYYY-MM-DD) della persona da eliminare: ");
            String DoB = reader.readLine();

            clearConsole();
            Persona p = new Persona(nome, cognome, DoB);
            if (manager.Del(p)) {
                System.out.println("Persona eliminata con successo!");
            } else {
                System.out.println("Errore: persona non trovata.");
            }
        } catch (IOException e) {
            System.out.println("Errore durante l'inserimento dei dati: ");
        }
    }

    private static void PrintLoP(ListOfPersone manager) {
        clearConsole();
        System.out.println("\n=== Lista delle Persone ===");
        if (manager.isEmpty()) {
            System.out.println("Nessuna persona presente.");
            return;
        }

        for (Persona persona : manager.getAll()) {
            System.out.println(persona.toString());
        }
    }
}


