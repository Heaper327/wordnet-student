import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class SAPTest {

  private SAP sapDigraph1;
  @Before
  public void setup() {
    In in = new In("wordnet-test-files/digraph1.txt");
    Digraph G = new Digraph(in);
    System.out.println(G);
    sapDigraph1 = new SAP(G);
  }

  @Test
  public void testSAPImmutable() {
    fail("Not yet implemented");
  }

  @Test
  public void testLengthIntInt() {
    assertEquals(4, sapDigraph1.length(3, 11));
  }

  //TODO: Write a testLengthIntInt test case for 
  //      digraph-ambiguous-ancestor.txt and another digraph of your choice

  @Test
  public void testAncestorIntInt() {
    assertEquals(1, sapDigraph1.ancestor(3, 11));
  }

  //TODO: Write a testAncestorIntInt test case for 
  //      digraph-ambiguous-ancestor.txt and another digraph of your choice

  @Test
  public void testLengthIterableIterable() {
    ArrayList<Integer> verticesV = new ArrayList<Integer>();
    verticesV.add(7);
    verticesV.add(4);
    verticesV.add(9);
    ArrayList<Integer> verticesW = new ArrayList<Integer>();
    verticesW.add(11);
    verticesW.add(2);
    assertEquals(3,sapDigraph1.length(verticesV, verticesW));
  }

  //TODO: Write a testLengthIterableIterable test case for 
  //      digraph-ambiguous-ancestor.txt and another digraph of your choice

  @Test
  public void testAncestorIterableIterable() {
    ArrayList<Integer> verticesV = new ArrayList<Integer>();
    verticesV.add(7);
    verticesV.add(4);
    verticesV.add(9);
    ArrayList<Integer> verticesW = new ArrayList<Integer>();
    verticesW.add(11);
    verticesW.add(2);
    assertEquals(5, sapDigraph1.ancestor(verticesV, verticesW));
  }

  //TODO: Write a testAncestorIterableIterable test case for 
  //      digraph-ambiguous-ancestor.txt and another digraph of your choice

}
