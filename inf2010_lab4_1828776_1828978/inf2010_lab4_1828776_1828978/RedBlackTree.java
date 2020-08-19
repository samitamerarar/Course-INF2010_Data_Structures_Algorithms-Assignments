import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree<T extends Comparable<? super T> > 
{
	enum ChildType{ left, right }
	private RBNode<T> root;  // Racine de l'arbre
   
   public RedBlackTree(){ 
      root = null;
   }
   
   public void printFancyTree(){
      printFancyTree( root, "", ChildType.right);
   }
   private void printFancyTree( RBNode<T> t, String prefix, ChildType myChildType){
	  //
      System.out.print( prefix + "|__"); // un | et trois _  
      if( t != null ){
         boolean isLeaf = (t.isNil()) || ( t.leftChild.isNil() && t.rightChild.isNil() );
         System.out.println( t );
         String _prefix = prefix;
         if( myChildType == ChildType.left )
            _prefix += "|   "; // un | et trois espaces
         else
            _prefix += "   " ; // trois espace
         if( !isLeaf ){
            printFancyTree( t.leftChild, _prefix, ChildType.left );
            printFancyTree( t.rightChild, _prefix, ChildType.right );
         }
      }
      else
         System.out.print("null\n");
   }
/*
 * recherche d'une clé
 */
   public T find(int key){
      return find(root, key);
   }
   private T find(RBNode<T> current, int key){
      // à completer 
	   if (current.value != null) {
		   
		   //return la position si on a trouve ce que l'on cherche
		   if (current.value.hashCode() == key)
			    return current.value;
		   
		   //continuer a chercher dans le fils de droite si la cle voulu est plus grande
		   else if (current.value.hashCode() < key && current.rightChild != null)
			   return find(current.rightChild, key);
		   
		   //continuer a chercher dans le fils de gauche si la cle voulu est plus petite
		   else if (current.value.hashCode() > key && current.leftChild != null)
			   return find(current.leftChild, key);
	   }
	   //rien trouve!
	   return null;
	 }
  /*
   * insertion d'une valeur 
   */
   public void insert(T val){
      insertNode( new RBNode<T>( val ) );
   }
   
   private void insertNode( RBNode<T> newNode ){ 
      if (root == null)  // Si arbre vide
         root = newNode;
      else{
         RBNode<T> position = root; // On se place a la racine     
         while( true ) // on insere le noeud (ABR standard)
         {
            int newKey = newNode.value.hashCode();
            int posKey = position.value.hashCode();
            if (newKey < posKey ){
               if ( position.leftChild.isNil()){
                  position.leftChild = newNode;
                  newNode.parent = position;
                  break;
               } 
               else 
                  position = position.leftChild;
            }else if ( newKey > posKey ){
               if ( position.rightChild.isNil()){
                  position.rightChild = newNode;
                  newNode.parent = position;
                  break;
               }
               else 
                  position = position.rightChild;
            }else // pas de doublons
               return;
         }
      }
      insertionCases( newNode );
   }
 /*
  * les cas d'insertion 
  */
   private void insertionCases( RBNode<T> X ){
      insertionCase1( X );
   }
   private void insertionCase1 ( RBNode<T> X )
   {
      // A MODIFIER/COMPLETER
	  if (X.parent == null) //si la racine mettre en noir
		  X.setToBlack();
	  else
		  insertionCase2(X); //on passe a la prochaine verification
   }

   private void insertionCase2( RBNode<T> X )
   {
      // A MODIFIER/COMPLETER
	  if (X.parent.isRed())	//parent est noir -> cas (3)
		  insertionCase3(X);
	  else
	  {} //parent est rouge, c'est parfait, on sort.
   }

   private void insertionCase3( RBNode<T> X )
   {
	// A MODIFIER/COMPLETER
	   
	//si le parent et l'oncle du nouveau noeud sont rouges
	if (X.parent.isRed() && X.uncle().isRed()) {
		X.parent.setToBlack();
		X.uncle().setToBlack();
		X.grandParent().setToRed();
		
		//faut que le grand-pere subisse des verifications a son tour 			
		insertionCases(X.grandParent());
	}
	else
		insertionCase4(X); //on passe a la prochaine verification
   }

   private void insertionCase4( RBNode<T> X )
   {
      // A MODIFIER/COMPLETER
	  //si le parent est rouge et l'oncle est noir
	  if (X.parent.isRed() && X.uncle().isBlack()) {
		  
		  	//et que le noeud X est l'enfant de gauche de P et P est l'enfant de droite de G
		  	if (X.parent.leftChild == X && X.grandParent().rightChild == X.parent) {
				//on effectue une rotation vers la droite autout de P
				rotateRight(X.parent);
				//on verifie le cas 5 sur l'enfant de droite de X
				insertionCase5(X.rightChild);
   			}
	  		//ou que le noeud X est l'enfant de droite de P et P est l'enfant de gauche de G
		  	else if (X.parent.rightChild == X && X.grandParent().leftChild == X.parent) {
				//on effectue une rotation vers la gauche autour de P
				rotateLeft(X.parent);
				//on verifie le cas 5 sur l'enfant de gauche de X
				insertionCase5(X.leftChild);
			}
		  	else //il n'y pas eu de rotation.
		  		insertionCase5(X); //on passe a la prochaine verification
	  }
	  else
	  {} //rien
			  
   }

   private void insertionCase5( RBNode<T> X )
   {
       // A MODIFIER/COMPLETER
	   //parent est rouge, oncle est noir,
	   //X est enfant de droite de Parent et Parent est enfant de droite de Grand Parent
	   if (X == X.parent.rightChild && X.parent == X.grandParent().rightChild)
	   {
		   //change la couleur de Grand Parent et Parent
		   X.grandParent().setToRed();
		   X.parent.setToBlack();
		   
		   //rotation vers la gauche
		   rotateLeft(X.grandParent());
	   }
	   
	   //parent est rouge, oncle est noir,
	   //X est enfant de gauche de Parent et Parent est enfant de gauche de Grand Parent
	   else if (X == X.parent.leftChild && X.parent == X.grandParent().leftChild)
	   {
		   //change la couleur de Grand Parent et Parent
		   X.grandParent().setToRed();
		   X.parent.setToBlack();
		   
		   //rotation vers la droite
		   rotateRight(X.grandParent());
	   }
   }
   
   private void rotateLeft( RBNode<T> P )
   {
		RBNode<T> X = P.rightChild;
		
		P.rightChild		= X.leftChild;
		X.leftChild			= P;
		P.rightChild.parent	= P;
		X.parent 			= P.parent;
		P.parent 			= X;
		X.parent.leftChild	= X;
   }
   
   private void rotateRight( RBNode<T> P)
   {
		RBNode<T> X = P.leftChild;
		
		P.leftChild			= X.rightChild;
		X.rightChild		= P;
		P.leftChild.parent	= P;
		X.parent 			= P.parent;
		P.parent 			= X;
		X.parent.rightChild	= X;
   }

   public void printTreePreOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "PreOrdre [ ");
         printTreePreOrder( root );
         System.out.println( " ]");
      }
      return;
   }
   public void printTreePostOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "PostOrdre [ ");
         printTreePostOrder( root );
         System.out.println( " ]");
      }
      return;
   }
   public void printTreeAscendingOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "AscendingOrdre [ ");
         printTreeAscendingOrder( root );
         System.out.println( " ]");
      }
      return;
   }
   
   public void printTreeDescendingOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "DescendingOrdre [ ");
         printTreeDescendingOrder( root );
         System.out.println( " ]");
      }
      return;
   }
   
   private void printTreePreOrder( RBNode<T> P )
   {
     // A MODIFIER/COMPLETER
	 String aAfficher = ""; //ce qu'on va afficher
	 
	 //pour pre-ordre : racine -> sous arbre de gauche en recursvité -> sous arbre de droite en recursvité
	 
	 if (P != root) {
		 aAfficher += " ; ";
		 System.out.print(aAfficher);
	 }
	 
	 aAfficher = "(" + P.value;
	 if (P.isRed())
		aAfficher += " , red)";
	 else
		aAfficher += " , black)";
	 
	 System.out.print(aAfficher);
	
	 
	 if (P.leftChild.value != null)		//vers la gauche
		 printTreePreOrder(P.leftChild);
	 
	 if (P.rightChild.value != null)	//vers la droite
		 printTreePreOrder(P.rightChild);	 
   }
   
   private void printTreePostOrder( RBNode<T> P )
   {
     // A MODIFIER/COMPLETER
	 String aAfficher = "";
	 
	 //pour post-ordre : sous arbre de gauche en recursvité -> sous arbre de droite en recursvité -> racine
	 
	 if (P.leftChild.value != null)		//vers la gauche
		 printTreePostOrder(P.leftChild);
	 
	 if (P.rightChild.value != null)	//vers la droite
		 printTreePostOrder(P.rightChild);
	 
	 if (P.value != null) {
		 aAfficher = "(" + P.value;
		 if (P.isRed())
			aAfficher += " , red)";
		 else
			aAfficher += " , black)";
		 
		 if (root != P)	//le dernier noeud affiché est la racine et n'a pas de point virgule
			 aAfficher += " ; ";
		 
		 System.out.print(aAfficher);
	 }
   }
     
   private void printTreeAscendingOrder( RBNode<T> P )
   {
     // A MODIFIER/COMPLETER
	 String aAfficher = "";
	 
	 //pour ordre croissant : sous arbre de gauche en recursvité -> racine -> sous arbre de droite en recursvité 
	 
	 if (P.leftChild.value != null)		//vers la gauche
		 printTreeAscendingOrder(P.leftChild);
	 
	 if (P.value != null) {
		 aAfficher = "(" + P.value;
		 if (P.isRed())
			aAfficher += " , red)";
		 else
			aAfficher += " , black)";
		 
		 //gerer le dernier noeud affiché
		 if (P != root) { //que la valeur de P soit plus grande que son Parent et Racine 
			 				//et que P n'aille pas de fils a droite.
			 if (P.value.compareTo(P.parent.value) == 1  && P.value.compareTo(root.value) == 1 && P.rightChild.value == null)
			 {/*ne pas afficher le ; */}
			 else
				 aAfficher += " ; ";
		 }
		 //afficher le ; si c'est la racine
		 if (P == root)
			 aAfficher += " ; ";
		 
		 System.out.print(aAfficher);
	 }
	 
	 if (P.rightChild.value != null)	//vers la droite
		 printTreeAscendingOrder(P.rightChild);
   }
  
   private void printTreeDescendingOrder( RBNode<T> P )
   {
     // A MODIFIER/COMPLETER
	 String aAfficher = "";
	 
	 //pour ordre decroissant : sous arbre de droite en recursvité -> racine -> sous arbre de gauche en recursvité 
	
	 if (P.rightChild.value != null)	//vers la droite
		 printTreeDescendingOrder(P.rightChild);

	 if (P.value != null) {
		 aAfficher = "(" + P.value;
		 if (P.isRed())
			aAfficher += " , red)";
		 else
			aAfficher += " , black)";
		 
		 //gerer le dernier noeud affiché
		 if (P != root) { //que la valeur de P soit plus petite que son Parent et Racine 
			 				//et que P n'aille pas de fils a gauche.
			 if (P.value.compareTo(P.parent.value) == -1  && P.value.compareTo(root.value) == -1 && P.leftChild.value == null)
			 {/*ne pas afficher le ; */}
			 else
				 aAfficher += " ; ";
		 }
		 //afficher le ; si c'est la racine
		 if (P == root)
			 aAfficher += " ; ";
		 
		 System.out.print(aAfficher);
	 }
	 
	 if (P.leftChild.value != null)		//vers la gauche
		 printTreeDescendingOrder(P.leftChild);
   }
   
   public void printTreeLevelOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "LevelOrdre [ ");
         
         Queue<RBNode<T>> q = new LinkedList<RBNode<T>>();
         q.add(root);
         
         // A COMPLETER
         while (!q.isEmpty()) { //jusqu'a ce qu'on ne puisse ajouter d'enfants a la liste
        	 
        	 String aAfficher = "";
        	 RBNode<T> noeudDuNiveau = q.poll(); //retire et conserve la tete
        	 
        	 if (noeudDuNiveau.leftChild.value != null)
        		 q.add(noeudDuNiveau.leftChild); 	//ajouter a la liste enfant de gauche
        	 
        	 if (noeudDuNiveau.rightChild.value != null)
        		 q.add(noeudDuNiveau.rightChild);	//ajouter a la liste enfant de droite
        	 
        	 aAfficher = "(" + noeudDuNiveau.value;	//afficher
    		 if (noeudDuNiveau.isRed())
    			aAfficher += " , red)";
    		 else
    			aAfficher += " , black)";
    		 
        	 System.out.print(aAfficher);
        	 
    		 if (q.size() > 0)	//gerer le point virgule a la fin de l'affichage
    		 	 System.out.print(" ; ");
         }
         
         System.out.println( " ]");
      }
      return;
   }
   
   private static class RBNode<T extends Comparable<? super T> > 
   {
      enum RB_COLOR { BLACK, RED }  // Couleur possible 
      RBNode<T>  parent;      // Noeud parent
      RBNode<T>  leftChild;   // Feuille gauche
      RBNode<T>  rightChild;  // Feuille droite
      RB_COLOR   color;       // Couleur du noeud
      T          value;       // Valeur du noeud
      // Constructeur (NIL)   
      RBNode() { setToBlack(); }
      // Constructeur (feuille)   
      RBNode(T val)
      {
         setToRed();
         value = val;
         leftChild = new RBNode<T>();
         leftChild.parent = this;
         rightChild = new RBNode<T>();
         rightChild.parent = this;
      }
      
      RBNode<T> grandParent()
      {
         // A COMPLETER
    	 // retourne le grand pere du noeud courant
    	 if (this.parent != null && this.parent.parent != null)
    		 return this.parent.parent;
    	 else
    		 return null;
      }
      
      RBNode<T> uncle()
      {
         // A COMPLETER
    	 //retourne le frere de son pere
    	 if (this.parent != null && this.parent.parent != null)
    		 return this.parent.sibling();
    	 else
    		 return null;
	  }
      
      RBNode<T> sibling()
      {
         // A COMPLETER
    	 if (this.parent != null) { //si noeud courant n'est pas la racine
    		 if (this.parent.rightChild == this) 	//si fils de droite est noeud courant
    			 return this.parent.leftChild;		//retourner le fils de gauche s'il y en a un
    		 else
    			 return this.parent.rightChild;		//sinon retourner le fils de droite s'il y en a un
    	 }
    	 else
    		 return null; //pas de frere
	  }
      
      public String toString()
      {
         return value + " , " + (color == RB_COLOR.BLACK ? "black" : "red"); 
      }
      
      boolean isBlack(){ return (color == RB_COLOR.BLACK); }
      boolean isRed(){ return (color == RB_COLOR.RED); }
      boolean isNil(){ return (leftChild == null) && (rightChild == null); }
      
      void setToBlack(){ color = RB_COLOR.BLACK; }
      void setToRed(){ color = RB_COLOR.RED; }
   }
}
