import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class WordNetTest {

  private WordNet wordNet6TwoAncestors;
  @Before
  public void setUp() throws Exception {
    wordNet6TwoAncestors = new WordNet("synsets6.txt", "hypernyms6TwoAncestors.txt");
  }

  @Test (timeout = 5000, expected = IllegalArgumentException.class)
  public void testWordNetNullSysnets() {
    WordNet w = new WordNet(null, "hypernyms.txt");
  }
  
  @Test (timeout = 5000, expected = IllegalArgumentException.class)
  public void testWordNetNullHypernyms() {
    WordNet w = new WordNet("sysnets.txt", null);
  }
  
  //TODO Create test case that throws IllegalArgumentException if there is a cycle
  
  //TODO Create test case that throws IllegalArgumentException if there are two roots

  @Test
  public void testNouns() {
    String[] expectedNounsArray = {"a", "b", "c", "d", "e", "f"};
    ArrayList<String> expectedNouns = new ArrayList<String>(Arrays.asList(expectedNounsArray));
    for(String actualNoun: wordNet6TwoAncestors.nouns()) {
      assertTrue(expectedNouns.contains(actualNoun));
    }
  }
  
  //TODO write testNouns for synsets3 and synsets15

  @Test
  public void testIsNoun() {
    assertTrue(wordNet6TwoAncestors.isNoun("a"));
  }
  
  //TODO write 3 testCases for isNoun for synsets.txt
  //Tip: pick a word at the beginning, in the middle and a word towards the end
  
  
  @Test
  public void testIsNounFalse() {
    assertFalse(wordNet6TwoAncestors.isNoun("g"));
  }
  
  //TODO write 3 testCases for isNoun for synsets.txt
  //Tip: pick a word that's close to the dictionary, jumbled letters, and null/empty string

  @Test
  public void testDistance() {
    assertEquals(3, wordNet6TwoAncestors.distance("e", "b"));
  }
  
  //TODO write 2 testDistance test cases for hypernyms11AmbigousAncesor
  //TODO write 2 testDistance test cases for hypernymsManyPathsOneAncestor

  @Test
  public void testSap() {
    assertEquals("a", wordNet6TwoAncestors.sap("e", "b"));
  }
  
  //TODO write 2 testSap test cases for hypernyms11AmbigousAncesor
  //TODO write 2 testSap test cases for hypernymsManyPathsOneAncestor

}
