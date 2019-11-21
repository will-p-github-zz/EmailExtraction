package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

public class RegexParse {

    public static String[] addToArray(String[] arr, String elem, int n, int idx) {
        String[] newarr = new String[n+1];
        if (n == 0) {
            newarr[0] = elem;
            return newarr;
        }
        for (int i = 0; i < n+1; i++) {
            if (i < idx) {
                newarr[i] = arr[i];
            } else if (i == idx) {
                newarr[i] = elem;
            } else {
                newarr[i] = arr[i-1];
            }
        }
        return newarr;
    }

    public static String[] removeEndOfArray(String[] arr, int n) {
        String[] newarr = new String[n];
        for (int i = 0; i < n; i++) {
            newarr[i] = arr[i];
        }
        return newarr;
    }

    public static void main(String[] args) throws IOException {

        Path filepath = Paths.get("regex.txt");
        String content = Files.readString(filepath);

        String[] regex = new String[0];
        String[] openBrackets = new String[0];

        for (int i = 0; i < content.length(); i++) {
            if (content.substring(i, i+1).equals("[")) {
                openBrackets = addToArray(openBrackets, Integer.toString(regex.length), openBrackets.length, openBrackets.length);
            } else if (content.substring(i, i+1).equals("-")) {
                regex = addToArray(regex, "between", regex.length,regex.length - 1);
                regex = addToArray(regex, "and", regex.length,regex.length);
            } else if (content.substring(i, i+1).equals("]")) {
                String entry = "";
                for (int j = Integer.parseInt(openBrackets[openBrackets.length-1]); j < regex.length; j++) {
                    entry += regex[j]+" ";
                }
                regex = removeEndOfArray(regex, Integer.parseInt(openBrackets[openBrackets.length-1]));
                regex = addToArray(regex, entry, regex.length,regex.length - 1);

            } else if (content.substring(i, i+1).equals("?")) {
                regex = addToArray(regex, "optional character", regex.length,regex.length - 1);
            } else {
                if (regex.length > 0) {
                    if (regex[regex.length-1].length() == 1) {
                        regex = addToArray(regex, "or", regex.length,regex.length);
                        regex = addToArray(regex, content.substring(i, i+1), regex.length,regex.length);
                    } else {
                        regex = addToArray(regex, content.substring(i, i+1), regex.length,regex.length);
                    }
                } else {
                    regex = addToArray(regex, content.substring(i, i+1), regex.length,regex.length);
                }
            }


        }

        Arrays.stream(regex).forEach(System.out::println);

    }
}
