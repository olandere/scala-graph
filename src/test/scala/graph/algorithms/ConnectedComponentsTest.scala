package graph.algorithms

import graph.{UndirectedGraph, Edge, Vertex}
import org.scalatest._

/**
  * Created by eolander on 1/9/16.
  */
class ConnectedComponentsTest extends FlatSpec with Matchers {

  "ConnectedComponents" should "return the same graph" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val v3 = Vertex("v3")
    val v4 = Vertex("v4")
    val e1 = Edge(v1, v2)
    val e2 = Edge(v1, v3)
    val e3 = Edge(v2, v4)
    val e4 = Edge(v3, v4)
    val graph = UndirectedGraph(Set[Vertex](v1, v2, v3, v4), Set[Edge[Vertex]]()).addEdge(e1, e2, e3, e4)
    val cc = ConnectedComponents(graph)
    val edgePairs = graph.edges.toList.combinations(2).filterNot{case List(e1, e2) => e2.contains(e1._1) || e2.contains(e1._2)}
    println("*******")
    println(edgePairs.toList)
    println("*******")
    cc.size should equal(1)
  }

  it should "return multiple graphs" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val v3 = Vertex("v3")
    val v4 = Vertex("v4")
    val e1 = Edge(v1, v2)
    val e4 = Edge(v3, v4)
    val graph = UndirectedGraph(Set[Vertex](v1, v2, v3, v4), Set[Edge[Vertex]]()).addEdge(e1, e4)
    val cc = ConnectedComponents(graph)
    println(cc)
    cc.size should equal(2)
  }

}
