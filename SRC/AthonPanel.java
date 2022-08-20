import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

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

        TitledBorder border = BorderFactory.createTitledBorder("Test");
        setBorder(border);
    }

   public void paint(Graphics g)
	{
        super.paint(g);

        if(img==null) { return; }

        /*g.drawImage(img, 
                    (getWidth()-img.getWidth())/2, 
                    (getHeight()-img.getHeight())/2, 
                    null);*/


        /*int x = 0;
        int y = 0;
            
        int w = getWidth();
        int h = getHeight();

        if(img.getWidth()>img.getHeight())
        {
            h = w * img.getHeight() / img.getWidth();
        }
        else
        {
           w = h * img.getWidth() / img.getHeight();
        }*/

        int x = 0;
        int y = 0;
        int client_w = getWidth();
        int client_h = getHeight();
        int viewport_w = 0;
        int viewport_h = 0;

        float ratio = (float)img.getWidth()/(float)img.getHeight();

        if( client_w/ratio < client_h)
        {
            viewport_w = client_w;
            viewport_h = (int)((float)viewport_w/ratio);
        }
        else
        {
            viewport_h = client_h;
            viewport_w = (int)((float)viewport_h*ratio);
        }

        x = (client_w-viewport_w)/2;
        y = (client_h-viewport_h)/2;
            
        g.drawImage(img, x, y, viewport_w, viewport_h, null);
        
    }    

    public Dimension getPreferredSize()
    {
        if(img==null) { return super.getPreferredSize(); }

        return new Dimension(img.getWidth(), img.getHeight());
    }

}
