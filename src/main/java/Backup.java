import java.io.*;

public class Backup {

    public static void main(String[] args) {
        String sourceDirectory = "C:\\Users\\gepat\\Documents\\training at GeekBrains\\Java Core\\homeworks\\homework_05";
        String backupDirectory = "C:\\Users\\gepat\\Documents\\training at GeekBrains\\Java Core\\homeworks\\homework_05\\backup";

        copyDirectory(new File(sourceDirectory), new File(backupDirectory));
    }

    static void copyDirectory(File sourceDir, File targetDir) {
        if (!sourceDir.isDirectory()) {
            System.out.println("Не является папкой.");
            return;
        }

        targetDir.mkdir();

        File[] files = sourceDir.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isHidden() || (file.isDirectory() && file.getName().equals("backup"))) {
                continue;
            }

            File destination = new File(targetDir, file.getName());
            if (file.isDirectory()) {
                copyDirectory(file, destination);
            } else {
                try (InputStream in = new FileInputStream(file);
                     OutputStream out = new FileOutputStream(destination)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                    System.out.println("Скопировано: " + file.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
