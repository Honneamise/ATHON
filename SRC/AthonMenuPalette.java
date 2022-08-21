import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.json.*;
import javax.swing.*;

//custom entry for palettes
class JMenuPalette extends JMenuItem
{
    List<Integer> palette;

    JMenuPalette(String name, List<Integer> palette)
    {
        super(name);
        this.palette = palette;
    }
}

public class AthonMenuPalette extends JMenu implements ActionListener
{
    Hashtable<String, JMenuItem> sections;

    AthonFrame frame;

    JMenuItem dither;

    AthonMenuPalette(AthonFrame frame)
    {
        super("Palette");

        this.frame = frame;

        JsonArray arr = null;

        sections = new Hashtable<String, JMenuItem>();

        //dither on/off
        dither = new JCheckBoxMenuItem("Enable dither");
        //((JCheckBoxMenuItem)dither).setSelected(true);
        add(dither);
        addSeparator();
        
        //get a json object from the file  
        try
        {
            InputStream is = new FileInputStream("LIB/palettes.json");
            
            JsonReader reader = Json.createReader(is);

            arr = reader.readArray();

            reader.close();

            is.close();

        } catch (Exception ex) { JOptionPane.showMessageDialog(frame, "Failed to load palettes", "Error", JOptionPane.ERROR_MESSAGE); }

        //build the menu
        for(int i=0;i<arr.size();i++)
        {
            JsonObject obj = arr.getJsonObject(i);

            String name = obj.getString("Name");
            
            List<Integer> palette = new ArrayList<Integer>();
            JsonArray _palette = obj.getJsonArray("Palette");
            for(int j=0;j<_palette.size();j++)
            {
                palette.add( Integer.parseInt(_palette.getString(j),16) );//could throw exception
            }

            JMenuItem entry = new JMenuPalette(name, palette);
            entry.addActionListener(this);
            
            String section = obj.getString("Section",null);

            if(section==null){ add(entry); continue; }

            if(!sections.containsKey(section))
            {
                JMenuItem sec = new JMenu(section);
                
                sections.put(section, sec);

                add(sec);
            }

            sections.get(section).add(entry);
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        JMenuPalette item = (JMenuPalette)e.getSource();

        if( ((JCheckBoxMenuItem)dither).isSelected() )
        {
            frame.dst_panel.img = AthonTransform.palette_dither(frame.src_panel.img, item.palette);
        }
        else
        {
            frame.dst_panel.img = AthonTransform.palette_nearest(frame.src_panel.img, item.palette);
        }

        frame.dst_panel.repaint();
    }
}
