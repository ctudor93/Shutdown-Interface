package main;

import java.io.IOException;

public class Scripter{

    String pathToScript="";
    String scriptName="";


    public Scripter(String path, String fileName) throws IOException {
        this.pathToScript=path;
        this.scriptName=fileName;

    }

    public void runScript() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe",
                "/C",
                pathToScript+scriptName);

        builder.start();
    }



}
