

public class EmptyQueueException extends Exception
{
	public EmptyQueueException() 
	{
	    super("Error: the queue is empty");
	}
	
	public EmptyQueueException(String s) 
	{
		super(s);
	}
};
