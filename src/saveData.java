import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class saveData {
    private File fileMain, fileType , fileChoice;
    private PrintWriter writerMain, writerType , writerChoice;
    public enum Op {create, answer}


    public saveData(String title,Op options) throws IOException {
        if(options.equals(Op.create)){
            fileMain = new File("library Test/"+title);
            if(!fileMain.exists()) {
                fileMain.mkdir();

                fileType = new File(fileMain.getPath()+"/"+title+"_TYPE.txt");
                fileChoice = new File(fileMain.getPath()+"/"+title+"_CHOICE.txt");
                fileMain = new File(fileMain.getPath()+"/"+title+".txt");

                fileType.createNewFile();
                fileChoice.createNewFile();
                fileMain.createNewFile();
            }
            else{
                fileType = new File(fileMain.getPath()+"/"+title+"_TYPE.txt");
                fileChoice = new File(fileMain.getPath()+"/"+title+"_CHOICE.txt");
                fileMain = new File(fileMain.getPath()+"/"+title+".txt");
            }

            writerType = new PrintWriter(fileType);
            writerChoice = new PrintWriter(fileChoice);
            writerMain = new PrintWriter(fileMain);

        }else if(options.equals(Op.answer)){
            fileMain = new File("library Answer/"+title+"_ANSWER.txt");
            if(!fileMain.exists()){
                fileMain.createNewFile();
                writerMain = new PrintWriter(new FileWriter("library Answer/"+title+"_ANSWER.txt",true));
            }else{
                writerMain = new PrintWriter(new FileWriter("library Answer/"+title+"_ANSWER.txt",true));
            }
        }


    }
    public void printlnMain(String str){
        writerMain.println(str);
        writerMain.println("Section Break#####");
    }

    public void printlnChoice(String str){
        writerChoice.println(str);
        writerChoice.println("Section Break#####");
    }

    public void printlnType(String str){
        writerType.println(str);
        writerType.println("Section Break#####");
    }

    public void append(String str){
        writerMain.append(str+"\n");
        writerMain.append("Section Break#####\n");
    }

    public void longSectionBreak(){
        writerMain.println("################################################################");
    }


    public void close(){
        writerMain.close();
        if(writerType != null && writerChoice != null){
            writerType.close();
            writerChoice.close();
        }
    }
}
