package graph.iterator

import graph._
import collection.mutable.Queue
import collection.mutable.Set

class BreadthFirstIterator[V](val graph: Graph[V], val start: V) extends Iterator[V] {
  val queue = Queue[V]()
  val visited = Set[V]()
  queue.enqueue(start)
  val adjList = graph.adjList

  def hasNext: Boolean = !queue.isEmpty

  def next(): V = {
    val elem = queue.dequeue()
    visited.add(elem)
    queue.enqueue(adjList(elem).filterNot(visited).toSeq:_*)
    elem
  }
}

object BreadthFirstIterator {
  def apply[V](graph: Graph[V], start: V): Iterator[V] = {
    new BreadthFirstIterator(graph, start)
  }
}
