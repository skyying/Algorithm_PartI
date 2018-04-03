	import edu.princeton.cs.algs4.StdRandom;
	import java.util.NoSuchElementException;
	import java.util.Iterator;
	
	public class RandomizedQueue<Item> implements Iterable<Item> {
	
	  private Item[] arr;
	  private int n = 0;
	  
	  private static <Item> Item cast(Object obj) {
	         return (Item) obj;
	    }
	  //initialize values
	  public RandomizedQueue() {
	    arr = cast(new Object[1]);
	  }
	
	  // construct an empty randomized queue
	  public boolean isEmpty() {
	  
		  return n == 0;
	
	  } // is the randomized queue empty?
	
	  public int size() {
	    return n;
	  }// return the number of items on the randomized queue
	
	  
	  //resize array base on their size
	  private void resize(int capacity) {
		 
	    assert capacity >= n;
	    Item[] tmp = cast(new Object[capacity]);
	    for (int i = 0; i < n; i++) {
	      tmp[i] = arr[i];
	    }
	   
	    arr = tmp;
	  }
	
	  /**
	   * Adds the item to the front of this queue.
	   *
	   * @param  item the item to add
	   */
	  public void enqueue(Item item) {
	    if (item == null) {
	    	throw new IllegalArgumentException();
	    }
	    
	    if (n == arr.length) {
	         resize(2 * n);
		    }
		
	    arr[n++] = item;
	
	    } // add the item
	
	
	  /**
	   * remove first item
	   * @return item be removed
	   */
	  public Item dequeue() {
	    if (isEmpty()) {
	    	throw new NoSuchElementException();
	    }
	    
	    
	    // get a random index j, delete arr[j], and make arr[last] to its pos;
	    // finally, make arr[last] null;
	    
	    int j = StdRandom.uniform(n);
	    Item item = arr[j];
	    //swap element of arr[j] and arr[last]
	    arr[j] =  arr[n-1];
	    arr[n-1] = item; 
	    // the last element
	    
	    
	    item = arr[n-1];
	    arr[n-1] = null; // this is the original n-1; 
	    
	    n--;
	    
	    if (n > 0 && n == arr.length / 4) {
	    	resize(arr.length / 2);
	    }
	    
	    return item;
	  } // remove and return a random item
	
	  
	  /**
	   * @return sample item
	   *
	   */
	  public Item sample() {
	    if (isEmpty())
	      throw new NoSuchElementException();
	   
	    int j = StdRandom.uniform(n);
	   
	    return arr[j];
	  } // return a random item (but do not remove it)
	
	  
	  
	  public Iterator<Item> iterator() {
	
	    return new RanQueueIterator();
	
	  } // return an independent iterator over items in random order
	
	  
	  //an iterator, doesn't implement remove() since it's optional
	  private class RanQueueIterator implements Iterator<Item>{
	
	    private Item[] shuffledArray;
	    private int current = 0;
	    public RanQueueIterator(){
	      shuffledArray = cast(new Object[n]);
	      
	      for(int i=0; i<n; i++) {
	    	  shuffledArray[i] = arr[i];
	      }
	      
	      StdRandom.shuffle(shuffledArray);
	      
	    }
	
	    @Override
	    public boolean hasNext() {
	      return current < n;
	    }
	    
	    public void remove() {
	      throw new UnsupportedOperationException();
	    }
	
	    //return next item of this iterator
	    public Item next() {
	      // TODO Auto-generated method stub
	      if(!hasNext()) {
	        throw new NoSuchElementException("no next element");
	      }

	        return shuffledArray[current++];
	    }
	    
	  
	
	  }
	
	  public static void main(String[] args) {
	    // TODO Auto-generated method stub
		  
//		  RandomizedQueue<Integer> a = new RandomizedQueue<Integer>();
//		  a.enqueue(5);
//		  a.enqueue(6);
//		  a.enqueue(7);
//		  a.dequeue();
//		  
//		  for(int s: a) {
//			  System.out.println("S: " + s );
//		  }
		  
	  }
	}
