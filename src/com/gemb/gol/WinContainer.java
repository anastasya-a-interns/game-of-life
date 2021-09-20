package com.gemb.gol;

import javax.swing.JFrame;

public class WinContainer extends JFrame {
  public WinContainer() {
    add(new BasePanel());
    setSize(1000,800);
    setVisible(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
