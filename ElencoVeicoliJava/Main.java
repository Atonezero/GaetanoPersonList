package ElencoVeicoliJava;

public class Main{
    public static void main(String[] args){
        IinputOutput io = new UIConsole();
        ElencoVeicoli manager = new ElencoVeicoli();
        UserInterface UI = UserInterface.getInstance(io);
        UI.runMenu(manager);
    }
}