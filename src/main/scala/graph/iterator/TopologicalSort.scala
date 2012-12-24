package graph.iterator

import graph._
import collection.mutable.{Set, Queue}

object TopologicalSort {

  def apply(graph: DirectedEdges) = {
    val revGraph = graph.reverse
    val startVertices = revGraph.vertices.filterNot{v: Vertex => revGraph.edges.exists(e => e._2 == v)}
    val visited:Set[Vertex] = Set()
    val adjList = revGraph.adjList
    val queue: Queue[Vertex] = Queue()

    def visit(v: Vertex) {
      if (!visited(v)) {
        visited.add(v)
        if (adjList.contains(v)) {
          adjList(v).foreach(visit)
        }
        queue.enqueue(v)
      }
    }

    startVertices.foreach(visit)
    queue.toList
  }
}
