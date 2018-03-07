package graph.algorithms

import graph.{DirectedGraph, Edge, Vertex}
import org.scalatest._

/**
  * Created by eolander on 2/22/16.
  */
class BellmanFordAlgTest extends FlatSpec with Matchers {
  "BellmanFordAlg" should "find shortest paths" in {
    val v1 = Vertex("1")
    val v2 = Vertex("2")
    val v3 = Vertex("3")
    val v4 = Vertex("4")
    val e1 = Edge(v2, v1, 4)
    val e2 = Edge(v1, v3, -2)
    val e3 = Edge(v2, v3, 3)
    val e4 = Edge(v3, v4, 2)
    val e5 = Edge(v4, v2, -1)
    val graph = DirectedGraph(Set[Vertex](v1, v2, v3, v4), Set[Edge[Vertex]]()).addEdge(e1, e2, e3, e4, e5)
    println(BellmanFordAlg(graph, v1))
  }

}
