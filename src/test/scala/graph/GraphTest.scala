package graph

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import graph._

class GraphTest extends FlatSpec with ShouldMatchers {

  "directGraph" should "be empty" in {
    DirectedGraph().edges.size should equal(0)
  }

  "directGraph" should "contain one edge" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val e1: Edge = (v1, v2)
    val g = DirectedGraph()
    g.addEdge(e1).edges.size should equal(1)
  }

  "directGraph" should "contain three vertices" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val v3 = Vertex("v3")
    val e1: Edge = (v1, v2)
    val e2: Edge = (v1, v3)
    val g = DirectedGraph(Set(), Set()).addEdge(e1).addEdge(e2)
    g.edges.size should equal(2)
    g.vertices.size should equal(3)
  }

  "undirectedGraph" should "be empty" in {
    UndirectedGraph().edges.size should equal(0)
  }

  "undirectedGraph" should "contain two edges" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val e1: Edge = (v1, v2)
    val g = UndirectedGraph()
    g.addEdge(e1).edges.size should equal(2)
  }

  "undirectedGraph" should "contain three vertices" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val v3 = Vertex("v3")
    val e1: Edge = (v1, v2)
    val e2: Edge = (v1, v3)
    val g = UndirectedGraph(Set(), Set()).addEdge(e1).addEdge(e2)
    g.edges.size should equal(4)
    g.vertices.size should equal(3)
  }
}
