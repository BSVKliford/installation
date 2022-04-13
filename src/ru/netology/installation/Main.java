package ru.netology.installation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Main {

    static final String absoluteName = "K:/Games/";

    public static boolean addDir(String dir) {
        File newDir = new File(absoluteName + dir);
        return newDir.mkdir();
    }

    public static boolean addFile(String dir, String file) {
        File newFile = new File(absoluteName + dir, file);
        try {
            if (newFile.createNewFile()) {
                return true;
            } else return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        List<String> dir = Arrays.asList("scr", "res", "savegames", "temp", "scr/main", "scr/test", "res/drawables", "res/vectors", "res/icons");
        StringBuilder sb = new StringBuilder();
        Boolean fault = false;
        for (String str : dir) {
            if (addDir(str)) {
                sb.append("Создали каталог " + absoluteName + str + "\n");
            } else {
                sb.append("Ошибка создания каталога " + absoluteName + str + "\n");
                fault = true;
                break;
            }
        }
        if (!fault) {
            if (addFile("scr/main/", "Main.java")) {
                sb.append("Создали файл " + absoluteName + "main/Main.java \n");
            } else {
                sb.append("Ошибка создания файла " + absoluteName + "main/Main.java \n");
            }
        }
        if (!fault) {
            if (addFile("scr/main/", "Utils.java")) {
                sb.append("Создали файл " + absoluteName + "scr/main/Utils.java \n");
            } else {
                sb.append("Ошибка создания файла " + absoluteName + "scr/main/Utils.java \n");
            }
        }
        try (FileOutputStream temp = new FileOutputStream(new File(absoluteName + ((!fault) ? "temp/" : ""), "temp.txt"))) {
            byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
            temp.write(bytes);
            System.out.println(!fault ? "Установка прошла успешно" : "Установка завершилась с ошибкой");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Установка завершилась с ошибкой");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Установка завершилась с ошибкой");
        }
    }
}