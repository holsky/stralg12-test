package stralg13;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuffixTreeNaiveTest {

	@Test
	public void testABAABTreeConstruction() {
		// a b a a b
		// 0 1 2 3 4
		SuffixTreeNaive actual = new SuffixTreeNaive("abaab");
		
		SuffixTreeNaive expected = new SuffixTreeNaive();
		expected.string = "abaab$";
		Node nodeA = expected.root.addEdgeAndNewNode(0, 1); // a
		Node nodeB = expected.root.addEdgeAndNewNode(1, 2); // b
		Node leaf = expected.root.addEdgeAndNewNode(5, 6); // $
		leaf.leafIndex = 5;
		
		Node nodeAB = nodeA.addEdgeAndNewNode(1, 2); // a b
		leaf = nodeA.addEdgeAndNewNode(3, 6); // a ab$
		leaf.leafIndex = 2;
		
		leaf = nodeAB.addEdgeAndNewNode(2, 6); // a b aab$
		leaf.leafIndex = 0;
		leaf = nodeAB.addEdgeAndNewNode(5, 6); // a b $
		leaf.leafIndex = 3;
		leaf = nodeB.addEdgeAndNewNode(2, 6); // b aab$
		leaf.leafIndex = 1;
		leaf = nodeB.addEdgeAndNewNode(5, 6); // b $
		leaf.leafIndex = 4;
		expected.root.suffixLink = expected.root;
		nodeA.suffixLink = expected.root;
		nodeAB.suffixLink = nodeB;
		//nodeB.suffixLink = expected.root;
		
		assertTrue(actual.equals(expected));
	}
	
	public void testABAABABA () {
		SuffixTreeNaive actual = new SuffixTreeNaive("abaababa");
		
		SuffixTreeNaive expected = new SuffixTreeNaive();
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
	
	public void testABABBBAB () {
		SuffixTreeNaive actual = new SuffixTreeNaive("ababbbab");
		
		SuffixTreeNaive expected = new SuffixTreeNaive();
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
	

}
