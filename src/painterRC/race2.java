/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painterRC;

import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.media.opengl.*;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.media.opengl.*;

import java.util.BitSet;
import java.util.Random;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author ahmed
 */
public class race2 extends JFrame {
          
public  static  Animator animator;
 public   static boolean muli;
 public static boolean puase=false ;
 public static boolean unpause=false;
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
//package project;
    public static void main(String[] args) {
        
        race2 r=new race2();
       }
   public race2 (Boolean multi){
         GLCanvas glcanvas;
       RaceGLEventListener2 listener = new RaceGLEventListener2();
        JMenuBar m =new JMenuBar();
        setJMenuBar(m);
       JMenu status= new JMenu("status"); m.add(status);
       JMenuItem multiplayer = new JMenuItem("multiplayer");JMenuItem single = new JMenuItem("single"); 
       status.add(single); status.add(multiplayer); single.addActionListener(new class2()); single.setActionCommand("single");multiplayer.addActionListener(new class2());multiplayer.setActionCommand("multiplayer");
       JMenu map= new JMenu("map"); m.add(map);
       JMenuItem map1 = new JMenuItem("map1");JMenuItem map2 = new JMenuItem("map2");JMenuItem map3 = new JMenuItem("map3");map.add(map3);
       map.add(map1);map.add(map2);map1.addActionListener(listener); map1.setActionCommand("map1");map2.addActionListener(listener);map2.setActionCommand("map2");map3.addActionListener(listener);map3.setActionCommand("map3");
       JMenu cars= new JMenu("cars");m.add(cars);
       JMenuItem car1 = new JMenuItem("red car");JMenuItem car2 = new JMenuItem("green car");
       cars.add(car1);  cars.add(car2);car1.setActionCommand("car1");car1.addActionListener(listener);car2.setActionCommand("car2");car2.addActionListener(listener);
       JMenu audio= new JMenu("audio"); m.add(audio); JMenuItem mute = new JMenuItem("mute"); JMenuItem voice = new JMenuItem("voice");audio.add(mute); audio.add(voice);
       mute.addActionListener(listener);mute.setActionCommand("mute"); voice.addActionListener(listener);voice.setActionCommand("voice");
        glcanvas = new GLCanvas();
       //playmusic("music\\\\music.wav");
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(glcanvas, 60);
        //animator.add(glcanvas);
        animator.start();
        setTitle("Race");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
       
   
   
   
   
   } 
    public race2( ) {
         
        GLCanvas glcanvas;
    
       RaceGLEventListener1 listener = new RaceGLEventListener1();
        JMenuBar m =new JMenuBar();
        setJMenuBar(m);
       JMenu status= new JMenu("status"); m.add(status);
       JMenuItem multiplayer = new JMenuItem("multiplayer");JMenuItem single = new JMenuItem("single"); 
       status.add(single); status.add(multiplayer); single.addActionListener(new class2()); single.setActionCommand("single");multiplayer.addActionListener(new class2());multiplayer.setActionCommand("multiplayer");
       JMenu map= new JMenu("map"); m.add(map);
       JMenuItem map1 = new JMenuItem("map1");JMenuItem map2 = new JMenuItem("map2");JMenuItem map3 = new JMenuItem("map3");map.add(map3);
       map.add(map1);map.add(map2);map1.addActionListener(listener); map1.setActionCommand("map1");map2.addActionListener(listener);map2.setActionCommand("map2");map3.addActionListener(listener);map3.setActionCommand("map3");
       JMenu cars= new JMenu("cars");m.add(cars);
       JMenuItem car1 = new JMenuItem("red car");JMenuItem car2 = new JMenuItem("green car");
       cars.add(car1);  cars.add(car2);car1.setActionCommand("car1");car1.addActionListener(listener);car2.setActionCommand("car2");car2.addActionListener(listener);
       JMenu audio= new JMenu("audio"); m.add(audio); JMenuItem mute = new JMenuItem("mute"); JMenuItem voice = new JMenuItem("voice");audio.add(mute); audio.add(voice);
       mute.addActionListener(listener);mute.setActionCommand("mute"); voice.addActionListener(listener);voice.setActionCommand("voice");
        glcanvas = new GLCanvas();
       //playmusic("music\\\\music.wav");
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(glcanvas, 60);
        //animator.add(glcanvas);
        animator.start();
        setTitle("Race");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
        }
      
  

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;

public class RaceGLEventListener1 extends AnimListener implements ActionListener {

//------------------------
    private int space;
    private int width = 80;
    private int height = 90;
    private int speed1 = 13;
    private int WIDTH = 1000;
    private int HEIGHT = 1000;
    private int move = 20;
    private int count = 1;
    private Random rand;
    BufferedImage bg;
    BufferedImage user;
    BufferedImage op1;
    BufferedImage op2;
    BufferedImage road;
    Boolean linef = true;
    Timer t;
    //-----------------------
     Boolean col=false;
     int color_car=5;int colli=9;
     int count2=1;
    int animationIndex = 0;
    final float maxWidth = 1000;
    final float maxHeight = 650;
    final float borderSize = 50;
    float speed = 15;
    float x, y = -230;
    float scrollBackground;
    boolean isMovingRight = true;
    int y1[] = {400, 200, 0, -200, -400};
    int y2[] = {400, 400, 400, 400, 400, 400};
    long s;
    boolean b[] = new boolean[6];
   int [] listX= {100,-100,350,-350};
    // Downloaded from https://craftpix.net/freebies/free-golems-chibi-2d-game-sprites/
    String textureNames[]
            = {"car_right_1.png",
               "car_left_2.png",
                "car_left_3.png","car_self.png","car_left_1.png","op1.png","op3 - Copy.png", "line.jpg",
                "bg3.jpeg","explo.png"};
    TextureReader.Texture textures[] = new TextureReader.Texture[textureNames.length];
    int textureIndecies[] = new int[textureNames.length];

    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    public void init(GLAutoDrawable gld) {

        assetsFolderName = "Assets1\\images";
        GL gl = gld.getGL();

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

        // We need to generate some numbers to associate textures with
        // and place it into textures array
        gl.glGenTextures(textureNames.length, textureIndecies, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                textures[i] = TextureReader.readTexture(assetsFolderName + "\\" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        textures[i].getWidth(), textures[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        textures[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        System.out.println("here init");

        gl.glLoadIdentity();
        gl.glOrtho(-maxWidth / 2, maxWidth / 2, -maxHeight / 2, maxHeight / 2, -1, 1);
    }

    public void display(GLAutoDrawable gld) {
        s = System.currentTimeMillis();
        s *= 0.001;
        s %= 60;
        if (s % 5 == 0) {
        }
    System.out.println("count:" + count);
        for (int i = 0; i < y1.length; i++) {
            if (y1[i] < -400) {
                y1[i] = 400;
//                b[i] = false;
            }
        }
        for (int i = 0; i < y2.length; i++) {
            if (y2[i] < -400) {
                count++;
                
//                b[i] = false;
                y2[i] = 400;
            }
        }
        if (y2[4] < -360) {
            b[0] = false;
        }
        if (y2[3] < -360) {
            b[2] = false;
        }
        if (y2[5] < -360) {
            b[3] = false;
        }
                if (y2[1] < -360) {
            b[4] = false;
        }
        GL gl = gld.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer

//        System.out.println("scrollBackground" + scrollBackground);
        DrawBackground(gl, scrollBackground);
       for(int i=0;i<=4;i++){
        addlines(gl, y1[i]);
        addlinesr(gl, y1[i]);
        addlinesl(gl, y1[i]);
         y1[i] -= speed;
       }
        int rondm= (int)(Math.random()*4);
        // y2[0] -= speed;
        if (x > -180 && x < -30) {//2
            b[0] = true;
        }
        if (x > -360 && x < -240) {//1
            b[2] = true;
        }
        if (x > 270 && x < 370) {
            b[3] = true;
        }
        if (x > 60 && x <150) {
            b[4] = true;
        }
         Rectangle rect0 = rect(listX[1],y2[0] );
       if(b[0]){
           DrawEnmie(gl,listX[1],y2[0]);
           rect0= rect(listX[1],y2[0] );
           y2[0] -= count+count2;
       }/*else{
            DrawEnmie(gl,listX[1],y2[0]);
             rect0 =rect(listX[1],y2[0] );
            y2[0] -= 5;}*/
           
          Rectangle rect1 = rect(listX[0],y2[1] );
        if ( b[4]) {
            DrawEnmie1(gl,listX[0],y2[1]);
           rect1= rect(listX[0],y2[1] );
           y2[1] -=count+count2;
        }/*else if(count>8){ 
            DrawEnmie1(gl,listX[0],y2[1]);
            rect1=rect(listX[0],y2[1] );
            y2[1] -= 5;   
        }*/
          Rectangle rect2 = rect(listX[3],y2[2] );
        if (count > 2 && b[2]) {        
            DrawEnmie2(gl,listX[3],y2[2]);
             rect2=rect(listX[3],y2[2] ); 
             y2[2] -= count+count2;     
       }
         Rectangle rect3 = rect(listX[3],y2[3] );
       if (count > 2 && b[3]) {
            DrawEnmie3(gl,listX[2],y2[3]);
            rect3=rect(listX[2],y2[3] );
          y2[3] -= count+count2;
        }
       
             //   if (count > 2 && b[4]&&Math.abs(y2[0]-y2[1])>300) {
            //DrawEnmie(gl, -x1, y2[1]);
            //y2[1] -= speed;

        //}

        handleKeyPress();
        animationIndex = animationIndex % 1;

      Rectangle rect = rect(x,y);
        DrawSprite(gl, x, y);
       // System.out.println(x+"  "+y);
     if(rect.intersects(rect1)||rect.intersects(rect2)||rect.intersects(rect0)||rect.intersects(rect3)){
            col=true;
           

    }
    if(puase){
            animator.stop();
            puase=false;
        } 
          if(unpause){
              animator.start();
              unpause=false;
          }
    
    
    
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void addlinesl(GL gl, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[7]);
        gl.glPushMatrix();
//        gl.glTranslatef(0, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glVertex3f(-200, y1, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-220, y1, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-220, y1 - 80, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-200, y1 - 80, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glEnd();
        gl.glPopMatrix();

    }

    public void addlinesr(GL gl, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[7]);
        gl.glPushMatrix();
//        gl.glTranslatef(0, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glVertex3f(200, y1, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(220, y1, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(220, y1 - 80, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(200, y1 - 80, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glEnd();
        gl.glPopMatrix();

    }

    public void addlines(GL gl, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[7]);
        gl.glPushMatrix();
//        gl.glTranslatef(0, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glVertex3f(-10.0f, y1, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(10.0f, y1, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(10.0f, y1 - 80, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-10.0f, y1 - 80, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glEnd();
        gl.glPopMatrix();

    }

    public void DrawSprite(GL gl, float x, float y) {
        gl.glEnable(GL.GL_BLEND);
        if(!col)
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[color_car]);	// Turn Blending On
        else{
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[colli]);
        puase=true;
        }	// Turn Blending On            
        gl.glPushMatrix();
        gl.glTranslatef(x, y, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 50.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 50.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawEnmie(GL gl, int x , int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[0]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslatef(x, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 50.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 50.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawEnmie1(GL gl,int x, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[1]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslatef(x, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 50.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 50.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawEnmie2(GL gl,int x, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[2]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslatef(x, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 50.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 50.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawEnmie3(GL gl,int x,int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[3]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslatef(x, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 50.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 50.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawBackground(GL gl, float x) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[8]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x, 0, 0);
        gl.glScalef(maxWidth / 2, maxHeight / 2, 1);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();
        gl.glDisable(GL.GL_BLEND);
    }

    /*
     * KeyListener
     */    boolean gamePaused = false;

    //...game logic
    public Rectangle rect(float x, float y) {
        return new Rectangle((int) x, (int) y, 50, 50);
    }

    public void handleKeyPress() {
        //System.out.println(x + "    " + y);

        if (isKeyPressed(KeyEvent.VK_P) && !gamePaused) {
            gamePaused = true;
        }
        if (isKeyPressed(KeyEvent.VK_P) && gamePaused) {
            gamePaused = false;
        }

        if (gamePaused) {
            while (true) {
                System.out.println("Game paused.");
                if (isKeyPressed(KeyEvent.VK_P)) {
                    gamePaused = false;
                    break;
                }
            }
        }

        if (isKeyPressed(KeyEvent.VK_UP)) {
            if (isMovingRight && y < 280) {
                if (x > -maxHeight / 2 - borderSize) {
                    y += speed;
                } else {
//                    scrollBackground -= speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
        if (isKeyPressed(KeyEvent.VK_DOWN)) {
            if (isMovingRight && y >= -285) {
                if (x > -maxHeight / 2 + borderSize) {
                    y -= speed;
                } else {
//                    scrollBackground += speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (isMovingRight && x > -380) {
                if (x >= -maxWidth / 2 + borderSize) {

                    x -= speed;
                } else {
//                    scrollBackground += speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (isMovingRight && x < 380) {
                if (x < maxWidth / 2 - borderSize) {
                    x += speed;
                } else {
//                    scrollBackground -= speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care 
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

  
 
class class2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           if(ae.getActionCommand().equals("multiplayer")){
                  puase=true;
                  
                  System.out.println("mecws");
           }
            if(ae.getActionCommand().equals("single"))
                 unpause=true;


}}

public class RaceGLEventListener2 extends AnimListener implements ActionListener {

//------------------------
    private int space;
    private int width = 80;
    private int height = 90;
    private int speed1 = 13;
    private int WIDTH = 1000;
    private int HEIGHT = 1000;
    private int move = 20;
    private int count = 1;
    private Random rand;
    BufferedImage bg;
    BufferedImage user;
    BufferedImage op1;
    BufferedImage op2;
    BufferedImage road;
    Boolean linef = true;
    Timer t;
    //-----------------------
int race1=1;int race2=6;int race3=1;int race4=6;
     Boolean col=false; Boolean col1=false; int colli=6;
    int animationIndex = 0;
    final float maxWidth = 1000;
    final float maxHeight = 750;
    final float borderSize = 50;
    float speed = 15;
    float x=200, y = -210;
    float x0=-200, y0 = -210;

    float scrollBackground;
    boolean isMovingRight = true;
    int y1[] = {400, 200, 0, -200, -400};
    int y2[] = {400, 400, 400, 400, 400};
    long s;
    // Downloaded from https://craftpix.net/freebies/free-golems-chibi-2d-game-sprites/
    String textureNames[]
            = {"op1.png",
                "op3 - Copy.png",
                "line.jpg",
                "car_left_3.png",
                "car_left_2.png",
                "bg2.jpeg","explo.png"};
    TextureReader.Texture textures[] = new TextureReader.Texture[textureNames.length];
    int textureIndecies[] = new int[textureNames.length];

    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    public void init(GLAutoDrawable gld) {

        assetsFolderName = "Assets1\\images";
        GL gl = gld.getGL();

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

        // We need to generate some numbers to associate textures with
        // and place it into textures array
        gl.glGenTextures(textureNames.length, textureIndecies, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                textures[i] = TextureReader.readTexture(assetsFolderName + "\\" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        textures[i].getWidth(), textures[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        textures[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        System.out.println("here init");

        gl.glLoadIdentity();
        gl.glOrtho(-maxWidth / 2, maxWidth / 2, -maxHeight / 2, maxHeight / 2, -1, 1);
    }

    public void display(GLAutoDrawable gld) {
        s = System.currentTimeMillis();
        s *= 0.001;
        s %= 60;
       
//        System.out.println("second" + s);
//        System.out.println("count:" + count);
        for (int i = 0; i < y1.length; i++) {
            if (y1[i] < -400) {
                y1[i] = 400;     
            }
            if (y2[i] < -400) {
                y2[i] = 400;
                 //count%=5;
                 count++;
            }
        }
        GL gl = gld.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer

//        System.out.println("scrollBackground" + scrollBackground);
        DrawBackground(gl, scrollBackground);

        addlinesr(gl, y1[0]);
        addlinesr(gl, y1[1]);
        addlinesr(gl, y1[2]);
        addlinesr(gl, y1[3]);
        addlinesr(gl, y1[4]);

        addlinesl(gl, y1[0]);
        addlinesl(gl, y1[1]);
        addlinesl(gl, y1[2]);
        addlinesl(gl, y1[3]);
        addlinesl(gl, y1[4]);
        
        y1[0] -= speed1;
        y1[1] -= speed1;
        y1[2] -= speed1;
        y1[3] -= speed1;
        y1[4] -= speed1;
       Rectangle rect0 = rect(140,y2[0] );
        if (count > 5){
        DrawEnmie1(gl, y2[3],140);
        rect0 = rect(140,y2[0] );
        y2[3] -= count/5+race1;
 }       
         Rectangle rect1 = rect(340,y2[0] );
         DrawEnmie1(gl, y2[0],340);
         y2[0] -= count/5+race2;
        
          Rectangle rect2 = rect(-140,y2[0] );
            DrawEnmie(gl, y2[1],-140);
            y2[1] -= count/5+race3;
        
          Rectangle rect3 = rect(-340,y2[0] );
        if (count > 5) {
            rect3 = rect(140,y2[0] );
            DrawEnmie(gl, y2[2],-340);
            y2[2] -= count/5+race4;
        }


        handleKeyPress();
        animationIndex = animationIndex % 1;

//        DrawGraph(gl);
        Rectangle Rect1 = rect(x,y );
         DrawCar(gl, x, y);
        Rectangle Rect2 = rect(x0,y0 );
        DrawCar2(gl, x0, y0);
         
        
        if(puase){
            animator.stop();
            puase=false;
        } 
          if(unpause){
              animator.start();
              unpause=false;
          }
 if(Rect1.intersects(rect0)||Rect1.intersects(rect1))
     col=true;
     if(Rect2.intersects(rect2)||Rect2.intersects(rect3))
           col1=true;

    
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void addlinesl(GL gl, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[2]);
        gl.glPushMatrix();
//        gl.glTranslatef(0, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glVertex3f(-200, y1, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-220, y1, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-220, y1 - 80, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-200, y1 - 80, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glEnd();
        gl.glPopMatrix();

    }

    public void addlinesr(GL gl, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[2]);
        gl.glPushMatrix();
//        gl.glTranslatef(0, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glVertex3f(200, y1, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(220, y1, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(220, y1 - 80, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(200, y1 - 80, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glEnd();
        gl.glPopMatrix();

    }

    public void DrawCar2(GL gl, float x, float y) {
        gl.glEnable(GL.GL_BLEND);
      if(!col1)
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[0]);	// Turn Blending On
        else{
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[colli]);
        puase=true;
        }	// T
        gl.glPushMatrix();
        gl.glTranslatef(x, y, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 50.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 50.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawCar(GL gl, float x, float y) {
        gl.glEnable(GL.GL_BLEND);
       if(!col)
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[1]);	// Turn Blending On
        else{
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[colli]);
        puase=true;
        }	// T
        gl.glPushMatrix();
        gl.glTranslatef(x, y, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 50.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 50.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawEnmie(GL gl, int y1,int x1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[3]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslatef(x1, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 50.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 50.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawEnmie1(GL gl, int y1 ,int x1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[4]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslatef(x1, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -50.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 50.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 50.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawBackground(GL gl, float x) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[textureIndecies.length - 2]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x, 0, 0);
        gl.glScalef(maxWidth / 2, maxHeight / 2, 1);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();
        gl.glDisable(GL.GL_BLEND);
    }
 public Rectangle rect(float x, float y) {
        return new Rectangle((int) x, (int) y, 50, 50);
    }
    /*
     * KeyListener
     */
    public void handleKeyPress() {
        System.out.println(x0+ "    " + y0);
        if (isKeyPressed(KeyEvent.VK_UP)) {
            if (isMovingRight && y < 280) {
                if (x > -maxHeight / 2 - borderSize) {
                    y += speed;
                } else {
//                    scrollBackground -= speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
                if (isKeyPressed(KeyEvent.VK_W)) {
            if (isMovingRight && y0 < 280) {
                if (x0 > -maxHeight / 2 - borderSize) {
                    y0 += speed;
                } else {
//                    scrollBackground -= speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
        if (isKeyPressed(KeyEvent.VK_DOWN)) {
            if (isMovingRight && y > -285) {
                if (x > -maxHeight / 2 + borderSize) {
                    y -= speed;
                } else {
//                    scrollBackground += speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
                if (isKeyPressed(KeyEvent.VK_S)) {
            if (isMovingRight && y0 >= -285) {
                if (x0 >-390) {
                    y0 -= speed;
                } else {
//                    scrollBackground += speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (isMovingRight && x > 110) {
                if (x > -maxWidth / 2 + borderSize) {

                    x -= speed;
                } else {
//                    scrollBackground += speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
                if (isKeyPressed(KeyEvent.VK_A)) {
            if (isMovingRight && x0 > -380) {
                if (x0 > -maxWidth / 2 + borderSize) {

                    x0 -= speed;
                } else {
//                    scrollBackground += speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (isMovingRight && x < 380) {
                if (x < maxWidth / 2 - borderSize) {
                    x += speed;
                } else {
//                    scrollBackground -= speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
                if (isKeyPressed(KeyEvent.VK_D)) {
            if (isMovingRight && x0 < -110) {
                if (x0 < maxWidth / 2 - borderSize) {
                    x0 += speed;
                } else {
//                    scrollBackground -= speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care 
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

}


