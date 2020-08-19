import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;


public class Bellman {
	private Graph graph;
	private Node sourceNode;
	private List<Vector<Double>> piTable;
	private List<Vector<Integer>> rTable;
	
	public Bellman (Graph g) {
		this.graph = g;
	}
	
	public void setSourceNode(Node source) {
		this.sourceNode = source;
	}
	
	/**
	 * Algorithme de Bellman
	 * Trouve le chemin le plus court entre un noeud de depart et tous les autres noeuds
	 */
	public void shortestPath() {
		piTable = new ArrayList<Vector<Double>>();
		rTable = new ArrayList<Vector<Integer>>();
		
		//Note : l'indice de la position d'un noeud dans la list<Node> nodes et le meme indice
		//que a un noeud dans la piTable et rTable
		
		//initialisation de la piTable avec un nombre de vecteurs = nombre de nodes
		//les vecteurs ont des elements => 99999.0
		for (int i = 0; i <= graph.getNodes().size(); i++) {
			Vector<Double> vec = new Vector<Double>();
			for (int j = 0; j < graph.getNodes().size(); j++) {
				vec.add(99999.0);
			}
			piTable.add(vec);
		}
		
		//mettre la source a une distance de zero dans la premiere ligne de piTable
		for (int i = 0; i < graph.getNodes().size(); i++) {
			if (graph.getNodes().get(i).equals(sourceNode))
				piTable.get(0).set(i, 0.0);
		}
		
		//initialisation de la rTable avec un nombre de vecteurs = nombre de nodes
		//les vecteurs ont des elements => -1
		for (int i = 0; i <= graph.getNodes().size(); i++) {
			Vector<Integer> vec = new Vector<Integer>();
			for (int j = 0; j < graph.getNodes().size(); j++) {
				vec.add(-1);
			}
			rTable.add(vec);
		}
		
		//algorithme de Bellman
		boolean fini = false;
		int i = 1; //on commence a remplir la piTable a partir de son deuxieme vecteur (2iem ligne)
		while (i <= graph.getNodes().size()) {
			
			//piTable : on descend les distance de la ligne d'avant a la ligne en cours
			for (int j = 0; j < graph.getNodes().size(); j++)
				piTable.get(i).set(j, piTable.get(i-1).get(j));
			
			//rTable : on descend les noeuds de la ligne d'avant a la ligne en cours
			for (int j = 0; j < graph.getNodes().size(); j++)
				rTable.get(i).set(j, rTable.get(i-1).get(j));
			
			//pour chaque noeud
			for (int j = 0; j < graph.getNodes().size(); j++) {
				Node temp = graph.getNodes().get(j);
				
				//pour chaque destination de ce noeud
				for (int k = 0; k < graph.getOutEdges(temp).size(); k++) {
					Node source = graph.getOutEdges(temp).get(k).getSource();
					int indice = graph.getNodes().indexOf(source);
					
					//ce noeud source est pas encore connu
					if (piTable.get(i-1).get(indice) == 99999.0)
						continue;
					
					//on cherche a savoir la distance pour aller au noeud destination
					Node dest = graph.getOutEdges(temp).get(k).getDestination();
					int indice2 = graph.getNodes().indexOf(dest);
					
					//si (la valeur connu pour atteindre le noeud source) + (la distance pour aller au noeud destination)
					//est plus petite que la valeur connu pour atteindre le noeud destination
					double valeur = piTable.get(i-1).get(indice) + graph.getOutEdges(temp).get(k).getDistance();
					if (valeur < (piTable.get(i-1).get(indice2))) {
						//on met a jour piTable pour la valeur la plus petite pour atteindre noeud destination
						piTable.get(i).set(indice2, valeur);
						//et on change le predecesseur au noeud destination
						Integer id = graph.getOutEdges(temp).get(k).getSource().getId();
						rTable.get(i).set(indice2, id);						
					}
				}
			}
			
			//verifier si 2 lignes consecutive de la piTable sont pareilles
			int compteur = 0;
			for (int j = 0; j < graph.getNodes().size(); j++) {
				if (piTable.get(i).get(j) == piTable.get(i-1).get(j))
					compteur++;
				if (compteur == graph.getNodes().size())
					fini = true;
			}
			//si 2 lignes consecutive de la piTable sont pareilles, on sort
			if (fini) {
				//on supprime d'abord le surplus de vecteurs dans piTable et rTable
				piTable.subList(i+1, piTable.size()).clear();
				rTable.subList(i+1, rTable.size()).clear();
				break; //on sort
			}
			
			//on passe a l'autre ligne de la piTable
			i++;
		}
	}
	
