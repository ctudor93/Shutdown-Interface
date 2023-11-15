package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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



        public static List<String> readConfig() {

        List<String> configs = new ArrayList();
        Path currentRelativePath = Paths.get("");
        File configFile = new File(currentRelativePath.toAbsolutePath() + "\\src\\Config\\Config.txt");

        try {
            try (Scanner sc = new Scanner(configFile)) {
                while (sc.hasNextLine()) {

//                configs.add(sc.findInLine("(\").*?(\")"));
                    //list where each entry is a line from the config file
                    configs.add(sc.findInLine("(.).*"));
                    if (sc.hasNextLine()) sc.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("this is main configs reader " + configs.toString());
        return configs;
    }


    public static void writeToConfigTxt(String option, String stringToReplaceWith) {
        System.out.println("received string in writeToConfigTxt " + stringToReplaceWith +" option " + option);
        String configFilePath = Paths.get("").toAbsolutePath() + "\\src\\Config\\Config.txt";
        List<String> linesInFile = readConfig();

        try {
            for (int i = 0; i < linesInFile.size(); i++) {
                if (linesInFile.get(i).matches("(" + option + ").*(\").*?(\")")) {
                    linesInFile.add(i, linesInFile.get(i).replaceFirst("(\").*?(\")", stringToReplaceWith));
                    System.out.println("Line in file "+linesInFile.get(i));
                    break;
                }
            }

            // write the new strings with the replaced lines OVER the same file
            FileOutputStream fileOut = new FileOutputStream(configFilePath);
            for (int i = 0; i < linesInFile.size(); i++) {
                fileOut.write(linesInFile.get(i).getBytes());
                fileOut.write("\n".getBytes());
            }
            fileOut.close();

            System.out.println(readConfig());
        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }


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
