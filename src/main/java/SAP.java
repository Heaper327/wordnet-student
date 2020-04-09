import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {

  private Digraph graph;
  private BreadthFirstDirectedPaths pathsV;
  private BreadthFirstDirectedPaths pathsW;
  
   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G) {
     graph = new Digraph(G);
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w) {
     pathsV = new BreadthFirstDirectedPaths(graph, v);
     pathsW = new BreadthFirstDirectedPaths(graph, w);
     int minimumDist = Integer.MAX_VALUE;
     // brute force through all vertices, find the minimum ancestor
     for (int i = 0; i < graph.V(); i++) {
       if (pathsV.hasPathTo(i) && pathsW.hasPathTo(i)) {
         int curDist = pathsV.distTo(i) + pathsW.distTo(i);
         if (curDist < minimumDist) {
           minimumDist = curDist;
         }
       }
     }
     return minimumDist;
   }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w) {
     pathsV = new BreadthFirstDirectedPaths(graph, v);
     pathsW = new BreadthFirstDirectedPaths(graph, w);
     int minimumDist = Integer.MAX_VALUE;
     int minimumAncestor = -1;
     // brute force through all vertices, find the minimum ancestor
     for (int i = 0; i < graph.V(); i++) {
       if (pathsV.hasPathTo(i) && pathsW.hasPathTo(i)) {
         int curDist = pathsV.distTo(i) + pathsW.distTo(i);
         if (curDist < minimumDist) {
           minimumDist = curDist;
           minimumAncestor = i;
         }
       }
     }
     return minimumAncestor;
   }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable v, Iterable w) {
     int minimumDist = Integer.MAX_VALUE;
     // find the distance between all pairs of vertices in v and w, and keep the minimum
     for (Object vMember: v) {
       for (Object wMember: w) {
         int curDist = length((int) vMember, (int) wMember);
         if (curDist < minimumDist) {
           minimumDist = curDist;
         }
       }
     }
     return minimumDist;
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable v, Iterable w) {
     int minimumDist = Integer.MAX_VALUE;
     int minimumAncestor = -1;
     // find the distance between all pairs of vertices in v and w, and keep the minimum
     // also keep the ancestor that is associated with the minimum distance
     for (Object vMember: v) {
       for (Object wMember: w) {
         int curDist = length((int) vMember, (int) wMember);
         int curAncestor = ancestor((int) vMember, (int) wMember);
         if (curDist < minimumDist) {
           minimumDist = curDist;
           minimumAncestor = curAncestor;
         }
       }
     }
     return minimumAncestor;
   }

   // do unit testing of this class
   public static void main(String[] args) {
     In in = new In(args[0]);
     Digraph G = new Digraph(in);
     SAP sap = new SAP(G);
     while (!StdIn.isEmpty()) {
         int v = StdIn.readInt();
         int w = StdIn.readInt();
         int length   = sap.length(v, w);
         int ancestor = sap.ancestor(v, w);
         StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
     }
   }
}