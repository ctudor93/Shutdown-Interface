package com.GUI;

import javax.swing.*;

import com.main.ConfigHandler;
import com.main.Scripter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Gui extends KeyAdapter implements MouseListener {

    boolean pathHasBeenSet = false;
    public static String scriptToRun = "";

    public void guiMain() {

        // Create a frame
        Frame f = new Frame();
        f.requestFocus();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        f.isFocusable();
        Font font = new Font("Dialog", Font.PLAIN, 22);
        f.setFont(font);

        // scripHandler.askAdminPermission();

        // exit on esc
        f.addKeyListener(this);

        Button scriptButton = new Button();
        scriptButton.setLabel("Yo I'm a button");
        scriptButton.addMouseListener(this);

        Button cancelButton = new Button();
        // cancelButton.setBounds(20, 50, 100, 30);
        cancelButton.setLabel("Cancel script");
        cancelButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                Scripter scripHandler = new Scripter();
                scripHandler.setScriptName("Cancel.bat");
                    runScript(scripHandler);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

        f.setLayout(new GridBagLayout());

        f.add(scriptButton, 0);
        f.add(cancelButton, 1);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.setVisible(true);
    }

    static void setPathForScriptDirectory() {
        // Create a file chooser
        final JFileChooser fc = new JFileChooser();
        // In response to a button click:
        fc.setDialogTitle("Choose script directory location");
        fc.setFileSelectionMode(1);
        fc.showOpenDialog(null);
        String path = (fc.getSelectedFile() != null ? fc.getSelectedFile().toPath() : fc.getCurrentDirectory().toPath())
                .toString();

        // System.out.println("path selected in viewer " + path);

        ConfigHandler.writeToConfigTxt("Path", path);

    }

    public void mouseClicked(MouseEvent e) {
        if (!pathHasBeenSet) {
            setPathForScriptDirectory();
            pathHasBeenSet = true;
        }

        Scripter scripHandler = new Scripter();
        if (folderHasScripts(scripHandler.getScriptPath())) {
            runScript(scripHandler);
        } else
            System.out.println("no scripts bby");
    }

    private boolean folderHasScripts(String pathToScripts) {

        File folder = new File(pathToScripts);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".bat"));

            if (files != null && files.length > 0) {
                System.out.println("The folder contains .bat files:");
                return true;

            } else {
                System.out.println("The folder does not contain any .bat files.");
            }
        } else {
            System.out.println("The specified folder does not exist or is not a directory.");
        }

        return false;
    }

    private static void runScript(Scripter scriptHandler) {
        // scriptHandler.setScriptName("Shutdown without admin inbuilt.bat");

        scriptHandler.setScriptName("Shutdown.bat");

        // scriptHandler.setScriptName("Cancel.bat");

        // System.out.println("This is script name in runScript()" +
        // scriptHandler.getScriptName() + " " + scriptHandler.getScriptPath());

        try {
            scriptHandler.runScript();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // close on ESC key
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
