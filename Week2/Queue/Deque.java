import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private int n; // number of elements on Deque
	private Node<Item> first; // first element
	private Node<Item> last; // last element

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;

	}

	/**
	 * Initializes an empty Deque;
	 */

	public Deque() {
		first = null;
		last = null;
		n = 0;
	}

	/**
	 * return true if this queue is empty;
	 * 
	 * @return {@code true} if this queue is empty; {@code false} otherwise;
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * return the number of items in this queue;
	 * 
	 * @return the number of items in this queue;
	 */
	public int size() {
		return n;
	}

	/**
     * Adds the item to the front of this queue.
     *
     * @param  item the item to add
     */
	public void addFirst(Item item) {

		if (item == null) {
			throw new java.lang.IllegalArgumentException("item can't be null");
		}

		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.prev = null;

		if (isEmpty()) {
			first.next = null;
			last = first;
			

		} else {
			oldfirst.prev = first;
			first.next = oldfirst;
		}
		n++;

	}

	/**
     * Adds the item to the last of this queue.
     *
     * @param  item the item to add
     */
	public void addLast(Item item) {

		if (item == null) {
			throw new java.lang.IllegalArgumentException("item can't be empty");
		}
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;

		if (isEmpty()) {
			first = last;
			last.prev = null;
		} else {
			last.prev = oldlast;
			oldlast.next = last;
		}
		n++;

	}

	/**
     * Removes and returns the item on this queue that was added to first place.
     *
     * @return the item on this queue that was added to first place
     * @throws NoSuchElementException if this queue is empty
     */
	public Item removeFirst() {

		if (isEmpty())
			throw new NoSuchElementException("No such element");
		Item item = first.item;
		if (n == 1) {
			last = null; // to avoid loitering;
			first = null;
		} else {
			first = first.next;
			first.prev = null;
		}

		n--;
		return item;

	}

	// delete and return the item at the end
	/**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
	public Item removeLast() {

		if (isEmpty()) {
			throw new NoSuchElementException("No such element");
		}

		Item item = last.item;
		if (n == 1) {
			first = null;
			last = null;
		} else {
			Node<Item> newLast = last.prev;
			last = newLast;
			last.next = null;
		}
		n--;
		return item;
	}

	/**
     * Returns an iterator that iterates over the items in this queue.
     *
     * @return an iterator that iterates over the items in this queue
     */
	public Iterator<Item> iterator() {

		return new ListIterator(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<Item> {
		
		private Node<Item> current;

		public ListIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException("no next element");
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
		
//		Deque<Integer> a = new Deque<Integer>();
//		a.addFirst(5);
//		a.addFirst(10);
//		a.addLast(20);
//		a.removeLast();
//		
//		for(int s: a) {
//			System.out.println("s: " + s );
//		}
		
	}
}