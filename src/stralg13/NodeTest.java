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
		Node newNode = actual.splitEdgeAndReturnNewNode(new Tuple(0,4), 2);
		
		Node expected = new Node();
		Node child = new Node(expected);
		expected.edges.put(new Tuple(0,2), child);
		child.edges.put(new Tuple(2,4), new Node(child));
		
		assertTrue(expected.equals(actual));
		assertTrue(newNode.parent.equals(actual));
	}
	
	@Test
	
	public void splitBiggerEdge()  {		
		SuffixTree actual = new SuffixTree();
	
		actual.string = "aba" + SuffixTree.STRING_END;
		actual.root.addEdgeAndNewNode(0, 3); // aba$
		actual.root.addEdgeAndNewNode(1, 3); // ba$
		Node newNode = actual.root.splitEdgeAndReturnNewNode(new Tuple(0,3), 1);
		
		SuffixTree expected = new SuffixTree();
		expected.string = "aba" + SuffixTree.STRING_END;
		expected.root.addEdgeAndNewNode(0, 1); // a
		expected.root.edges.get(new Tuple(0,1)).addEdgeAndNewNode(1,3); //ba$
		expected.root.addEdgeAndNewNode(1, 3); // ba$
		
		assertTrue(expected.equals(actual));
		assertTrue(newNode.parent.equals(actual.root));

		
	}
}
