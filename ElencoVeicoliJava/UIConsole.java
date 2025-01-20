package ElencoPersone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UIConsole implements IinputOutput{
    private BufferedReader _reader;
    private PrintStream _out;
     

    public UIConsole(){
        
        this._out = System.out;
        this._reader = new BufferedReader(
            new InputStreamReader(System.in));
    }
    
    public void print(String msg) {
        _out.print(msg);
    }

    public void println(String msg) {
        _out.println(msg);
    }

    public String input() {
        
        try {
            return _reader.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    public int inputInt() {
        
        String s = this.input();
        
        if(s==null) return 0;
        
        int num  = Integer.parseInt(s);
        
        return num;
    }
    
}