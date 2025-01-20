package ElencoVeicoliJava;

public class Veicolo{
    private String Ruote;
    private String Marca;
    private String Targa;
    private String Cilindrata;

    public Veicolo(String Ruote, String Marca, String Cilindrata, String Targa){
        this.Ruote = Ruote;
        this.Marca = Marca;
        this.Targa = Targa;
        this.Cilindrata = Cilindrata;
    }

    public String getRuote() {
        return Ruote;
    }
    
    public String getTarga() {
        return Targa;
    }

    public String getMarca() {
        return Marca;
    }

    public String getCilindrata() {
        return Cilindrata;
    }

    @Override
    public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) return null;
        Veicolo veicolo = (Veicolo) o;
        return Object.equals(Targa, veicolo.Targa) && Object.equals(Ruote, veicolo.Ruote) && Object.equals(Marca, veicolo.Marca) && Object.equals(Cilindrata, veicolo.Cilindrata);
    }

    @Override
    public String toString() {
        return "{" +
                "Ruote: " + Ruote  +
                ", Marca: " + Marca +
                ", Targa: " + Targa +
                ", Cilindrata: " + Cilindrata  +
                '}';
    }

    /**
     * @param Ruote the Ruote to set
     */
    public void setRuote(String Ruote) {
        this.Ruote = Ruote;
    }

    /**
     * @param Marca the Marca to set
     */
    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    /**
     * @param Cilindrata the Cilindrata to set
     */
    public void setCilindrata(String Cilindrata) {
        this.Cilindrata = Cilindrata;
    }


    /**
     * @param Targa the Targa to set
     */
    public void setTarga(String Targa) {
        this.Targa = Targa;
    }

}