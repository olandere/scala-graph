package graph

import org.scalatest._

class GraphTest extends FlatSpec with Matchers {

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
    g.containsEdge(e2) shouldBe true
    g.containsEdge(e2.reverse) shouldBe false
    println(s"adjList: ${g.adjList}")
    g.degree(v1) should equal(2)
    g.degree(v2) should equal(1)
    g.degree(v3) should equal(1)
  }

  "undirectedGraph" should "be empty" in {
    UndirectedGraph().edges.size should equal(0)
  }

  it should "contain two edges" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val e1 = Edge(v1, v2)
    val g = UndirectedGraph[Vertex]()
    g.addEdge(e1).edges.size should equal(1)
  }

  it should "have a correct equals operation" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val e1 = Edge(v1, v2)
    val g1 = UndirectedGraph(Set[Vertex](v1, v2), Set[Edge[Vertex]](e1))
    val g2 = UndirectedGraph(Set[Vertex](v1, v2), Set[Edge[Vertex]](e1))
    g1 should equal(g2)
  }

  it should "contain three vertices" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val v3 = Vertex("v3")
    val e1 = Edge(v1, v2)
    val e2 = Edge(v1, v3)
    val g = UndirectedGraph(Set[Vertex](), Set[Edge[Vertex]]()).addEdge(e1).addEdge(e2)
    g.edges.size should equal(2)
    g.vertices.size should equal(3)
    g.degree(v1) should equal(2)
    g.degree(v2) should equal(1)
    g.degree(v3) should equal(1)
    g.neighbors(v1) should equal(Set(v2, v3))
  }

  "unionOfUndirectedGraphs" should "be the union of vertices and edges" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val v3 = Vertex("v3")
    val e1 = Edge(v1, v2)
    val e2 = Edge(v1, v3)
    val g = UndirectedGraph(Set[Vertex](), Set[Edge[Vertex]]()).addEdge(e1, e2)
    val v4 = Vertex("v4")
    val v5 = Vertex("v5")
    val e3 = Edge(v4, v5)
    val g1 = UndirectedGraph(Set[Vertex](v4, v5), Set[Edge[Vertex]](e3))
    val g2 = g.union(g1)
    g2.order should equal(5)
    g2.size should equal(3)
    g2.degree should equal(6)
    g2.containsEdge(e3) shouldBe true
    g2.containsEdge(e3.reverse) shouldBe true
  }

  it should "handle duplicate verticies" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v1")
    val v3 = Vertex("v2")
    val e1 = Edge(v1, v3)
    val e2 = Edge(v2, v3)
    val g = UndirectedGraph(Set[Vertex](), Set[Edge[Vertex]]()).addEdge(e1, e2)
    g.vertices.size should equal(2)
    g.edges.size should equal(1)
  }
}
