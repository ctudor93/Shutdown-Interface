package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigHandler {


    static File configFile = new File(Paths.get("").toAbsolutePath() + "\\src\\com\\Config\\Config.txt");


    public static void writeToConfigTxt(String option, String stringToReplaceWith) {
        List<String> fileAsList = readConfig();
        
        int lineInList = findLineForGivenOption(option);

        fileAsList.set(lineInList, option + " = \"" + stringToReplaceWith + "\"");

        // rewrite config file with new updated information
        try {
            Files.write(configFile.toPath(), fileAsList);
        } catch (IOException e) {
            System.out.println("Problem reading or writing the file: " + e.getMessage());
        }

    }

    // scan through list of lines from config file to find the line of the given
    // option to be changed
    public static int findLineForGivenOption( String option) {

        for (int i = 0; i < readConfig().size(); i++) {
            if (readConfig().get(i).contains(option))
                return i;
        }

        return 0;
    }

    // create and return the config file as a string list

    public static List<String> readConfig() {

        List<String> configs= new ArrayList<>();
        try {
            try (Scanner sc = new Scanner(configFile)) {
                while (sc.hasNextLine()) {

                    // list where each entry is a line from the config file
                    configs.add(sc.findInLine("(.).*"));
                    if (sc.hasNextLine())
                        sc.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // System.out.println("this is readConfig() " + configs.toString());
        return configs;
    }

    public static String matchRegex(String stringToLookIn, String regexString) {

        Pattern regexPattern = Pattern.compile(regexString);
        Matcher m = regexPattern.matcher(stringToLookIn);

        if (m.find()) {
            return m.group();
        }
        return "";
    }

}
