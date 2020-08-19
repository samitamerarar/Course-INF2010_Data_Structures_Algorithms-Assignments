/**
 * Classe de pixel transparent
 * @author :
 * @date : 
 */

public class TransparentPixel extends AbstractPixel
{
	public int[] rgba; // donnees de l'image
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	TransparentPixel()
	{
		rgba = new int[4];
		rgba[0] = 255;
		rgba[1] = 255;
		rgba[2] = 255;
		rgba[3] = 255;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner 
	 */
	TransparentPixel(int[] rgba)
	{
		// compléter
		this.rgba = new int[4];
		this.rgba = rgba;
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		// compléter
		boolean bool;
		if ((rgba[0] + rgba[1] + rgba[2])/3 <= 127){
			bool = false;
		}
		else {
			bool = true;
		}
		BWPixel bw = new BWPixel (bool);
		return bw;
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		// compléter
		return new GrayPixel ((rgba[0] + rgba[1] + rgba[2])/3);
		
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		// compléter
		int[] rgb = new int[3];
		rgb[0] = rgba[0];
		rgb[1] = rgba[1];
		rgb[2] = rgba[2];
		return new ColorPixel ( rgb );
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public TransparentPixel Negative()
	{
		// compléter
		int[] negativeRgba = new int[4];
		negativeRgba[0] = 255-rgba[0];
		negativeRgba[1] = 255-rgba[1];
		negativeRgba[2] = 255-rgba[2];
		negativeRgba[3] = rgba[3];
		return new TransparentPixel( rgba );
	}
	
	public TransparentPixel toTransparentPixel()
	{
		// compléter
		return new TransparentPixel (this.rgba);
		
	}
	
	public void setAlpha(int alpha)
	{
		rgba[3] = alpha;
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgba[0]).toString() + " " + 
				((Integer)rgba[1]).toString() + " " +
				((Integer)rgba[2]).toString() + " " +
				((Integer)rgba[3]).toString() + " ";
	}
	
	public int compareTo(AbstractPixel p) {
		if (rgba[0] < ((TransparentPixel) p).rgba[0]
				&& rgba[1] < ((TransparentPixel) p).rgba[1]
				&& rgba[2] < ((TransparentPixel) p).rgba[2]
				&& rgba[3] < ((TransparentPixel) p).rgba[3]) {
			return -1;
		} else {
			if (rgba[0] == ((TransparentPixel) p).rgba[0]
					&& rgba[1] == ((TransparentPixel) p).rgba[1]
					&& rgba[2] == ((TransparentPixel) p).rgba[2]
					&& rgba[3] == ((TransparentPixel) p).rgba[3]) {
				return 0;
			} else {
				return 1;
			}
		}
	}
	
}
