import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Test11 {
    public static void main(String[] args) {

        Path pathSource = Paths.get("C:\\Users\\wunsh\\Downloads\\project\\server\\fesb-manager\\web\\static\\src\\pages\\sops\\endpoint");
        try {
            SimpleFileVisitor simpleFileVisitor = new MyFileVisitor();
            Files.walkFileTree(pathSource, simpleFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class MyFileVisitor extends SimpleFileVisitor {
    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes fileAttributes) {
        System.out.println("file name:" + path.getFileName());
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult preVisitDirectory(Path path,
                                             BasicFileAttributes fileAttributes) {
        System.out.println("Directory name:" + path);
        return FileVisitResult.CONTINUE;
    }
}