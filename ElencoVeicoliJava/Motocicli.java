package ElencoVeicoli;

public class Motocicli extends Veicolo{
    private Boolean Bauletto;

    public Motocicli(String Ruote, String Marca, String Cilindrata, String Targa, Boolean Bauletto){
        super(Ruote, Marca, Cilindrata, Targa);
        this.Bauletto = Bauletto;
    }
    /**
     * @return boolean return the Bauletto
     */
    public boolean isBauletto() {
        return Bauletto;
    }

    /**
     * @param Bauletto the Bauletto to set
     */
    public void setBauletto(boolean Bauletto) {
        this.Bauletto = Bauletto;
    }

    @Override
    public String toString() {
        return "{" +
                "Ruote: " + getRuote()  +
                ", Marca: " + getMarca() +
                ", Targa: " + getTarga() +
                ", Cilindrata: " + getCilindrata()  +
                ", Bauletto: " + Bauletto +
                '}';
    }
}