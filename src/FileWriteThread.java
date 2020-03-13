import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileWriteThread extends Thread {
    private String name;
    public static FileWriter fileWriter;
    public static AtomicBoolean endWrite = new AtomicBoolean(false);

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
        try {
            if (endWrite.getAndSet(true)) {
                fileWriter.write(name + ": проиграл\n");
            } else fileWriter.write(name + ": выиграл\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String name, int number) throws IOException {
        fileWriter.write(name + ": " + number + "\n");
    }
}
