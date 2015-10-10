package searches;

import java.util.Stack;

import utility.Node;

public class DFSearchDS implements SearchDS {
	private static Stack<Node> queue = new Stack<Node>();

	@Override
	public void addElement(Node node) {
		queue.push(node);
	}

	@Override
	public Node peekNextElement() {
		return queue.peek();
	}

	@Override
	public Node popNextElement() {
		return queue.pop();
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
