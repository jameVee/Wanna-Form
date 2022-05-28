import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;

public abstract class FaceDetection extends JFrame{
    private JLabel lb;
    private int LIMIT = 60 ;
    private float smile = 0;
    private static boolean Run = true , test = false;
    private static boolean check = false;
    private String time = "";

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public FaceDetection(String t){
        this.time = t;
        setLayout(null);
        lb = new JLabel();
        lb.setBounds(0, 0, 640, 480);
        add(lb);
        setVisible(true);
        setSize(640,480);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Thread another = new Thread(){
            @Override
            public void run(){
                Run();
            }
        };
        another.start();



        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("amount of smile : "+smile);
                if(!test)
                    System.out.println("happiness level : "+getSmile());
                super.windowClosing(e);
            }
        });

    }



    public int detectAndDisplay(Mat frame, CascadeClassifier faceCascade, CascadeClassifier smileCascade) {

        Mat frameGray = new Mat();
        Imgproc.cvtColor(frame, frameGray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(frameGray, frameGray);
        // -- Detect faces
        MatOfRect faces = new MatOfRect();
        faceCascade.detectMultiScale(frameGray, faces);

        List<Rect> listOfFaces = faces.toList();
        List<Rect> listOfSmiles = null;
        if (test) {
            Imgproc.putText(frame,"Warning : You should adjust your camera to detect all the time.",new Point(10,frame.height()-50),Imgproc.FONT_HERSHEY_DUPLEX,0.57,new Scalar(0, 0, 255));
        }

        for (Rect face : listOfFaces) {
            Point point1 = new Point(face.x, face.y - 25);
            Point point2 = new Point(face.x + face.width, face.y + face.height + 25);
            Imgproc.rectangle(frame, point1, point2, new Scalar(255, 0, 255), 0, 0, 0);
            Imgproc.putText(frame,"found a face",point1,Imgproc.FONT_HERSHEY_DUPLEX,0.7,new Scalar(0, 255, 0));



            Mat faceROI = frameGray.submat(face);
            // -- In each face, detect smiles
            MatOfRect smiles = new MatOfRect();
            smileCascade.detectMultiScale(faceROI, smiles,1.5,5);
            listOfSmiles = smiles.toList();


            for (Rect smile : listOfSmiles) {
                if (listOfSmiles.size() > 1) {
                    Point mouthCenter = new Point(face.x + smile.x + smile.width / 2, face.y + smile.y + smile.height / 2);
                    int radius = (int) Math.round((smile.width + smile.height) * 0.25);
                    Imgproc.circle(frame, mouthCenter, radius, new Scalar(255, 0, 0), 4);
                    break;
                }

            }
            if(listOfSmiles.size() > 1 && check){
                smile++;
            }

        }


        final MatOfByte buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", frame, buf);
        byte[] imageData = buf.toArray();
        lb.setIcon(new ImageIcon(imageData));

        if(check){
            if(listOfFaces.size() == 0 && !test){
                LIMIT--;
                System.out.println("Limit left: "+LIMIT);
                swapCheck();
                return -1;
            }else if(listOfSmiles.size() > 1 && check){
                smile++;
            }

        }

        //-- Show what you got
        return 0;
    }

    public void Run() {
        String filenameFaceCascade = "haarcascade_frontalface_alt.xml";
        String filenameSmileCascade = "haarcascade_smile.xml";

        CascadeClassifier faceCascade = new CascadeClassifier();
        CascadeClassifier smileCascade = new CascadeClassifier();


        if (!faceCascade.load(filenameFaceCascade)) {
            System.err.println("Error loading face cascade: " + filenameFaceCascade);
            System.exit(0);
        }
        if (!smileCascade.load(filenameSmileCascade)) {
            System.err.println("Error loading eyes cascade: " + filenameSmileCascade);
            System.exit(0);
        }

        VideoCapture capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.err.println("Error opening video capture");
            System.exit(0);
        }
        Mat frame = new Mat();
        int i = 0;


        while (capture.read(frame) && Run) { // เอา mat เเต่ ลง frame
            //System.out.println("ทำงานอยู่");
            if (frame.empty()) {
                System.err.println(" No captured frame -- Break!");
                break;
            }

            if (detectAndDisplay(frame, faceCascade, smileCascade) < 0) {
                System.out.println("Not found a face :" + LIMIT);
            }

            if (!test) {
                if (LIMIT <= 0) {
                    out();
                    break;
                }
            }
        }
        setVisible(false);
        capture.release();
    }

    public static void swapCheck() {
        check = (check == true) ? false:true;

    }

    public abstract void out();

    public static void setRun(boolean r){Run = r;}

    public void setTest(boolean b){
        test = b;
    }

    public int getLIMIT(){
        return this.LIMIT;
    }

    public int getSmile(){
        int max;
        float percent;
        if(time.equals("3 hours"))
            max = 10_800;
        else if(time.equals("2 hours 30 minutes"))
            max = 9_000;
        else if(time.equals("2 hours"))
            max = 7_200;
        else if(time.equals("1 hours 30 minutes"))
            max = 5_400;
        else if(time.equals("1 hour"))
            max = 3600;
        else if(time.equals("30 minutes"))
            max = 1_800;
        else
            max = 900;


        percent = (this.smile / max) * 100;
        System.out.printf("%f / %d * 100 = %f%n ",this.smile,max,percent);
        if(percent > 80)
            return 5;
        else if(percent > 60)
            return 4;
        else if(percent > 40)
            return 3;
        else if(percent > 20)
            return 2;
        else
            return 1;

    }


    public static void main(String[] args) {
        FaceDetection test = new FaceDetection(""){
            @Override
            public void out(){

            }
        };
        test.setTest(true);
    }

}
