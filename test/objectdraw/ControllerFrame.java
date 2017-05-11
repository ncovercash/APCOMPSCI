/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import objectdraw.Controller;

class ControllerFrame
extends JFrame
implements ActionListener {
    private Controller myController;
    private JMenuItem quitItem;

    public ControllerFrame(String title, Controller myController, int width, int height) {
        super(title);
        this.myController = myController;
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File", true);
        menubar.add(file);
        this.quitItem = new JMenuItem("Quit");
        file.add(this.quitItem);
        this.setJMenuBar(menubar);
        this.quitItem.addActionListener(this);
        this.getContentPane().add("Center", myController);
        this.setSize(width, height);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem && e.getSource() == this.quitItem) {
            this.dispose();
        }
    }
}

