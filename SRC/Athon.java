import javax.swing.*;

class Athon 
{
    public static void main(String args[])
    {
        try 
        {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            
        } catch (Exception e) { e.printStackTrace(); }
        
        new AthonFrame();
    }

}