import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;


public class AthonPanel extends JPanel
{
    BufferedImage img;

    AthonPanel(String file)
    {
        super(); 
        
        if (file==null) 
        { 
            img = null;
            return; 
        }

        try
        {
            img = ImageIO.read( new File(file) );

        } catch ( Exception e) { }
    }

   public void paint(Graphics g)
	{
        super.paint(g);

        if(img==null) { return; }

        g.drawImage(img, 
                    (getWidth()-img.getWidth())/2, 
                    (getHeight()-img.getHeight())/2, 
                    null);
        
    }    

    public Dimension getPreferredSize()
    {
        if(img==null) { return super.getPreferredSize(); }

        return new Dimension(img.getWidth(), img.getHeight());
    }

}
