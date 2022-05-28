import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class WindowsMain extends JFrame {

    public WindowsMain(){
        super("Welcome");

        JLabel i = new JLabel();
        ImageIcon image = new ImageIcon(new ImageIcon("image/ezgif.com-gif-maker.gif").getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT));
        i.setIcon(image);
        add(i);

        JLabel name = new JLabel("Wanna  FORM");
        name.setFont(new Font("Freehand521 BT",Font.PLAIN,50));
        name.setBounds(60,240,400,100);
        i.add(name);

        JLabel details = new JLabel("Questionnaire For Everyone.");
        details.setFont(new Font("Freehand521 BT",Font.PLAIN,25));
        details.setBounds(100,290,300,150);
        i.add(details);


        JButton User = new JButton("USER");
        User.setBounds(120,410,100,50);
        i.add(User);
        User.addActionListener(e -> {
            setVisible(false);
            new FirstFrameUSER();
        });
        User.addMouseListener(new MouseAdapter() {
            JLabel describe = new JLabel();
            @Override
            public void mouseEntered(MouseEvent e) {
                describe.setText("DESCRIBE : Answer the Creator's questionnaire.");
                describe.setFont(new Font("2006_iannnnnBKK",Font.PLAIN,30));
                describe.setForeground(Color.RED);
                describe.setBounds(120,450,480,100);
                i.add(describe);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                describe.setText("");
            }
        });




        JButton Writer = new JButton("CREATOR");
        Writer.setBounds(270,410,100,50);
        i.add(Writer);
        Writer.addActionListener(e -> {
            setVisible(false);
            new FirstFrameWriter();

        });
        Writer.addMouseListener(new MouseAdapter() {
            JLabel describe = new JLabel();
            @Override
            public void mouseEntered(MouseEvent e) {
                describe.setText("DESCRIBE : Create a questionnaire for users to answer.");
                describe.setFont(new Font("2006_iannnnnBKK",Font.PLAIN,30));
                describe.setForeground(Color.RED);
                describe.setBounds(120,450,480,100);
                i.add(describe);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                describe.setText("");
            }
        });

        JLabel credit = new JLabel("Create by Weerathep Rattanajaratkul.");
        credit.setFont(new Font("Freehand521 BT",Font.PLAIN,20));
        credit.setBounds(650,490,500,300);
        i.add(credit);

        JLabel version = new JLabel("Version 1.0.0");
        version.setFont(new Font("Arial",Font.PLAIN,18));
        version.setForeground(Color.WHITE);
        version.setBounds(865,525,500,300);
        i.add(version);





        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new WindowsMain();
    }
}