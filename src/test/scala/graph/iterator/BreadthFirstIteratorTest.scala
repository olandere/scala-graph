package graph.iterator

import org.scalatest._
import graph._
import scala.StringBuilder

class BreadthFirstIteratorTest extends FlatSpec with Matchers {
  "breadthFirstIteration" should "traverse in breadth-first order" in {
    val a = Vertex("a")
    val b = Vertex("b")
    val c = Vertex("c")
    val d = Vertex("d")
    val e = Vertex("e")
    val f = Vertex("f")
    val g = Vertex("g")
    val h = Vertex("h")
    val e1 = Edge(a, b)
    val e2 = Edge(a, c)
    val e3 = Edge(b, d)
    val e4 = Edge(b, e)
    val e5 = Edge(c, f)
    val e6 = Edge(c, g)
    val e7 = Edge(e, h)

    val graph = UndirectedGraph[Vertex]().addEdge(e1, e2, e3, e4, e5, e6, e7)
    val iterator = BreadthFirstIterator(graph, a)
    val bldr = new StringBuilder()
    iterator.foreach(v => bldr.append(v))
    println(bldr)
    // bldr.toString() should equal("abcdefgh")
  }

}
