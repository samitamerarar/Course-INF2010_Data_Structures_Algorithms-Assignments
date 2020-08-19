
public class LinkedListQueue<AnyType> implements Queue<AnyType>
{	
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> 
	{
		private AnyType data;
		private Node next;
		
		public Node(AnyType data, Node next) 
		{
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) 
		{
			this.next = next;
		}
		
		public Node<AnyType> getNext() 
		{
			return next;
		}
		
		public AnyType getData() 
		{
			return data;
		}
	}
   
	private int size = 0;		//Nombre d'elements dans la file.
	private Node<AnyType> last;	//Dernier element de la liste
	
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexit� asymptotique: O(1)
	public AnyType peek()
	{
		//A completer
		if (size ==0){
			return null;
		}
		else{
			return this.last.getNext().getData();
		}
	}
	
	//Retire l'element en tete de file
	//complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		//A completer
		if(size == 0){
			throw new EmptyQueueException();
		}
		else if(size == 1){
			last = null;
			size--;
		}
		else{
			last.setNext(last.getNext().getNext()) ;
			size--;
		}
	}
	
	//Ajoute un element a la fin de la file
	//complexit� asymptotique: O(1)
	public void push(AnyType item)
	{		
		//A completer
		if (size ==0){
			//Pour le premier élément, il faut que le next pointe sur lui-même
			Node<AnyType> temp = new Node<AnyType>(item, null);
			last = temp;
			last.setNext(temp);
		}

		else {
			Node<AnyType> temp = new Node<AnyType>(item, last.getNext());
			last.setNext(temp);
			last = temp;
		}
		
		
		size++;
	}  
}
