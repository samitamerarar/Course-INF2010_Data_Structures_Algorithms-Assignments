/**
 * Classe abstraite de pixel
 * @author Tarek Ould Bachir, Wail Khemir
 * @date : 2015-09-06
 */

public abstract class AbstractPixel 
{
	public abstract BWPixel toBWPixel();
	public abstract GrayPixel toGrayPixel();
	public abstract ColorPixel toColorPixel();
	public abstract TransparentPixel toTransparentPixel();
	
	public abstract AbstractPixel Negative();
	public abstract void setAlpha(int alpha);
	
	public abstract String toString();
	
	public abstract int compareTo(AbstractPixel p);
}
