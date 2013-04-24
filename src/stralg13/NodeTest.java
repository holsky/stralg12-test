package stralg13;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test
	public void nodeEqualsSimple() {
		Node root1 = new Node();
		root1.addEdgeAndNewNode(0, 2);
		root1.addEdgeAndNewNode(1, 2);
		Node root2 = new Node();
		root2.addEdgeAndNewNode(0, 2);
		root2.addEdgeAndNewNode(1, 2);
		assertTrue(root1.equals(root2));
	}
	@Test
	public void nodeEqualsDeepTree()
	{
	    Node root1 = new Node();
	    root1.addEdgeAndNewNode(0, 1);
	    root1.addEdgeAndNewNode(1, 2);

	    root1.edges.get(new Tuple(0,1)).addEdgeAndNewNode(2,3);
	    root1.edges.get(new Tuple(1,2)).addEdgeAndNewNode(3,4);

	    Node root2 = new Node();
	    root2.addEdgeAndNewNode(0, 1);
	    root2.addEdgeAndNewNode(1, 2);
	    root2.edges.get(new Tuple(0,1)).addEdgeAndNewNode(2,3);
	    root2.edges.get(new Tuple(1,2)).addEdgeAndNewNode(3,4);

	    assertTrue(root1.equals(root2));
	}
	@Test
	public void nodeNotEqualsDeepTree()
	{
	    Node root1 = new Node();
	    root1.addEdgeAndNewNode(0, 1);
	    root1.addEdgeAndNewNode(1, 2);
	    root1.edges.get(new Tuple(0,1)).addEdgeAndNewNode(2,3);
	    root1.edges.get(new Tuple(1,2)).addEdgeAndNewNode(3,4);
	    root1.edges.get(new Tuple(0,1)).edges.get(new Tuple(2,3)).addEdgeAndNewNode(4,5);
	    
	    Node root2 = new Node();
	    root2.addEdgeAndNewNode(0, 1);
	    root2.addEdgeAndNewNode(1, 2);
	    root2.edges.get(new Tuple(0,1)).addEdgeAndNewNode(2,3);
	    root2.edges.get(new Tuple(1,2)).addEdgeAndNewNode(3,4);

	    assertTrue(!root1.equals(root2));
	}
	
	@Test
	public void splitEdge() {
		Node actual = new Node();
		actual.addEdgeAndNewNode(0, 4);
		actual.splitEdge(new Tuple(0,4), 2);
		
		Node expected = new Node();
		Node child = new Node(expected);
		expected.edges.put(new Tuple(0,2), child);
		child.edges.put(new Tuple(2,4), new Node(child));
		
		assertTrue(expected.equals(actual));
	}
}
