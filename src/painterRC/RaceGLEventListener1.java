//package painterRC;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.BitSet;
import java.util.Random;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.swing.JOptionPane;
import painterRC.AnimListener;
import painterRC.TextureReader;
import painterRC.race;
import static painterRC.race.animator;
import static painterRC.race.puase;
import static painterRC.race.unpause;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

   public class RaceGLEventListener1 extends AnimListener implements ActionListener {
    race r;
    //static GLCanvas glcanvas = null;
    Boolean music_state=true;
    public  Boolean enter=true;
   // public  static  Animator animator;
//------------------------
    private int space;
    private int width = 80;
    private int height = 90;
    private int speed1 = 13;
    private int WIDTH = 1000;
    private int HEIGHT = 1000;
    private int move = 20;
    private int count = 1;
     int color_car=0;
     int map=8;int chengemap=1; int left=-150; int right=150;
    private Random rand;
    BufferedImage bg;
    BufferedImage user;
    BufferedImage op1;
    BufferedImage op2;
    BufferedImage road;
    Boolean linef = true;
     private LocalTime t;private long elapsedSeconds;  private int elapsedNanos;int frames = 0;
    //-----------------------
    
     boolean col=false;
    int animationIndex = 0;
    final float maxWidth = 1000;
    final float maxHeight = 650;
    final float borderSize = 100;
    float speed = 10;
    float x, y = -230;
    float scrollBackground;
    boolean isMovingRight = true;
    int y1[] = {400, 200, 0, -200, -400};
    int y2[] = {-100, 50, -300, 300,- 100};
    long s;
    int colli=10;    // Downloaded from https://craftpix.net/freebies/free-golems-chibi-2d-game-sprites/
    String textureNames[]
            = {"car_left_1.png",
                "line.jpg",
                "car_left_3.png",
                "car_left_2.png",
                "op1.png","op3.png","op3 - Copy.png","car_self.png","bg1.png","st_road2.png","explo.png","background-1.png"};
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
     public float getDeltaTime() {
        Duration timeSinceStart = Duration.between(t, LocalTime.now());
        int deltaTime = timeSinceStart.minusSeconds(elapsedSeconds).getNano() - elapsedNanos;
        if (timeSinceStart.getSeconds() > elapsedSeconds) {
           System.out.println(frames + " " + elapsedSeconds);
            frames = 0;
            elapsedSeconds = timeSinceStart.getSeconds();
        } else {
            frames++;
        }
        elapsedNanos = elapsedNanos + deltaTime;
        //System.out.println(elapsedNanos + " - " + deltaTime);
        return deltaTime < 0 ? 0 : deltaTime / 1000000000f;
    }

    public void display(GLAutoDrawable gld) {
       //  float deltaTime = getDeltaTime(); 
        
          if(puase){
            animator.stop();
            puase=false;
        } 
          if(unpause){
              animator.start();
              unpause=false;
          }
          
        s = System.currentTimeMillis();
        s %= 60;
        if (s % 20 == 0) {
            count++;
        }
          
           
        //System.out.println("second" + s);
        System.out.println("count:" + count);
        for (int i = 0; i < y1.length; i++) {
            if (y1[i] < -400) {
                y1[i] = 400;
            }
            if (y2[i] < -400) {
                y2[i] = 400;
            }
        }
        GL gl = gld.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer

//        System.out.println("scrollBackground" + scrollBackground);
        DrawBackground(gl, scrollBackground);
        
        for(int i=0;i<=4&&chengemap==1;i++){
        addlines(gl, y1[i]);
        y1[i] -= speed1;
        }
         for(int i=0;i<=4&&chengemap==2;i++){
        addlines3_1(gl, y1[i]);
        addlines3_2(gl,y1[i]);  
        addlines3_3( gl,y1[i]);
        y1[i] -= speed1;
        }
//        for(int i=0;i<10;i++){
      Rectangle rect2 = rect(100,y2[0] );
       if(count<50 ){
           DrawEnmie1(gl, y2[0]);
           rect2 = rect(100,y2[0] );
           y2[0] -= 5;
       }else{
            DrawEnmie1(gl, y2[0]);
             rect2 =rect(100,y2[0] );
            y2[0] -= 8;}
           
          Rectangle rect3 = rect(100,y2[1] );
        if (count >10&& count<50) {
            DrawEnmie(gl, y2[1]);
           rect3= rect(-100,y2[1] );
          y2[1] -= 5;
        }else{ 
            DrawEnmie(gl, y2[1]);
            rect3=rect(-100,y2[1] );
            y2[1] -= 8;   
        }
          Rectangle rect4 = rect(100,y2[2] );
        if (count > 50) {        
            DrawEnmie2(gl, y2[2]);
             rect4=rect(90,y2[2] ); 
             y2[2] -= 8;     
       }
       if (count > 80) {
            DrawEnmie3(gl, y2[3]);
            rect4=rect(-100,y2[3] );
          y2[3] -= 8;
        }

        handleKeyPress();
        animationIndex = animationIndex % 1;

//        DrawGraph(gl);
        DrawCar(gl, x, y);
        Rectangle rect1 = rect( x, y);
        
        
        if(rect1.intersects(rect2)||rect1.intersects(rect3)||rect1.intersects(rect4)){
          col=true;
          // animator.stop();
        }
        //collisiot( gl, x,  y);
            
        
        
       //if(enter()){
        //playmusic("music\\\\music.wav",music_state);
        //enter=false;
//       }

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void addlines(GL gl, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[1]);
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
    public void addlines3_1(GL gl, int y1) {     
        gl.glEnable(GL.GL_BLEND);    
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[1]);
        gl.glPushMatrix();
    
        gl.glScalef(isMovingRight ? 1: -1, 1, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        
        gl.glVertex3f(-5.0f, y1, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(5f, y1, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(5f, y1-80 , -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-5f, y1 -80, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glEnd();
        gl.glPopMatrix();

    }
    public void addlines3_2(GL gl, int y1) {     
        gl.glEnable(GL.GL_BLEND);    
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[1]);
        gl.glPushMatrix();
        gl.glTranslatef( 160, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-5.0f, y1, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(5.0f, y1, -1.0f);
        gl.glTexCoord2f(3.0f, 0.0f);
        gl.glVertex3f(5.0f, y1-80 , -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-5.0f, y1 -80, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glEnd();
        gl.glPopMatrix();

    }
    public void addlines3_3(GL gl, int y1) {     
        gl.glEnable(GL.GL_BLEND);    
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[1]);
        gl.glPushMatrix();
        gl.glTranslatef( -160, y1, 0);
        gl.glScalef(isMovingRight ? 1 : -1, 1, 1);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-5.0f, y1, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(5.0f, y1, -1.0f);
        gl.glTexCoord2f(3.0f, 0.0f);
        gl.glVertex3f(5.0f, y1-80 , -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-5.0f, y1 -80, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glEnd();
        gl.glPopMatrix();

    }
    

    public void DrawCar(GL gl, float x, float y) {
        gl.glEnable(GL.GL_BLEND);
        if(!col)
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[color_car]);	// Turn Blending On
else
   gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[colli]);	// Turn Blending On            
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

    public void DrawEnmie(GL gl, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[2]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslatef(-100, y1, 0);
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

    public void DrawEnmie1(GL gl, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[3]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslatef(100, y1, 0);
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
  public void DrawEnmie2(GL gl, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[4]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslatef(90, y1, 0);
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
  public void DrawEnmie3(GL gl, int y1) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[5]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslatef(-100, y1, 0);
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
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[map]);	// Turn Blending On

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
    public  Boolean enter(){return enter ;}
    public void collisiot(GL gl, float x, float y) {
         gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[colli]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x, 0, 0);
        gl.glScalef(maxWidth / 2, maxHeight / 2, 1);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-80.0f, -80.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(80.0f, -80.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(80.0f, 80.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-80.0f, 80.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();
        gl.glDisable(GL.GL_BLEND);
    }

    public   void playmusic(String filepath ,Boolean mu) {
       
        InputStream music;
        try
        { 
            music =new FileInputStream(new File(filepath));
            if(mu){
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);    
        }else{ System.out.print("mo2");
                music.close();
        }}
        catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error");
    }
    }
           //music.close();
    
    /*
     * KeyListener
     */
    public void handleKeyPress() {
//        System.out.println(x + "    " + y);
        if (isKeyPressed(KeyEvent.VK_UP)) {
            if (isMovingRight && y < 280) {
                if (x > -maxHeight / 2 - borderSize) {
                    y += speed;
                } else {
                    scrollBackground -= speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
        if (isKeyPressed(KeyEvent.VK_DOWN)) {
            if (isMovingRight && y > -275) {
                if (x > -maxHeight / 2 +borderSize) {
                    y -= speed;
                } else {
                    scrollBackground += speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (isMovingRight && x > left) {
                if (x > -maxWidth / 2 + borderSize) {

                    x -= speed;
                } else {
                    scrollBackground += speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (isMovingRight && x < right) {
                if (x < maxWidth / 2 - borderSize) {
                    x += speed;
                } else {
                    scrollBackground -= speed;
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
   
   if(e.getActionCommand().equals("car1"))
        color_car=6;
     
     
    if(e.getActionCommand().equals("car2"))
          color_car=7;
    
     if(e.getActionCommand().equals("map2")){
          map=9;
          chengemap=1;
          left=-210;
          right=160;
     }
      if(e.getActionCommand().equals("map1")){
          map=8;
         chengemap=1;
         left=-150;
         right=150;
         
          
     
    }
      if(e.getActionCommand().equals("map3")){
          map=11;
         chengemap=2;
         left=-150;
         right=150;
         
          
     
    }
        if(e.getActionCommand().equals("mute")){
                music_state=false;
                enter=true;
                System.out.println("mo");
        }
           if(e.getActionCommand().equals("voice"))
                music_state=true;
}

    public Rectangle rect(float x,float y)
    {
         return new  Rectangle((int)x,(int)y,50,50);
    }
   



}