package classes;

import interfaces.EventLogger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Maksym_Petrenko on 8/18/2017.
 */
public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void init() throws IOException{
    this.file = new File(filename);
        file.canWrite();
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(new File(filename),event.toString(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
