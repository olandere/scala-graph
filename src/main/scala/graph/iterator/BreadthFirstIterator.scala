package iterator

import graph._
import collection.mutable.Queue
import collection.mutable.Set

class BreadthFirstIterator(val graph: Graph, val start: Vertex) extends Iterator[Vertex] {
  val queue = Queue[Vertex]()
  val visited = Set[Vertex]()
  queue.enqueue(start)
  val adjList = graph.adjList

  def hasNext = !queue.isEmpty

  def next() = {
    val elem = queue.dequeue()
    visited.add(elem)
    queue.enqueue(adjList(elem).filterNot(visited).toSeq:_*)
    elem
  }
}

object BreadthFirstIterator {
  def apply(graph: Graph, start: Vertex) = {
    new BreadthFirstIterator(graph, start)
  }
}
