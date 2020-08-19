/**
 * Interface des operations sur PixelMap
 * @author Tarek Ould Bachir, Wail Khemir
 * @date : 2015-09-06
 */

public interface ImageOperations 
{
	public void negate();
	public void convertToBWImage();
	public void convertToGrayImage();
	public void convertToColorImage();
	public void resize(int x, int y);
	public void inset(PixelMap pm, int row0, int col0);
	public void rotate(int x, int y, double angleRadian);
	public void crop(int h, int w);
	public void translate(int colOffset, int rowOffset);
	public void replaceColor(AbstractPixel min, AbstractPixel max, AbstractPixel newPixel);
}
