package stralg13;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuffixTreeTest {

	@Test
	public void testCase() {
		SuffixTree actual = new SuffixTree("a");
		SuffixTree expected = new SuffixTree();
		expected.string = "a" + SuffixTree.STRING_END;
		expected.root.addEdgeAndNewNode(0, 2);
		expected.root.addEdgeAndNewNode(1, 2);
		expected.root.suffixLink = expected.root;

		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void testCaseTwoCharacters() {
		SuffixTree actual = new SuffixTree("ab");

		SuffixTree expected = new SuffixTree();
		expected.string = "ab" + SuffixTree.STRING_END;
		expected.root.addEdgeAndNewNode(0, 3);
		expected.root.addEdgeAndNewNode(1, 3);
		expected.root.addEdgeAndNewNode(2, 3);
		expected.root.suffixLink = expected.root;

		assertTrue(actual.equals(expected));
	}

	
	@Test
	public void testAAB() {
		SuffixTree actual = new SuffixTree("aba");

		SuffixTree expected = new SuffixTree();
		expected.string = "aba" + SuffixTree.STRING_END;
		expected.root.addEdgeAndNewNode(3, 4); // $
		Node nodeA = expected.root.addEdgeAndNewNode(0, 1); // a
		expected.root.edges.get(new Tuple(0,1)).addEdgeAndNewNode(3,4); //$
		expected.root.edges.get(new Tuple(0,1)).addEdgeAndNewNode(1,4); //ba$
		expected.root.addEdgeAndNewNode(1, 4); // ba$
		
		expected.root.suffixLink = expected.root;
		//nodeA.suffixLink = expected.root;

		assertTrue(actual.equals(expected));
	}
	
	
	@Test
	public void testEdgeStartsWithString() {
		SuffixTree tree = new SuffixTree("abcde");

		assertTrue(tree.shouldSplitEdge(1, new Tuple(1, 5)));
	}

	
	
	@Test
	public void testABAABTreeConstruction() {
		// a b a a b
		// 0 1 2 3 4
		SuffixTree actual = new SuffixTree("abaab");
		
		SuffixTree expected = new SuffixTree();
		expected.string = "abaab$";
		Node nodeA = expected.root.addEdgeAndNewNode(0, 1); // a
		Node nodeB = expected.root.addEdgeAndNewNode(1, 2); // b
		expected.root.addEdgeAndNewNode(5, 6); // $
		
		Node nodeAB = nodeA.addEdgeAndNewNode(1, 2); // a b
		nodeA.addEdgeAndNewNode(3, 6); // a ab$
		
		nodeAB.addEdgeAndNewNode(2, 6); // a b aab$
		nodeAB.addEdgeAndNewNode(5, 6); // a b $
		nodeB.addEdgeAndNewNode(2, 6); // b aab$
		nodeB.addEdgeAndNewNode(5, 6); // b $
		
		expected.root.suffixLink = expected.root;
		nodeA.suffixLink = expected.root;
		nodeAB.suffixLink = nodeB;
		//nodeB.suffixLink = expected.root;
		
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void testABAABABA () {
		SuffixTree actual = new SuffixTree("abaababa");
		
		SuffixTree expected = new SuffixTree();
		expected.string ="abaababa$";
		
		expected.root.addEdgeAndNewNode(8,9);
		expected.root.suffixLink = expected.root;
		
		Node nodeA = expected.root.addEdgeAndNewNode(0,  1);	
		nodeA.addEdgeAndNewNode(8, 9);
		nodeA.addEdgeAndNewNode(3, 9);
	
		Node nodeABA = nodeA.addEdgeAndNewNode(1, 3);
		
		nodeABA.addEdgeAndNewNode(6, 9);
		nodeABA.addEdgeAndNewNode(8, 9);
		nodeABA.addEdgeAndNewNode(3, 9);
		
		Node nodeBA = expected.root.addEdgeAndNewNode(1, 3);
		nodeBA.addEdgeAndNewNode(3, 9);
		nodeBA.addEdgeAndNewNode(8, 9);
		nodeBA.addEdgeAndNewNode(6, 9);
		
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testABABBBAB () {
		SuffixTree actual = new SuffixTree("ababbbab");
		
		SuffixTree expected = new SuffixTree();
		expected.string = "ababbbab" + SuffixTree.STRING_END;
		
		Node nodeA = expected.root.addEdgeAndNewNode(0, 1);
		Node nodeB = expected.root.addEdgeAndNewNode(1, 2);
		expected.root.addEdgeAndNewNode(8, 9);
		
		Node nodeAB = nodeA.addEdgeAndNewNode(2, 3);
		nodeA.addEdgeAndNewNode(7, 9);
		
		nodeAB.addEdgeAndNewNode(3, 9);
		nodeAB.addEdgeAndNewNode(2, 9);
		
		nodeB.addEdgeAndNewNode(5, 9);
		nodeB.addEdgeAndNewNode(8, 9);
		nodeB.addEdgeAndNewNode(2, 9);
		
		Node nodeBB = nodeB.addEdgeAndNewNode(4, 5);
		nodeBB.addEdgeAndNewNode(5, 9);
		nodeBB.addEdgeAndNewNode(4, 9);
		
	//	assertTrue(expected.equals(actual));
		
		System.out.println(actual);
	}
	
	@Test
	public void testABBBA () {
		SuffixTree actual = new SuffixTree("abbba");
		
		SuffixTreeNaive expected = new SuffixTreeNaive("abbba");
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testABBBBABA () {
		SuffixTree actual = new SuffixTree("abbbbaba");
		
		SuffixTreeNaive expected = new SuffixTreeNaive("abbbbaba");
		assertTrue(expected.equals(actual));
	}
	
	
	
	@Test
	public void testABABBBA () {
		String string = "ababbba";
		SuffixTree actual = new SuffixTree(string);
		
		SuffixTreeNaive expected = new SuffixTreeNaive(string);
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testMississippi () {
		String string = "mississippi";
		SuffixTree actual = new SuffixTree(string);
		
		SuffixTreeNaive expected = new SuffixTreeNaive(string);
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testToString(){
		SuffixTree expected = new SuffixTree();
		expected.string = "aba" + SuffixTree.STRING_END;
		expected.root.addEdgeAndNewNode(3, 4); // $
		Node nodeA = expected.root.addEdgeAndNewNode(0, 1); // a
		expected.root.edges.get(new Tuple(0,1)).addEdgeAndNewNode(3,4); //$
		expected.root.edges.get(new Tuple(0,1)).addEdgeAndNewNode(1,4); //ba$
		expected.root.addEdgeAndNewNode(1, 4); // ba$
		
		System.out.println(expected);
	}
	
	@Test
	public void testDepth() {
		SuffixTree tree = new SuffixTree();
		tree.string = "aba" + SuffixTree.STRING_END;
		Node node = tree.root.addEdgeAndNewNode(3, 4).addEdgeAndNewNode(4, 6).addEdgeAndNewNode(6, 7); // $
		
		
		int actual = tree.getDepth(node);
		assertEquals (actual, 4);
	}
	
	@Test
	public void thethe() {
		String walrus = "ThTheTher";
		SuffixTree actual = new SuffixTree(walrus);
		SuffixTreeNaive expected = new SuffixTreeNaive(walrus);
		assertTrue(expected.equals(actual));
		
	}
}
