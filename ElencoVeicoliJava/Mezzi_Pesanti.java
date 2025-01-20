package ElencoVeicoliJava;

public class Mezzi_Pesanti extends Veicolo{
    private String PesoSupportato;

    public Mezzi_Pesanti(String Ruote, String Marca, String Cilindrata, String Targa, String PesoSupportato){
        super(Ruote, Marca, Cilindrata, Targa);
        this.PesoSupportato = PesoSupportato;
    }

    /**
     * @return String return the PesoSupportato
     */
    public String getPesoSupportato() {
        return PesoSupportato;
    }

    /**
     * @param PesoSupportato the PesoSupportato to set
     */
    public void setPesoSupportato(String PesoSupportato) {
        this.PesoSupportato = PesoSupportato;
    }

    @Override
    public String toString() {
        return "{" +
                "Ruote: " + getRuote()  +
                ", Marca: " + getMarca() +
                ", Targa: " + getTarga() +
                ", Cilindrata: " + getCilindrata()  +
                ", Peso Supportato: " + PesoSupportato +
                " Kg }";
    }
}