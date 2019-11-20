package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Map.Entry.comparingByValue;

public class Main {

    public static void main(String[] args) throws IOException {

        Path filepath = Paths.get("sample.txt");
        String content = Files.readString(filepath);

        Pattern pattern = Pattern.compile("\\s([\\w.'_%+-]+@)([\\w-]+)(\\.[\\w-]+)*\\s");
        Matcher matcher = pattern.matcher(content);

        HashMap<String, Integer> domains = new HashMap<String, Integer>();
        int currPos = 0;

        while (matcher.find(currPos)) {

            currPos = matcher.end() - 1;
            String domain = matcher.group(2);
            int count = domains.containsKey(domain) ? domains.get(domain) : 0;
            domains.put(domain, count+1 );

        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("domain frequency cutoff?");
        int cutoff = scanner.nextInt();

        domains.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(10).forEach(entry->{
            if (entry.getValue() > cutoff) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        });
    }
}
