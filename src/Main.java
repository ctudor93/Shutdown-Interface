import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        String path = "cmd /c start C:\\Users\\thudz\\IdeaProjects\\ShutdownGUI\\scripts\\";
        String fileName ="Shutdown.bat";

        ProcessBuilder builder = new ProcessBuilder(Arrays.asList("cmd.exe", "/C",path+fileName));
        builder.start();
    }
}


