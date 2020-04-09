import java.util.HashMap;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class WordNet {

  /**
   * A mapping from the nouns to the corresponding synsets.
   */
  private HashMap<String, Bag<Integer>> nounsToSynsets;
  /**
   * A mapping from the synsets to the corresponding nouns.
   */
  private HashMap<Integer, Bag<String>> synsetsToNouns;
  /**
   * A directed acyclic graph of the synsets based on hypernyms relationship.
   */
  private Digraph wordGraph;
  /**
   * A SAP of the wordGraph.
   */
  private SAP wordSAP;

  /**
   * Constructor that takes the name of two input files.
   * @param synsets the name of the synsets input file.
   * @param hypernyms the name of the hypernyms input file.
   */
  public WordNet(String synsets, String hypernyms) {

    In reader = new In(synsets);
    synsetsToNouns = new HashMap<Integer, Bag<String>>();
    nounsToSynsets = new HashMap<String, Bag<Integer>>();

    int numSynsets = 0;

    while (reader.hasNextLine()) {
      /*
       * first token is the id of the synset
       * second token is the nouns
       * third token is the definition
       */
      String[] tokens = reader.readLine().split(",");

      // store the id of the current synset
      int id = Integer.parseInt(tokens[0]);

      // construct the bag of nouns of the current synset
      Bag<String> nouns = new Bag<String>();
      synsetsToNouns.put(id, nouns);

      /*
       * read all the nouns in this synset
       * 1. add these nouns to the bag of nouns of this synset
       * 2. add this synset to the bag of synsets of these nouns
       */
      for (String curNoun: tokens[1].split(" ")) {
        nouns.add(curNoun);
        if (!nounsToSynsets.containsKey(curNoun)) {
          nounsToSynsets.put(curNoun, new Bag<Integer>());
        }
        nounsToSynsets.get(curNoun).add(id);
      }

      numSynsets++;
    }

    // construct wordGraph based on the number of synsets
    wordGraph = new Digraph(numSynsets);

    // use the reader to read the hypernyms file
    reader = new In(hypernyms);

    // create a Digraph of size number of lines in the synset file
    while (reader.hasNextLine()) {
      // grab the current line
      /*
       * first token is the current synset
       * second to last tokens are its hypernyms
       */
      String[] tokens = reader.readLine().split(",");

      // store the id of the current synset
      int id = Integer.parseInt(tokens[0]);

      // read its hypernyms and add the edges into the digraph
      for (int i = 1; i < tokens.length; i++) {
        int hypernym = Integer.parseInt(tokens[i]);
        wordGraph.addEdge(id, hypernym);
      }
    }

    // create a SAP based of wordGraph
    wordSAP = new SAP(wordGraph);
  }

  /**
   * return an iterable list of all nouns in the WordNet.
   * @return an iterable list of all nouns
   */
  public Iterable<String> nouns() {
    return nounsToSynsets.keySet();
  }

  /**
   * determine whether a noun is in the WordNet or not.
   * @param word the noun to check
   * @return true if the noun is in the WordNet, false otherwise
   */
  public boolean isNoun(String word) {
    return nounsToSynsets.keySet().contains(word);
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    return wordSAP.length(nounsToSynsets.get(nounA), nounsToSynsets.get(nounB));
  }

  // a synset (second field of synsets.txt) that is the common ancestor of nounA
  // and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    int synset = wordSAP.ancestor(nounsToSynsets.get(nounA), nounsToSynsets.get(nounB));
    StringBuilder sb = new StringBuilder();
    for (String noun: synsetsToNouns.get(synset)) {
      sb.append(noun + " ");
    }
    // remove the extra space after the last noun
    return sb.substring(0, sb.length() - 1);
  }

  // do unit testing of this class
  public static void main(String[] args) {

  }
}