package graph.iterator

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import graph.{UndirectedGraph, Vertex}

class DepthFirstIteratorTest extends FlatSpec with ShouldMatchers {
  "depthFirstIteration" should "handle cycles" in {
    val a = Vertex("a")
    val b = Vertex("b")
    val c = Vertex("c")
    val d = Vertex("d")
    val e1 = (a, b)
    val e2 = (a, c)
    val e3 = (b, d)
    val e4 = (c, d)
    val graph = UndirectedGraph().addEdge(e1, e2, e3, e4)
    val iterator = DepthFirstIterator(graph, a)
    val bldr = new StringBuilder()
    iterator.foreach(v => bldr.append(v))
    println(bldr)
  }


  "depthFirstIteration" should "traverse in depth first order" in {
    val a = Vertex("a")
    val b = Vertex("b")
    val c = Vertex("c")
    val d = Vertex("d")
    val e = Vertex("e")
    val f = Vertex("f")
    val g = Vertex("g")
    val h = Vertex("h")
    val e1 = (a, b)
    val e2 = (a, c)
    val e3 = (b, d)
    val e4 = (b, e)
    val e5 = (c, f)
    val e6 = (c, g)
    val e7 = (e, h)
    val e8 = (h, f)

    val graph = UndirectedGraph().addEdge(e1, e2, e3, e4, e5, e6, e7, e8)
    val iterator = DepthFirstIterator(graph, a)
    val bldr = new StringBuilder()
    iterator.foreach(v => bldr.append(v))
    println(bldr)
    // bldr.toString() should equal("abcdefgh")
  }
}
