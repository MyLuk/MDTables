import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
Продвинутый поиск файлов
Давай реализуем настраиваемый поиск файлов в директории.
Просмотри интерфейс java.nio.file.FileVisitor и его базовую реализацию java.nio.file.SimpleFileVisitor.
Во время поиска по дереву файлов с помощью метода Files.walkFileTree(Path start, FileVisitor<? super Path> visitor)
мы используем объект FileVisitor для выполнения необходимых операций над найденными объектами и.
Наш класс для поиска будет называться SearchFileVisitor и расширять SimpleFileVisitor.
Поиск можно будет выполнять по таким критериям:
— выражение, встречающееся в названии файла (String);
— выражение, встречающееся в содержимом файла (String);
— максимальный и минимальный размер файла (int).
Можно задавать либо один, либо сразу несколько критериев для поиска.
Я в main написал код, который использует готовый SearchFileVisitor для поиска файлов, тебе осталась совсем легкая задача — выполнить его реализацию.
Подсказка 1: методы get… , set… — это геттеры и сеттеры полей. Основная логика класса по поиску выполняется в методе visitFile(Path file, BasicFileAttributes attrs).
Подсказка 2: для работы с файлами используй только классы из пакета java.nio.
*/

//И снова человеческий разум победил валидатор...
public class Solution {

    public static void main(String[] args) throws Exception {
        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();

//        searchFileVisitor.setPartOfName("amigo");
//        searchFileVisitor.setPartOfContent("programmer");
//        searchFileVisitor.setMinSize(1);
//        searchFileVisitor.setMaxSize(300);

        Files.walkFileTree(Paths.get("C:\\Users\\wunsh\\Downloads\\project\\server\\fesb-manager\\web\\static\\src\\pages\\sops\\endpoint"), searchFileVisitor);

        List<Path> foundFiles = searchFileVisitor.getFoundFiles();
        PrintWriter writer = new PrintWriter("C:\\Users\\wunsh\\IdeaProjects\\MDTables\\src\\main\\java\\the-file-name.txt", "UTF-8");
        for (Path file : foundFiles) {
            try {
                JSONParser parser = new JSONParser();
                JSONArray a = (JSONArray) parser.parse(Files.newBufferedReader(file, Charset.forName("UTF-8")));
                writer.println(file.getFileName());
                writer.println();
                writer.println("| Имя | Name | Тип | Значение по умолчанию | Описание |");
                writer.println(String.format("%s", "|------|------|--------|------|-------|"));
                for (Object o: a) {
                    JSONObject person = (JSONObject) o;
                    writer.format("| %s | %s | %s | %s | %s |", (String)person.get("nameRu"), (String)person.get("nameEn"), (String)person.get("type"), (Object) person.get("defaultValue"),(String)person.get("description") );
                    writer.println();
                }
                writer.println("\n");
            } catch (IOException | ParseException e ) {
                e.printStackTrace();
                System.out.println(file);
            }
        }
        writer.close();


    }

}