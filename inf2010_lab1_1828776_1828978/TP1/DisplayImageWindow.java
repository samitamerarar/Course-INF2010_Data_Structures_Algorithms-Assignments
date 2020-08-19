 /**
 * Fenetre pour afficher l'image
 * Inspire du code source DisplayPixmapAWT.java
 * http://www.enseignement.polytechnique.fr/profs/informatique/Philippe.Chassignet/PGM/index.html
 * @author Tarek Ould Bachir, Wail Khemir
 * @date : 2015-09-06
 */

import java.awt.*;
import java.awt.image.MemoryImageSource;
import javax.swing.*;

@SuppressWarnings("serial")
public class DisplayImageWindow extends JFrame 
{
	public DisplayImageWindow(String name, PixelMap p, int row, int col) 
	{
		this(name, p, true, row, col);
	}
	public DisplayImageWindow(String name, PixelMap p)
	{
		this(name, p, true, 50, 50);
	}
	
	public DisplayImageWindow(String name, PixelMap p, boolean exitonclose)
	{
		this(name, p , exitonclose, 50, 50);
	}
	
	public DisplayImageWindow(String name, PixelMap p, boolean exitonclose, int row, int col) 
	{
		super(name);
		setLocation(row, col);
		
		if(exitonclose)
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PixelMap c = p.toTransparentImage();
		
		// fabrication des pixels gris au format usuel AWT : ColorModel.RGBdefault
		int[] pixels = new int[p.getHeight() * p.getWidth()];
		for (int i = 0; i < pixels.length; i++)
		{
			int column = i%c.getWidth();
			int line = (i - column)/c.getWidth();
			
			TransparentPixel pix = (TransparentPixel)c.getPixel(line, column);
			
			pixels[i] = pix.rgba[ 3 ] * 0x01000000 + 
				pix.rgba[ 0 ] * 0x010000 +
				pix.rgba[ 1 ] * 0x000100 +
				pix.rgba[ 2 ] * 0x000001;
		}
		
		// Construire une image avec ces pixels
		MemoryImageSource source = new MemoryImageSource(
				p.getWidth(), p.getHeight(), pixels, 0, p.getWidth());
		Image img = Toolkit.getDefaultToolkit().createImage(source);
		add(new DisplayImage(img));
		pack();
		setVisible(true);
	}
}
