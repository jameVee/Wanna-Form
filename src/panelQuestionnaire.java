import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class panelQuestionnaire extends JPanel{
    private JLabel question;
    private JRadioButton radioButton1 , radioButton2 , radioButton3 , radioButton4;
    private ButtonGroup group;
    private JCheckBox checkBox1 , checkBox2 , checkBox3 , checkBox4;
    private JTextArea textArea;
    private ArrayList<ArrayList<Component>> Choice = new ArrayList<>();
    private ArrayList<String> questions = new ArrayList<>(), typeEach = new ArrayList<>();
    private Scanner scanQuestion , scanChoice , scanType;
    private File fileQuestions , fileChoice , fileType;
    private String time;

    public panelQuestionnaire(String title) throws Exception {
        fileQuestions = new File("library Test/"+title+"/" + title + ".txt");
        fileChoice = new File("library Test/"+title+"/"+title+"_CHOICE.txt");
        fileType = new File("library Test/"+title+"/"+title+"_TYPE.txt");

        scanQuestion = new Scanner(fileQuestions);
        scanChoice = new Scanner(fileChoice);
        scanType = new Scanner(fileType);

        time = scanQuestion.nextLine();
        scanQuestion.nextLine();
        String s = "" ,str;

        while(scanQuestion.hasNext()){
            str = scanQuestion.nextLine();
            if(str.equals("Section Break#####")){
                questions.add(s);
                s="";
                continue;
            }
            s += str;
        }

        while(scanType.hasNext()){
            typeEach.add(scanType.nextLine());
            scanType.nextLine();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (int i = 0; i < questions.size(); i++) {
            Choice.add(new ArrayList<>());
            question = new JLabel((i + 1) + ".) " + questions.get(i));
            add(question);
            if (typeEach.get(i).equals("Radio Button")) {
                radioButton1 = new JRadioButton(scanChoice.nextLine());
                scanChoice.nextLine();

                radioButton2 = new JRadioButton(scanChoice.nextLine());
                scanChoice.nextLine();

                radioButton3 = new JRadioButton(scanChoice.nextLine());
                scanChoice.nextLine();

                radioButton4 = new JRadioButton(scanChoice.nextLine());
                scanChoice.nextLine();

                group = new ButtonGroup();

                group.add(radioButton1);
                group.add(radioButton2);
                group.add(radioButton3);
                group.add(radioButton4);

                Choice.get(i).add(radioButton1);
                Choice.get(i).add(radioButton2);
                Choice.get(i).add(radioButton3);
                Choice.get(i).add(radioButton4);

                add(radioButton1);
                add(radioButton2);
                add(radioButton3);
                add(radioButton4);


            } else if (typeEach.get(i).equals("Text Area")) {
                textArea = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                textArea.setLineWrap(true);
                add(scrollPane);
                Choice.get(i).add(textArea);
                for(int j=0;j<8;j++){
                    scanChoice.nextLine();
                }
            } else {
                checkBox1 = new JCheckBox(scanChoice.nextLine());
                scanChoice.nextLine();
                checkBox2 = new JCheckBox(scanChoice.nextLine());
                scanChoice.nextLine();
                checkBox3 = new JCheckBox(scanChoice.nextLine());
                scanChoice.nextLine();
                checkBox4 = new JCheckBox(scanChoice.nextLine());
                scanChoice.nextLine();

                add(checkBox1);
                add(checkBox2);
                add(checkBox3);
                add(checkBox4);

                Choice.get(i).add(checkBox1);
                Choice.get(i).add(checkBox2);
                Choice.get(i).add(checkBox3);
                Choice.get(i).add(checkBox4);

            }
        }
        add(new JPanel());
    }

    public void saveData(saveData save){
        for(int i=0;i<Choice.size();i++){
            for(int j=0;j<Choice.get(i).size();j++){
                Component answer = Choice.get(i).get(j);
                if(answer instanceof JRadioButton){
                    JRadioButton r = (JRadioButton) answer;
                    if(r.isSelected()){
                        save.append(r.getText());
                    }else
                        save.append("");

               }else if(answer instanceof JCheckBox){
                    JCheckBox c = (JCheckBox) answer;
                    if(c.isSelected()){
                        save.append(c.getText());
                    }else
                        save.append("");

                }else{
                    JTextArea t = (JTextArea) answer;
                    save.append(t.getText());
                    save.append("");
                    save.append("");
                    save.append("");
                }
            }
        }
        save.longSectionBreak();
    }

    public ArrayList<ArrayList<Component>> getChoice(){
        return Choice;
    }

    public String getTime() {
        return time;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

    }
}
