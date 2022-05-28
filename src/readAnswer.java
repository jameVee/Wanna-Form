import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class readAnswer extends JFrame {
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<ArrayList<String>> answer = new ArrayList<>();
    private ArrayList<ArrayList<Component>> Choice;
    JComboBox cb;
    panelQuestionnaire panelMain;

    public readAnswer(String title) throws Exception {
        super(title);
        readData(title); // อ่านข่อมูลคำตอบ
        JPanel Top = new JPanel();
        JLabel List = new JLabel("Name List: ");
        cb = new JComboBox(nameList.toArray());
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                changeStatus(cb.getSelectedItem().toString());
            }
        });
        Top.add(List);
        Top.add(cb);
        add(Top, BorderLayout.NORTH);

        panelMain = new panelQuestionnaire(title);

        JScrollPane scroll = new JScrollPane(panelMain,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);
        Choice = panelMain.getChoice();


        changeStatus(cb.getSelectedItem().toString());

        for(ArrayList i: answer){
            System.out.println(Arrays.toString(i.toArray()));
        }

        ImageIcon iconBack = new ImageIcon(new ImageIcon("image/back-button.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JButton back = new JButton("Back", iconBack);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new FirstFrameWriter();
            }
        });
        add(back, BorderLayout.SOUTH);

        setResizable(false);
        setSize(700, 725);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void readData(String title) throws Exception {
        Scanner scanAnswer = new Scanner(new File("library Answer/" + title + "_ANSWER.txt"));
        String check, s = "";
        int i = 0, number = 0 ;

        while (scanAnswer.hasNext()) {
            check = scanAnswer.nextLine();
            if (check.equals("################################################################")) {
                i = 0;
                number++;
            } else if (check.equals("Section Break#####") && (i != 0)) {
                answer.get(number).add(s);
                s = "";
            } else if (i == 0) {
                nameList.add(check);
                scanAnswer.nextLine();
                scanAnswer.nextLine();
                scanAnswer.nextLine();
                answer.add(new ArrayList());
                i++;
            }else {
                s += check;
            }
        }
    }

    public void changeStatus(String cb) {
        int index = nameList.indexOf(cb);
        int number = 0;
        String a;
        for (int i = 0; i < Choice.size(); i++) {
            for (int j = 0; j < Choice.get(i).size(); j++) {
                Component check = Choice.get(i).get(j);
                a = answer.get(index).get(number);
                if (check instanceof JRadioButton) {
                    JRadioButton b = (JRadioButton) check;
                    b.setSelected(false);
                    if (b.getText().equals(a)) {
                        b.setSelected(true);
                    }

                } else if (check instanceof JCheckBox) {
                    JCheckBox c = (JCheckBox) check;
                    c.setSelected(false);
                    if (c.getText().equals(a)) {
                        c.setSelected(true);

                    }

                } else if (check instanceof JTextArea) {
                    JTextArea t = (JTextArea) check;
                    t.setText(a);
                    number = number + 3;
                }
                number++;

            }
        }

    }

}
