import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

public class Lisp {

	
/*
 * cette fonction permet de resoudre  une expresion Lisp   
 * le paramètre peut être transformer en token à l'aide de la fonction getTokens(expresion) 
 * NB une seule pile peut être utilisée
 * retourn "double" le resultat de l'expresion 
*/
static public  double solve(String expresion){
	Stack<String> stack = new Stack<String>();
	//A complete
	Vector<String> temp = getTokens(expresion);

	//gere chaque token de l'expression en partant de droite vers la gauche
	for(int i=temp.size()-1;i>=0;i--) {
		
		//multiplication des 2 premiers nombres de la pile et ensuite verifier s'il y a d'autres operandes
		//a multiplier et mettre le resulat sur la pile
		if (temp.get(i).equals("*")) {
			Double temporaire = Double.parseDouble(stack.pop()) * Double.parseDouble(stack.pop());

			//regarder s'il y a une operande a multiplier
			if (stack.peek().equals(")"))
				stack.pop();

			else {
				temporaire = temporaire * Double.parseDouble(stack.pop());
				stack.pop();
			}
			
			stack.push(String.valueOf(temporaire));
		}
		
		//addition des 2 premiers nombres de la pile et ensuite verifier s'il y a d'autres operandes
		//a additionner et mettre le resulat sur la pile
		else if (temp.get(i).equals("+")) {
			Double temporaire = Double.parseDouble(stack.pop()) + Double.parseDouble(stack.pop());

			//regarder s'il y a une operande a additionner
			if (stack.peek().equals(")"))
				stack.pop();
			
			else {
				temporaire = temporaire + Double.parseDouble(stack.pop());
				stack.pop();
			}
			
			stack.push(String.valueOf(temporaire));
		}
		
		//soustraction des 2 premiers nombres de la pile et ensuite verifier s'il y a d'autres operandes
		//a soustraire et mettre le resulat sur la pile
		else if (temp.get(i).equals("-")) {
			Double temporaire = Double.parseDouble(stack.pop()) - Double.parseDouble(stack.pop());

			//regarder s'il y a une operande a soustraire
			if (stack.peek().equals(")"))
				stack.pop();

			else {
				temporaire = temporaire - Double.parseDouble(stack.pop());
				stack.pop();
			}
			
			stack.push(String.valueOf(temporaire));
		}
		
		//division des 2 premiers nombres de la pile et ensuite verifier s'il y a d'autres operandes
		//a diviser et mettre le resulat sur la pile
		else if (temp.get(i).equals("/")) {
			Double temporaire = Double.parseDouble(stack.pop()) / Double.parseDouble(stack.pop());

			//regarder s'il y a une operande a diviser
			if (stack.peek().equals(")"))
				stack.pop();
			
			else {
				temporaire = temporaire / Double.parseDouble(stack.pop());
				stack.pop();
			}
			
			stack.push(String.valueOf(temporaire));
		}
		
		else if (temp.get(i).equals("(")) {
			//fait rien
		}
		
		//ajoute une parenthese fermee a la pile
		else if (temp.get(i).equals(")")) {
			String parenthese = temp.get(i);
			stack.push(parenthese);
		}
		
		//ajoute un nombre a la pile
		else {
			String nombre = temp.get(i);
			stack.push(nombre);
		}
		
	}
	
	return Double.parseDouble(stack.pop());
	
	
}	
/*
 * cette fonction vérifier si une expression est équilibree 
 * i.e. toutes parenthèse ouverte à une parenthèse fermante
 * N.B: une seule pile peut être utilisée 
 * return true si equilibree, false sinon
 */

static public boolean isEquilibre(String expresion){		
	Stack<String> stack = new Stack<String>();
	//A completer 
	int positionChar = 0;
	int parentheseOuverte = 0;
	int parentheseFermee = 0;
	
	//parcours l'expression et compare le nombre de parantheses ouvertes et fermees
	while ((positionChar) != expresion.length() ) {
		stack.push(expresion.substring(positionChar, positionChar + 1));
		
		if (stack.peek().equals("("))
			parentheseOuverte++;
		
		else if (stack.peek().equals(")"))
			parentheseFermee++;
		
		positionChar++;
	}
	
	return (parentheseOuverte == parentheseFermee); //retourne vrai si le nb parantheses sont egaux
}

/*
 * fonction transforme une expresion (String) lisp en tokens (Vector<String>)
 */
static public Vector<String> getTokens(String expresion){
	
	Vector<String> vectorOfTokens=new Vector<String>();
	int lenght=expresion.length();
	String token="",number="";
	//==
	for(int i=lenght-1;i>=0;i--) {
		//
		token=String.valueOf(expresion.charAt(i));
		if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
			if(!number.isEmpty()){
				vectorOfTokens.addElement(number);
				number="";
			}
			vectorOfTokens.addElement(token);
		}else if(token.equals(")")|| token.equals("(") ){
			if(!number.isEmpty()){
				vectorOfTokens.addElement(number);
				number="";	
			}	
			vectorOfTokens.addElement(token);				
		}else if(token.equals(" ")){
			if(!number.isEmpty()){
				vectorOfTokens.addElement(number);
				number="";	
			}	
		}else if(!token.equals(" ")){
			number=token+number;		
		}
	}
	// invert vectorOfTokens;
	 Collections.reverse(vectorOfTokens);
	
	return vectorOfTokens;
}


}
