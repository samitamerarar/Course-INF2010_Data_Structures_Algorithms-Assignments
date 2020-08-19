/**
 * Classe de pixel noir et blanc
 * @author Tarek Ould Bachir, Wail Khemir
 * @date : 2015-09-06
 */

public class BWPixel extends AbstractPixel 
{
	boolean pixel; // donnee du pixel
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	BWPixel()
	{
		this(true);
	}
	
	/**
	 * Constructeur par parametre
	 * @param pixel : valeur du pixel
	 */
	BWPixel(boolean pixel)
	{
		this.pixel = pixel;
	}
	
	/**
	 * Renvoie la valeur du pixel
	 */
	public boolean getPixel()
	{
		return pixel;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param pixel: valeur a assigner 
	 */
	public void setPixel(boolean pixel)
	{
		this.pixel = pixel;
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		return new BWPixel(this.pixel);
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		GrayPixel gp = new GrayPixel(
			pixel ? 255 : 0
				);
		return gp;
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		int pixelVal = (pixel ? 255 : 0);
		int[] rgb = new int[3];
		rgb[0] = rgb[1] = rgb[2] = pixelVal;
		ColorPixel cp = new ColorPixel( rgb );
		return cp;
	}
	
	public TransparentPixel toTransparentPixel()
	{
		int pixelVal = (pixel ? 255 : 0);
		int[] rgba = new int[4];
		rgba[0] = rgba[1] = rgba[2] = pixelVal;
		rgba[3] = 255;
		TransparentPixel cp = new TransparentPixel( rgba );
		return cp;
	}
	
	/**
	 * Renvoie le negatif du pixel
	 */
	public AbstractPixel Negative()
	{
		return new BWPixel(!this.pixel);
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
		return ((Integer)(pixel ? 1 : 0)).toString() + " ";
	}
	
	public int compareTo(AbstractPixel p) {
		return this.pixel == ((BWPixel) p).pixel ? 0 : 1;
	}
	
}
