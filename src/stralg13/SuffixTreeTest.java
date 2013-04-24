package stralg13;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuffixTreeTest {

	@Test
	public void testCase() {
		SuffixTree actual = new SuffixTree("a");
		SuffixTree expected = new SuffixTree();
		expected.string = "a" + SuffixTree.STRING_END;
		expected.root.addEdgeAndNewNode(0, 1);
		expected.root.addEdgeAndNewNode(1, 1);

		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void testCaseTwoCharacters() {
		SuffixTree actual = new SuffixTree("ab");

		SuffixTree expected = new SuffixTree();
		expected.string = "ab" + SuffixTree.STRING_END;
		expected.root.addEdgeAndNewNode(0, 2);
		expected.root.addEdgeAndNewNode(1, 2);
		expected.root.addEdgeAndNewNode(2, 2);

		assertTrue(actual.equals(expected));
	}

	@Test
	public void testSplitEdge() {
		SuffixTree actual = new SuffixTree("aba");

		SuffixTree expected = new SuffixTree();
		expected.string = "aba" + SuffixTree.STRING_END;
		expected.root.addEdgeAndNewNode(0, 1); // a
		expected.root.edges.get(new Tuple(0,1)).addEdgeAndNewNode(3,3); //$
		expected.root.edges.get(new Tuple(0,1)).addEdgeAndNewNode(1,3); //ba$
		expected.root.addEdgeAndNewNode(1, 3); // ba$

		assertTrue(actual.equals(expected));
	}
		
	@Test
	public void testCompareString() {
		SuffixTree tree = new SuffixTree("abcde");

		assertTrue(tree.edgeStartsWithString(1, new Tuple(1, 3)));
	}

}
