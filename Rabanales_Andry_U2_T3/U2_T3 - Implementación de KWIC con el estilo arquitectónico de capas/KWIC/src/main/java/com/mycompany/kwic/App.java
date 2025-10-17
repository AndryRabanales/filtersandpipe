package com.mycompany.kwic;

import com.mycompany.kwic.ui.IKwicAppFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            IKwicAppFrame frame = new IKwicAppFrame();
            frame.setVisible(true);
        });
    }
}