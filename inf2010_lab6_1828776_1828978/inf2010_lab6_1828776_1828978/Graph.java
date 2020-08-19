import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Graph {

	private List<Node> nodes; // Noeuds
	private List<Edge> edges; // Les arcs
	static final double inf = 99999;
	public Graph() {
		
	}
	
	/**
	 * Cree un graphe a partir d'un fichier
	 * 
	 * @param filePath Le repertoire du fichier
	 * @param separtor Le separateur qui separe chaque info du fichier (ex: , )
	 */
	public void readFromFile(String filePath,String separtor){
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		
		BufferedReader fichier;
		String tempLuDuFichier;
		String ligneDeNodesALire = null; //la premiere ligne du fichier contenant les noeuds
		List<String> lignesDeEdgesALire = new ArrayList<String>(); //les lignes contenant les arcs
		try {
			fichier = new BufferedReader(new FileReader(filePath));
			ligneDeNodesALire = fichier.readLine(); //lire la premiere ligne du fichier contant les noeuds
			while ((tempLuDuFichier = fichier.readLine()) != null) {
				lignesDeEdgesALire.add(tempLuDuFichier); //lire les lignes contenant les arcs
			}
			
		} catch (IOException exception) {
			System.out.println("Erreur lecture fichier"); //si le fichier n'arrive pas a etre lu
		}
		
		//les noeuds du fichier sont separer et ensuite mis un par un dans la liste de noeuds
		String [] tableau = ligneDeNodesALire.split(separtor);
		for (int i = 0; i < tableau.length; i++) {
			Node node1 = new Node(i, tableau[i]);
			this.nodes.add(node1);
		}
		
		//les arcs du fichier sont creer avec la source, destination et la distance et ensuite mis dans la liste de edges
		String [] tabLigne; //sert a stocker ligne par ligne les info sur les edges
		for (int i = 0; i < lignesDeEdgesALire.size(); i++) { //on parcours les differentes lignes sur les edges, ligne par ligne
			tabLigne = lignesDeEdgesALire.get(i).split(separtor);
			
			int sauvNodeSource = 0;
			List<Integer> sauvNodeVoisins = new ArrayList<Integer>();
			List<Double> sauvNodeVoisinsValeur = new ArrayList<Double>();
			for (int j = 0; j < tabLigne.length; j++) {
				if (tabLigne[j].equals("0")) {
					sauvNodeSource = j; //sauvegarde du noeud source (sa distance vers lui meme = 0)
				}
				if (!tabLigne[j].equals("0") && !tabLigne[j].equals("inf") && !tabLigne[j].equals("")) {
					sauvNodeVoisins.add(j); //sauvegarde de la destination
					sauvNodeVoisinsValeur.add(Double.parseDouble(tabLigne[j])); //sauvegarde de la distance
				}
			}
			for (int j = 0; j < sauvNodeVoisins.size(); j++) { //creation des edges et ajout sur la liste de edges
				Edge edge = new Edge(nodes.get(sauvNodeSource), nodes.get(sauvNodeVoisins.get(j)), sauvNodeVoisinsValeur.get(j));
				edges.add(edge);
			}
		}
	}
	
	/**
	 * Retourne tous les arcs sortants d'un noeud source
	 * 
	 * @param source Le noeud source
	 * @return une list d'arcs sortants
	 */
	public List<Edge> getOutEdges(Node source) {
		List<Edge> outEdges = new LinkedList<Edge>();
		for (Edge edge : edges) {
			if (edge.getSource().getName().equals(source.getName())
					&& edge.getSource().getId() == source.getId())
				outEdges.add(edge);
		}
		
		return outEdges;
	}
	
	/**
	 * Retourne tous les arcs entrant d'un noeud destination
	 * 
	 * @param dest Le noeud destination
	 * @return une list d'arcs entrants
	 */
	public List<Edge> getInEdges(Node dest) {
		List<Edge> inEdges = new LinkedList<Edge>();
		for (Edge edge : edges) {
			if (edge.getDestination().getName().equals(dest.getName())
					&& edge.getDestination().getId() == dest.getId())
				inEdges.add(edge);
		}
		
		return inEdges;
	}
	// Accesseurs 
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public Node getNodeByName(String name){
		for (Node node : nodes) {
			if(node.getName().equals(name)){
				return node;
			}
		}
		return null;
	}
}
