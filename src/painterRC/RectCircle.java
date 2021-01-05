/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painterRC;

/**
 *
 * @author ahmed
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.media.opengl.*;



import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.swing.*;

public class RectCircle extends JFrame {

    GLCanvas glcanvas;

    Point point1 = new Point();
    Point point2 = new Point();

    DrawEventListener listener = new DrawEventListener(point1, point2);

    public static void main(String[] args) {
        final RectCircle app = new RectCircle();

        
        SwingUtilities.invokeLater(
                () -> app.setVisible(true)
        );
    }

    public RectCircle() {
        super("Drawing Line Circle Rectangle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseMotionListener(listener);
        glcanvas.addMouseListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(800, 800);
     
        centerWindow(this);
    }

    public void centerWindow(Component frame) {
        Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        frame.setLocation(
                (screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1
        );
    }


/**
 * For our purposes only two of the
 * GLEventListeners matter. Those would
 * be init() and display().
 */
class DrawEventListener implements GLEventListener,MouseMotionListener,MouseListener{

    Point point1;
    Point point2;

    public DrawEventListener(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    /**
     * Take care of initialization here.
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        gl.glLineWidth(2.0f);
        gl.glPointSize(2.0f);

        gl.glViewport(0, 800, 0, 800);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 800, 0, 800, -1, 1);
    }

    /**
     * Take care of drawing here.
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        Point point3 = new Point(point2.x, point1.y);
        Point point4 = new Point(point1.x, point2.y);

        drawLine(gl, point1, point2);
        drawLine(gl, point1, point3);
        drawLine(gl, point1, point4);
        drawLine(gl, point2, point3);
        drawLine(gl, point2, point4);

        int cx = point1.x + ((point3.x - point1.x) / 2);
        int cy = point1.y + ((point2.y - point1.y) / 2);

        int len = Math.min(Math.abs(point1.x - point3.x), Math.abs(point1.y - point4.y)) / 2;

        Point center = new Point(cx, cy);

        drawCircle(gl, center, len);


    }

    private void drawLine(GL gl, Point point1, Point point2) {

        float red = 1.0f;
        float green = 0.2f;
        float blue = 0.2f;

        gl.glColor3f(red, green, blue);

        gl.glBegin(GL.GL_LINES);

        gl.glVertex2d(point1.x, point1.y);
        gl.glVertex2d(point2.x, point2.y);
        gl.glEnd();
    }

    public void drawCircle(GL gl, Point center, int radius) {
        float red = 1.0f;
        float green = 0.2f;
        float blue = 0.2f;

        gl.glColor3f(red, green, blue);
        gl.glBegin(GL.GL_POINTS);

        final double ONE_DEGREE = (Math.PI / 180);
        final double THREE_SIXTY = 2 * Math.PI;
        for (double a = 0; a < THREE_SIXTY; a += ONE_DEGREE) {
            double x = radius * Math.cos(a)+ center.x ;
            double y = radius * Math.sin(a) +center.y;
            gl.glVertex2d(x, y);
        }

        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

  
    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        point2.x = e.getX();
                point2.y =800- e.getY();
                glcanvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
       
    }

        @Override
        public void mouseClicked(MouseEvent me) {
           
        }

        @Override
        public void mousePressed(MouseEvent e) {
                point1.x = e.getX();
                point1.y = 800-e.getY();
                glcanvas.repaint();

        }

        @Override
        public void mouseReleased(MouseEvent e) {
                point2.x = e.getX();
                point2.y = 800- e.getY();
                glcanvas.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            
        }

        @Override
        public void mouseExited(MouseEvent me) {
           
        }

   
}
}