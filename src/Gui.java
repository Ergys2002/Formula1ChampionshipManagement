import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui extends JFrame {
    Gui(){
       super("Formula 1 Championship Management App");
       ImageIcon f1Icon = new ImageIcon("images/F1_Formula_1_logo.png");
       setIconImage(f1Icon.getImage());

       JPanel rightPanel = new JPanel();
       JPanel screenPanel = new JPanel();

       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new GridLayout(1, 2));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        Formula1ChampionshipManagement formula1Championship = new Formula1ChampionshipManagement();
        try {
            formula1Championship.uploadRacesFromFile();
            formula1Championship.readDriversFromFile();
        } catch (Exception e) {
            System.out.print("");
        }

        JButton button = new JButton("                 Create a new driver");
        Icon createNewDriverImg = new ImageIcon("images/create_driver_icon.png");
        button.setIcon(createNewDriverImg);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                button.setBackground(Color.GREEN);
            }
            public void mouseExited(MouseEvent evt)
            {
                button.setBackground(null);
            }
        });

        JButton button2 = new JButton("                 Remove an existing driver");
        Icon removeDriverImg = new ImageIcon("images/remove_driver_icon.png");
        button2.setIcon(removeDriverImg);
        button2.setHorizontalAlignment(SwingConstants.LEFT);
        button2.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                button2.setBackground(Color.GREEN);
            }
            public void mouseExited(MouseEvent evt)
            {
                button2.setBackground(null);
            }
        });

        JButton button3 = new JButton("                 Change driver for an existing team");
        Icon changeDriverImg = new ImageIcon("images/change_driver_icon.png");
        button3.setIcon(changeDriverImg);
        button3.setHorizontalAlignment(SwingConstants.LEFT);
        button3.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                button3.setBackground(Color.GREEN);
            }
            public void mouseExited(MouseEvent evt)
            {
                button3.setBackground(null);
            }
        });

        JButton button4 = new JButton("                 Show statisics for a driver");
        Icon showStatsImg = new ImageIcon("images/show_stats_icon.png");
        button4.setIcon(showStatsImg);
        button4.setHorizontalAlignment(SwingConstants.LEFT);
        button4.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                button4.setBackground(Color.GREEN);
            }
            public void mouseExited(MouseEvent evt)
            {
                button4.setBackground(null);
            }
        });

        JButton button5 = new JButton("                 Show table of points");
        Icon tableOfPointsImg = new ImageIcon("images/table_of_points_icon.png");
        button5.setIcon(tableOfPointsImg);
        button5.setHorizontalAlignment(SwingConstants.LEFT);
        button5.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                button5.setBackground(Color.GREEN);
            }
            public void mouseExited(MouseEvent evt)
            {
                button5.setBackground(null);
            }
        });

        JButton button6 = new JButton("                 Create a race");
        Icon crateRaceImg = new ImageIcon("images/create_race_icon.png");
        button6.setIcon(crateRaceImg);
        button6.setHorizontalAlignment(SwingConstants.LEFT);
        button6.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                button6.setBackground(Color.GREEN);
            }
            public void mouseExited(MouseEvent evt)
            {
                button6.setBackground(null);
            }
        });

        JButton button7 = new JButton("                 Show statistics by the first place");
        Icon showStatsByFirstPlaceImg = new ImageIcon("images/show_stats_by_first_place_icon.png");
        button7.setIcon(showStatsByFirstPlaceImg);
        button7.setHorizontalAlignment(SwingConstants.LEFT);

        button7.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                button7.setBackground(Color.GREEN);
            }
            public void mouseExited(MouseEvent evt)
            {
                button7.setBackground(null);
            }
        });

        JButton button8 = new JButton("                 Show all races");
        Icon showAllRacesImg = new ImageIcon("images/show_all_races_icon.png");
        button8.setIcon(showAllRacesImg);
        button8.setHorizontalAlignment(SwingConstants.LEFT);
        button8.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                button8.setBackground(Color.GREEN);
            }
            public void mouseExited(MouseEvent evt)
            {
                button8.setBackground(null);
            }
        });

        rightPanel.setLayout(new GridLayout(1,2));
        rightPanel.setVisible(true);
        rightPanel.setPreferredSize(new Dimension(screenSize.width/2, screenSize.height));

        screenPanel.setVisible(true);
        screenPanel.setPreferredSize(new Dimension(screenSize.width/2, screenSize.height));
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.X_AXIS));

        JTextArea jTextArea = new JTextArea();
        jTextArea.setBackground(new Color(215,250,243));

        jTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        jTextArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        screenPanel.add(jScrollPane);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(8,1));
        buttonsPanel.add(button);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        buttonsPanel.add(button4);
        buttonsPanel.add(button5);
        buttonsPanel.add(button6);
        buttonsPanel.add(button7);
        buttonsPanel.add(button8);

        JPanel carAndCopyrightPanel = new JPanel();
        carAndCopyrightPanel.setLayout(new GridLayout(2,1));
        carAndCopyrightPanel.setVisible(true);

        JPanel carPanel = new JPanel();
        Icon ic = new ImageIcon("images/cars_picture.png");
        JLabel carsPic = new JLabel(ic);
        carPanel.add(carsPic);

        JPanel copyrightPanel = new JPanel();
        copyrightPanel.setLayout(new GridLayout(6, 1));
        JLabel copyrightIcon = new JLabel(new ImageIcon("images/copyright_icon.gif"));
        JLabel name1 = new JLabel();
        name1.setText("Artur "+(char)199+"ala");
        name1.setFont(new Font("Monospaced " ,Font.PLAIN , 18));
        name1.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel name2 = new JLabel();
        name2.setFont(new Font("Monospaced " ,Font.PLAIN , 18));
        name2.setHorizontalAlignment(SwingConstants.CENTER);
        name2.setText("Ergi Kaso");
        JLabel name3 = new JLabel();
        name3.setFont(new Font("Monospaced " ,Font.PLAIN , 18));
        name3.setHorizontalAlignment(SwingConstants.CENTER);
        name3.setText("Redjon Belishta");
        JLabel name4 = new JLabel();
        name4.setFont(new Font("Monospaced " ,Font.PLAIN , 18));
        name4.setHorizontalAlignment(SwingConstants.CENTER);
        name4.setText("Maksimo Ramaj");
        JLabel name5 = new JLabel();
        name5.setFont(new Font("Monospaced " ,Font.PLAIN , 18));
        name5.setHorizontalAlignment(SwingConstants.CENTER);
        name5.setText("Ergys Xhaollari");

        copyrightPanel.add(copyrightIcon);
        copyrightPanel.add(name1);
        copyrightPanel.add(name2);
        copyrightPanel.add(name3);
        copyrightPanel.add(name4);
        copyrightPanel.add(name5);

        carAndCopyrightPanel.add(carPanel);
        carAndCopyrightPanel.add(copyrightPanel);

        rightPanel.add(buttonsPanel);
        rightPanel.add(carAndCopyrightPanel);

        add(screenPanel);
        add(rightPanel);

        button.addActionListener(e -> formula1Championship.createNewDriver(jTextArea));

        button2.addActionListener(e -> formula1Championship.removeExistingDriver(jTextArea));

        button3.addActionListener(e -> formula1Championship.changeDriverForExistingTeam(jTextArea));

        button4.addActionListener(e -> formula1Championship.getStatisticsForTheSpecifiedDriver(jTextArea));

        button5.addActionListener(e -> formula1Championship.printOrderedListOfDrivers(jTextArea));

        button6.addActionListener(e -> formula1Championship.createRace(jTextArea));

        button7.addActionListener(e -> formula1Championship.printOrderedListOfDriversByFirstPlace(jTextArea));

        button8.addActionListener(e -> formula1Championship.printAllRaces(jTextArea));
    }
}
