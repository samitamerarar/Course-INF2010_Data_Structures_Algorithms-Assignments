
public class BinaryTree<AnyType> {
	private Node<AnyType> root = null; // Racine de l'arbre

	// insert element in arbre
	public void insert (AnyType elem) {
	        if(root==null){
	        	this.root= new Node<AnyType>(elem);
	        }else
	        	insert(root, elem);
	}	
	
	@SuppressWarnings("unchecked")
	private void insert(Node<AnyType> node, AnyType elem) {
		// A completer	
		if (elem.hashCode() > node.val.hashCode()) {	
			if (node.right == null)							
				node.right = new Node<AnyType>(elem);		
			else											//si noeud pas null
				insert(node.right, elem);					//chercher fils de droite
		}
		
		else if (elem.hashCode() < node.val.hashCode()) {	
			if (node.left == null)
				node.left = new Node<AnyType>(elem);
			else											//si noeud pas null
				insert(node.left, elem);					//chercher fils de gauche
		}
		
		else {}	//peuvent pas etre egaux dans un arbre binaire de recherche
	}
	
	public int getHauteur () {
		return this.getHauteur(this.root);
	}
 
	public String printPrefixe() {
		return "{ " + this.printPrefixe(this.root) + " }";
	}
	public String printInFixe() {
		return "{ " + this.printInfixe(this.root) + " }";
	}
	
	public String printPostFixe() {
		return "{ " + this.printPostfixe(this.root) + " }";
	}
	
	@SuppressWarnings("unchecked")
	private int getHauteur(Node<AnyType> tree) {
		// A completer
		if (tree != null) {
			//on calcule le maximum de noeuds en hauteur + 1 (pour la racine)
			if (getHauteur(tree.left) > getHauteur(tree.right))
				return getHauteur(tree.left) + 1;
			else //si hauteur de gauche < que hauteur de droite
				return getHauteur(tree.right) + 1;
		}
		else
			return 0;
	}
	
	@SuppressWarnings("unchecked")
	private String printPrefixe(Node<AnyType> node) {
		// COMPLETER
		if (node != null)
			return node.val + ", " + printPrefixe(node.left) + printPrefixe(node.right);
		else
			return "";
	}

	@SuppressWarnings("unchecked")
	private String printInfixe(Node<AnyType> node) {
		// COMPLETER
		if (node != null)
			return printInfixe(node.left) + node.val + ", " + printInfixe(node.right);
		else
			return "";
	}
	
	@SuppressWarnings("unchecked")
	private String printPostfixe(Node<AnyType> node) {
		// COMPLETER
		if (node != null)
			return printPostfixe(node.left) + printPostfixe(node.right) + node.val + ", ";
		else
			return "";
	}
	
	private class Node<AnyType> {
		AnyType val; // Valeur du noeud
		Node right; // fils droite
		Node left; // fils gauche

		public Node (AnyType val) {
			this.val = val;
			right = null;
			left = null;
		}

	}
}