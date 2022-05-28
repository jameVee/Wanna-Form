import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FirstFrameWriter extends JFrame {
    DefaultTableModel model;
    ImageIcon Wonderful = new ImageIcon(new ImageIcon("image/5.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    ImageIcon Great = new ImageIcon(new ImageIcon("image/4.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    ImageIcon Good = new ImageIcon(new ImageIcon("image/3.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    ImageIcon Neutral = new ImageIcon(new ImageIcon("image/2.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    ImageIcon So = new ImageIcon(new ImageIcon("image/1.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));


    public FirstFrameWriter(){
        super("Creator");
        File file = new File("library Test");
        String[] q = file.list();


        ImageIcon image = new ImageIcon("image/backgroundWriter.jpg");
        JLabel bg = new JLabel(image);
        add(bg);

        JPanel Panel = new JPanel();
        Panel.setBackground(new Color(0,0,0, 0));
        Panel.setBounds(20,100,320,180);
        Panel.setLayout(new GridBagLayout());
        Insets ins = new Insets(5,5,5,5);
        int anc = GridBagConstraints.WEST;

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel title = new JLabel("Title: ");
        title.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 0;
        gbc.gridy = 0;
        Panel.add(title,gbc);

        gbc = new GridBagConstraints();
        JComboBox comboBox = new JComboBox(q);
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 1;
        gbc.gridy = 0;
        Panel.add(comboBox,gbc);

        gbc = new GridBagConstraints();
        ImageIcon check = new ImageIcon(new ImageIcon("image/check.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton Answer = new JButton("USERS' Answer",check);
        Answer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new readAnswer(comboBox.getSelectedItem().toString());
                    setVisible(false);
                }catch (Exception exception){
                    System.err.println(exception);
                    JOptionPane.showMessageDialog(null,"No one has done now.");
                }

            }
        });
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 0;
        gbc.gridy = 1;
        Panel.add(Answer,gbc);

        gbc = new GridBagConstraints();
        ImageIcon plus = new ImageIcon(new ImageIcon("image/plus-icon-13079.png").getImage().getScaledInstance(28, 25, Image.SCALE_SMOOTH));
        JButton Build = new JButton("Create",plus);
        Build.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CREATOR();
                setVisible(false);
            }
        });
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 0;
        gbc.gridy = 2;
        Panel.add(Build,gbc);

        gbc = new GridBagConstraints();
        ImageIcon back = new ImageIcon(new ImageIcon("image/back-button.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton backButton = new JButton("",back);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new WindowsMain();
            }
        });
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 0;
        gbc.gridy = 3;
        Panel.add(backButton,gbc);

//////////////////////////////////////////////////////////////////////////////////////////
        JTable tableTotal = new JTable(){
            public Class getColumnClass(int column) {
                return (column == 2) ? Icon.class : Object.class;
            }
        };

        String[] columns = {"Title","Amount of user","Happiness Level Average"};
        model = new DefaultTableModel();

        model.setColumnIdentifiers(columns);
        tableTotal.setModel(model);
        tableTotal.setBackground(Color.white);
        tableTotal.setForeground(Color.BLACK);
        tableTotal.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
        tableTotal.setRowHeight(30);
        tableTotal.setAutoCreateRowSorter(true);

        JScrollPane scroll = new JScrollPane(tableTotal);
        scroll.setBounds(380,100,540,500);
        readDataBase();
///////////////////////////////////////////////////////////////////////////////////////////

        JTable tableFeeling = new JTable(){
            public Class getColumnClass(int column) {
                return (column == 0) ? Icon.class : Object.class;
            }
        };

        String[] columnsFeeling = {"Emoji" , "Happiness Level"};
        Object[][] rowFeeling = {{Wonderful,"5"},{Great , "4"},{Good,"3"},{Neutral,"2"},{So , "1"}};
        DefaultTableModel modelFeeling = new DefaultTableModel();

        modelFeeling.setColumnIdentifiers(columnsFeeling);
        tableFeeling.setModel(modelFeeling);
        tableFeeling.setBackground(Color.white);
        tableFeeling.setForeground(Color.BLACK);
        tableFeeling.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
        tableFeeling.setRowHeight(30);
        tableFeeling.setAutoCreateRowSorter(true);

        for(Object[] obj : rowFeeling){
            modelFeeling.addRow(obj);
        }
        JScrollPane scrollFeeling = new JScrollPane(tableFeeling);
        scrollFeeling.setBounds(160,200,210,175);

        bg.add(scrollFeeling);
        bg.add(scroll);
        bg.add(Panel);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void readDataBase(){

        Object[] row = {"","",null};
        int amount ;
        File Answer = new File("library Answer");
        Scanner scan = null;
        String[] title = Answer.list();

        int count = 0 ;
        double feeling;
        String str;
        for(int i=0;i<title.length;i++){
            amount = 0;
            feeling = 0;
            try{
                scan = new Scanner(new File("library Answer/"+title[i]));
            }catch (FileNotFoundException e){
                System.err.println(e);

            }

            row[0] = title[i].substring(0, title[i].length()-11);


            while(scan.hasNext()){
                str = scan.nextLine();
                if(str.equals("################################################################")){
                    amount++;
                    count = 0;
                }else if(count == 1){
                    feeling += Integer.valueOf(str.substring(14));
                    count++;
                }
                else if(str.equals("Section Break#####"))
                    count++;

            }
            row[1] = amount;
            if(amount != 0){
                feeling = feeling / amount;
                if (feeling >= 4.5)
                    row[2] = Wonderful;
                else if (feeling >= 3.5)
                    row[2] = Great;
                else if(feeling >= 2.5)
                    row[2] = Good;
                else if(feeling >= 1.5)
                    row[2] = Neutral;
                else
                    row[2] = So;
            }else{
                row[2] = null;

            }


            model.addRow(row);

        }

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FirstFrameWriter();
            }
        });
    }
}
