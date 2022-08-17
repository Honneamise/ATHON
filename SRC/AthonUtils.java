import java.util.*;

public class AthonUtils 
{
    //util
    public static int clamp(int val)
    {
        return Math.max(0, Math.min(255, val));
    }

    public static int clamp(float val)
    {
        return (int)(Math.max(0, Math.min(255, val)));
    }

    public static int map(int in, int in_min, int in_max, int out_min, int out_max)
    {
		return  out_min + ((in - in_min)*(out_max - out_min))/(in_max - in_min);
	}

    public static float map(float in, float in_min, float in_max, float out_min, float out_max)
    {
		return  out_min + ((in - in_min)*(out_max - out_min))/(in_max - in_min);
	}
    
    public static float colorDistance(int color1, int color2)
    {
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >>  8) & 0xFF;
        int b1 = (color1 >>  0) & 0xFF;

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >>  8) & 0xFF;
        int b2 = (color2 >>  0) & 0xFF;

        int r = (r1 - r2) * (r1 - r2);
        int g = (g1 - g2) * (g1 - g2);
        int b = (b1 - b2) * (b1 - b2);

        return (float)Math.sqrt( r + g + b );
    }

    public static int closestColor(int color, List<Integer> palette)
    {
        int res = palette.get(0);
        float res_distance = colorDistance(color, palette.get(0));

        for(int i=1; i<palette.size(); i++)
        {
            float dist = colorDistance(color, palette.get(i));

            if(dist < res_distance)
            {
                res_distance = dist;
                res = palette.get(i);
            }
        }

        return res;
    }
}
