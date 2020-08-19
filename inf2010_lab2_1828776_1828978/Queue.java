public interface Queue<AnyType> 
{
	public boolean empty();
	public int size();
	public AnyType peek();
	public void pop() throws EmptyQueueException;
	public void push(AnyType item);
}
