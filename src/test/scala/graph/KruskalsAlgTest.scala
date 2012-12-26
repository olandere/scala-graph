package graph

import graph._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class KruskalsAlgTest extends FlatSpec with ShouldMatchers {
  "KruskalsAlg" should "find spanning tree" in {
    val v1 = Vertex("v1")
    val v2 = Vertex("v2")
    val v3 = Vertex("v3")
    val v4 = Vertex("v4")
    val e1 = Edge(v1, v2, 1)
    val e2 = Edge(v1, v3, 4)
    val e3 = Edge(v2, v4, 3)
    val e4 = Edge(v3, v4, 2)
    val graph = UndirectedGraph(Set[Vertex](v1, v2, v3, v4), Set[Edge[Vertex]]()).addEdge(e1, e2, e3, e4)
    val spanTree = KruskalsAlg[Vertex](graph)
    println(spanTree.edges)
  }
  ""
  "KruskalsAlg" should "find min tree" in {
    val a = Vertex("A")
    val b = Vertex("B")
    val c = Vertex("C")
    val d = Vertex("D")
    val e = Vertex("E")
    val f = Vertex("F")
    val g = Vertex("G")
    val ab = Edge(a, b, 7)
    val ad = Edge(a, d, 4)
    val bc = Edge(b, c, 8)
    val bd = Edge(b, d, 9)
    val be = Edge(b, e, 7)
    val ce = Edge(c, e, 5)
    val de = Edge(d, e, 15)
    val df = Edge(d, f, 6)
    val ef = Edge(e, f, 8)
    val eg = Edge(e, g, 9)
    val fg = Edge(f, g, 11)
    val graph = UndirectedGraph(Set[Vertex](a,b,c,d,e,f,g), Set[Edge[Vertex]]()).addEdge(ab, ad, bc, bd, be, ce, de, df, ef, eg, fg)
    val spanTree = KruskalsAlg[Vertex](graph)
    println(spanTree.edges)
  }
}
