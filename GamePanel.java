package Baitaplon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN settings
    final int originalTitlesize = 16; // 16*16 tittle
    final int scale = 4;

    final int tittlesize = originalTitlesize * scale; // 48*48 tittle
    final int maxscreenCol = 16;
    final int maxscreenRow = 12;
    final int screenWidth = tittlesize * maxscreenCol; // width of screen
    final int screenHeight = tittlesize * maxscreenRow;// height of screen

    // FPS
    int fps = 120;
    Keyinput keyI = new Keyinput();
    Thread gameThread;
    // set palyer defause locaction
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyI);
        this.setFocusable(true);
    }
    public void startGamethreads(){
        gameThread = new Thread(this);
        gameThread.start();
    }
     public void run(){
        double drawinterval = 1000000000/fps; //0.016666 sec
        double delta = 0;
        long lasttime = System.nanoTime();
        long currentTime;
        long timer =0;
        int drawcount = 0;
        while ((gameThread != null)) {
            currentTime = System.nanoTime();
            delta += (currentTime - lasttime) / drawinterval;
            timer += (currentTime - lasttime);
            lasttime = currentTime;
            
            if(delta >=1){
                 update();

                repaint();
                delta--;
                drawcount ++;
            }
           if(timer >=1000000000){
            System.out.println("FPS: "+drawcount);
            drawcount =0;
            timer = 0;
           }
        }
    
     } 
    public void update(){
        if( keyI.up == true){
            playerY -= playerSpeed;
        }
        else if(keyI.down == true){
            playerY += playerSpeed;
        }
        else if(keyI.right == true){
            playerX += playerSpeed;
        }
        else if (keyI.left == true){
            playerX -= playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX , playerY, tittlesize, tittlesize);
        g2.dispose(); // loai bo boi canh va toi uu hoa tai nguyen su dung, not need!
    }
}
