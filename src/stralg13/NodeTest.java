package stralg13;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test
	public void nodeEqualsSimple() {
		Node root1 = new Node();
		root1.addEdge(0, 2);
		root1.addEdge(1, 2);
		Node root2 = new Node();
		root2.addEdge(0, 2);
		root2.addEdge(1, 2);
		assertTrue(root1.equals(root2));
	}
	@Test
	public void nodeEqualsDeepTree()
	{
	    Node root1 = new Node();
	    root1.addEdge(0, 1);
	    root1.addEdge(1, 2);

	    root1.edges.get(new Tuple(0,1)).addEdge(2,3);
	    root1.edges.get(new Tuple(1,2)).addEdge(3,4);

	    Node root2 = new Node();
	    root2.addEdge(0, 1);
	    root2.addEdge(1, 2);
	    root2.edges.get(new Tuple(0,1)).addEdge(2,3);
	    root2.edges.get(new Tuple(1,2)).addEdge(3,4);

	    assertTrue(root1.equals(root2));
	}
	@Test
	public void nodeNotEqualsDeepTree()
	{
	    Node root1 = new Node();
	    root1.addEdge(0, 1);
	    root1.addEdge(1, 2);
	    root1.edges.get(new Tuple(0,1)).addEdge(2,3);
	    root1.edges.get(new Tuple(1,2)).addEdge(3,4);
	    root1.edges.get(new Tuple(0,1)).edges.get(new Tuple(2,3)).addEdge(4,5);
	    
	    Node root2 = new Node();
	    root2.addEdge(0, 1);
	    root2.addEdge(1, 2);
	    root2.edges.get(new Tuple(0,1)).addEdge(2,3);
	    root2.edges.get(new Tuple(1,2)).addEdge(3,4);

	    assertTrue(!root1.equals(root2));
	}
}
