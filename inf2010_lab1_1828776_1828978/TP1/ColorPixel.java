/**
 * Classe de pixel en couleurs 
 * @author :
 * @date : 
 */

public class ColorPixel extends AbstractPixel
{
	public int[] rgb; // donnees de l'image
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	ColorPixel()
	{
		rgb = new int[3];
		rgb[0] = 255;
		rgb[1] = 255;
		rgb[2] = 255;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner 
	 */
	ColorPixel(int[] rgb)
	{
		// compléter
		this.rgb = new int[3];
		this.rgb[0] = rgb[0];
		this.rgb[1] = rgb[1];
		this.rgb[2] = rgb[2];
	
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		// compléter
		boolean bool;
		int moyenne = (rgb[0] + rgb[1] + rgb[2]) / 3;
		if (moyenne <= 127){
			bool = false;
		}
		else
			bool = true;
		BWPixel bw = new BWPixel(bool);
		return bw;
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		// compléter
		int moyenne = (rgb[0] + rgb[1] + rgb[2]) / 3;
		GrayPixel gp = new GrayPixel(moyenne);
		
		return gp;
	}
	
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		// compléter
		return new ColorPixel(this.rgb);
		
	}
	
	public TransparentPixel toTransparentPixel()
	{
		// compléter
		int[] rgbt = new int[4];
		rgbt[0] = rgb[0];
		rgbt[1] = rgb[1];
		rgbt[2] = rgb[2];
		rgbt[3] = 255;
		
		return new TransparentPixel(rgbt);
		
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative()
	{
		// compléter
		rgb[0]= 255 - rgb[0];
		rgb[1]= 255 - rgb[1];
		rgb[2]= 255 - rgb[2];
		
		return new ColorPixel(rgb);
	}
	
	public void setAlpha(int alpha)
	{
		//ne fait rien
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgb[0]).toString() + " " + 
				((Integer)rgb[1]).toString() + " " +
				((Integer)rgb[2]).toString() + " ";
	}
	
	public int compareTo(AbstractPixel p) {
		if (rgb[0] < ((ColorPixel) p).rgb[0]
				&& rgb[1] < ((ColorPixel) p).rgb[1]
				&& rgb[2] < ((ColorPixel) p).rgb[2]) {
			return -1;
		} else {
			if (rgb[0] == ((ColorPixel) p).rgb[0]
					&& rgb[1] == ((ColorPixel) p).rgb[1]
					&& rgb[2] == ((ColorPixel) p).rgb[2]) {
				return 0;
			} else {
				return 1;
			}
		}
	}

}