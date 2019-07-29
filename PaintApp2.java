package paintApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintApp2 extends JComponent {
    private Graphics2D graphics2D;
    private Image image;
    private int oldX,oldY,currentX,currentY;

    public PaintApp2(){
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                if(graphics2D!=null){
                    graphics2D.drawLine(oldX,oldY,currentX,currentY);
                    repaint();
                    oldX = currentX;
                    oldY = currentY;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(image==null){
            image = createImage(getSize().width,getSize().height);
            graphics2D = (Graphics2D) image.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image,0,0,null);
    }

    public void clear() {
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0,0,getSize().width,getSize().height);
        graphics2D.setPaint(Color.BLACK);
        repaint();
    }

    public void red(){
        graphics2D.setPaint(Color.RED);
    }

    public void black(){
        graphics2D.setPaint(Color.BLACK);
    }

    public void blue(){
        graphics2D.setPaint(Color.BLUE);
    }
}

class PaintApp2Test{
    JButton clearB,redB,blackB,blueB;
    PaintApp2 paintArea = new PaintApp2();
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==blackB)
                paintArea.black();
            else if(e.getSource() == clearB)
                paintArea.clear();
            else if(e.getSource() == redB)
                paintArea.red();
            else if(e.getSource() == blueB)
                paintArea.blue();
        }
    };
    public void show(){
        JFrame paintApp = new JFrame("Paint 2");

        Container container = paintApp.getContentPane();
        container.setLayout(new BorderLayout());

        container.add(paintArea,BorderLayout.CENTER);

        JPanel controls = new JPanel();

        clearB = new JButton("Clear"); clearB.addActionListener(actionListener);
        redB = new JButton("Red");     redB.addActionListener(actionListener);
        blackB = new JButton("Black"); blackB.addActionListener(actionListener);
        blueB = new JButton("Blue");   blueB.addActionListener(actionListener);

        controls.add(clearB);
        controls.add(redB);
        controls.add(blackB);
        controls.add(blueB);

        container.add(controls,BorderLayout.NORTH);

        paintApp.setSize(600,600);
        paintApp.setVisible(true);
        paintApp.setLocationRelativeTo(null);
        paintApp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        paintApp.setResizable(false);
    }
    public static void main(String[] args) {
        new PaintApp2Test().show();
    }
}
