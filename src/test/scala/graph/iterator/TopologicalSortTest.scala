package graph.iterator

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import graph._

class TopologicalSortTest extends FlatSpec with ShouldMatchers {
  "topologicalSort" should "vvv" in {
    val v7 = Vertex("7")
    val v5 = Vertex("5")
    val v3 = Vertex("3")
    val v11 = Vertex("11")
    val v8 = Vertex("8")
    val v2 = Vertex("2")
    val v9 = Vertex("9")
    val v10 = Vertex("10")
    val e1 = (v7, v11)
    val e2 = (v7, v8)
    val e3 = (v5, v11)
    val e4 = (v3, v8)
    val e5 = (v3, v10)
    val e6 = (v11, v2)
    val e7 = (v11, v9)
    val e8 = (v8, v9)
    val e9 = (v11, v10)
    val graph = DirectedGraph().addEdge(e1, e2, e3, e4, e5, e6, e7, e8, e9)
    println(TopologicalSort(graph))
  }
}
