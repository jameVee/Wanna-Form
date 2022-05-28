import javax.swing.*;
import java.awt.*;


public class question extends JFrame {
    private JButton submit;
    clockPanel clockPanel;
    FaceDetection faceDetect;


    public question(String title, String fName, String lName, String ID) throws Exception{
        super(title);
        panelQuestionnaire panelQ = new panelQuestionnaire(title);


        saveData save = new saveData(title, saveData.Op.answer);
        submit = new JButton("Submit");

        JScrollPane scrollPane = new JScrollPane(panelQ,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        clockPanel = new clockPanel(panelQ.getTime()) {
            @Override
            public void Save() {
                JOptionPane.showMessageDialog(panelQ,"Time Out","Warning",JOptionPane.WARNING_MESSAGE);
                try {
                    save.printlnMain(String.format("%s  %s  ID: %s", fName, lName, ID));
                    save.printlnMain(String.format("Happy index = %d",faceDetect.getSmile()));
                    panelQ.saveData(save);
                    save.close();
                } catch (Exception exception) {
                    System.err.print(exception);
                }
                FaceDetection.setRun(false);
                close();

            }
        };

        submit.addActionListener(e -> {
            int c = JOptionPane.showConfirmDialog(null, "Are you sure to submit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (c == JOptionPane.OK_OPTION) {
                try {
                    clockPanel.haveTime = false;
                    save.printlnMain(String.format("%s  %s  ID: %s", fName, lName, ID));
                    save.printlnMain(String.format("Happy index = %d",faceDetect.getSmile()));
                    panelQ.saveData(save);
                    save.close();
                } catch (Exception exception) {
                    System.err.print(exception);
                }
                clockPanel.stopTime();
                FaceDetection.setRun(false);
                close();

            }
        });


        Thread c = new Thread() {
            @Override
            public void run() {
                faceDetect = new FaceDetection(panelQ.getTime()) {
                    @Override
                    public void out() {
                        if (getLIMIT() <= 0) {
                            JOptionPane.showMessageDialog(null, "You are missing.", "Warning", JOptionPane.WARNING_MESSAGE);
                            clockPanel.stopTime();
                            close();
                        }
                    }
                };
                faceDetect.setRun(true);
                faceDetect.setTest(false);
            }
        };
        c.start();

        add(clockPanel,BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(submit, BorderLayout.SOUTH);

        setResizable(false);
        setSize(700, 725);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    public void close(){
        this.setVisible(false);
        new FirstFrameUSER();
    }


}




