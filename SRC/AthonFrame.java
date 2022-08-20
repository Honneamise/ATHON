import java.awt.*;
import javax.swing.*;

public class AthonFrame extends JFrame
{
    //menus
    JMenuBar menu_bar;

    AthonMenuFile menu_file;
    AthonMenuFilter menu_filter;
    AthonMenuPalette menu_palette;
    
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
        
        //menu bar
        menu_bar = new JMenuBar();
        menu_bar.add(menu_file);
        menu_bar.add(menu_filter);
        menu_bar.add(menu_palette);

        setJMenuBar(menu_bar);

        //core stuff
        src_panel = new AthonPanel(null);
        dst_panel = new AthonPanel(null);

        pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, src_panel, dst_panel);
        pane.setEnabled(false);
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pane.setDividerSize(10);
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
