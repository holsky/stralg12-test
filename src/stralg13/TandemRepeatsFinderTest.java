package stralg13;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.TreeSet;

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
		TandemRepeatFinder finder = new TandemRepeatFinder(string);
		int[] actual = finder.leafToDFS;
		return Arrays.equals(actual, expected);
	}
	
	@Test
	public void testBuildLeafListsAba() {
		TandemRepeatFinder finder = new TandemRepeatFinder("aba");
		Node a = finder.tree.root.edges.get(new Tuple(0,1));
		assertEquals(a.leafList, new Tuple(0,1));  
	}
	
	@Test
	public void testBuildLeafListsAbaab() {
		TandemRepeatFinder finder = new TandemRepeatFinder("abaab");
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
		TandemRepeatFinder finder = new TandemRepeatFinder("abaabaabbba");
		Node root = finder.tree.root;
		Node a = root.edges.get(new Tuple(0,1));
		assertEquals(new Tuple(0,5), a.leafList);
		assertEquals(new Tuple(2,4), a.largeList);
		
		Node b = root.edges.get(new Tuple(1,2));
		assertEquals(new Tuple(6,10), b.leafList);
		assertEquals(new Tuple(6,8), b.largeList);
	}
	
	@Test
	public void testFindTRsmalltree() {
		TandemRepeat[] expected = {new TandemRepeat(2,1, true)};
		assertTrue(compareRepeats("abaab", expected));
	}
	
	@Test
	public void testFindTRabaabaabbba() {
		TandemRepeat[] expected = {new TandemRepeat(2, 3, true),
				new TandemRepeat(2,1, true),
				new TandemRepeat(5,1, true),
				new TandemRepeat(8,1, true),
				new TandemRepeat(0,3, false),
				new TandemRepeat(1, 3, false),
				new TandemRepeat(7, 1, false)};
		assertTrue(compareRepeats("abaabaabbba", expected));
	}
	
	@Test
	public void testFindTRmississippi() {
		TandemRepeat[] expected = {new TandemRepeat(2, 1, true),
				new TandemRepeat(2,3, true),
				new TandemRepeat(5,1, true),
				new TandemRepeat(8,1, true),
				new TandemRepeat(1,3, false)};
		assertTrue(compareRepeats("mississippi", expected));
	}
	
	@Test
	public void testFileAbaabaabbba() throws Exception {
		assertTrue(runOnFileCompareWithOutput("abaabaabbba"));
	}
	
	@Test
	public void testFib10() throws Exception {
		assertTrue(runOnFileCompareWithOutput("fib10"));
	}
	
	@Test
	public void testFib15() throws Exception {
		assertTrue(runOnFileCompareWithOutput("fib15"));
	}
	
	@Test
	public void testGene1() throws Exception {
		assertTrue(runOnFileCompareWithOutput("gene1"));
	}
	
	@Test
	public void testGene2() throws Exception {
		assertTrue(runOnFileCompareWithOutput("gene2"));
	}
	
	@Test
	public void testProtein1() throws Exception {
		assertTrue(runOnFileCompareWithOutput("protein1"));
	}
	
	@Test
	public void testProtein2() throws Exception {
		assertTrue(runOnFileCompareWithOutput("protein2"));
	}
	
	protected boolean compareRepeats(String string, TandemRepeat[] expected) {
		TandemRepeatFinder finder = new TandemRepeatFinder(string);
		return finder.getRepeats().equals(new TreeSet<TandemRepeat>(Arrays.asList(expected)));
	}
	
	protected boolean runOnFileCompareWithOutput(String filename) throws Exception {
		String actual = TandemRepeatFinder.findTandemRepeats("testdata/" + filename + ".txt");
		String expected = TandemRepeatFinder.readFile("testdata/" + filename + ".out");
		return expected.equals(actual);
	}
	
	@Test 
	public void testReadFile() throws Exception {
		assertEquals("abaabaabbba\n", TandemRepeatFinder.readFile("testdata/abaabaabbba.txt"));
	}
}
