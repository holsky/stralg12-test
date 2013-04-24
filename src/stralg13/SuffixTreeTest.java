package stralg13;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuffixTreeTest {

	@Test
	public void testCase() {
		SuffixTree actual = new SuffixTree("a");
		SuffixTree expected = new SuffixTree();
		expected.string = "a" + SuffixTree.STRING_END;
		expected.root.addEdge(0, 1);

		assertTrue(actual.equals(expected));
	}

	public void testCaseTwoCharacters() { 
		SuffixTree actual = new SuffixTree("ab");
		
		SuffixTree expected = new SuffixTree();
		expected.string = "ab" + SuffixTree.STRING_END;
		expected.root.addEdge(0, 2);
		expected.root.addEdge(1, 2);

		assertTrue(actual.equals(expected));
	}
	
	  public void testSlowscanSimple() { 
		  SuffixTree tree = new SuffixTree("ab");
		  Node actual = tree.slowscan(1);
		  
	  }
	  
	 /* public void testCompareString() { SuffixTree tree(QString("abcde"));
	  
	  //QVERIFY(!QString("abcde").isNull());
	  QVERIFY(tree.root->compareString(1, Tuple(1,3))); }
	  
	  public void twoEdges() { //Node *root = SuffixTree("ab").root;
	  
	  assertTrue(root->contains(0, 1), "Contains the string");
	  assertTrue(root->contains(1, 1), "Contains the string");
	  
	  }
	 */
}
