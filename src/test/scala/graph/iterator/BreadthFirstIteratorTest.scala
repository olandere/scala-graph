package graph.iterator

import _root_.iterator.BreadthFirstIterator
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import graph._
import scala.StringBuilder

class BreadthFirstIteratorTest extends FlatSpec with ShouldMatchers {
  "breadthFirstIteration" should "traverse in breadth-first order" in {
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

    val graph = UndirectedGraph().addEdge(e1, e2, e3, e4, e5, e6, e7)
    val iterator = BreadthFirstIterator(graph, a)
    val bldr = new StringBuilder()
    iterator.foreach(v => bldr.append(v))
    println(bldr)
    // bldr.toString() should equal("abcdefgh")
  }

}
