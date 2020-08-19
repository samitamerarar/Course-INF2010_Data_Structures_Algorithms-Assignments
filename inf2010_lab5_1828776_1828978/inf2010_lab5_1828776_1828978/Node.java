
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) {
        enfants.add(enfant);
    }

    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    public void removeEnfants(ArrayList<Node> enfants) {
        this.enfants.removeAll(enfants);
    }

    /**
    Fusionne deux arbres donnés.
	 *
     * @param autre L'arbre dont on devra fusionner avec l'arbre en cours.
     * @return Le nouvel arbre.
	 * @throws DifferentOrderTrees
    */
    public Node fusion(Node autre) throws DifferentOrderTrees {
    	//arbres d'ordre different => exception
    	if (ordre != autre.ordre)
    		throw new DifferentOrderTrees();
    	
    	Node arbreTemp = null;

    	//arbres de meme ordre et sont des racines
        if (this.ordre == autre.ordre && this.parent == null && autre.parent == null) {
        	//respect de la condition d'ordre du monceau
        	if (this.valeur < autre.valeur) {
        		arbreTemp = this;
        		arbreTemp.addEnfant(autre);
        		autre.parent = this;
        		arbreTemp.ordre++;
        		arbreTemp.parent = null;
        	}
        	else {
        		arbreTemp = autre;
        		arbreTemp.addEnfant(this);
        		this.parent = autre;
        		arbreTemp.ordre++;
        		arbreTemp.parent = null;
        	}
        }
        //fusion
        return arbreTemp;
    }

    /**
    Echange la position d'un noeud et de son parent pour
    la fonction delete().
     *
    */
    private void moveUp() {
    	Node sauvegardeAncienParent = this.parent; //sauvegarde le parent
    	this.parent = this.parent.parent; //on met le grand-parent dans parent

    	if (this.parent != null) //mettre node en cours (this) a la position de l'ancien parent
	    	this.parent.enfants.set(this.parent.enfants.indexOf(sauvegardeAncienParent), this);
    	
    	ArrayList<Node> sauvegardeAncienEnfants = this.enfants; //sauvegarde les enfants
    	this.enfants = sauvegardeAncienParent.enfants; //enfants ancien parent
    	
    	if (this.enfants != null) //mettre l'ancien parent a la position de l'enfant
    		this.enfants.set(this.enfants.indexOf(this), sauvegardeAncienParent);
    	
    	//transfert d'enfants
    	sauvegardeAncienParent.enfants = sauvegardeAncienEnfants;
    	
    	//transfert d'ordres
    	int sauvegardeAncienOrdre = this.ordre;
    	this.ordre = sauvegardeAncienParent.ordre;
    	sauvegardeAncienParent.ordre = sauvegardeAncienOrdre;
    }

    /**
    Supprime un noeud de l'arbre apres l'avoir monté en racine.
	 *
     * @return Les k-1 enfants du noeud supprimé.
    */
    public ArrayList<Node> delete() {
    	//monter le noeud jusqu'a en faire la racine
        while (this.parent != null)
        	this.moveUp();
        
        //supression de la racine
        //le parent des ses enfants n'existe plus!
        for(int i = 0; i < this.enfants.size(); i++)
        	enfants.get(i).parent = null;
        
        //k-1 enfants
        return enfants;
    }

    /**
    Permet d'afficher les valeurs d'un arbre.
	 *
     * @param tabulation La tabulation s'incremente recursivement.
    */
    public void print(String tabulation) {
    	System.out.print(tabulation + valeur);
    	if(enfants.size() > 0) {
    		for(int i = 0; i < enfants.size(); i ++) {
   
    			if (i == 0)
    				enfants.get(i).print("\t");
    			else
    				enfants.get(i).print(tabulation + "\t");
    		}
    	}
    	else
    		System.out.println();
    }
    
    public Node findValue(int valeur) {
        return findValue(this, valeur);
    }
    
    /**
    Renvoi un premier noeud contenant une valeur demandée
    parmi les enfants, sous enfant, etc, du noeud.
	 *
     * @param n Le noeud dans lequel on commence la recherche.
     * @param valeur La valeur recherchée.
     * @return Le noeud demandé, si existant, parmi les descendants.
    */
    public Node findValue (Node n, int valeur) {
    	Node temp = null;
    	if (n.valeur == valeur)
    		return n; //on a trouvé!
    	
    	for (int i = 0; i < n.enfants.size(); i++) {
    		
    		if (n.enfants.get(i).valeur <= valeur)
    			//recursion
    			temp = findValue(n.enfants.get(i), valeur);
    	}
    	return temp;
    }
}