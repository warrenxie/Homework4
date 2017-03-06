import java.util.Arrays;

public class ArrayList<T> implements ListInterface<T>{
		private T[] list;
		private int entries;
		private boolean initialized = false;
		private static final int CAPACITY = 10;
		private static final int MAX_CAPACITY = 1000;
		
		public ArrayList(){
			this(CAPACITY);
		}
		
		public ArrayList(int initialCapacity){
			if (initialCapacity < CAPACITY)
				initialCapacity = CAPACITY;
			else
				checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[]templist = (T[])new Object[initialCapacity +1];
		list = templist;
		entries = 0;
		initialized = true;
		}
		   private void checkCapacity(int initialCapacity){
		    	if(initialCapacity > MAX_CAPACITY)
		    		throw new IllegalStateException("Attempt to create a list " +
		                                            "whose capacity exceeds " +
		                                            "allowed maximum.");
		   }
		    
		public void add( T newentry){
			checkInitialization();
			list[entries + 1] = newentry;
			entries++;
			initialized = true;
		}

	    private void checkInitialization()
	    {
	        if (!initialized)
	             throw new SecurityException("List object is not initialized " +
	                                        "properly.");
	   }
	   public void add(int newPosition, T newentry){
		   checkInitialization();
		   if((newPosition >= 1) && ( newPosition <= entries + 1)){
			   if( newPosition <= entries)
				   makeRoom(newPosition);
			   list[newPosition] = newentry;
			   entries++;
			   ensurecapacity();
		   }
		   else 
			   throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
		   }
	   private void makeRoom(int newPosition){
		   assert ( newPosition >=1) && (newPosition <= entries + 1);
		   int newindex = newPosition;
		   int lastindex = entries;
		   
		   for( int i = lastindex; i >=newindex; i--)
			   list[i + 1] = list[i];
	   }
	   public T remove(int givenposition){
		   checkInitialization();
		   if((givenposition >= 1) && ( givenposition <= entries)){
			   assert !isEmpty();
			   T result = list[givenposition];
			   if(givenposition < entries)
				   removeGap(givenposition);
			   entries--;
			   return result;
		   }
		   else 
			   throw new IndexOutOfBoundsException("Illegal position");
		   
	   }
	   private void removeGap( int givenposition){
		   assert ( givenposition >=1 && ( givenposition < entries));
		   int removedindex = givenposition;
		   int lastindex = entries;
		   for( int i = removedindex; i < lastindex; i++)
			   list[i] = list[ i -1];
	   }
	   
	   public T replace( int givenposition, T newentry){
		   checkInitialization();
		   if((givenposition >= 1) && (givenposition <= entries)){
			   assert !isEmpty();
			   T originalentry = list[givenposition];
			   list[givenposition] = newentry;
			   return originalentry;
		   }
		   else
			   throw new IndexOutOfBoundsException("Illegal position");
	   }
	   
	   public T getEntry(int givenposition){
		   checkInitialization();
		   if((givenposition >= 1) && (givenposition <= entries)){
			   assert !isEmpty();
			   return list[givenposition];
			   
		   }
		   else
			   throw new IndexOutOfBoundsException("Illegal position");
	   }
	   public boolean contains(T anEntry){
		   checkInitialization();
		   boolean found = false;
		   int i = 1;
				   while(!found && ( i <= entries)){
					   if( anEntry.equals(list[i]))
						   found = true;
					   i++;
				   }
				   return found;
	   }

	public void clear() {
		for( int i = 0; i < getLength(); i++)
			list[i] = null;
		
	}

	public T[] toArray() {
		checkInitialization();
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[entries];
		for(int i = 0; i < entries; i++){
			result[i] = list[i+1];
		}
		return result;
	}

	public int getLength() {
		return entries;
	}

	public boolean isEmpty() {
		return entries == 0;
	}
	private void ensurecapacity(){
		int capacity = list.length-1;
		if(entries >= capacity){
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity);
			list= Arrays.copyOf(list , newCapacity + 1);
		}
		
	}
		
}