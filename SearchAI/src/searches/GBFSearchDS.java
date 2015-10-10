package searches;

import java.util.PriorityQueue;

import utility.Node;

public class GBFSearchDS implements SearchDS {
	private static PriorityQueue<Node> queue = new PriorityQueue<Node>();

	@Override
	public void addElement(Node node) {
		queue.add(node);
	}

	@Override
	public Node peekNextElement() {
		return queue.peek();
	}

	@Override
	public Node popNextElement() {
		return queue.poll();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	@Override
	public boolean elementExists(Node node) {
		return queue.contains(node);
	}

	@Override
	public int getSize() {
		return queue.size();
	}
	
}
