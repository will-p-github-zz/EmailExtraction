package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        Path filepath = Paths.get("sample.txt");
        String content = Files.readString(filepath);

        Pattern pattern = Pattern.compile("\\b([\\w.'_%+-]+@)([\\w.-]+)\\b");
        Matcher matcher = pattern.matcher(content);

        HashMap<String, Integer> domains = new HashMap<String, Integer>();

        while (matcher.find()) {

            String domain = matcher.group(2);
            int count = domains.containsKey(domain) ? domains.get(domain) : 0;
            domains.put(domain, count+1 );

        }

        domains.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

    }
}
