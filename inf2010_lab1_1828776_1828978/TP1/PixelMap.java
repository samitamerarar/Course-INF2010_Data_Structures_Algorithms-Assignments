/**
 * Classe PixelMap
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * @author : 
 * @date   : 
 */

import java.io.*;
import java.util.StringTokenizer;

public class PixelMap 
{
	public enum ImageType{BW, Gray, Color, Transparent} //enum definissant type image 
	
	protected int height; // hauteur de l'image
	protected int width; // largeur de l'image
	protected ImageType imageType; // type de l'image
	
	AbstractPixel[][] imageData; // donnees de l'image
	
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMap(String fileName)
	{
		try{
			readFromFile( fileName );
		}
		catch( IOException e ){
			System.err.println( e );
			clearData();
		}
	}

	/**
	 * Constructeur copie
	 * @param image : source
	 */
 	PixelMap(PixelMap image)
	{
		this(image.imageType, image);
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMap(ImageType type, PixelMap image)
	{
		imageType = type;
		
		if( !( image.width > 0 && image.height > 0 ) || image == null )
		{
			height = width = 0;
			return;
		}
		
		AllocateMemory(type, image.height, image.width);
		
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				if( type == ImageType.BW )
					imageData[row][col] = ( image.getPixel(row, col) ).toBWPixel();
				else if( type == ImageType.Gray )
					imageData[row][col] = ( image.getPixel(row, col) ).toGrayPixel();
				else if( type == ImageType.Color )
				{					
					imageData[row][col] = ( image.getPixel(row, col) ).toColorPixel();
				}
				else
				{
					imageData[row][col] = ( image.getPixel(row, col) ).toTransparentPixel();
				}
			}
		}
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMap(ImageType type, int h, int w)
	{
		imageType = type; 
		if( !(h >0 && w > 0) )
		{
			height = width = 0;
			return;
		}
		
		AllocateMemory(type, h, w);
	}
	
	/**
	 * Alloue la memoire de l'image, tous les pixels sont blancs
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	protected void AllocateMemory(ImageType type, int h, int w)
	{
		if( !(h >0 && w > 0) )
			return;
		
		// check if must be kept 
		imageType = type; 
		height = h; 
		width = w;
		
		// compl�ter
		
		imageData = new AbstractPixel[h][w];
		switch (type){
			case BW:
				for (int i = 0; i < height; i++){
					for (int j = 0; j < width; j++){
						imageData[i][j] = new BWPixel();
					}
				}
				break;
			case Gray:
				for (int i = 0; i < height; i++){
					for (int j = 0; j < width; j++){
						imageData[i][j] = new GrayPixel();
					}
				}
				break;
			case Color:
				for (int i = 0; i < height; i++){
					for (int j = 0; j < width; j++){
						imageData[i][j] = new ColorPixel();
					}
				}
				break;
			case Transparent:
				for (int i = 0; i < height; i++){
					for (int j = 0; j < width; j++){
						imageData[i][j] = new TransparentPixel();
					}
				}
				break;
		}
	}
	
	/**
	 * Lib�rer la m�moire
	 */
	public void clearData()
	{
		// compl�ter
		for (int i = 0; i < height; i++)
			imageData[i] = null;
		imageData = null;
		System.gc();
	}
	
	/**
	 * Retourne le pixel associe a la position recherchee
	 * @param row
	 * @param col
	 * @return
	 */
	public AbstractPixel getPixel(int row, int col) throws IndexOutOfBoundsException 
	{
		if((row<0) || (col <0))
			throw new java.lang.IndexOutOfBoundsException();
		else if((row<height) && (col <width))
			return imageData[row][col];
		else
			throw new java.lang.IndexOutOfBoundsException();
	}
	
	/**
	 * Retourne la hauteur de l'image
	 */
	public int getHeight()
	{ 
		return height; 
	}
	
	/**
	 * Retourne la latgeur de l'image
	 */
	public int getWidth()
	{ 
		return width; 
	}
	
	/**
	 * Retourne le type de l'image
	 */
	public ImageType getType()
	{ 
		return imageType; 
	}
	
	/**
	 * Retourne une copie dans le format noir et blanc 
	 */
	public PixelMap toBWImage()
	{ 
		return new PixelMap(ImageType.BW, this ); 
	}
	
	/**
	 * Retourne une copie dans le format tons de gris
	 */
	public PixelMap toGrayImage()
	{ 
		return new PixelMap(ImageType.Gray, this ); 
	}
	
