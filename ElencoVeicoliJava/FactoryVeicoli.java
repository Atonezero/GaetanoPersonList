import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

    public class FactoryVeicoli {
        public static Veicolo creaVeicolo(int tipo, String marca, String modello, String targa, int numeroPorte, boolean haBauletto) {
            switch (tipo) {
                case 1: 
                    return new Automobili(marca, modello, targa, numeroPorte);
                case 2: 
                    return new Motocicli(marca, modello, targa, haBauletto);
                case 3:
                    return new Mezzi_Pesanti(marca, modello, targa, pesoMassimo)
                default:
                    throw new IllegalArgumentException("Tipo di veicolo non valido.");
            }
        }
    }