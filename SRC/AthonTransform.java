import java.awt.image.*;
import java.util.*;

public class AthonTransform 
{
    //NOT USED ( instead use transform with mono palette )
    public static BufferedImage blackwhite(BufferedImage src)
    {
        if(src==null) { return null; }

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());

        for(int i=0; i<src.getHeight(); i++)
        {
            for(int j=0; j<src.getWidth(); j++)
            {
                int in = src.getRGB(j,i);

                int alpha = in & 0xFF000000;
                int r = (in >> 16) & 0xFF;
                int g = (in >>  8) & 0xFF;
                int b = (in >>  0) & 0xFF;
                
                int out = ( (( r + g + b ) / 3) > 0x7F ) ? 0x00FFFFFF : 0x00000000;

                dst.setRGB(j, i, alpha | out);
            }
        }

        return dst;
    }

    //greyscale
    public static BufferedImage greyscale(BufferedImage src)
    {
        if(src==null) { return null; }

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());

        for(int i=0; i<src.getHeight(); i++)
        {
            for(int j=0; j<src.getWidth(); j++)
            {
                int in = src.getRGB(j,i);

                int alpha = in & 0xFF000000;
                int r = (in >> 16) & 0xFF;
                int g = (in >>  8) & 0xFF;
                int b = (in >>  0) & 0xFF;

                int v = (int)(r * 0.212671f + g * 0.715160f + b * 0.072169f);
                
                v = AthonUtils.clamp(v);

                int out = v << 16 | v << 8 | v ;

                dst.setRGB(j, i, alpha | out);
            }
        }

        return dst;
    }

    //negative
    public static BufferedImage negative(BufferedImage src)
    {
        if(src==null) { return null; }

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());

        for(int i=0; i<src.getHeight(); i++)
        {
            for(int j=0; j<src.getWidth(); j++)
            {
                int in = src.getRGB(j,i);

                int alpha = in & 0xFF000000;
                int r = (in >> 16) & 0xFF;
                int g = (in >>  8) & 0xFF;
                int b = (in >>  0) & 0xFF;

                int out = ((0xFF - r) << 16) | ((0xFF - g) << 8) | (0xFF - b) ;

                dst.setRGB(j, i, alpha | out);
            }
        }

        return dst;
    }

    //sepia
    public static BufferedImage sepia(BufferedImage src)
    {
        if(src==null) { return null; }

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());

        for(int i=0; i<src.getHeight(); i++)
        {
            for(int j=0; j<src.getWidth(); j++)
            {
                int in = src.getRGB(j,i);

                int alpha = in & 0xFF000000;
                int r = (in >> 16) & 0xFF;
                int g = (in >>  8) & 0xFF;
                int b = (in >>  0) & 0xFF;
                
                int _r = AthonUtils.clamp(r*0.393f + g*0.769f + b*0.189f);
                int _g = AthonUtils.clamp(r*0.349f + g*0.686f + b*0.168f);
                int _b = AthonUtils.clamp(r*0.272f + g*0.534f + b*0.131f);

                int out = _r << 16 | _g << 8 | _b;

                dst.setRGB(j, i, alpha | out);
            }
        }

        return dst;
    }

    public static BufferedImage palette_nearest(BufferedImage src, List<Integer> palette)
    {
        if(src==null) { return null; }

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());

        for(int i=0; i<src.getHeight(); i++)
        {
            for(int j=0; j<src.getWidth(); j++)
            {
                int in = src.getRGB(j,i);

                int alpha = in & 0xFF000000;
                
                int out = AthonUtils.closestColor(in, palette);

                dst.setRGB(j, i, alpha | out);
            }
        }

        return dst;
    }

    public static BufferedImage palette_dither(BufferedImage src, List<Integer> palette)
    {
        if(src==null) { return null; }

        int in = 0;
        int out = 0;

        int alpha = 0;
        int r = 0;
        int g = 0;
        int b = 0;

        float r_err = 0;
        float g_err = 0;
        float b_err = 0;

        //no deep copy for BufferedImage :-(
        ColorModel cm = src.getColorModel();
        boolean mult = cm.isAlphaPremultiplied();
        WritableRaster raster = src.copyData(null);
        BufferedImage dst = new BufferedImage(cm, raster, mult, null);
        
        for(int i=0; i<src.getHeight(); i++)
        {
            for(int j=0; j<src.getWidth(); j++)
            {
                //current pixel
                in = dst.getRGB(j,i);
                alpha = in & 0xFF000000;

                out = AthonUtils.closestColor(in, palette);

                dst.setRGB(j, i, alpha | out );

                r_err = ((in >> 16) & 0xFF) - ((out >> 16) & 0xFF);
                g_err = ((in >>  8) & 0xFF) - ((out >>  8) & 0xFF);
                b_err = ((in >>  0) & 0xFF) - ((out >>  0) & 0xFF);
               
                //near pixels
                if(j+1<src.getWidth())
                { 
                    in = dst.getRGB(j+1, i);

                    alpha = in & 0xFF000000;
                    r = (in >> 16) & 0xFF;
                    g = (in >>  8) & 0xFF;
                    b = (in >>  0) & 0xFF;

                    r = AthonUtils.clamp(r + r_err * 7.0f / 16.0f);
                    g = AthonUtils.clamp(g + g_err * 7.0f / 16.0f);
                    b = AthonUtils.clamp(b + b_err * 7.0f / 16.0f);

                    out = r << 16 | g << 8 | b;

                    dst.setRGB(j+1, i, alpha | out);
                }

                if(i+1<src.getHeight()  && j-1>=0)
                {
                    in = dst.getRGB(j-1, i+1);
                    
                    alpha = in & 0xFF000000;
                    r = (in >> 16) & 0xFF;
                    g = (in >>  8) & 0xFF;
                    b = (in >>  0) & 0xFF;

                    r = AthonUtils.clamp(r + r_err * 3.0f / 16.0f);
                    g = AthonUtils.clamp(g + g_err * 3.0f / 16.0f);
                    b = AthonUtils.clamp(b + b_err * 3.0f / 16.0f);

                    out = r << 16 | g << 8 | b;

                    dst.setRGB(j-1, i+1, alpha | out);
                }

                if(i+1<src.getHeight())
                {
                    in = dst.getRGB(j, i+1);

                    alpha = in & 0xFF000000;
                    r = (in >> 16) & 0xFF;
                    g = (in >>  8) & 0xFF;
                    b = (in >>  0) & 0xFF;

                    r = AthonUtils.clamp(r + r_err * 5.0f / 16.0f);
                    g = AthonUtils.clamp(g + g_err * 5.0f / 16.0f);
                    b = AthonUtils.clamp(b + b_err * 5.0f / 16.0f);

                    out = r << 16 | g << 8 | b;

                    dst.setRGB(j, i+1, alpha | out);
                }

                if(i+1<src.getHeight() && j+1<src.getWidth())
                {
                    in = dst.getRGB(j+1, i+1);

                    alpha = in & 0xFF000000;
                    r = (in >> 16) & 0xFF;
                    g = (in >>  8) & 0xFF;
                    b = (in >>  0) & 0xFF;

                    r = AthonUtils.clamp(r + r_err * 1.0f / 16.0f);
                    g = AthonUtils.clamp(g + g_err * 1.0f / 16.0f);
                    b = AthonUtils.clamp(b + b_err * 1.0f / 16.0f);

                    out = r << 16 | g << 8 | b;

                    dst.setRGB(j+1, i+1, alpha | out);
                }

            }
        }

        return dst;
    }

}
