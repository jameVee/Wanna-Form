import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class clockPanel extends JPanel {
    JLabel timeLabel = new JLabel();
    DateTimeFormatter timeForm = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime timeLimit;
    protected boolean haveTime = true;
    private clock myClock;

    public clockPanel(String t){
        setLayout(new FlowLayout());


        if(t.equals("3 hours")) {
            timeLimit = LocalTime.of(3,0,0);
        }else if(t.equals("2 hours 30 minutes")) {
            timeLimit = LocalTime.of(2,30,0);
        }else if(t.equals("2 hours")){
            timeLimit = LocalTime.of(2,0,0);
        }else if(t.equals("1 hours 30 minutes")){
            timeLimit = LocalTime.of(1,30,0);
        }else if(t.equals("1 hour")){
            timeLimit = LocalTime.of(1,0,0);
        }else if(t.equals("30 minutes")){
            timeLimit = LocalTime.of(0,30,0);
        }else if (t.equals("15 minutes")){
            timeLimit = LocalTime.of(0,15,0);
        }else{
            timeLimit = LocalTime.of(0,0,0);
            haveTime = false;
        }

        JLabel l = new JLabel("Time: ");
        l.setFont(new Font("5012_tLU_infection",Font.PLAIN,20));
        add(l);

        if (haveTime){
            timeLabel = new JLabel(this.timeForm.format(timeLimit));
            timeLabel.setFont(new Font("5012_tLU_infection",Font.PLAIN,20));
            add(timeLabel);

            myClock = new clock(timeLimit);
            myClock.start();
        }else{
            timeLabel.setText("No Limits");
            add(timeLabel);
        }

        setVisible(true);

    }

    public void checkTime(){

        repaint();
        LocalTime t = myClock.getTime();
        timeLabel.setText(timeForm.format(t));
        if((t.equals(LocalTime.of(0,0,0)) && haveTime)){
            haveTime = false;
            Save();
        }

    }

    public abstract void Save();



    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(haveTime){
            checkTime();
        }
    }

    public void stopTime(){
        myClock.interrupt();
    }

}
