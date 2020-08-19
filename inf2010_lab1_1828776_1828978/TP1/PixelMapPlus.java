import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		for (int i = 0; i < height ; i++){
			for ( int j = 0; j < width; j++){
				imageData[i][j] = imageData[i][j].Negative();
			}
		}
		
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// compl�ter
		imageData = this.toBWImage().imageData;
		imageType = imageType.BW;
		
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */	
	public void convertToGrayImage()
	{
		// compl�ter
		imageData = this.toGrayImage().imageData;
		imageType = imageType.Gray;
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// compl�ter
		imageData = this.toColorImage().imageData;
		imageType = imageType.Color;
	}
	
	public void convertToTransparentImage()
	{
		// compl�ter
		imageData = this.toTransparentImage().imageData;
		imageType = imageType.Transparent;
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		PixelMap imagePivote = new PixelMap(this); //créer une nouvel image à partir de l'autre
		
		
		AbstractPixel[][] temp = new AbstractPixel[height][width];
		// Le pixel est porté vers un nouvel emplacement (nouveauI, nouveauJ) dans la nouvelle image
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int nouveauI = (int)(Math.cos(angleRadian)*j + Math.sin(angleRadian)*i - Math.cos(angleRadian)*x
						- Math.sin(angleRadian)*y + x);
				int nouveauJ = (int)(- Math.sin(angleRadian)*j + Math.cos(angleRadian)*i + Math.sin(angleRadian)*x
						- Math.cos(angleRadian)*y + y);
				
				if(nouveauI < 0|| nouveauI >= width || nouveauJ < 0 || nouveauJ >= height){
					switch (imageType){
					case BW:
						temp[i][j] = new BWPixel();
						break;
					case Gray:
						temp[i][j] = new GrayPixel();
						break;
					case Color:
						temp[i][j] = new ColorPixel();
						break;
					case Transparent:
						temp[i][j] = new TransparentPixel();
						break;
					}
				}
				else {
					temp[i][j] = imageData[nouveauJ][nouveauI];
				}
			}
		}
		
		imageData = temp;
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{

		if (w < 0 || h < 0)
			throw new IllegalArgumentException();

		// complï¿½ter


		AbstractPixel[][] temp = new AbstractPixel[h][w];


		if (w > width && h > height) {
			for (int i = 0; i < height; i++)
			{
				for (int j = 0; j < width; j++)
				{
					temp[i * h / height][j * w / width] = imageData[i][j];
				}
			}
		}
		else if (w < width && h < height) {
			for (int i = 0; i < h && (i * height / h) < height; i++)
			{
				for (int j = 0; j < w && (j * width / w) < width; j++)
				{
					temp[i][j] = imageData[i * height / h][j * width / w];
				}
			}
		}

		height = h;
		width = w;
		imageData = temp;


	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// compl�ter
		for (int i = 0; i < pm.getHeight() && i < height - row0; i++){
			for (int j = 0; j < pm.getWidth() && j < width - col0; j++){
				this.imageData[row0 + i][col0 + j] = pm.imageData[i][j];
			}
		}
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		// compl�ter		
		if( !(h > 0 && w > 0) )
			return;
		
		AbstractPixel[][] temp = new AbstractPixel[h][w];
		for (int i = 0; i < h; i++){
			for (int j = 0; j < w; j++){
				if (i >= height || j >= width){
					switch (this.imageType){
					case BW:
						temp[i][j] = new BWPixel();
						break;
					case Gray:
						temp[i][j] = new GrayPixel();
						break;
					case Color:
						temp[i][j] = new ColorPixel();
						break;
					case Transparent:
						temp[i][j] = new TransparentPixel();
						break;
					}
				}
				else {
					temp[i][j] = imageData[i][j];
				}
			}
			
		}
		
		width = w;
		height = h;
		imageData = temp;
		
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// compl�ter
		AbstractPixel[][] temp = new AbstractPixel[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ((i - rowOffset) < 0 || i -rowOffset >= height || j - colOffset < 0 || j - colOffset >= width){
					switch (this.imageType){
					case BW:
						temp[i][j] = new BWPixel();
						break;
					case Gray:
						temp[i][j] = new GrayPixel();
						break;
					case Color:
						temp[i][j] = new ColorPixel();
						break;
					case Transparent:
						temp[i][j] = new TransparentPixel();
						break;
					}
				}
				else
					temp[i][j] = this.imageData[i-rowOffset][j-colOffset];
			}
		}
		this.imageData = temp;
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		int tempWidth = width;
		int tempHeight = height;
		
		int zoomEntier = (int)zoomFactor;
		// compl�ter
		if (x < width / zoomEntier / 2 - 1){
			x = width / zoomEntier / 2 - 1;
		}
		else if (x > width - (width / zoomEntier / 2)){
			x = width - (width / zoomEntier / 2);
		}
		
		
		if (y < height / zoomEntier / 2 -1){
			y = height/zoomEntier/2 - 1;
		}
		else if (y > height - (height / zoomEntier / 2)){
			y = height - (height / zoomEntier / 2);
		}
				
		this.translate((int)(-(y - height / zoomFactor / 2)),(int)(-(x - width / zoomFactor / 2)));
		this.resize((int)(width*zoomFactor), (int)(height*zoomFactor));
		this.crop(tempHeight, tempWidth);
		
		
	}

	/**
	 * Effectue un remplacement de tout les pixels dont la valeur est entre min et max 
	 * avec newPixel
	 * @param min : La valeur miniale d'un pixel
	 * @param max : La valeur maximale d'un pixel  
	 * @param newPixel : Le pixel qui remplacera l'ancienne couleur 
	 * (sa valeur est entre min et max)
	 */
	public void replaceColor(AbstractPixel min, AbstractPixel max,
			AbstractPixel newPixel) {
		// compl�ter
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if(imageData[i][j].compareTo(min)!=-1 && imageData[i][j].compareTo(max) != 1){
					imageData[i][j] = newPixel;
				}
			}
		}
	}
}
