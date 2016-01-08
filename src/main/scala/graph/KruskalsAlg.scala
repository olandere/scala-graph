package graph

import annotation.tailrec

object KruskalsAlg {

  type UndirectedGraph[V] = Graph[V] with UndirectedEdges[V]

  def apply[V](graph: UndirectedGraph[V]): UndirectedGraph[V] = {
    @tailrec
    def buildSpanningTree(forest: Set[UndirectedGraph[V]], edges: Seq[Edge[V]]): UndirectedGraph[V] = {
      if (edges.isEmpty) {forest.head} else {
        val edge = edges.head
        val g1 = forest.filter(_.contains(edge._1)).head
        val g2 = forest.filter(_.contains(edge._2)).head
        if (g1 != g2) {
          val g3 = g1.union(g2).addEdge(edge)
          buildSpanningTree(forest - g1 - g2 + g3, edges.tail)
        } else {
          buildSpanningTree(forest, edges.tail)
        }
      }
    }

    //Create forest of graphs
    val forest: Set[UndirectedGraph[V]] = (for (v <- graph.vertices) yield {
      UndirectedGraph(Set(v), Set[Edge[V]]())
    })
                                          .toSet
    //Sort edges by weight
    val edges = graph.edges.toSeq.sortBy(_.weight)
    buildSpanningTree(forest, edges)
  }

}
