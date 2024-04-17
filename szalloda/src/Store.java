import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Store {
    
    public void fillDatabase(ArrayList<Szobak> szobakList) throws SQLException{
        String user = "root";
        String password = "";
        String url ="jdbc:mariadb://localhost:3306/szalloda";

        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = 
        """
        insert into szobak(id, szoba_merete, agyak_szama, terasz, haziallat)
        values
        (?,?,?,?,?);        
        """;

        for(Szobak szobak: szobakList){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, szobak.id);
            ps.setInt(2, szobak.szoba_merete);
            ps.setInt(3, szobak.agyak_szama);
            ps.setBoolean(4, szobak.terasz);
            ps.setBoolean(5, szobak.haziallat);
            ps.execute();
        }
        System.out.println("Sikeres feltöltés!");
        connection.close();
    }
}
