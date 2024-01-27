package movie;

public class MyLinkedList<AnyType> {
	
	// A private nested Node class
	private static class Node<AnyType>{
		
		public AnyType data; // Data members are public, this is fine as the Node class itself is private
		public Node<AnyType> next;
		public Node(AnyType d, Node<AnyType> n)
		{
			data = d;
			next=n;
		}
		@Override
		public String toString() {
			return data.toString();
		}
	}
	
	// Data Members
	private Node<AnyType> head; // A node pointer to the first item in the List
	
	private int theSize; // a integer variable to hold the size of the List
	
		public MyLinkedList() {
			doClear();
		}
		public void clear() {
			doClear();
	    }
		private void doClear() {
			head = null;
		
			theSize =0 ;
			
		}
		
		// An add method that will always add at the beginning of the List
		public boolean add(AnyType x) {
			if(head == null) // List is empty
			{
				head = new Node<AnyType>(x, null);
				theSize++;
				return true;
			}
			else  // List has at least one item lets get to the end of it
			{
				Node<AnyType> newnode = new Node<AnyType>(x, head);
				head =  newnode;
				theSize++;
				return true;
			}
		
		
		}
		@Override
		public String toString() {
		// This to String method will print the List from head to the end.
			String theList = "";
			if(head==null)
				return theList;
			Node<AnyType> cur = head;
			
			while(cur.next!=null) {
				theList = theList + cur.toString() + " ";
				cur= cur.next;
			}
			return theList + cur.toString();
		}
		public int getTheSize() {
			return theSize;
		}
		
		
public boolean insert(AnyType element, int index) {
    if (index < 0)
        return false; // Invalid index

    // Insert at the front
    if (index == 0) {
        Node<AnyType> newNode = new Node<>(element, head);
        head = newNode;
        theSize++;
        return true;
    }

    if (index >= theSize)
        //return add(element); // Insert at the end
    	index=theSize;

    Node<AnyType> current = head;
    for (int i = 0; i < index - 1; i++) {
        current = current.next;
    }

    Node<AnyType> newNode = new Node<>(element, current.next);
    current.next = newNode;
    theSize++;
    return true;
}


}