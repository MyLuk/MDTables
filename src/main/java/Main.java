
import lombok.SneakyThrows;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\wunsh\\Downloads\\project\\server\\fesb-manager\\web\\static\\src\\pages\\sops\\endpoint\\bean\\properties\\BeanEndpointProperties.json"));
            System.out.println("| Имя | Name | Тип | Значение по умолчанию | Описание |");
        System.out.println(String.format("%s", "|------|------|--------|------|-------|"));
        for (Object o: a) {
            JSONObject person = (JSONObject) o;
            System.out.format("| %s | %s | %s | %s | %s |", (String)person.get("nameRu"), (String)person.get("nameEn"), (String)person.get("type"), (Object) person.get("defaultValue"),(String)person.get("description") );
            System.out.println();
        }
    }
}
