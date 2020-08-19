package probleme1;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			// A completer
			a = b = 0;
			data = null;
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
			data = new QuadraticSpacePerfectHashing[1];
			data[0].SetArray(array);
			// A completer
			return;
		}
		a = generator.nextInt(p-1) + 1;
		b = generator.nextInt(p);
		
		int m = array.size();
		
		data = new QuadraticSpacePerfectHashing[m];
		for (int i = 0; i < m; i++){
			data[i] = new QuadraticSpacePerfectHashing<AnyType>();
		}
		
		for (int i = 0; i < m; i++){
			int index = getKey(array.get(i));
			ArrayList<AnyType> arrayTemp = new ArrayList<AnyType>();
			
			if (data[index] == null){
				arrayTemp.add(array.get(i));
			}
			else{
				for (int j = 0; j < data[index].Size(); j++){
					if (data[index].items[j] != null){
						arrayTemp.add(data[index].items[j]);
					}
				}
				arrayTemp.add(array.get(i));
			}
			data[index].SetArray(arrayTemp);
		}
		// A completer
	}

	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		// A completer
		if (data.length > key) {
			if (data[key] != null)
				return true;
		}
		return false;
	}
	
	public int getKey (AnyType x) {
		// A completer
		return ((a*x.hashCode() + b) % p) % data.length;
		
	}
	
	public boolean containsValue (AnyType x) {
		// A completer
		return (this.containsKey(getKey(x)) && data[getKey(x)]!= null && data[getKey(x)].containsValue(x));
	}
	
	public void remove (AnyType x) {
		// A completer
		if (containsValue(x)){
			data[getKey(x)].remove(x);
		}
	}

	public String toString () {
		String result = "";
		
		// A completer
		for (int i =0; i< data.length; i++){
			result += data[i].toString();
		}
		
		return result; 
	}
}