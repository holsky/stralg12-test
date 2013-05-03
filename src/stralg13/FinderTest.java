package stralg13;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

public class FinderTest {
	@Test
	public void testSimpleSearch() {
		Finder finder = new Finder("aba", "b");
		List<Integer> results = finder.search();
		assertTrue(results.size() == 1);

		finder = new Finder("aba", "a");
		results = finder.search();
		assertTrue(results.size() == 2);

		finder = new Finder("ababbba", "bb");
		results = finder.search();
		assertTrue(results.size() == 2);
	}

	@Test
	public void testSearchInABAABABA() {
		Finder finder = new Finder("abaababa", "a");
		List<Integer> results = finder.search();
		assertTrue(results.size() == 5);

		finder = new Finder("abaababa", "b");
		results = finder.search();
		assertTrue(results.size() == 3);

		finder = new Finder("abaababa", "ba");
		results = finder.search();
		assertTrue(results.size() == 3);

		finder = new Finder("abaababa", "bab");
		results = finder.search();
		assertTrue(results.size() == 1);

	}

	@Test
	public void testMississippi() {
		Finder finder = new Finder("mississippi", "ss");
		List<Integer> results = finder.search();
		System.out.println(results);
	}

	@Test
	public void testSearchLongerInput() { // Doesn't work due to wrong suffix
											// tree
		Finder finder = new Finder("ababbbab", "a");
		List<Integer> results = finder.search();
		assertTrue(results.size() == 3);
	}

	@Test
	public void walruss() {
		
		
		/*Finder finder = new Finder(readFile("walrus.txt"), "Walrus");
		List<Integer> results = finder.search();
		assertTrue(results.size() == 10);*/
	}

	protected static String readFile(String filename) {
		try {
			FileInputStream fstream = new FileInputStream("walrus.txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = "";
			StringBuilder sb = new StringBuilder();
			while (line != null) {

				line = br.readLine();
				
				if (line == null) {
					Thread.sleep(500);
				} else {
					sb.append(line);
					sb.append("\n");
				}
				
			}
			in.close();
			return sb.toString();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return null;

	}
}
