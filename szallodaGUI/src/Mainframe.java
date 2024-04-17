import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mainframe extends JFrame {

    private LoadData dataLoader;
    private JTextArea idTextArea;
    private JPanel rightJPanel;
    private JPanel panel;


    public Mainframe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        
        panel = new JPanel(new BorderLayout());
        
        idTextArea = new JTextArea(10, 10);
        idTextArea.setEditable(false);
        JScrollPane idScrollPane = new JScrollPane(idTextArea);
        panel.add(idScrollPane, BorderLayout.WEST);

        rightJPanel = new JPanel(new BorderLayout());
        panel.add(rightJPanel, BorderLayout.CENTER);
        
        add(panel);
        loadDataAndDisplay();
        setVisible(true);
    }

    public void loadDataAndDisplay(){
        dataLoader = new LoadData();
        try {
            ArrayList<Szobak> szobakList = dataLoader.getSzobak();
            displayIds(szobakList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayIds(ArrayList<Szobak> szobakList){
        JPanel buttonPanel = new JPanel(new GridLayout(szobakList.size(), 1));
        for(Szobak szobak : szobakList){
            JButton button = new JButton(String.valueOf(szobak.id));
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayDetails(szobak);
                }
            });
            buttonPanel.add(button);
            idTextArea.append(String.valueOf(szobak.id)+ "\n");
        }
        JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);
        panel.add(buttonScrollPane, BorderLayout.WEST);
        idTextArea.setCaretPosition(idTextArea.getDocument().getLength());
    }
    
    private void displayDetails(Szobak szobak){
        rightJPanel.removeAll();
        
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField idField = new JTextField(String.valueOf(szobak.id));
        JTextField meretField = new JTextField(String.valueOf(szobak.szoba_merete));
        JTextField agyakField = new JTextField(String.valueOf(szobak.agyak_szama));
        JTextField teraszField = new JTextField(String.valueOf(szobak.terasz));
        JTextField haziallatField = new JTextField(String.valueOf(szobak.haziallat));
    
        inputPanel.add(new JLabel("ID: "));
        inputPanel.add(idField);
    
        inputPanel.add(new JLabel("Méret: "));
        inputPanel.add(meretField);
    
        inputPanel.add(new JLabel("Ágyak száma: "));
        inputPanel.add(agyakField);
    
        inputPanel.add(new JLabel("Terasz: "));
        inputPanel.add(teraszField);
    
        inputPanel.add(new JLabel("Háziállat: "));
        inputPanel.add(haziallatField);
        
        rightJPanel.add(inputPanel, BorderLayout.CENTER);
        JButton saveButton = new JButton("Mentés");
        saveButton.setPreferredSize(new Dimension(100, 30));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Szobak updatedSzobak = new Szobak();
                updatedSzobak.id = Integer.parseInt(idField.getText());
                updatedSzobak.szoba_merete = Integer.parseInt(meretField.getText());
                updatedSzobak.agyak_szama = Integer.parseInt(agyakField.getText());
                updatedSzobak.terasz = Integer.parseInt(teraszField.getText());
                updatedSzobak.haziallat = Integer.parseInt(haziallatField.getText());
        
                System.out.println("Frissített Szobak: " + updatedSzobak);
        
                try {
                    LoadData dataLoader = new LoadData();
                    dataLoader.updateSzobak(updatedSzobak);
                    System.out.println("Adatok frissítve az adatbázisban.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    
        rightJPanel.add(saveButton, BorderLayout.SOUTH);
    
        revalidate();
        repaint();
    }
}
