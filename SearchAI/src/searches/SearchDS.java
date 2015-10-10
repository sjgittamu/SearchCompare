package searches;

import utility.Node;

public interface SearchDS {
	public void addElement(Node node);
	public Node peekNextElement();
	public Node popNextElement();
	public boolean isEmpty();
	public boolean elementExists(Node node);
	public int getSize();
}
