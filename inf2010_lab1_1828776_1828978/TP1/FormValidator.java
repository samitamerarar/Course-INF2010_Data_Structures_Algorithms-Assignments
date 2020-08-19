/**
 * Fichier principal pour l'exercice
 * @author : 
 * @date : 
 */

import java.util.Scanner;
import java.util.Random;

public class FormValidator 
{
	static final double RADIAN_10DEGREE = 0.174532925;
	static final double RADIAN_15DEGREE = 0.261799388;
	static final double RADIAN_20DEGREE = 0.34906585;

	/**
	 * Fonction principale
	 * @param args (non utilise)
	 */
	public static void main(String[] args) 
	{
		// Longueur du code
		final int codeLength = 5;
		
		// Generation du code
		String code = generateCode( codeLength );
		if( code == null ) return;
		
		// Generation des transformations a appliquer
		int[] transform = generateTransform( codeLength );
		if( transform == null ) return;
		
		// Lecture des images correspondant aux codes et tranformations 
		PixelMapPlus[] pm =  new PixelMapPlus[ codeLength ];
		for(int i=0; i<codeLength; i++)
		{
			String letter = code.substring(i, i+1);
			String fileName = "./src/alphabet/" + letter + ".ppm";
			pm[ i ] = new PixelMapPlus( fileName ); 
			
			switch( transform[i] )
			{
				case 1: 
					pm[i].rotate( pm[i].width/2, pm[i].height/2, RADIAN_10DEGREE); 
					break;
				case 2: 
					pm[i].rotate( pm[i].width/2, pm[i].height/2, -RADIAN_10DEGREE); 
					break;
				case 3: 
					pm[i].negate(); 
					break;
				case 4: 
					pm[i].rotate( pm[i].width/4, pm[i].height/2, RADIAN_15DEGREE); 
					pm[i].negate(); 
					break;
				case 5: 
					pm[i].rotate( pm[i].width/2, pm[i].height/4, -RADIAN_15DEGREE); 
					pm[i].negate(); 
					break;
				case 6:
					int oldh = pm[i].height, oldw = pm[i].width; 
					pm[i].resize(pm[i].width/2, pm[i].height/2);
					pm[i].crop(oldh, oldw);
					pm[i].translate(oldw/4, oldh/4);
					pm[i].rotate( pm[i].width/2, 0, RADIAN_20DEGREE);
					break;
				case 7:
					oldh = pm[i].height; oldw = pm[i].width; 
					pm[i].resize(pm[i].width/2, pm[i].height/2);
					pm[i].crop(oldh, oldw);
					pm[i].translate(oldw/4, oldh/4);
					pm[i].rotate( pm[i].width/2, 0, RADIAN_15DEGREE);
					break;
				case 8:
					oldh = pm[i].height; oldw = pm[i].width; 
					pm[i].resize(pm[i].width/2, pm[i].height/2);
					pm[i].crop(oldh, oldw);
					pm[i].translate(oldw/4, oldh/4);
					pm[i].rotate( 0, pm[i].height/4, RADIAN_10DEGREE);
					pm[i].negate();
					break;
				case 9:
					oldh = pm[i].height; oldw = pm[i].width; 
					pm[i].resize(pm[i].width/2, pm[i].height/2);
					pm[i].crop(oldh, oldw);
					pm[i].translate(oldw/4, oldh/4);
					pm[i].rotate( pm[i].width/2, pm[i].height/4, -RADIAN_10DEGREE);
					pm[i].negate();
					break;
				default: ; // 0
			}
		}
		
		// Creation de l'image cible a afficher
		PixelMapPlus codedImage = new PixelMapPlus(PixelMap.ImageType.Color, 
				pm[0].height, codeLength*pm[0].width);
		
		// Insertion des images du code
		for(int i=0; i<codeLength; i++)
			codedImage.inset(pm[ i ], 0, i*pm[i].width);
		
		// Demander le code a l'usager
		String wName = "Code a entrer";
		DisplayImageWindow di = new DisplayImageWindow(wName, codedImage, true);
		
		System.out.print("Entrer code:");
		Scanner in = new Scanner(System.in);
		String response = in.nextLine();
		
		// Validation
		if( response.equals( code ) )
			System.out.println("Acces accorde");
		else
			System.out.println("Acces refuse");
			
		in.close();
		di.dispose();
	}
	
	/**
	 * Genere et retourne un string de longueur length avec des caracteres aleatoires
	 * choisis entre A et Z
	 * @param length : longueur de la chaine de caractere a generer (inferieur ou egal a 10)
	 */
	private static String generateCode(int length)
	{
		if (length > 10) return null;
		
		char[] charKey = new char[ length ];
		
		Random generator = new Random( System.nanoTime() );
		
		// compl�ter
		
		for (int i = 0; i < length; i++) {
			charKey[i] = (char)(generator.nextInt(26) + 'A');
		}
		
		String charAleatoire = new String(charKey);
		
		return charAleatoire;
		
	}
	
	/**
	 * Genere et retourne plusieurs nombres aleatoires entre 0 et 9 (inclus) 
	 * @param nb : longueur de la chaine de caractere a generer (inferieur ou egal a 10)
	 */
	private static int[] generateTransform(int nb)
	{
		if (nb > 10) return null;
		
		int[] charTransform = new int[ nb ];
		
		Random generator = new Random( System.nanoTime() );
		
		// compl�ter
		
		//met dans le tableau de longueur "nb" des chiffres entre 0 et 9
		for (int i = 0; i < nb; i++) {
			charTransform[i] = generator.nextInt(9); //le param�tre pris par nextInt est exclusif
		}
		
		return charTransform;
	}

}