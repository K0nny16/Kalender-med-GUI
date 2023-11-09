import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Calendar;

public class GUI implements ActionListener {

    JFrame frame;
    JPanel[] panels;
    JPanel[] bottom;
    JPanel[] top;
    JButton[] buttons;
    JTextField[] textFields;
    JLabel[] text;
    JLabel[] title;

    GUI(){
        frame = new JFrame();
        frame.setSize(1500,1000);
        frame.setTitle("Kalender med GUI!");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        panels = new JPanel[7];
        bottom = new JPanel[7];
        top = new JPanel[7];
        title = new JLabel[7];

        buttons = new JButton[7];
        textFields = new JTextField[7];
        text = new JLabel[7];


        panels();
        buttons();
        textFrame();
        labels();
        dateChecker();

        frame.setVisible(true);
    }
////////////////////////////////////////////////////////////////////////////////////
    void panels(){
        Border gray = BorderFactory.createLineBorder(Color.GRAY);
        for(int i = 0; i < 7; i++){
            panels[i]= new JPanel();
            panels[i].setBorder(gray);
            frame.add(panels[i]);
        }
        frame.setLayout(new GridLayout(0,7,1,0));
    }

    void labels(){
        LocalDate now = LocalDate.now();
        LocalDate måndag = now.minusDays(now.getDayOfWeek().getValue()-1);
        String[] vecka = new String[7];
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE dd/MM");

        for(int i = 0; i < 7; i++){
            top[i] = new JPanel();
            text[i] = new JLabel();
            top[i].setLayout(new FlowLayout());

            LocalDate dag = måndag.plusDays(i);
            vecka[i]=dag.format(format);

            title[i] = new JLabel(vecka[i]);
            top[i].add(title[i]);

            top[i].add(text[i]);
            panels[i].add(top[i],BorderLayout.NORTH);
        }
    }

    void buttons(){
        for(int i = 0; i < 7; i++){
            bottom[i] = new JPanel();
            panels[i].setLayout(new BorderLayout());
            bottom[i].setLayout(new BorderLayout());

            buttons[i]= new JButton("Skapa");
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);

            panels[i].add(bottom[i],BorderLayout.SOUTH);
            bottom[i].add(buttons[i],BorderLayout.SOUTH);
        }
    }

    void textFrame(){
        for(int i = 0; i < 7; i++)
        {
            textFields[i] = new JTextField();
            bottom[i].add(textFields[i],BorderLayout.NORTH);
        }
    }

    void dateChecker(){
        LocalDate idag = LocalDate.now();
        int idagSiffra = idag.getDayOfWeek().getValue();
        panels[idagSiffra-1].setBackground(Color.decode("#05eba9"));
        bottom[idagSiffra-1].setBackground(Color.decode("#05eba9"));
        top[idagSiffra-1].setBackground(Color.decode("#05eba9"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //När rätt knapp trycks (1-7) så skriver man text från JTextfieldet i rätt JLabel.
    }
}
