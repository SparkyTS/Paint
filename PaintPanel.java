package paintApp;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class PaintPanel extends JLabel {

    private final ArrayList<Point> points = new ArrayList<>();
    private JRadioButton green = new JRadioButton("Green");
    private JRadioButton red = new JRadioButton("Red");
    private JRadioButton black = new JRadioButton("Black");
    private ButtonGroup colors = new ButtonGroup();
    private JButton clear = new JButton("Clear");
    public PaintPanel(){
        setLayout(new FlowLayout());
        setVisible(true);
        colors.add(green);
        colors.add(red);
        colors.add(black);
        green.addActionListener(e -> repaint());
        red.addActionListener(e->repaint());
        black.addActionListener(e->repaint());
        add(green);add(red);add(black);add(clear);
        clear.addActionListener(e -> {points.clear();repaint();});
        addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent event){
                points.add(event.getPoint());
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        getParent().setBackground(Color.BLACK);
        graphics.setColor(getSelectedColor());
        for(Point point : points)
            graphics.fillOval(point.x,point.y,4,4);
       // for(int i = 0 ; i < points.size() -1 ; i++)
         //   graphics.drawLine(points.get(i).x,points.get(i).y,points.get(i+1).x,points.get(i+1).y);
    }

    private Color getSelectedColor() {
        if(green.isSelected()) return Color.GREEN;
        if(red.isSelected()) return Color.RED;
        if(black.isSelected()) return Color.BLACK;
        return Color.WHITE;
    }
}

class paintPanelTest{
    public static void main(String[] args) {
        JFrame paintApp = new JFrame("Paint App");
        paintApp.add(new PaintPanel(),BorderLayout.CENTER);
        paintApp.add(new JLabel("Drag the mouse to draw"),BorderLayout.SOUTH);
        paintApp.setSize(500,300);
        paintApp.setLocationRelativeTo(null);
        paintApp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        paintApp.setVisible(true);
    }
}