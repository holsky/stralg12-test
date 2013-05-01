package stralg13;

import static org.junit.Assert.*;

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
	public void testSearchLongerInput() { // Doesn't work due to wrong suffix tree
		Finder finder = new Finder("ababbbab", "a");
		List<Integer> results = finder.search();
		assertTrue(results.size() == 3);
	}
}
