package gui;

import main.Scripter;

import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GUI{

    public static void AppFront() throws IOException {
        Scripter scripHandler = new Scripter();


        //Create a frame
        Frame f = new Frame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        f.isFocusable();
        //Prepare font
        Font font = new Font( "Dialog", Font.PLAIN, 22 );
        f.setFont(font);
        //Make visible
        f.setVisible(true);

//        scripHandler.askAdminPermission();

        //exit on esc
        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        Button button = new Button();
        button.setLabel("Yo I'm a button");
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scripHandler.setScriptName("Shutdown without admin inbuilt.bat");

                try {
                    scripHandler.runScript();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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

        });
//        button.setFont(font);
        f.add(button);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
