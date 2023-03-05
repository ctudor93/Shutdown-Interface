import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String path="cmd /c start C:\\Users\\thudz\\IdeaProjects\\ShutdownGUI\\scripts\\Shutdown.bat";
        Runtime rn=Runtime.getRuntime();
        Process pr=rn.exec(path);


    }
}