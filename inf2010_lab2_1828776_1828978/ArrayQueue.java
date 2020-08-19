

public class ArrayQueue<AnyType> implements Queue<AnyType>
{
	private int size = 0;		//Nombre d'elements dans la file.
	private int startindex = 0;	//Index du premier element de la file
	private AnyType[] table;
   
	@SuppressWarnings("unchecked")
	public ArrayQueue() 
	{
		//A completer
		table = (AnyType[]) new Object[size];
	}
	
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
		if (table[startindex] == null){
			return null;
		}
		else{
			return table[startindex];
		}
	}
	
	//Retire l'element en tete de file
	//complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		//A completer
		if (size ==0) {
			throw new EmptyQueueException();
		}
		else{
			table[startindex] = null;
			startindex++;
			size--;
		}
		
	}
	
	//Ajoute un element a la fin de la file
	//Double la taille de la file si necessaire
	//complexit� asymptotique: O(1) ( O(N) lorsqu'un redimensionnement est necessaire )
	public void push(AnyType item)
	{
		//A completer
		if(table.length == size){
			this.resize(size);
		}
		if(size == 0){ //Si le tableau est vide, on assigne l'item à l'index 0
			table[0] = item;
		}
		
		else if(startindex + size >= table.length){ 
			//Si le tableau n'est pas plein et qu'on est rendu à la fin du tableau on ajoute avant le startindex
			table[size - (table.length-startindex)] = item;
		}
		else{
			//On ajoute au premier élément vide après le startindex
			table[startindex + size] = item;
		}
		size++;
	}
   
	//Redimensionne la file. La capacite est multipliee par un facteur de resizeFactor.
	//Replace les elements de la file au debut du tableau
	//complexit� asymptotique: O(N)
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor)
	{
		//A completer
		if (size == 0){
			//si le tableau est vide, on crée un tableau vide
			table = (AnyType[]) new Object[1];
		}
		else if(size ==1){
			//pour doubler un size 1
			AnyType[] temp = (AnyType[]) new Object[size * 2];
			temp[0] = table[0];
			table = temp;
		}
		else {
			AnyType[] temp = (AnyType[]) new Object[size*resizeFactor];
			
			if (startindex ==0){
				//si le startindex est toujours 0, on recopie normalement
				for (int i = 0; i < table.length; i++){
					temp[i] = table[i];
				}
			}
			else{
				//si le start index n'est pas zéro
				int indexTemp=0;
				for (int i = startindex; i < table.length; i++){
					//on recopie au début du nouveau tableau les valeurs après le startindex
					temp[i - startindex] = table[i];
					indexTemp = i - startindex;
				}
				for (int i = 0; i < startindex; i ++){
					//on recopie les données avant le start index à la suite des items déjà ajoutés
					temp[i + indexTemp + 1] = table[i];
				}
				//pisque les items sont réordonnées au début du nouveau tableau, on doit réinitialiser le startindex
				startindex = 0;
				
			}
			table = temp;
		}
	}   
}

