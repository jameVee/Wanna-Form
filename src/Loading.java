import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Loading extends JFrame{
    public Loading(){

        ImageIcon image = new ImageIcon("image/loading.gif");
        JLabel bg = new JLabel();
        bg.setIcon(image);

        add(bg, BorderLayout.CENTER);

        setSize(500,320);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);


        for(int i=0 ; i<2 ; i++){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.print(e);
            }
        }
        setVisible(false);
    }

    public static void main(String[] args) {
        new Loading();

    }
}