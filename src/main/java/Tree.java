import java.io.File;

public class Tree {

    /**
     * TODO: Доработать метод print, необходимо распечатывать директории и файлы
     * @param args
     */
    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory())
            {
                subDirTotal++;
            }
        }

        int subDirCounter = 0;
        for (File f : files) {
            boolean isSubDir = f.isDirectory();
            boolean last = (++subDirCounter == subDirTotal);
            if (isSubDir) {
                print(f, indent, last);
            } else {
                System.out.print(indent);
                if (last) {
                    System.out.print("└─");
                } else {
                    System.out.print("├─");
                }
                System.out.println(f.getName());
            }
        }
    }

}
