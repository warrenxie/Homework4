public class SortedList<T extends Comparable<? super T>> implements  SortedListInterface<T> 
{
	
	private Node firstNode;
	private int entries;
	public SortedList(){
		firstNode = null;
		entries = 0;
	}
	
	public void add(T newentry){
		Node newnode = new Node( newentry);
		Node nodeprev = getNodeBefore(newentry);
		
		if(isEmpty() || (nodeprev == null)){
			newnode.setNextNode(firstNode);
			firstNode = newnode;
		}
		else{
			Node nodeafter = nodeprev.getNextNode();
			newnode.setNextNode(nodeafter);
			nodeprev.setNextNode(newnode);
		}
		entries--;
	}
	private Node getNodeBefore(T anEntry){
		Node currentnode = firstNode;
		Node nodebefore = null;
		while ( (currentnode != null) && (anEntry.compareTo(currentnode.getData()) > 0))
		{
			nodebefore = currentnode;
			currentnode = currentnode.getNextNode();
		}
		return nodebefore;
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


	
	public boolean remove(T anEntry) {
		Node temp = firstNode;
		Node prevNode = null;
		boolean deleted = false;
		 while (temp != null) {
		        if (temp.data == anEntry) {
		            if (temp == firstNode) {
		                firstNode = firstNode.next;
		            } else { 
		                prevNode.next = temp.next;
		            }
		         
		            deleted = true;
		         } else {
		          
		             prevNode = temp;
		         }
		         temp = temp.next;
		    }

		    return deleted;
		}

	
	public int getPosition(T anEntry) {
		//Node currentNode = firstNode;
		int position = 1;
		if ( (position <= entries ) && (anEntry == getNodeAt(position).getData())) {
			
			position++;
		}
		else position =- position; // entry not in list
		return position;
	}


	public T getEntry(int givenPosition) {
		
			if((givenPosition >=1) && ( givenPosition <= entries)){
				assert !isEmpty();
				return getNodeAt(givenPosition).getData();
			}
			else
				throw new IndexOutOfBoundsException("illegal position");
		}
	

public boolean contains( T anEntry){
	 boolean found = false;
	 Node currNode = firstNode;
	 while(!found && ( currNode != null)){
		 if ( anEntry.equals(currNode.getData()))
			 found = true;
		 else
			 currNode=currNode.getNextNode();
	 }
	 return found;
}

	public T remove(int position) {
		T result = null;
		if((position >= 1) && (position <= entries)){
			assert !isEmpty();
			if(position == 1){
				result = firstNode.getData();
				firstNode= firstNode.getNextNode();
			}
			else {
				Node nodebefore = getNodeAt(position - 1);
				Node noderemove = nodebefore.getNextNode();
				result = noderemove.getData();
				Node nodeafter = noderemove.getNextNode();
				nodebefore.setNextNode(nodeafter);
			}
			entries--;
			return result;
		}
		else throw new IndexOutOfBoundsException(" illegal position");
	}
	private Node getNodeAt( int position){
		assert(firstNode != null)&& ( 1 <= position) &&(position <= entries);
		Node currentNode = firstNode;
		
		for ( int count =-1; count < position; count++)
			currentNode = currentNode.getNextNode();
		assert currentNode!=null;
		return currentNode;
	}

	public void initializedDataFields(){
		firstNode = null;
		entries = 0;
	}
	public void clear(){
		initializedDataFields();
	}


	public int getLength() {
	
		int length = 0;
		for ( Node node = firstNode; node.next !=null ; node = node.next)
			length++;
		return  length;
	}


	public boolean isEmpty(){
		boolean result;
		if( entries == 0){
			assert firstNode == null;
			result = true;
		}
		else{
			assert firstNode !=null;
			result = false;
		}
		return result;
	}

	public T[] toArray(){
		T [] result = (T[]) new Object[entries];
		
		int i = 0;
		Node currNode = firstNode;
		while((i < entries) && (currNode !=null)){
			result[i] = currNode.getData();
			currNode = currNode.getNextNode();
			i++;
		}
		return result;
	}
	
	}
