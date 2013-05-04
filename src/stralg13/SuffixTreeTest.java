package stralg13;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuffixTreeTest {

	@Test
	public void testCase() {
		compareRealAndNaiveTrees("a");
	}
	
	@Test
	public void testCaseTwoCharacters() {
		compareRealAndNaiveTrees("ab");
	}

	
	@Test
	public void testAAB() {
		compareRealAndNaiveTrees("aba");	}
	
	
	@Test
	public void testEdgeStartsWithString() {
		SuffixTree tree = new SuffixTree("abcde");

		assertTrue(tree.shouldSplitEdge(1, new Tuple(1, 5)));
	}

	
	
	@Test
	public void testABAABTreeConstruction() {
		compareRealAndNaiveTrees("abaab");
	}
	
	@Test
	public void testABAABABA () {
		compareRealAndNaiveTrees("abaababa");
	}
	
	@Test
	public void testABABBBAB () {
		String string = "ababbbab";
		compareRealAndNaiveTrees(string);
	}
	
	@Test
	public void testABBBA () {
		SuffixTree actual = new SuffixTree("abbba");
		
		SuffixTreeNaive expected = new SuffixTreeNaive("abbba");
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testABBBBABA () {
		String string = "abbbbaba";
		compareRealAndNaiveTrees(string);
	}
	
	
	
	@Test
	public void testABABBBA () {
		String string = "ababbba";
		compareRealAndNaiveTrees(string);
	}

	protected void compareRealAndNaiveTrees(String string) {
		SuffixTree actual = new SuffixTree(string);
		
		SuffixTreeNaive expected = new SuffixTreeNaive(string);
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testMississippi () {
		String string = "mississippi";
		compareRealAndNaiveTrees(string);
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
	public void dna1() {
		String dna = "gcagaggcaga";
		compareRealAndNaiveTrees(dna);
	}
	
	@Test
	public void thethe() {
		String walrus = "ThTheTher";
		compareRealAndNaiveTrees(walrus);
		
	}
}
