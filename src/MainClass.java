import java.io.*;

public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        File fileToWrite = new File(System.getProperty("user.dir"), "fileToWrite.txt");
        if (fileToWrite.exists()) {
            fileToWrite.delete();
            fileToWrite.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(fileToWrite, true);
        FileWriteThread.fileWriter = fileWriter;

        FileWriteThread threadA = new FileWriteThread("Поток А");
        FileWriteThread threadB = new FileWriteThread("Поток Б");

        String s;
        while (!"exit".equals(s = reader.readLine())) {
            if ("start".equals(s)) {
                threadA.start();
                threadB.start();
                break;
            }
        }

        threadA.join();
        threadB.join();

        reader.close();
        fileWriter.close();
    }
}
