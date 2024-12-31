package javaapplication19;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class JavaApplication19 extends JFrame implements ActionListener, MouseListener {
    // إعداد المتغيرات
    private JPanel drawingPanel; // منطقة الرسم
    private JButton close;
    private JComboBox<String> comboBox;
    private JCheckBox drawCircle, drawSquare;
    private boolean isCircle = true; // افتراضيًا، يتم رسم دائرة
    private Color shapeColor = Color.BLACK; // اللون الافتراضي
    private int shapeSize = 10; // حجم الشكل

    public JavaApplication19() {
        // إعداد النافذة الرئيسية
        setTitle("Konga Shapes ");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        
        
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(shapeColor);
                if (isCircle) {
                    g.drawOval(getWidth() / 2 - shapeSize, getHeight() / 2 - shapeSize, shapeSize * 2, shapeSize * 2);
                } else {
                    g.drawRect(getWidth() / 2 - shapeSize, getHeight() / 2 - shapeSize, shapeSize * 2, shapeSize * 2);
                }
            }
        };
        drawingPanel.setBounds(50, 50, 500, 400);
        drawingPanel.setBackground(Color.WHITE);
        drawingPanel.addMouseListener(this);
        add(drawingPanel);
        

        // إعداد زر الإغلاق
        close = new JButton("Close");
        close.setBounds(50, 470, 100, 30);
        close.addActionListener(this);
        add(close);
        

        // إعداد القائمة المنسدلة للألوان
        String[] colors = {"Random", "Black", "Red", "Green", "Blue"};
        comboBox = new JComboBox<>(colors);
        comboBox.setBounds(180, 470, 150, 30);
        comboBox.addActionListener(this);
        add(comboBox);
        

        // إعداد CheckBox لاختيار الشكل
        drawCircle = new JCheckBox("Draw Circle", true);
        drawCircle.setBounds(350, 470, 100, 30);
        drawCircle.addActionListener(this);
        add(drawCircle);

        drawSquare = new JCheckBox("Draw Square", false);
        drawSquare.setBounds(450, 470, 100, 30);
        drawSquare.addActionListener(this);
        add(drawSquare);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            System.exit(0); // إغلاق البرنامج
        } else if (e.getSource() == comboBox) {
            // تغيير اللون
            String selectedColor = (String) comboBox.getSelectedItem();
            if ("Random".equals(selectedColor)) {
                Random random = new Random();
                shapeColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            } else {
                switch (selectedColor) {
                    case "Red":
                        shapeColor = Color.RED;
                        break;
                    case "Green":
                        shapeColor = Color.GREEN;
                        break;
                    case "Blue":
                        shapeColor = Color.BLUE;
                        break;
                    default:
                        shapeColor = Color.BLACK;
                }
            }
            drawingPanel.repaint();
        } else if (e.getSource() == drawCircle) {
            isCircle = true;
            drawCircle.setSelected(true);
            drawSquare.setSelected(false);
        } else if (e.getSource() == drawSquare) {
            isCircle = false;
            drawSquare.setSelected(true);
            drawCircle.setSelected(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // زيادة حجم الشكل مع كل نقرة
        if (shapeSize < 200) {
            shapeSize += 10;
        } else {
            shapeSize = 10;
        }
        drawingPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new JavaApplication19();
    }
}
