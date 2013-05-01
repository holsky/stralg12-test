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
	public void testSearchLongerInput() {
		Finder finder = new Finder("ababbbab", "a");
		List<Integer> results = finder.search();
		assertTrue(results.size() == 3);
	}
}
