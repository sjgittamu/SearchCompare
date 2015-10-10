package searches;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import utility.Node;

public class BFSearchDS implements SearchDS{
	private static Queue<Node> queue = new LinkedBlockingQueue<Node>();
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
