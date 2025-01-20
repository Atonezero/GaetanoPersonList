package ElencoVeicoliJava;

public class Quadriciclo extends Veicolo{
    private String Porte;

    public Quadriciclo(String Ruote, String Marca, String Cilindrata, String Targa, String Porte){
        super(Ruote, Marca, Cilindrata, Targa);
        this.Porte = Porte;
    }

    public String getPorte() {
        return Porte;
    }

    public void setPorte(String Porte) {
        this.Porte = Porte;
    }
    
    @Override
    public String toString() {
        return "{" +
                "Ruote: " + getRuote()  +
                ", Marca: " + getMarca() +
                ", Targa: " + getTarga() +
                ", Cilindrata: " + getCilindrata()  +
                ", Porte: " + Porte +
                '}';
    }

}
