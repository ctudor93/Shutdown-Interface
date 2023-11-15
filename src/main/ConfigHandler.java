package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// (?<=\").*?(?=\") regex for anything between ""
//     (").*?(") better

public class ConfigHandler {

    public static void writeToConfigTxt(String option, String stringToReplaceWith) {

        System.out.println("received string in writeToConfigTxt " + stringToReplaceWith + " option " + option);

        String configFilePath = Paths.get("").toAbsolutePath() + "\\src\\Config\\Config.txt";
        List<String> fileAsList = readConfig();

        int lineInList = findLineForGivenOption(fileAsList, option);

        fileAsList.set(lineInList, option + " = \"" + stringToReplaceWith + "\"");

        // rewrite config file with new updated information
        try {
            Files.write(Paths.get(configFilePath), fileAsList);
        } catch (IOException e) {
            System.out.println("Problem reading or writing the file: " + e.getMessage());
        }

    }

    // scan through list of lines from config file to find the line of the given
    // option to be changed
    public static int findLineForGivenOption(List<String> fileAsList, String option) {
        for (int i = 0; i < fileAsList.size(); i++) {
            if (fileAsList.get(i).contains(option))
                return i;
        }

        return 0;
    }

    // create and return the config file as a string list

    public static List<String> readConfig() {

        List<String> configs = new ArrayList();
        Path currentRelativePath = Paths.get("");
        File configFile = new File(currentRelativePath.toAbsolutePath() + "\\src\\Config\\Config.txt");

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

        System.out.println("this is readConfig() " + configs.toString());
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
