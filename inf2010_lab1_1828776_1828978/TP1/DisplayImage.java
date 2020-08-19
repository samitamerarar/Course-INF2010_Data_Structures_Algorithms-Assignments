 /**
 * Canevas pour contenir l'image a afficher
 * Inspire du code source DisplayPixmapAWT.java
 * http://www.enseignement.polytechnique.fr/profs/informatique/Philippe.Chassignet/PGM/index.html
 * @author Tarek Ould Bachir
 * @date : 2011-09-10
 */
 
import java.awt.*;

public class DisplayImage extends Canvas
{
	static final long serialVersionUID = 0;
	Image  image;
	
	public DisplayImage(Image img) {
		this.image = img;
		setSize(img.getWidth(this), img.getHeight(this));
	}
	
	public void paint(Graphics gr) {
		gr.drawImage(image, 0, 0, this);
	}	
}