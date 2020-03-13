import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {

        File fileToWrite = new File(System.getProperty("user.dir"), "fileToWrite.txt");

        if(fileToWrite.exists()){
            fileToWrite.delete();
            fileToWrite.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(fileToWrite,true);
        FileWriteThread.fileWriter=fileWriter;

        FileWriteThread threadA = new FileWriteThread("Поток А");
        FileWriteThread threadB = new FileWriteThread("Поток Б");

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        fileWriter.close();
    }
}
