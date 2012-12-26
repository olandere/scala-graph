package graph

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class GraphTest extends FlatSpec with ShouldMatchers {

  "directGraph" should "be empty" in {
    DirectedGraph().edges.size should equal(0)
  }

  "directGraph" should "contain one edge" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val e1 = Edge(v1, v2)
    val g = DirectedGraph[Vertex]()
    g.addEdge(e1).edges.size should equal(1)
  }

  "directGraph" should "contain three vertices" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val v3 = Vertex("v3")
    val e1 = Edge(v1, v2)
    val e2 = Edge(v1, v3)
    val g = DirectedGraph(Set[Vertex](), Set[Edge[Vertex]]()).addEdge(e1, e2)
    g.edges.size should equal(2)
    g.vertices.size should equal(3)
  }

  "undirectedGraph" should "be empty" in {
    UndirectedGraph().edges.size should equal(0)
  }

  "undirectedGraph" should "contain two edges" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val e1= Edge(v1, v2)
    val g = UndirectedGraph[Vertex]()
    g.addEdge(e1).edges.size should equal(2)
  }

  "undirectedGraph" should "contain three vertices" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val v3 = Vertex("v3")
    val e1= Edge(v1, v2)
    val e2= Edge(v1, v3)
    val g = UndirectedGraph(Set[Vertex](), Set[Edge[Vertex]]()).addEdge(e1).addEdge(e2)
    g.edges.size should equal(4)
    g.vertices.size should equal(3)
  }

  "unionOfUndirectedGraphs" should "be the union of vertices and edges" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val v3 = Vertex("v3")
    val e1= Edge(v1, v2)
    val e2= Edge(v1, v3)
    val g = UndirectedGraph(Set[Vertex](), Set[Edge[Vertex]]()).addEdge(e1, e2)
    val v4 = Vertex("v4")
    val v5 = Vertex("v5")
    val e3= Edge(v4, v5)
    val e4= Edge(v5, v4)
    val g1 = UndirectedGraph(Set[Vertex](v4, v5), Set[Edge[Vertex]](e3, e4))
    val g2 = g.union(g1)
    g2.order should equal(5)
    g2.size should equal(3)
    g2.degree should equal(6)
  }
}