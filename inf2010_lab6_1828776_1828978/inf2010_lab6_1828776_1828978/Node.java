
public class Node {

	private int id;
	private String name;
	
	public Node(int id, String n) {
		this.id = id;
		this.name = n;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	@Override
	public int hashCode() {
		return id;
	}
}
