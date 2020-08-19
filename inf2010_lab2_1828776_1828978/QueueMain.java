


public class QueueMain 
{
	final static int COUNT = 1013;
	
	public static void main(String[] args) {

		Queue<Integer> arrayQueue = new ArrayQueue<Integer>();
		Queue<Integer> listQueue = new LinkedListQueue<Integer>();
		int pushCounter = 0;
		int popCounter = 0;	
		
		for(int i = 0; i < COUNT*2; i++)
		{
			arrayQueue.push(pushCounter);
			listQueue.push(pushCounter);
			pushCounter++;
		}
		if(arrayQueue.size() != COUNT*2 || listQueue.size() != COUNT*2)
		{
			System.out.println("Erreur: La taille de la file n'est egale a " + COUNT + " apres avoir ajoute " + COUNT + " elements");
		}
		
		for(int i = 0; i < COUNT; i++)
		{
			try 
			{
				arrayQueue.pop();
				listQueue.pop();
				popCounter++;
			} 
			catch (EmptyQueueException e) 
			{
				e.printStackTrace();
				return;
			}
		}
		
		for(int i = 0; i < COUNT*2.5; i++)
		{
			arrayQueue.push(pushCounter);
			listQueue.push(pushCounter);
			pushCounter++;
		}
		
		for(int i = 0; i < COUNT*3.5; i++)
		{
			if(arrayQueue.peek() != popCounter || listQueue.peek() != popCounter)
			{
				System.out.println("Erreur: l'ordre de sortie(FIFO) n'est pas respecte");
				return;
			}
			
			try 
			{
				arrayQueue.pop();
				listQueue.pop();
				popCounter++;
			} 
			catch (EmptyQueueException e) 
			{
				e.printStackTrace();
				return;
			}
		}
		
		if(!arrayQueue.empty() || !listQueue.empty())
		{
			System.out.println("Erreur: la file devrait etre vide, mais elle ne l'est pas");
			return;
		}
		
		if(arrayQueue.peek() != null || listQueue.peek() != null)
		{
			System.out.println("Erreur: peek doit retourner null lorsque la file est vide");
			return;
		}
		
		try 
		{
			arrayQueue.pop();
			System.out.println("Erreur: Pop doit lancer une exception lorsque la file est vide");
			return;
		} 
		catch (EmptyQueueException e){}
		
		try 
		{
			listQueue.pop();
			System.out.println("Erreur: Pop doit lancer une exception lorsque la file est vide");
			return;
		} 
		catch (EmptyQueueException e){}
		
		
		System.out.print("It's all good");
	}
}
