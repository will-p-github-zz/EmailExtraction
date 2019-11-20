package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {
    public static void main(String[] args) throws IOException {

        Path filepath = Paths.get("numbers.txt");
        String content = Files.readString(filepath);

        Pattern pattern = Pattern.compile("(\\+[1-9])?\\d{5}[\\t -]?\\d{6}");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {

            System.out.println(matcher.group());

        }

    }
}
