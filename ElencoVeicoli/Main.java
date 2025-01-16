package ElencoVeicoli;

public class Main{
    public static void main(String[] args){
        ListOfVeicoli manager = new ListOfVeicoli();
        UserInterface UI = UserInterface.getInstance();
        UI.run(manager);
    }
}