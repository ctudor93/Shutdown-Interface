package main;

import java.io.IOException;

public class Scripter{

    String pathToScript="C:\\Users\\thudz\\IdeaProjects\\ShutdownGUI\\src\\scripts\\";
    String scriptName="";


    public Scripter() {
    }

    public void setScriptName(String scriptName){
        this.scriptName=scriptName;
    }

    public void runScript() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe",
                "/C",
                pathToScript+scriptName);

        builder.start();
    }

    public void askAdminPermission() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe",
                "/C",
                pathToScript+"Admin Permission.bat");

        builder.start();
    }





}
