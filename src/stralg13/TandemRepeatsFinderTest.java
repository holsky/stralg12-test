package stralg13;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class TandemRepeatsFinderTest {

	@Test
	public void testDFSabaab() {
		int[] expected = {1,3,0,2,4,5};
		assertTrue(compareDFSmapping("abaab", expected ));
	}
	
	@Test
	public void testDFSabaabaabbba() {
		int[] expected = {2,6,0,3,7,1,4,10,9,8,5,11};
		assertTrue(compareDFSmapping("abaabaabbba", expected ));
	}

	@Test
	public void testDFSaba() {
		int[] expected = {0,2,1, 3};
		assertTrue(compareDFSmapping("aba", expected ));		
	}
	
	protected boolean compareDFSmapping(String string, int[] expected) {
		TandemRepeatsFinder finder = new TandemRepeatsFinder(string);
		finder.createDFSOrdering();
		int[] actual = finder.leafToDFS;
		return Arrays.equals(actual, expected);
	}
	
	@Test
	public void testBuildLeafListsAba() {
		TandemRepeatsFinder finder = new TandemRepeatsFinder("aba");
		finder.createDFSOrdering();
		Node a = finder.tree.root.edges.get(new Tuple(0,1));
		assertEquals(a.leafList, new Tuple(0,1));  
	}
	
	@Test
	public void testBuildLeafListsAbaab() {
		TandemRepeatsFinder finder = new TandemRepeatsFinder("abaab");
		finder.createDFSOrdering();
		Node a = finder.tree.root.edges.get(new Tuple(0,1));
		assertEquals(a.leafList, new Tuple(0,2));
		assertEquals(a.largeList, new Tuple(1,2));
		
		Node ab = a.edges.get(new Tuple(1,2));
		assertEquals(ab.leafList, new Tuple(1,2));
		assertEquals(ab.largeList, new Tuple(1,1));
		
		Node b = finder.tree.root.edges.get(new Tuple(1,2));
		assertEquals(b.leafList, new Tuple(3,4));  
	}
	
	@Test
	public void testBuildLeafListAbaabaabbba () {
		TandemRepeatsFinder finder = new TandemRepeatsFinder("abaabaabbba");
		finder.createDFSOrdering();
		Node root = finder.tree.root;
		Node a = root.edges.get(new Tuple(0,1));
		assertEquals(new Tuple(0,5), a.leafList);
		assertEquals(new Tuple(2,4), a.largeList);
		
		Node b = root.edges.get(new Tuple(1,2));
		assertEquals(new Tuple(6,10), b.leafList);
		assertEquals(new Tuple(6,8), b.largeList);
	}
}
