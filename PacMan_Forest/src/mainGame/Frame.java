package mainGame;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Frame extends JFrame {
    Panel panel;

    Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PacMan");
        panel = new Panel();

        // Fit the window to the desktop, including the menu bar and Dock.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        panel.setPreferredSize(new Dimension(
                Math.min(1100, screen.width - insets.left - insets.right - 40),
                Math.min(700, screen.height - insets.top - insets.bottom - 80)));
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
