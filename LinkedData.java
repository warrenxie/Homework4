

public class LinkedData <T> implements ListInterface<T> {
	private Node node1;
	private int numberOfEntries;

	public LinkedData(){
		initializedDataFields();
	}
	
	public void add( T newEntry){
		Node newNode = new Node( newEntry);
		 if(isEmpty())
			 node1=newNode;
		 else{
			 Node lastNode = getNodeAt(numberOfEntries);
			 lastNode.setNextNode(newNode);
		 }
		 numberOfEntries++;
	}
	public void add( int newPosition , T newEntry){
		if( (newPosition >= 1) && (newPosition <= numberOfEntries+1)){
			Node newNode = new Node(newEntry);
			if(newPosition ==1){
				newNode.setNextNode(node1);
			}
			else{
				Node nodePrev = getNodeAt(newPosition - 1);
				Node nodeAfter = nodePrev.getNextNode();
				nodePrev.setNextNode(newNode);
			}
			numberOfEntries++;
		}
		else
			throw new IndexOutOfBoundsException("illegal position");
	}
	
	public boolean isEmpty(){
		boolean result;
		if( numberOfEntries == 0){
			assert node1 == null;
			result = true;
		}
		else{
			assert node1 !=null;
			result = false;
		}
		return result;
	}
	
	public T[] toArray(){
		T [] result = (T[]) new Object[numberOfEntries];
		
		int i = 0;
		Node currNode = node1;
		while((i < numberOfEntries) && (currNode !=null)){
			result[i] = currNode.getData();
			currNode = currNode.getNextNode();
			i++;
		}
		return result;
	}
	
	public T remove ( int position){
		T result = null;
		if((position >= 1) && (position <= numberOfEntries)){
			assert !isEmpty();
			if(position == 1){
				result = node1.getData();
				node1= node1.getNextNode();
			}
			else {
				Node nodebefore = getNodeAt(position - 1);
				Node noderemove = nodebefore.getNextNode();
				result = noderemove.getData();
				Node nodeafter = noderemove.getNextNode();
				nodebefore.setNextNode(nodeafter);
			}
			numberOfEntries--;
			return result;
		}
		else throw new IndexOutOfBoundsException(" illegal position");
	}
	
	public T replace( int position, T newEntry){
		if((position >= 1) && (position <= numberOfEntries)){
		assert !isEmpty();
		Node nodewanted = getNodeAt(position);
		T originalEntry = nodewanted.getData();
		nodewanted.setData(newEntry);
		return originalEntry;
		}
		else throw new IndexOutOfBoundsException(" illegal position");
	}
	 public T getItem ( int position){
		 if((position >= 1) && (position <= numberOfEntries)){
			 assert !isEmpty();
			 return getNodeAt(position).getData();
		 }
		 else throw new IndexOutOfBoundsException(" illegal position");
	 }
	 
	 public boolean contains( T anEntry){
		 boolean found = false;
		 Node currNode = node1;
		 while(!found && ( currNode != null)){
			 if ( anEntry.equals(currNode.getData()))
				 found = true;
			 else
				 currNode=currNode.getNextNode();
		 }
		 return found;
	 }
	public void initializedDataFields(){
		node1 = null;
		numberOfEntries = 0;
	}
	public void clear(){
		initializedDataFields();
	}
	
	private Node getNodeAt( int position){
		assert(node1 != null)&& ( 1 <= position) &&(position <= numberOfEntries);
		Node currentNode = node1;
		
		for ( int count =-1; count < position; count++)
			currentNode = currentNode.getNextNode();
		assert currentNode!=null;
		return currentNode;
	}
	
	private class Node{
		private T data;
		private Node next;
		Node(T data){
			this.data = data;
			this.next = next;
		
		}
		private void setNextNode(Node nextNode){
			next = nextNode;
		}
	public T getData(){
			return data;
		}
	public Node getNextNode(){
			return next;
		}
	public void setData(T data){
			this.data = data;
		}
	
	}


	public int getLength() {
		// TODO Auto-generated method stub
		int length = 0;
		for ( Node node = node1; node.next !=null ; node = node.next)
			length++;
		return  length;
	}

	
	public T getEntry(int givenPosition) {
		if((givenPosition >=1) && ( givenPosition <= numberOfEntries)){
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		}
		else
			throw new IndexOutOfBoundsException("illegal position");
	}
	
}
