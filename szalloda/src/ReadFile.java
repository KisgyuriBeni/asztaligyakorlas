import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    
    public ArrayList<Szobak> readFile(String path) throws FileNotFoundException{
        ArrayList<Szobak> szobakList = new ArrayList<>();

        File file = new File(path);
        Scanner sc = new Scanner(file);
        sc.nextLine();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArray = line.split(";");

            Szobak szobak = new Szobak();
            szobak.id = Integer.parseInt(lineArray[0]);
            szobak.szoba_merete = Integer.parseInt(lineArray[1]);
            szobak.agyak_szama = Integer.parseInt(lineArray[2]);
            szobak.terasz = Boolean.parseBoolean(lineArray[3]);
            szobak.haziallat = Boolean.parseBoolean(lineArray[4]);

            szobakList.add(szobak);
        }
        sc.close();
        return szobakList;

    }
}
