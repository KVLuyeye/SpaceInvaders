package project.view;

import oop.lib.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MyAnimation implements Runnable, ActionListener {

    private JFrame frame;
    private Timer timer;
    private boolean autoplay;
    private Display display;

    //////////////////////////////////////////////////////////////////////
    // PUBLIC
    //////////////////////////////////////////////////////////////////////

    @Override
    public synchronized void run() {
        frame = new JFrame();
        frame.setTitle("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timer = new Timer(20, this);

        init();

        // for the case the dipslay has changed 
        frame.getContentPane().add(display);
        frame.pack();

        frame.setLocationRelativeTo(null);
        display.requestFocus();
        frame.setVisible(true);
        if (autoplay) {
            timer.start();
            timer.setInitialDelay(20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
        display.repaint();
    }

    //////////////////////////////////////////////////////////////////////
    // PROTECTED
    //////////////////////////////////////////////////////////////////////

    protected abstract void init();

    protected synchronized void launch(boolean automatic) {
        autoplay = automatic;
        SwingUtilities.invokeLater(this);
    }

    //////////////////////////////////////////////////////////////////////
    // ABSTRACT
    //////////////////////////////////////////////////////////////////////

    protected abstract void step();

    //////////////////////////////////////////////////////////////////////
    // GETTERS & SETTERS
    //////////////////////////////////////////////////////////////////////

    protected final void setDisplay(Display display) {
        this.display = display;
    }
}
