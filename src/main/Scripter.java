package main;

import java.io.IOException;

public class Scripter {

    String pathToScripts = "";

    String scriptName = "";

    public Scripter() {

        this.pathToScripts = ConfigHandler.matchRegex(ConfigHandler.readConfig().get(0), "(\").*?(\")");
        System.out.println("Path to scripts as found in scripter " + pathToScripts);
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public void setPathToScripts(String pathToScripts) {
        this.pathToScripts = pathToScripts;
    }

    public void runScript() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe",
                "/C",
                pathToScripts + scriptName);

        builder.start();
    }

    public void askAdminPermission() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe",
                "/C",
                pathToScripts + "Admin Permission.bat");

        builder.start();
    }

    public String getScriptName() {
        return scriptName;
    }

    public String getScriptPath() {
        return pathToScripts;
    }
}
