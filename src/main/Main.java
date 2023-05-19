package main;

import gui.GUI;

import java.io.IOException;


public class Main {

    static boolean firstTimeOpening = true;

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Tudor\\Documents\\projects\\Shutdown-Interface-master\\src\\scripts\\";
        String fileName = "Shutdown.bat";
        String fileName1 = "Shutdown without admin inbuilt.bat";

        if (firstTimeOpening) {
            openDirectory();
            firstTimeOpening = false;
        }
        GUI gui = new GUI();
        gui.GUI();
    }

    private static void openDirectory() throws IOException {


//        File directory = new File("C://Program Files//");
//        Desktop.getDesktop().browseFileDirectory(directory);
//        Runtime.getRuntime().exec("explorer.exe /select," + "C://Program Files//");
    }
}


