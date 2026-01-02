package utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public final class FileUploadUtils {
    private FileUploadUtils() {
    }

    public static void uploadFileViaExplorer(String absolutePath) {
        try {
            StringSelection ss = new StringSelection(absolutePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

            Robot robot = new Robot();
            robot.setAutoDelay(200);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.setAutoDelay(200);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(500);
        } catch (AWTException | InterruptedException e) {
            throw new RuntimeException("Failed to upload file via explorer", e);
        }
    }
}
