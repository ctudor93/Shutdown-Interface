package GUI;

import main.Main;
import main.Scripter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class GUI extends KeyAdapter implements MouseListener {

    public void GUI() {


        //Create a frame
        Frame f = new Frame();
        f.requestFocus();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        f.isFocusable();
        Font font = new Font("Dialog", Font.PLAIN, 22);
        f.setFont(font);
        f.setVisible(true);

//        scripHandler.askAdminPermission();

        //exit on esc
        f.addKeyListener(this);

        Button button = new Button();
        button.setLabel("Yo I'm a button");

        button.addMouseListener(this);
        button.addKeyListener(this);

        f.add(button, 0);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    static void setPathForScriptDirectory() {
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        //In response to a button click:
        fc.setDialogTitle("Choose script directory location");
        fc.setFileSelectionMode(1);
        fc.showOpenDialog(null);
        String path = (fc.getSelectedFile() != null ? fc.getSelectedFile().toPath() : fc.getCurrentDirectory().toPath()).toString();

        System.out.println("path selected in viewer"+path);


//        Main.writeToConfigTxt("Path",path.toString());

    }



    public void mouseClicked(MouseEvent e) {
        Scripter scripHandler = new Scripter();
        setPathForScriptDirectory();

        //        runScript(scripHandler);
    }

    private static void runScript(Scripter scriptHandler) {
//        scriptHandler.setScriptName("Shutdown without admin inbuilt.bat");
        scriptHandler.setScriptName("Shutdown.bat");
        System.out.println(scriptHandler.getScriptName() + " " + scriptHandler.getScriptPath());
        try {
            scriptHandler.runScript();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        //close on ESC key
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
