package ElencoVeicoliJava;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FileManager {

    public static <T> List<T> caricaDaFile(String fileName, FileParser<T> parser) {
        List<T> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T oggetto = parser.parse(line);
                lista.add(oggetto);
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
        return lista;
    }

    public static <T> void salvaSuFile(String fileName, List<T> lista, FileFormatter<T> formatter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (T oggetto : lista) {
                writer.write(formatter.format(oggetto));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }

    public interface FileParser<T> {
        T parse(String line);
    }

    public interface FileFormatter<T> {
        String format(T oggetto);
    }
}