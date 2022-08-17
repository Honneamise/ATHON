import java.awt.event.*;
import javax.swing.*;

public class AthonMenuFilter extends JMenu implements ActionListener
{
    AthonFrame frame;

    JMenuItem greyscale;
    JMenuItem negative;
    JMenuItem sepia;

    AthonMenuFilter(AthonFrame frame)
    {
        super("Filter");
        this.frame = frame;

        greyscale = new JMenuItem("Greyscale");
        greyscale.addActionListener(this);
        add(greyscale);

        negative = new JMenuItem("Negative");
        negative.addActionListener(this);
        add(negative);

        sepia = new JMenuItem("Sepia");
        sepia.addActionListener(this);
        add(sepia);
    }

    public void actionPerformed(ActionEvent e)
    {
        //greyscale
        if (e.getSource() == greyscale) 
        {
            frame.dst_panel.img = AthonTransform.greyscale( frame.src_panel.img);

            frame.dst_panel.getParent().repaint();
        }

        //negative
        if (e.getSource() == negative) 
        {
            frame.dst_panel.img = AthonTransform.negative( frame.src_panel.img);

            frame.dst_panel.getParent().repaint();
        }

        //sepia
        if (e.getSource() == sepia) 
        {
            frame.dst_panel.img = AthonTransform.sepia( frame.src_panel.img);

            frame.dst_panel.getParent().repaint();
        }
    }
}
