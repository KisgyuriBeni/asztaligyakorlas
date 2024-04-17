import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoadData {
    private String user = "root";
    private String password = "";
    private String url ="jdbc:mariadb://localhost:3306/szalloda";
    
    public ArrayList<Szobak> getSzobak() throws SQLException{
        ArrayList<Szobak> szobakList = new ArrayList<>();

        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "select * from szobak";
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(sql);

        while(res.next()){
            Szobak szobak = new Szobak();

            szobak.id = res.getInt("id");
            szobak.szoba_merete = res.getInt("szoba_merete");
            szobak.agyak_szama = res.getInt("agyak_szama");
            szobak.terasz = res.getInt("terasz");
            szobak.haziallat = res.getInt("haziallat");

            szobakList.add(szobak);
        }
        res.close();
        return szobakList;
    }

    public void updateSzobak(Szobak updatedSzobak) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement statement = conn.createStatement();
        
        String updateSql = "UPDATE szobak SET szoba_merete = " + updatedSzobak.szoba_merete +
                            ", agyak_szama = " + updatedSzobak.agyak_szama +
                            ", terasz = " + updatedSzobak.terasz +
                            ", haziallat = " + updatedSzobak.haziallat +
                            " WHERE id = " + updatedSzobak.id;
        
        statement.executeUpdate(updateSql);
        
        statement.close();
        conn.close();
    }
}
