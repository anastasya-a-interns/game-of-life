package com.gemb.gol;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BasePanel extends JPanel implements ActionListener,
    MouseListener, MouseMotionListener {
  int panelWidth = 1000;
  int panelHeight = 800;
  int boxSize = 10;
  boolean start = true;
  int[][] directions = {
      {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}
  };
  int xWidth = panelWidth/boxSize;
  int yHeight = panelHeight/boxSize;
  int[][] lives = new int[xWidth][yHeight];
  int[][] nextGen = new int[xWidth][yHeight];

  public BasePanel() {
    setSize(panelWidth,panelHeight);
    setLayout(null);
    addMouseMotionListener(this);
    addMouseListener(this);
    setBackground(Color.black);
    new Timer(100, this).start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    board(g);
    createLife(g);
    showLife(g);
  }

  private void copyArray() {
    for (int i = 0; i < lives.length; i++) {
      for (int j = 0; j < yHeight; j++) {
        lives[i][j] = nextGen[i][j];
      }
    }
  }

  private void board(Graphics g){
    g.setColor(Color.darkGray);
    for (int i = 0; i < lives.length; i++) {
      g.drawLine(0, i*boxSize, panelWidth, i*boxSize);
      g.drawLine(i*boxSize, 0, i*boxSize, panelHeight);
    }
  }

  private void createLife(Graphics g) {
    if (start) {
      for (int i = 0; i < lives.length; i++) {
        for (int j = 0; j < yHeight; j++) {
          if ((int)(3*Math.random()) == 0){
            nextGen[i][j] = 1;
          }
        }
      }
      start = false;
    }
  }

  private void showLife(Graphics g){
    g.setColor(Color.pink);
    copyArray();
    for (int i = 0; i < lives.length; i++) {
      for (int j = 0; j < yHeight; j++) {
        if (lives[i][j] == 1){
          g.fillRect(i*boxSize, j*boxSize, boxSize, boxSize);
        }
      }
    }
  }

  private int checkAliveNeighbor(int x, int y){
    int aliveNeighbor = 0;
    for (int[] dir : directions) {
      int nextX = (dir[0] + x + xWidth) % xWidth;
      int nextY = (dir[1] + y + yHeight) % yHeight;
      aliveNeighbor += lives[nextX][nextY];
    }
    return aliveNeighbor;
  }

  public void actionPerformed(ActionEvent e){
    int lifeCount;
    for (int i = 0; i < lives.length; i++) {
      for (int j = 0; j < yHeight; j++) {
        lifeCount = checkAliveNeighbor(i, j);
        if (lifeCount == 3){
          nextGen[i][j] = 1;
        } else if (lifeCount == 2 && lives[i][j] == 1){
          nextGen[i][j] = 1;
        } else {
          nextGen[i][j] = 0;
        }
      }
    }
    repaint();
  }

  @Override
  public void mouseClicked(MouseEvent e) {

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

  @Override
  public void mouseDragged(MouseEvent e) {
    int x = e.getX() / boxSize;
    int y = e.getY() / boxSize;
    if (lives[x][y] == 0){
      nextGen[x][y] = 1;
    } else if (lives[x][y] == 1){
      nextGen[x][y] = 0;
    }
    repaint();
  }

  @Override
  public void mouseMoved(MouseEvent e) {

  }
}
