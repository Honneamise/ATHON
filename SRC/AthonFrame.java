import java.awt.*;
import javax.swing.*;

public class AthonFrame extends JFrame
{
    //menus
    JMenuBar menu_bar;

    AthonMenuFile menu_file;
    AthonMenuFilter menu_filter;
    AthonMenuPalette menu_palette;
    AthonMenuSample menu_sample;
    
    //core
    JSplitPane pane;
    AthonPanel src_panel;
    AthonPanel dst_panel;

    AthonFrame()
    {
        //frame stuff
        super();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(1280,720));
        
        //menus
        menu_file = new AthonMenuFile(this);
        menu_filter = new AthonMenuFilter(this);
        menu_palette = new AthonMenuPalette(this);
        menu_sample = new AthonMenuSample(this);
        
        //menu bar
        menu_bar = new JMenuBar();
        menu_bar.add(menu_file);
        menu_bar.add(menu_filter);
        menu_bar.add(menu_palette);
        menu_bar.add(menu_sample);

        setJMenuBar(menu_bar);

        //core stuff
        src_panel = new AthonPanel(null);
        dst_panel = new AthonPanel(null);

        pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(src_panel), new JScrollPane(dst_panel));
        pane.setEnabled(false);

        add(pane, BorderLayout.CENTER);

        pack();

        setVisible(true);
    }

    public void paint(Graphics g)
	{
        super.paint(g);

        pane.setDividerLocation(0.5); 
    }

}
