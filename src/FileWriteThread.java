import java.io.FileWriter;
import java.io.IOException;

public class FileWriteThread extends Thread {
    private String name;
    public static FileWriter fileWriter;
    private static boolean endWrite = false;

    public FileWriteThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                write(name, i + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void write(String name, int number) throws IOException {
        fileWriter.write(name + ": " + number + "\n");
        if (number == 100) {
            if (!endWrite) {
                fileWriter.write(name + ": выиграл\n");
                endWrite = true;
            } else fileWriter.write(name + ": проирал\n");
        }
    }
}
