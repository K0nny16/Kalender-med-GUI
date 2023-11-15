import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class GUI implements ActionListener {

    JFrame frame;
    JPanel[] panels;
    JPanel[] bottom;
    JPanel[] top;
    JButton[] buttons;
    JTextField[] textFields;
    JLabel[] title;
    JPanel[] center;


    GUI(){
        frame = new JFrame();
        frame.setSize(1500,1000);
        frame.setTitle("Kalender med GUI!");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        panels = new JPanel[7];
        bottom = new JPanel[7];
        top = new JPanel[7];
        title = new JLabel[7];
        center = new JPanel[7];

        buttons = new JButton[7];
        textFields = new JTextField[7];


        createPanels();
        createBottom();
        createTopCenter();
        dateChecker();

        frame.setVisible(true);
    }
////////////////////////////////////////////////////////////////////////////////////
    void createPanels(){
        Border gray = BorderFactory.createLineBorder(Color.GRAY);
        for(int i = 0; i < 7; i++){
            panels[i]= new JPanel();
            panels[i].setBorder(gray);
            frame.add(panels[i]);
        }
        frame.setLayout(new GridLayout(0,7,1,0));
    }

    void createTopCenter(){
        LocalDate now = LocalDate.now();
        LocalDate monday = now.minusDays(now.getDayOfWeek().getValue()-1);         //Tar reda på måndagens datum.
        String[] vecka = new String[7];
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE dd/MM");      //Formaterar det som veckodag dag/månad.

        for(int i = 0; i < 7; i++){
            top[i] = new JPanel();
            top[i].setLayout(new FlowLayout());

            LocalDate dag = monday.plusDays(i);                                 //Tar måndagens datum och plussar på en dag genom forloopen.
            vecka[i]=dag.format(format);

            title[i] = new JLabel(vecka[i].substring(0,1).toUpperCase()+vecka[i].substring(1)); //Gör första bokstaven till en stor bokstav.
            top[i].add(title[i]);

            panels[i].add(top[i],BorderLayout.NORTH);

            center[i] = new JPanel();
            center[i].setLayout(new BoxLayout(center[i],BoxLayout.Y_AXIS));
            panels[i].add(center[i],BorderLayout.CENTER);
        }
    }

    void createBottom(){
        for(int i = 0; i < 7; i++){
            bottom[i] = new JPanel();                   // Skapar en ny panel "bottom" som jag sätter i botten av den befintiliga panelen.
            panels[i].setLayout(new BorderLayout());
            bottom[i].setLayout(new BorderLayout());

            buttons[i]= new JButton("Skapa");           //Lägger till en knapp med actionlistener och tar även bort focusen för att undvika rutan det blir runt texten.
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);

            panels[i].add(bottom[i],BorderLayout.SOUTH);
            bottom[i].add(buttons[i],BorderLayout.SOUTH);

            textFields[i] = new JTextField();               //Lägger till ett textfield i den norra delan av "bottom" panelen.
            bottom[i].add(textFields[i],BorderLayout.NORTH);
        }
    }
    void dateChecker(){
        LocalDate idag = LocalDate.now();
        int idagSiffra = idag.getDayOfWeek().getValue();                //Gör en metod som tar dagens veckodag som en int och jämför det värdet mot dom olika arrayerna jag har och färgar dom grönt ifall det är rätt veckodag.
        panels[idagSiffra-1].setBackground(Color.decode("#05eba9"));
        bottom[idagSiffra-1].setBackground(Color.decode("#05eba9"));
        top[idagSiffra-1].setBackground(Color.decode("#05eba9"));
        center[idagSiffra-1].setBackground(Color.decode("#05eba9"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();                         //Kollar vilken knapp som har blivit tryckt och gör en ny knapp med dens värden.

        for (int i = 0; i < 7; i++) {                                           //Gör en forloop som kollar "källan" med alla knappar i arrayn tills den hittar rätt.
            if (sourceButton == buttons[i]) {
                JLabel events = new JLabel("<html>"+textFields[i].getText()+"</html>");   //Sätter texten på JLabeln till det som finns i textFieldet.
                events.setHorizontalAlignment(JLabel.CENTER);
                events.setVerticalAlignment(JLabel.CENTER);                                 //Centrerar texten.
                center[i].add(events);
                panels[i].revalidate();                                                      //Använder också revalidate och repaint för att updatera panelen.
                panels[i].repaint();
            }
        }
    }
}
