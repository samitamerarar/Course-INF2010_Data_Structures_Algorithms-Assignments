
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Monceau {
    ArrayList<Node> arbres;
    
    public Monceau() {
        arbres = new ArrayList<Node>();
    }
    
    /**
    Fusionne deux monceaux binomiaux.
     *
     * @param autre Un autre monceau binomial.
     * @throws DifferentOrderTrees
    */
    public void fusion(Monceau autre) throws DifferentOrderTrees {
        // à compléter
    	ArrayList<Node> temp = new ArrayList<Node>();
		boolean fini = false;
    	
    	if (arbres.isEmpty())
    		arbres = autre.arbres;
    	
    	else if (!arbres.isEmpty() || !autre.arbres.isEmpty()) {
    		//remplir la arraylist (ou arbre temp)
    		for (int i = 0; i < arbres.size(); i++)
    			temp.add(arbres.get(i));
    		
    		for (int i = 0; i < autre.arbres.size(); i++)
    			temp.add(autre.arbres.get(i));
    		
    		//trier la arraylist qui contient l'arbre temp
    		Collections.sort(temp, new Comparator<Node>() {
    			@Override
    			public int compare(Node objet1, Node objet2) {
    				Integer noeud1 = new Integer(objet1.ordre);
    				Integer noeud2 = new Integer(objet2.ordre);
    				return noeud1.compareTo(noeud2);}
    		});
    		
    		while (!fini) {    			
    			fini = true;
    			for (int j = 0; j < temp.size(); j++) {
    				for (int k = j+1; k < temp.size(); k++) {
    					if (temp.get(j).ordre == temp.get(k).ordre)
    						fini = false; //faut pas avoir le meme ordre
    				}
    			}
    			
    			for (int j = 0; j < temp.size()-1; j++) {
    				if (temp.get(j).ordre == temp.get(j + 1).ordre) {
    					//les elements doivent conserver leur ordre
    					temp.set(j, temp.get(j).fusion(temp.get(j+1)));
    					temp.remove(j+1);
    				}
    			}
    		}
    		
    		arbres = temp;
    	}
    	
    	return;
    }
    
    /**
    Insere une valeur dans le monceau.
     *
     * @param val La valeur a inserer.
     * @throws DifferentOrderTrees
    */
    public void insert(int val) throws DifferentOrderTrees {
    	Monceau monceauTemp = new Monceau();
    	
    	monceauTemp.arbres.add(new Node(val));
    	this.fusion(monceauTemp);
    }
    
    /**
    Supprime tous les noeuds ayant la valeur a supprimer.
     *
     * @param val La valeur a supprimer.
     * @return Vrai si la valeur a ete supprimee, sinon Faux.
     * @throws DifferentOrderTrees
    */
    public boolean delete (int val) throws DifferentOrderTrees {
    	boolean supprime = false;
    	Monceau monceauTemp = new Monceau();
    	
    	//recherche de la valeur a supprimer
    	for (int i = 0; i < arbres.size(); i++) {
    		if (arbres.get(i).findValue(val) == null)
    			continue;
    		else {
    			monceauTemp.arbres = arbres.get(i).findValue(val).delete();
    			arbres.remove(arbres.get(i)); //supprime noeud
    			this.fusion(monceauTemp); //fusionne les 2 monceaux
    			i = (0-1); //on recommence (-1 car on fait i++ apres (boucle for))
    			supprime = true;
    		}
    	}
    	
        return supprime;
    }
    
    /**
    Permet d'afficher le monceau.
     *
    */
    public void print() {
    	for (int i = 0; i < arbres.size(); i++) {
    		System.out.println("Ordre de " + arbres.get(i).ordre + ":");
    		arbres.get(i).print("");
    		System.out.println();
    	}
    }
}