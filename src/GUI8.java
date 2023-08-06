import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI8 {
    static JFrame frame = new JFrame();//создаем форму
    static JLayeredPane panel = new JLayeredPane();//создаем многослойную панель

    public static void main(String[] args) throws IOException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Более хитрое перемещение");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dim.width / 2 - 1000 / 2, dim.height / 2 - 1000 / 2, 1000, 1000);
        panel.setFocusable(true);
        frame.add(panel);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    add(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frame.setVisible(true);
    }

    static public void add(MouseEvent e) throws IOException {
        BufferedImage im = ImageIO.read(new File("src/Son.png"));

        JLabel label = new JLabel(new ImageIcon(im), JLabel.RIGHT);
        label.setBounds(e.getX(),e.getY(),50,50);
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                delete(e);
            }
        });
        panel.add(label);//добаавляем метку на панель
    }
    static public void delete(MouseEvent e) {
        if (e.getButton()==2){//если кнопка колесико
            panel.remove((JLabel) e.getSource());
            panel.repaint();
        }
    }

}

