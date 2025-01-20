package ElencoVeicoli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UITextFile implements IinputOutput{

    private BufferedReader _reader;
    private BufferedWriter _writer;
    
    public UITextFile(){
        
       File file = new File("input.txt");

        try {
            _reader = new BufferedReader(new FileReader(file));
            _writer = new BufferedWriter(new FileWriter("output.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UITextFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UITextFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void print(String msg) {
        try {
            _writer.write(msg);
            _writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(UITextFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void println(String msg) {
        try {
            _writer.write(msg);
            _writer.write("\n");
            _writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(UITextFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String input() {
        
        try {
            return _reader.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public int inputInt() {
        
        String s = this.input();
        
        if(s==null) return 0;
        
        int num  = Integer.parseInt(s);
        
        return num;
    }
    
}