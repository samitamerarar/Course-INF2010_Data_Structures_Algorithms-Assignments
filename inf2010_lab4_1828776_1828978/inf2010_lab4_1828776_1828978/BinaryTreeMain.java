

public class BinaryTreeMain 
{
   public static void main(String[] args)
   {
      // Valeurs a inserer dans l'arbre
      int val[] = {50, 40, 20,30,70,60,80};
      
      // Creation de l'arbre
      BinaryTree<Integer> binTree = new BinaryTree<Integer>();
      // Insertion des elements dans l'arbre binaire
      for(int i=0; i<val.length; i++){
    	 // System.out.println("insert : "+ val[i]+" ");
         binTree.insert(new Integer(val[i]));
      }
      System.out.println("Prefixe: "+binTree.printPrefixe());
      System.out.println("Infixe: "+binTree.printInFixe());
      System.out.println("Postfixe: "+binTree.printPostFixe());
      System.out.println("Hauteur: "+binTree.getHauteur());
      // test delete 
      
  }
}
