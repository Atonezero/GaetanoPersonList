package ElencoVeicoli;

public class Veicolo{
    private short Ruote;
    private String Marca;
    private Int Cilindrata;

    public Veicolo(short Ruote, String Marca, Int Cilindrata){
        this.Ruote = Ruote;
        this.Marca = Marca;
        this.Cilindrata = Cilindrata;
    }

    public short getRuote() {
        return Ruote;
    }

    public String getMarca() {
        return Marca;
    }

    public Int getCilindrata() {
        return Cilindrata;
    }

    @Override
    public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) return null;
        Veicolo veicolo = (Veicolo) o;
        return Object.equals(Ruote, veicolo.Ruote) && Object.equals(Marca, veicolo.Marca) && Object.equals(Cilindrata, veicolo.Cilindrata);
    }

    @Override
    public String toString() {
        return "{" +
                "Nome: " + Ruote  +
                ", Marca: " + Marca +
                ",  Cilindrata: " + Cilindrata  +
                '}';
    }
}