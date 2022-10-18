package oop.lib;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Animation extends ArrayList<Paintable> implements Runnable, ActionListener {
    private JFrame frame;
    private Timer timer;
    private boolean autoplay;
    private boolean running;
    private Display display;

    public Animation() {
    }

    public synchronized void run() {
        this.frame = new JFrame();
        this.frame.setTitle("Animation");
        this.frame.setDefaultCloseOperation(3);
        this.timer = new Timer(20, this);
        this.display = new LocalDisplay();
        this.frame.getContentPane().add(this.display);
        this.frame.pack();
        this.init();
        this.frame.getContentPane().add(this.display);
        this.frame.pack();
        this.frame.setLocationRelativeTo((Component) null);
        this.display.requestFocus();
        this.frame.setVisible(true);
        if (this.autoplay) {
            this.timer.start();
            this.timer.setInitialDelay(20);
        }

    }

    public void actionPerformed(ActionEvent e) {
        this.step();
        this.display.repaint();
    }

    protected void init() {
    }

    protected synchronized void launch(boolean automatic) {
        this.autoplay = automatic;
        this.running = automatic;
        SwingUtilities.invokeLater(this);
    }

    protected void step() {
    }

    protected final JFrame getFrame() {
        return this.frame;
    }

    protected final Timer getTimer() {
        return this.timer;
    }

    protected final Display getDisplay() {
        return this.display;
    }

    protected final void setDisplay(Display display) {
        this.display = display;
    }

    private class LocalDisplay extends Display {
        private LocalDisplay() {
            super(600, 400);
            this.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    if (Animation.this.running) {
                        Animation.this.timer.stop();
                    } else {
                        Animation.this.timer.start();
                    }

                    Animation.this.running = !Animation.this.running;
                }
            });
            this.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    if (!Animation.this.running) {
                        Animation.this.step();
                        Animation.this.display.repaint();
                    }

                }
            });
        }

        @Override
        public void paint(Display var1) {

        }
    }
}
