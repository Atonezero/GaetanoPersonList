package ElencoVeicoliJava;

public class Main{
    public static void main(String[] args){
        IinputOutput io = new UIConsole();
        ElencoVeicoli manager = new ElencoVeicoli(io);
        UserInterface UI = new UserInterface(io, manager);
        UI.runMenu();
    }
}