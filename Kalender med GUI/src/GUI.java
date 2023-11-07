import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class GUI implements ActionListener {

    JFrame frame;
    JPanel[] panels;
    JButton[] buttons;
    JTextField[] ruta;
    JLabel[][] text;

    GUI(){
        frame = new JFrame();
        frame.setSize(1500,1000);
        frame.setTitle("Kalender med GUI!");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        panels = new JPanel[7];
        buttons = new JButton[7];
        ruta = new JTextField[7];
        text = new JLabel[7][1];

        panels();
        buttons();

        frame.setVisible(true);
    }

    void panels(){
        Border gray = BorderFactory.createLineBorder(Color.GRAY);
        for(int i = 0; i < 7; i++){
            panels[i]= new JPanel();
            panels[i].setBorder(gray);
            frame.add(panels[i]);
        }
        frame.setLayout(new GridLayout(0,7,1,0));
        dateChecker();
    }

    void buttons(){
        for(int i = 0; i < 7; i++){
            buttons[i]= new JButton("Skapa");
            panels[i].setLayout(new BorderLayout());
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            panels[i].add(buttons[i],BorderLayout.SOUTH);
        }
    }

    void dateChecker(){
        LocalDate idag = LocalDate.now();
        int idagSiffra = idag.getDayOfWeek().getValue();
        panels[idagSiffra-1].setBackground(Color.decode("#05eba9"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //När rätt knapp trycks (1-7) så skriver man text från JTextfieldet i rätt JLabel.
    }
}
