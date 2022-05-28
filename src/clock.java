import java.time.LocalTime;

public class clock extends Thread{
    private LocalTime timeLimit;
    public clock(LocalTime Limit){
        this.timeLimit = Limit;
    }
    @Override
    public void run(){
        while(!timeLimit.equals(LocalTime.of(0,0,0))){
            timeLimit = timeLimit.minusSeconds(1);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Interrupted clock");
                break;
            }
            FaceDetection.swapCheck();
        }
    }

    public LocalTime getTime(){
        return timeLimit;
    }


}
