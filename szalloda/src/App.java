import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        ReadFile rf = new ReadFile();
        ArrayList<Szobak> szobakList = rf.readFile("szobak.csv");
        Store store = new Store();
        store.fillDatabase(szobakList);

        // for(Szobak szobak: szobakList){
        //     System.out.println(szobak.id);
        // }
    }
}
