import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CREATOR extends JFrame {
    private JTextField textFieldTitle;
    private JTextField textFieldAmountQuestion;
    private JPanel panelSetting ;
    private panel1question panelSingleQuestion;
    private panel2questions panelMultiQuestion;

    private int PAGE_UP = 1, PAGE_DOWN = 2;

    private CardLayout c = new CardLayout();
    private JPanel CenterPanel = new JPanel(c) ;

    private saveData save;

    private JButton buttonNext, buttonApply;

    private int amount;
    private JComboBox comboTime;


    public CREATOR() {
        setTitle("Test Writer");


        // การตั้งค่า
        panelSetting = new JPanel();
        panelSetting.setBackground(Color.ORANGE);
        panelSetting.setBorder(BorderFactory.createTitledBorder("Setting"));

        JLabel labelChoiceAmount = new JLabel("Amount of Question : ");
        textFieldAmountQuestion = new JTextField(3);

        JLabel title = new JLabel("Title : ");
        textFieldTitle = new JTextField(10);

        JLabel labelTime = new JLabel("times : ");
        String[] str = {"No limits","15 minutes", "30 minutes", "1 hour", "1 hours 30 minutes", "2 hours", "2 hours 30 minutes", "3 hours"};
        comboTime = new JComboBox(str);
        buttonApply = new JButton("Apply");
        buttonApply.addActionListener(new ButtonHandler());

        panelSetting.add(title);            //ชื่อเเบบสอบถาม
        panelSetting.add(textFieldTitle);

        panelSetting.add(labelChoiceAmount);    //จ.น. ข้อ
        panelSetting.add(textFieldAmountQuestion);

        panelSetting.add(labelTime);            //เวลา
        panelSetting.add(comboTime);

        panelSetting.add(buttonApply);
        add(panelSetting, BorderLayout.NORTH);

        panelSingleQuestion = new panel1question();
        panelMultiQuestion = new panel2questions();
        CenterPanel.add(panelSingleQuestion,"single");
        CenterPanel.add(panelMultiQuestion,"Both");
        CenterPanel.add(new JPanel(),"Blank");
        c.show(CenterPanel,"Blank");
        add(CenterPanel, BorderLayout.CENTER);

        setResizable(false);
        setSize(700, 725);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String txt = e.getActionCommand();
            if (txt.equals("Apply")) {
                String title = textFieldTitle.getText() , a = textFieldAmountQuestion.getText();
                if(!title.isBlank() && !a.isBlank()){
                    try {
                        amount = Integer.valueOf(a);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "amount must be integer.", "warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    amount = Math.abs(Integer.valueOf(a));
                    if (amount > 99) {
                        JOptionPane.showMessageDialog(null, "Question amount maximum is 99.");
                        amount = 0;
                        return;
                    }
                    if (amount > 0) {
                        buttonApply.setEnabled(false);
                        buttonNext = new JButton("Next");
                        buttonNext.addActionListener(new ButtonHandler());
                        setCenterPanel();

                        add(buttonNext, BorderLayout.SOUTH);
                        try {
                            save = new saveData(title, saveData.Op.create);
                            save.printlnMain(comboTime.getSelectedItem().toString());
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(null, "Error!!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        textFieldTitle.setEnabled(false);
                        textFieldAmountQuestion.setEnabled(false);
                        comboTime.setEnabled(false);
                    }
                }else
                    JOptionPane.showMessageDialog(null, "Please fill out all information.", "warning", JOptionPane.WARNING_MESSAGE);

            } else if (txt.equals("Next")) {
                panelMultiQuestion.save(save);
                PAGE_UP = PAGE_UP + 2;
                PAGE_DOWN = PAGE_DOWN + 2;
                setCenterPanel();


            } else if (txt.equals("Finish")) {
                if(PAGE_UP == amount){
                    panelSingleQuestion.save(save);
                }else{
                    panelMultiQuestion.save(save);
                }
                JOptionPane.showMessageDialog(null, "successful!!", "warning", JOptionPane.PLAIN_MESSAGE);
                save.close();
                setVisible(false);
                new FirstFrameWriter();

            }

        }
    }

    public void setCenterPanel() {
        panelSingleQuestion.clear();
        panelMultiQuestion.clear();
        if (amount == 1) {
            panelSingleQuestion.setNumber(1);
            buttonNext.setText("Finish");
            c.show(CenterPanel,"single");
        } else if (PAGE_UP == amount){
            panelSingleQuestion.setNumber(PAGE_UP);
            c.show(CenterPanel,"single");
            buttonNext.setText("Finish");
        } else if(PAGE_DOWN == amount){
            panelMultiQuestion.setNumber(PAGE_UP, PAGE_DOWN);
            c.show(CenterPanel,"Both");
            buttonNext.setText("Finish");
        } else{
            panelMultiQuestion.setNumber(PAGE_UP, PAGE_DOWN);
            c.show(CenterPanel,"Both");
        }
    }

    public static class panel1question extends JPanel {
        private JLabel  label;
        private JTextArea textArea;
        private JTextField textField1,textField2,textField3,textField4;
        private JComboBox comboBox;


        public panel1question(){
            setBackground(Color.PINK);
            label = new JLabel();
            String[] typeChoice = {"Radio Button", "Text Area", "CheckBox"};
            comboBox = new JComboBox(typeChoice);

            textArea = new JTextArea(10,47);
            textArea.setLineWrap(true);
            JScrollPane scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


            JLabel one = new JLabel("1.) ");
            JLabel two = new JLabel("2.) ");
            JLabel three = new JLabel("3.) ");
            JLabel four = new JLabel("4.) ");
            textField1 = new JTextField(58);
            textField2 = new JTextField(58);
            textField3 = new JTextField(58);
            textField4 = new JTextField(58);

            comboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getItem().toString() == "Text Area") {
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        textField4.setText("");

                        textField1.setEnabled(false);
                        textField2.setEnabled(false);
                        textField3.setEnabled(false);
                        textField4.setEnabled(false);
                    }
                }
            });

            add(label);
            add(scroll);
            add(comboBox);
            add(one);add(textField1);
            add(two);add(textField2);
            add(three);add(textField3);
            add(four);add(textField4);

        }

        public void setNumber(int number) {
            label.setText(number+".) ");
            if (number >= 10) {
                textArea.setColumns(46);
            }

        }

        public void clear(){
            textArea.setText("");
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
        }


        public void save(saveData save){
            save.printlnType(comboBox.getSelectedItem().toString());
            save.printlnMain(textArea.getText());
            save.printlnChoice(textField1.getText());
            save.printlnChoice(textField2.getText());
            save.printlnChoice(textField3.getText());
            save.printlnChoice(textField4.getText());
        }
    }

    public static class panel2questions extends JPanel {
        private JLabel  labelTop , labelBelow;
        private JTextArea textAreaTop , textAreaBelow;
        private JTextField textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8;
        private JComboBox comboBoxTop , comboBoxBelow;

        public panel2questions(){
            setBackground(Color.PINK);
            String[] typeChoice = {"Radio Button", "Text Area", "CheckBox"};
            comboBoxTop = new JComboBox(typeChoice);
            comboBoxBelow = new JComboBox(typeChoice);

            labelTop = new JLabel();
            labelBelow = new JLabel();
            textAreaTop = new JTextArea(10,47);
            textAreaTop.setLineWrap(true);
            textAreaBelow = new JTextArea(10,47);
            textAreaBelow.setLineWrap(true);
            JScrollPane scrollTOP = new JScrollPane(textAreaTop,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            JScrollPane scrollBelow = new JScrollPane(textAreaBelow,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            JLabel one = new JLabel("1.) ");
            JLabel two = new JLabel("2.) ");
            JLabel three = new JLabel("3.) ");
            JLabel four = new JLabel("4.) ");
            JLabel five = new JLabel("1.) ");
            JLabel six = new JLabel("2.) ");
            JLabel seven = new JLabel("3.) ");
            JLabel eight = new JLabel("4.) ");


            textField1 = new JTextField(58);
            textField2 = new JTextField(58);
            textField3 = new JTextField(58);
            textField4 = new JTextField(58);
            textField5 = new JTextField(58);
            textField6 = new JTextField(58);
            textField7 = new JTextField(58);
            textField8 = new JTextField(58);


            comboBoxTop.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getItem().toString() == "Text Area") {
                        textField1.setEnabled(false);
                        textField2.setEnabled(false);
                        textField3.setEnabled(false);
                        textField4.setEnabled(false);
                    }
                    else{
                        textField1.setEnabled(true);
                        textField2.setEnabled(true);
                        textField3.setEnabled(true);
                        textField4.setEnabled(true);
                    }
                }
            });

            comboBoxBelow.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getItem().toString() == "Text Area") {
                        textField5.setEnabled(false);
                        textField6.setEnabled(false);
                        textField7.setEnabled(false);
                        textField8.setEnabled(false);
                    }else
                    {
                        textField5.setEnabled(true);
                        textField6.setEnabled(true);
                        textField7.setEnabled(true);
                        textField8.setEnabled(true);
                    }
                }
            });

            add(labelTop);
            add(scrollTOP);
            add(comboBoxTop);
            add(one);   add(textField1);
            add(two);   add(textField2);
            add(three); add(textField3);
            add(four);  add(textField4);

            add(labelBelow);
            add(scrollBelow);
            add(comboBoxBelow);
            add(five);  add(textField5);
            add(six);   add(textField6);
            add(seven); add(textField7);
            add(eight); add(textField8);

        }

        public void setNumber(int Top , int Below){
            labelTop.setText(Top+".) ");
            labelBelow.setText(Below+".) ");
            if (Top >= 10) {
                textAreaTop.setColumns(46);
            }
            if(Below >= 10){
                textAreaBelow.setColumns(46);
            }
        }

        public void clear(){
            textAreaTop.setText("");
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");

            textAreaBelow.setText("");
            textField5.setText("");
            textField6.setText("");
            textField7.setText("");
            textField8.setText("");
        }

        public void save(saveData save){
            save.printlnType(comboBoxTop.getSelectedItem().toString());
            save.printlnMain(textAreaTop.getText());
            save.printlnChoice(textField1.getText());
            save.printlnChoice(textField2.getText());
            save.printlnChoice(textField3.getText());
            save.printlnChoice(textField4.getText());

            save.printlnType(comboBoxBelow.getSelectedItem().toString());
            save.printlnMain(textAreaBelow.getText());
            save.printlnChoice(textField5.getText());
            save.printlnChoice(textField6.getText());
            save.printlnChoice(textField7.getText());
            save.printlnChoice(textField8.getText());
        }

    }
}
