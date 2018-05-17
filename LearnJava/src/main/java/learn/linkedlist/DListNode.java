package learn.linkedlist;

public class DListNode<T> {

	private T data;
	private DListNode<T> next;
	private DListNode<T> prev;

	public DListNode(T data, DListNode<T> next, DListNode<T> prev)
	{
		this.data = data;
		
		this.next = next;
		if (next != null)
		{
			next.setPrev(this);
		}
		
		this.prev = prev;
		if (prev != null)
		{
			prev.setNext(this);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DListNode<T> getNext() {
		return next;
	}

	public void setNext(DListNode<T> next) {
		this.next = next;
	}
	
	public DListNode<T> getPrev() {
		return prev;
	}

	public void setPrev(DListNode<T> prev) {
		this.prev = prev;
	}
	
	@Override
	public String toString()
	{
		return "" + this.data;
	}

	public void reverse() {
		DListNode<T> tempNode = prev;
		prev = next;
		next = tempNode;
		
	}
}