	/**
	 * Affichage du circuit de cout negatif
	 * sinon affichage des chemins les plus court jusqua chacun des sommets
	 */
	public void  diplayShortestPaths() {
		Stack<Node> path=new Stack<Node>();
		
		//on regarde s'il y a presence d'un cycle, 2 dernieres ligne de piTable
		//pas pareilles = presence d'un cyle
		boolean cycle = true;
		int compteur = 0;
		for (int i = 0; i < graph.getNodes().size(); i++) {
			if (piTable.get(piTable.size()-1).get(i) == piTable.get(piTable.size()-2).get(i))
				compteur++;
			if (compteur == graph.getNodes().size())
				cycle = false;
		}
		
		//string dans lequel on store ce qu'on va afficher
		String temp = "";
		
		//presence d'un cycle
		if (cycle) {
			System.out.println("\n=> le graphe contient un circuit de cout negatif :\n");
			for (int i = 0; i < graph.getNodes().size(); i++) {
				//on veut pas traiter le noeud source
				if (graph.getNodes().get(i).getName() == sourceNode.getName())
					continue;
				else {
					//on verifie d'abord si on connait le noeud
					double distance = piTable.get(piTable.size()-1).get(i);
					if (distance == 99999.0)
						continue; //on ne le connait pas
					
					//on met sur le chemin un noeud
					path.push(graph.getNodes().get(i));
					
					int index = i;
					while (true) {
						int id = rTable.get(rTable.size()-1).get(index);
						//on met sur le chemin le noeud predecesseur au noeud courant
						path.push(graph.getNodes().get(id));
						index = id; //on traite dans la prochaine iteration le noeud predecesseur
						
						//si on atteint un cycle : le premier noeud = le dernier noeud, on arrete
						if (graph.getNodes().get(index).getName() == graph.getNodes().get(i).getName()) {
							temp = "[" + graph.getNodes().get(i).getName() + " - " + graph.getNodes().get(i).getName() + "] : ";
							break;
						}
						
						//on veut voir si on n'a pas deja un noeud pareil sur le stack
						//donc on cree une nouvelle stack, en depilant path sur tempPath
						//on compare le noeud en cours avec les noeuds qui se depilent
						Stack<Node> tempPath=new Stack<Node>();
						Node tempNoeud;
						boolean cycleDansUnCycle = false;
						if(path.peek() != null) {
							tempNoeud = path.pop();
							tempPath.push(tempNoeud);
						
							while (!path.isEmpty()) {
								if (path.peek() != tempNoeud)
									tempPath.push(path.pop());
								else {
									cycleDansUnCycle = true;
									break;
								}
							}
						}
						
						//si on trouve un noeud pareil, on sort de la boucle
						//et on continu a chercher un cycle avec le prochain noeud
						if (cycleDansUnCycle) {
							//faut vider path du noeud en cours
							while (!path.isEmpty())
								path.pop();
							temp = "";
							break;
						}
						//on a pas trouver un noeud pareil, donc on remet tous
						//les noeuds sur path
						else {
							while (!tempPath.isEmpty()) {
								path.push(tempPath.pop());
							}
						}
					}
				}
				
				//afichage de path
				if (temp != "") {
					Node noeudSource = path.peek();
					while (!path.isEmpty()) {
						temp += path.pop().getName() + " -> ";
						if (!path.isEmpty()) {
							if (path.peek() != noeudSource) {
								temp += path.pop().getName() + " -> ";
							}
							else
								temp += path.pop().getName();
						}
					}
					System.out.println(temp);
					break;
				}
			}
		}
		
		//pas de cycle, affichage des chemins les plus court jusqua chaque sommet
		else {
			System.out.println("\n=> Les chemins sont :\n");
			//pour chaque sommet 
			for (int i = 0; i < graph.getNodes().size(); i++) {
				temp = "[" + sourceNode.getName() + " - ";
				//on ne veut pas traiter le noeud source
				if (graph.getNodes().get(i).getName() == sourceNode.getName())
					continue;
				else {
					//distance totale a afficher pour se rendre a ce sommet destination
					double distance = piTable.get(piTable.size()-1).get(i);
					if (distance == 99999.0)
						continue;
					
					temp += graph.getNodes().get(i).getName() + "] " + distance + " : ";
					
					//on met sur la route le sommet en cours
					path.push(graph.getNodes().get(i));
					
					//on parcours la piTable pour retrouver le chemin
					int index = i;
					while (graph.getNodes().get(index).getName() != sourceNode.getName()) {
						if (rTable.get(rTable.size()-1).get(index) != -1) {
							int id = rTable.get(rTable.size()-1).get(index);
							path.push(graph.getNodes().get(id)); //on met sur le path le predecesseur
							index = id; //on traite le predecesseur a la prochaine iteration
						}
						else
							break;
					}
					
					//on prend tout ce qu'il y a sur la stack path
					while (!path.isEmpty()) {
						if (path.peek() != graph.getNodes().get(i)) {
							temp += path.pop().getName() + " -> ";
						}
						else
							temp += path.pop().getName();
						
					}
				}
				//on affiche la ligne
				System.out.println(temp);
			}
		}		
	}

	/**
	 * Affichage de la table piTable et rTable
	 */
	public void displayTables() {
		//affichage de piTable
		System.out.println("<< PITable >>:");
		String temp = "k\t:\t";
		//affichage de la premiere lignes avec le nom des noeuds
		for (int i = 0; i < graph.getNodes().size(); i++) 
			temp += graph.getNodes().get(i).getName() + "\t";
		
		System.out.println(temp);
		//remplissage des donnees pour chaque iteration
		for (int i = 0; i < piTable.size(); i++) {
			temp = i + "\t:\t";
			for (int j = 0; j < piTable.get(i).size(); j++) {
				if (piTable.get(i).elementAt(j) == 99999.0)
					temp += "inf\t";
				else
					temp += piTable.get(i).elementAt(j) + "\t";
			}
			System.out.println(temp);
		}
		
		//affichage de rTable
		System.out.println("\n<< rTable >>:");
		temp = "k\t:\t";
		//affichage de la premiere lignes avec le nom des noeuds
		for (int i = 0; i < graph.getNodes().size(); i++)
			temp += graph.getNodes().get(i).getName() + "\t";
			
		System.out.println(temp);
		//remplissage des donnees pour chaque iteration
		for (int i = 0; i < rTable.size(); i++) {
			temp = i + "\t:\t";
			for (int j = 0; j < rTable.get(i).size(); j++) {
				if (rTable.get(i).elementAt(j) == -1)
					temp += "-\t";
				else {
					int ind = rTable.get(i).elementAt(j);
					temp += graph.getNodes().get(ind).getName() + "\t";
				}
			}
			System.out.println(temp);
		}
	}
}
