package Malha;

import java.util.ArrayList;
import java.util.List;

class Graph {

  private int n;
  private int m;

  private Vertex[] vtcs;
  


  /********************************************************************************
     Builds an instance of a graph. 

     @param n The number of vertices to be inserted (later) in the graph.

     @param m The number of pairs of vertices to be connected (later) in the
     graph, counted as follows:

     If edges (u,v) and (v,u) are both going to be inserted, add 1 to m. 
     If edge (u,v) is going to be inserted (but not (v,u)), also add 1 to m.
     For parallel edges use the same rule as long as its length differentiate
     them.

     @param loadfactor The graph index load factor, that must be <=1.  For large
     graphs the index performance is poor if the factor equals 1 and the use of
     memory goes to infinity as the load factor goes to zero...  Some tunning
     may be required to find a good factor.  (There is already a tiny factor due
     to the fact that the size of the vertex index is a prime greater than n.)
  ********************************************************************************/
  public Graph(int n) {

    vtcs = new Vertex[n];
    
    for (int i=0; i<n; i++)
    	vtcs[i] = new Vertex();
    
    this.n = n;
    this.m = 0;

  }


  /********************************************************************************
   ********************************************************************************/
  public int getN() {
    return n;
  }

  /********************************************************************************
   ********************************************************************************/
  public ArrayList<Integer> getNeighbors(int u) {
	  ArrayList<Integer> N = new ArrayList<>();
	  for (int index=0; index < this.getVertex(u).Neighbors.size() ; index++) {

    	  Edge uv = getVertex(u).Neighbors.get(index);
    	  int v = uv.getTerminal();
    	  N.add(v);
        }
	  
    return N;
  }

  /********************************************************************************
     Returns the vertex whose index is u.
  ********************************************************************************/
  public Vertex getVertex(int u) {
    return vtcs[u];
  }


  /********************************************************************************
     Adds edge uv and its attributes to the graph, for vertex indices u and v.
     Returns the index of the new edge.  Loops are skipped in which case the
     return is -1.
  ********************************************************************************/
  void addEdge(int u, int v, int length) {

    // Skips loops:
    if (u == v)
      return;
    
    System.out.printf("%d %d %d\n",u,v,length);

    vtcs[u].Neighbors.add(new Edge(v,length));
    vtcs[v].Neighbors.add(new Edge(u,length));
    m++;
  }




  /********************************************************************************
  ********************************************************************************/
  public void print() {

    System.out.printf("Vertices %d\n",n);
    System.out.printf("Edges %d\n",m);

    for (int u=0; u<n; u++) {
      System.out.printf("Vertice %d: ",u);

      for (int index=0; index < this.getVertex(u).Neighbors.size() ; index++) {

    	  Edge uv = getVertex(u).Neighbors.get(index);
    	  int v = uv.getTerminal();

        System.out.printf("(%d,%d) %d\n",u,v,uv.getLength());
        }
      System.out.printf("\n");
    }
  }
  
  /**
   * Return the next vertex in the path from u to v. Next meaning adjacent to u.
   * @param u
   * @param v
   * @return
   */
  
  int nextVertex(int u, int v) {
  
  	if (u == v) return u;
  	
  	return 1;
  }


public int getM() {
	return m;
}
  	
  
}