	/**
	 * Retourne une copie dans le format couleur 
	 */
	public PixelMap toColorImage()
	{ 
		return new PixelMap(ImageType.Color, this ); 
	}
	
	/**
	 * Retourne une copie dans le format transparent 
	 */
	public PixelMap toTransparentImage()
	{ 
		return new PixelMap(ImageType.Transparent, this ); 
	}
	
	/**
	 * Ecrit les donnees de l'image dans un fichier.
	 * @param fileName : Nom du fichier (sans extension)
	 * @throws IOException : renvoie une erreur le cas echeant (disque plein par ex.)
	 */
	void writeToFile(String fileName) throws IOException 
	{
		FileOutputStream ofile = null;
		
		if( imageType == ImageType.BW ){
			ofile = new FileOutputStream( fileName + ".pbm" );
			ofile.write( ((String)"P1\n").getBytes() );
		}
		else if( imageType == ImageType.Gray ){
			ofile = new FileOutputStream( fileName + ".pgm" );
			ofile.write( ((String)"P2\n").getBytes() );
		}
		else{ //if( imageType == ImageType.Color )
			ofile = new FileOutputStream( fileName + ".ppm" );
			ofile.write( ((String)"P3\n").getBytes() );
		}
		
		ofile.write( ( 
				((Integer)width).toString() + " " + ((Integer)height).toString() + "\n" 
					).getBytes() );
		
		ofile.write( ((String)"# Created by PixelMap\n").getBytes() );
		
		if( imageType == ImageType.Gray )
			ofile.write( ((String)"255\n").getBytes() );
		else if( imageType == ImageType.Color )
			ofile.write( ((String)"255\n").getBytes() );
		
		for(int row=0; row<height; row++)
			for(int col=0; col<width; col++)
			{
				byte[] buf = (imageData[row][col].toString()).getBytes();
				ofile.write( buf );
			}
		
		ofile.close();
	}
	
	/**
	 * Initialise les donnees de l'image a partir d'un fichier PNM
	 * @param fileName : nom du fichier (avec extension)
	 * @throws IOException : renvoie une erreur le cas echeant (pas de ficher par ex.)
	 */
	void readFromFile(String fileName) throws IOException
	{
		FileReader fr = new FileReader( new File( fileName ) ); 
		BufferedReader br = new BufferedReader(fr);
		int nbTokens = 0, ExpectedTokens = 3;
		int curColor = 0, ExpectedColors = 1;
		int[] params = new int[3];
		int[] rgb = new int[3];
		int col=0, row=0, w=0, h=0;
		boolean TableInitialized = false;
		StringTokenizer st;
		
		ImageType imtype;
		
		String buffer = br.readLine(); // File type
		
		String header = buffer.trim();
		
		if( header.equals( "P1" ) ){
			imtype = ImageType.BW;
			ExpectedTokens = 2;
		}
		else if( header.equals( "P2" ) )
			imtype = ImageType.Gray;
		else if( header.equals( "P3" ) ){
			imtype = ImageType.Color;
			ExpectedColors = 3;
		}
		else
			throw new IOException(fileName + " : ne contient pas le bon entête");
		
		
		while ((buffer = br.readLine()) != null){
			if( buffer.length() > 0 &&  buffer.charAt(0) != '#'){
				
				st = new StringTokenizer(buffer); 
				
				while( st.hasMoreTokens() ){
					
					if( nbTokens < ExpectedTokens){
						
						params[nbTokens] = Integer.parseInt( st.nextToken() );
						nbTokens++;
						
					}else if( !TableInitialized ){
						
						w = params[0];
						h = params[1];
						AllocateMemory(imtype, h, w);
						TableInitialized = true;
					}else{
						rgb[curColor] = Integer.parseInt( st.nextToken() );
						curColor++;
							
						if( curColor == ExpectedColors){
							
							if(imtype == ImageType.BW)
								imageData[row][col] = new BWPixel( rgb[0] == 1 );
							else if(imtype == ImageType.Gray)
								imageData[row][col] = new GrayPixel( rgb[0] );
							else //if(imtype == ImageType.Color)
								imageData[row][col] = new ColorPixel( rgb );
							
							curColor = 0;
							
							if(col==w-1){
								row++; col=0;
							}else
								col++;
						}
					}
				}
			}
		}
		
		br.close();
	}
}
