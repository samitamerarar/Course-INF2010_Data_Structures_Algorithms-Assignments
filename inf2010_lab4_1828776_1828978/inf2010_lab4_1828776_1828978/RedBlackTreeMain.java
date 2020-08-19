public class RedBlackTreeMain 
{
   public static void main(String[] args)
   {
      // Valeurs a inserer dans l'arbre
      int val[] = {53, 77, 29, 64, 70, 15, 30};
      
      // Creation de l'arbre
      RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
      
      // Insertion des elements dans l'arbre
      for(int i=0; i<val.length; i++){
         // insérer cle
         tree.insert( new Integer( val[i] ) );
         // afficher
          tree.printFancyTree();
          System.out.println();
      }
      // Verifier find()
      Integer n = tree.find(15);
      System.out.print("Recherche valeur 15 : ");
      if ( n != null )  
        System.out.println("Noeud trouvé."); 
      else
        System.out.println("Noeud introuvable.");
      
      n = tree.find(99);
      System.out.print("Recherche valeur 99 : ");
      if(n != null)
        System.out.println("Noeud trouvé.");
      else
        System.out.println("Noeud introuvable.");
            
      System.out.println();
      
      // Affichage pre-ordre et post-ordre
      tree.printTreePreOrder();
      tree.printTreePostOrder();
      tree.printTreeAscendingOrder();
      tree.printTreeDescendingOrder();
      // Affichage par niveaux
      tree.printTreeLevelOrder(); 
   }
}
