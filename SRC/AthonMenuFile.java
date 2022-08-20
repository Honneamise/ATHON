import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class AthonMenuFile extends JMenu implements ActionListener
{
    AthonFrame frame;

    JMenuItem open;

    JMenuItem save;
    JMenuItem save_BMP;
    JMenuItem save_GIF;
    JMenuItem save_JPEG;
    JMenuItem save_PNG;
    JMenuItem save_TIFF;
    JMenuItem save_WBMP;

    AthonMenuFile(AthonFrame frame)
    {
        super("File");
        this.frame = frame;

        //open
        open = new JMenuItem("Open");
        open.addActionListener(this);
        add(open);

        //save options
        save = new JMenu("Save as");

        save_BMP= new JMenuItem("BMP");
        save_BMP.addActionListener(this);
        save.add(save_BMP);

        save_GIF= new JMenuItem("GIF");
        save_GIF.addActionListener(this);
        save.add(save_GIF);

        save_JPEG= new JMenuItem("JPEG");
        save_JPEG.addActionListener(this);
        save.add(save_JPEG);

        save_PNG= new JMenuItem("PNG");
        save_PNG.addActionListener(this);
        save.add(save_PNG);

        save_TIFF= new JMenuItem("TIFF");
        save_TIFF.addActionListener(this);
        save.add(save_TIFF);

        save_WBMP= new JMenuItem("WBMP");
        save_WBMP.addActionListener(this);
        save.add(save_WBMP);

        add(save);
    }

    public void actionPerformed(ActionEvent e) 
    {
        //open
        if (e.getSource() == open) 
        {
            JFileChooser fc = new JFileChooser("./");

            int res = fc.showOpenDialog(frame);

            if (res == JFileChooser.APPROVE_OPTION) 
            {
                String file = fc.getSelectedFile().getAbsolutePath();

                BufferedImage img = null;

                try
                {
                    img = ImageIO.read( new File(file) );

                } catch (Exception ex) { img = null; }

                if(img == null)
                {
                    JOptionPane.showMessageDialog(frame, "Failed to open image", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                frame.src_panel.img = img;
                frame.dst_panel.img = null;
                
                frame.repaint();
            }
        }

        //save
        if (e.getSource() == save_BMP ||
            e.getSource() == save_GIF ||
            e.getSource() == save_JPEG ||
            e.getSource() == save_PNG ||
            e.getSource() == save_TIFF ||
            e.getSource() == save_WBMP ) 
        {
            if(frame.dst_panel.img==null) { return; }

            JFileChooser fc = new JFileChooser("./");

            int res = fc.showSaveDialog(frame);

            if (res == JFileChooser.APPROVE_OPTION) 
            {
                try
                {
                    String file = fc.getSelectedFile().getAbsolutePath();

                    file += "." + e.getActionCommand();

                    ImageIO.write(frame.dst_panel.img, e.getActionCommand(), new File(file));
            
                } catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(frame, "Failed to save image", "Error", JOptionPane.ERROR_MESSAGE);
                }
        
            }
        }
    }
}
