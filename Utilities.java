package ElencoPersone;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Utilities {
    public static Utilities _instance = null;
    private int id;

    //auto-correzione dell'IDE, che mi impone di settare static UserInterface
    //static UserInterface _UI = UserInterface.getInstance();
    //non mi serve piu'

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private Utilities() {
        this.id = 0;
    }

    public static Utilities getInstance() {
        if (Utilities._instance == null) {
            Utilities._instance = new Utilities();
        }
        return Utilities._instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
     *
     * Funzioni Utili
     *
     * */

    public static String readValidDate() {
        String date;
        while (true) {
            try {
                System.out.print("Inserisci una data di nascita (YYYY-MM-DD): ");
                date = reader.readLine();
                if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    System.out.println("Formato non valido. Riprova.");
                    continue;
                }
                java.time.LocalDate.parse(date);
                break;
            } catch (Exception e) {
                System.out.println("Data non valida. Riprova.");
            }
        }
        return date;
    }

}
