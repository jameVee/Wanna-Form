import java.awt.*;
import javax.swing.*;
import java.io.File;

public class FirstFrameUSER extends JFrame{
    FaceDetection test;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FirstFrameUSER();
            }
        });
    }
    public FirstFrameUSER(){
        super("Questionnaire");
        setResizable(false);
        File file = new File("library Test");
        String[] q = file.list();


        JPanel panelDetails = new JPanel();
        ImageIcon image = new ImageIcon(new ImageIcon("image/colorful-holographic-gradient-background-design-vector.jpg").getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH));
        JLabel background = new JLabel("",image,JLabel.CENTER);
        //background.setBounds(0,0,580,400);
        panelDetails.add(background);

        background.setLayout(new GridBagLayout());
        Font font = new Font("Aldhabi",Font.BOLD,25);
        Insets ins = new Insets(5,5,5,5);
        int anc = GridBagConstraints.WEST;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = anc;
        gbc.insets = ins;
        JLabel main = new JLabel("Questionnaire");
        main.setFont(new Font("Aldhabi",Font.BOLD,35));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        background.add(main,gbc);

        JLabel firstNameLabel = new JLabel("Firstname: ");
        firstNameLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        background.add(firstNameLabel,gbc);


        gbc = new GridBagConstraints();
        JTextField fnTextField = new JTextField();
        fnTextField.setPreferredSize(new Dimension(150,25));
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        background.add(fnTextField,gbc);

        gbc = new GridBagConstraints();
        JLabel lastNameLabel = new JLabel("Lastname: ");
        lastNameLabel.setFont(font);
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 0;
        gbc.gridy = 2;
        background.add(lastNameLabel,gbc);

        gbc = new GridBagConstraints();
        JTextField lnTextField = new JTextField();
        lnTextField.setPreferredSize(new Dimension(150,25));
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        background.add(lnTextField,gbc);

        gbc = new GridBagConstraints();
        JLabel IDLabel = new JLabel("ID: ");
        IDLabel.setFont(font);
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 0;
        gbc.gridy = 3;
        background.add(IDLabel,gbc);

        gbc = new GridBagConstraints();
        JTextField idTextField = new JTextField();
        idTextField.setPreferredSize(new Dimension(150,25));
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        background.add(idTextField,gbc);

        gbc = new GridBagConstraints();
        JLabel details = new JLabel("Title:");
        details.setFont(font);
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 0;
        gbc.gridy = 4;
        background.add(details,gbc);

        gbc = new GridBagConstraints();
        JComboBox cb = new JComboBox(q);
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        background.add(cb,gbc);

        ImageIcon camera = new ImageIcon(new ImageIcon("image/camera.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
        ImageIcon nonCamera = new ImageIcon(new ImageIcon("image/noun_No Camera_2073307.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
        gbc = new GridBagConstraints();
        JButton checkButton = new JButton("Check",nonCamera);
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 1;
        gbc.gridy = 5;
        background.add(checkButton,gbc);


        checkButton.addActionListener(e ->{
            if(checkButton.getText().equals("Check")){
                Thread c = new Thread(){
                    @Override
                    public void run(){
                        test = new FaceDetection(null){
                            @Override
                            public void out(){}

                        };
                        test.setRun(true);
                        test.setTest(true);

                    }
                };
                c.start();
                checkButton.setIcon(camera);
                checkButton.setText("Close");
            }else{
                test.setRun(false);
                checkButton.setText("Check");
                checkButton.setIcon(nonCamera);
            }
        });

        gbc = new GridBagConstraints();
        ImageIcon backicon = new ImageIcon(new ImageIcon("image/back-button.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
        JButton backButton = new JButton("",backicon);
        backButton.addActionListener(e -> {
            if(test != null)
                test.setRun(false);
            setVisible(false);
            new WindowsMain();
        });
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 0;
        gbc.gridy = 5;
        background.add(backButton,gbc);


        gbc = new GridBagConstraints();
        JButton startButton = new JButton("Start");
        gbc.anchor = anc;
        gbc.insets = ins;
        gbc.gridx = 2;
        gbc.gridy = 5;
        background.add(startButton,gbc);

        startButton.addActionListener(e -> {
            String fn = fnTextField.getText() , ln = lnTextField.getText() , id = idTextField.getText();
            if(!fn.isBlank() && !ln.isBlank() && !id.isBlank()){
                setVisible(false);
                if(test != null)
                    test.setRun(false);
                try{
                    new question(cb.getSelectedItem().toString(),fn,ln,id);
                }catch (Exception exception){
                    System.out.println(exception);
                    JOptionPane.showMessageDialog(null,"Error");
                    setVisible(true);
                }
            }else
                JOptionPane.showMessageDialog(null,"Please fill out all information.");


        });



        add(panelDetails);

        setSize(580,445);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
