import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class AthonMenuSample extends JMenu implements ActionListener
{
    AthonFrame frame;

    JMenuItem sample1;
    JMenuItem sample2;
    JMenuItem sample3;

    AthonMenuSample(AthonFrame frame)
    {
        super("Sample");
        this.frame = frame;

        File dir = new File("SAMPLES");

        String[] files = dir.list();

        for (String file_str : files) 
        {
            JMenuItem item = new JMenuItem(file_str);
            item.addActionListener(this);
            add(item);
        }

    }

    public void actionPerformed(ActionEvent e)
    {
        String file_str = "SAMPLES/" + e.getActionCommand();

        File file = new File(file_str);

        BufferedImage img = null;

        try
        {
            img = ImageIO.read(file);

        } catch (Exception ex) { img = null; }

        if(img == null)
        {
            JOptionPane.showMessageDialog(frame, "Failed to open image", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        frame.src_panel.img = img;
        frame.dst_panel.img = null;

        frame.setTitle(file.getAbsolutePath());
        frame.repaint();
  
    }
}
