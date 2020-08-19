
public class Edge {

	private Node source;
	private Node destination;
	private double distance;


	public Edge () {}
	
	public Edge(Node s, Node d,double dist) {
		this.source = s;
		this.destination = d;
		this.distance=dist;
	}
	
	public Node getSource() {
		return source;
	}
	
	public void setSource(Node source) {
		this.source = source;
	}
	
	public Node getDestination() {
		return destination;
	}
	
	public void setDestination(Node destination) {
		this.destination = destination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDsitance(double distance) {
		this.distance = distance;
	}
}