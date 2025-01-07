package ElencoPersone;

public class Main {
    public static void main(String[] args) {
        ListOfPersone manager = new ListOfPersone();
        UserInterface UI  = UserInterface.getInstance();
        UI.callMenu(manager);
    }

}