package ElencoVeicoliJava;

    public class FactoryVeicoli {
        public static Veicolo creaVeicolo(int tipo, String ruote, String marca, String cilindrata, String targa, String numeroPorte, Boolean haBauletto, String pesoMassimo) {
            switch (tipo) {
                case 1: 
                    return new Quadriciclo(ruote, marca, cilindrata, targa, numeroPorte);
                case 2: 
                    return new Motocicli(ruote, marca, cilindrata, targa, haBauletto);
                case 3:
                    return new Mezzi_Pesanti(ruote, marca, cilindrata, targa, pesoMassimo);
                default:
                    throw new IllegalArgumentException("Tipo di veicolo non valido.");
            }
        }
    }