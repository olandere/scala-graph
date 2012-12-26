package graph.iterator

import graph._
import collection.mutable.{Set, Queue}

object TopologicalSort {

  def apply[V, E <: Edge[V]](graph: DirectedEdges[V]) = {
    val revGraph = graph.reverse
    val startVertices = revGraph.vertices.filterNot{v: V => revGraph.edges.exists(e => e._2 == v)}
    val visited:Set[V] = Set()
    val adjList = revGraph.adjList
    val queue: Queue[V] = Queue()

    def visit(v: V) {
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
