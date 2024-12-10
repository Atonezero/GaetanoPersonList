package ElencoPersone;

import java.util.ArrayList;

public class ListOfPersone {
    private ArrayList<Persona> _elenco;
    private int lenght;

    public ListOfPersone() {
        this._elenco = new ArrayList<>();
    }

    public int getLenght() {
        return _elenco.size();
    }

    public boolean isEmpty() {
        return _elenco.isEmpty();
    }

    public ArrayList<Persona> getAll() {
        return new ArrayList<>(_elenco);
    }

    public boolean Add(Persona p) {
        if(p!=null) {
            this._elenco.add(p);
            return true;
        } else return false;
    }

    public boolean Del(Persona p) {
        if(p!=null && this._elenco.contains(p)) {
            this._elenco.remove(p);
            return true;
        } else return false;
    }

    public boolean Contains(Persona p) {
        return ( p!=null && this._elenco.contains(p) );
    }
}
