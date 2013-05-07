package stralg13;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.TreeSet;

import org.junit.Test;

public class TandemRepeatsFinderTest {

		
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
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		TandemRepeatFinder.findTandemRepeats("testdata/" + filename + ".txt", out);
		String actual = baos.toString("UTF8");
		String expected = TandemRepeatFinder.readFile("testdata/" + filename + ".out");

		return expected.equals(actual);
	}
	
	@Test 
	public void testReadFile() throws Exception {
		assertEquals("abaabaabbba\n", TandemRepeatFinder.readFile("testdata/abaabaabbba.txt"));
	}
}
