package probleme1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key)
	{
		// A completer
		if (items.length > key){
			if (items[key] != null){
				return true;
			}
		}
		return false;

	}
	
	
	public boolean containsValue(AnyType x )
	{
		if (items == null)
			return false;
		
		int currentPos=0;
		if (a != 0){
			currentPos= getKey(x);
		}
		return x.equals(items[currentPos]);

	}

	public void remove (AnyType x) {
		int currentPos = getKey(x);
		
		if (items[currentPos]!= null){
			items[currentPos] = null;
		}

	}

	public int getKey (AnyType x) {
		return ((a*x.hashCode() + b)%p)%items.length;
		
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			a = b = 0;
			items = null;
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
			items = (AnyType[]) new Object[1];		
			items[0] = array.get(0);
			return;
		}

		do
		{
			items = null;
			a = generator.nextInt(p-1)+1;
			b = generator.nextInt(p);
			int m = array.size() * array.size();
			items = (AnyType[]) new Object[m];
			for (int i = 0; i < array.size(); i++){
				items[getKey(array.get(i))]= array.get(i); //placer l'item i à la key de i
			}
			// A completer

		}
		while( collisionExists( array ) );
	}

	@SuppressWarnings("unchecked")
	private boolean collisionExists(ArrayList<AnyType> array)
	{
		//Modifier
		for(int i = 0; i < array.size(); i++){
			if (items[getKey(array.get(i))] != array.get(i)){
				return true;
			}
		}
		return false;
	}
	
	public String toString () {
		String result = "";
		
		// A completer
		for (int i = 0; i<items.length;i++){
			if (items[i]!= null){
				result += "(" + i + ", " + items[i] +"), ";
			}
		}
		
		if (result.length() > 0)
			result = result.substring(0, result.length()-2);
		
		return result; 
	}
	
}