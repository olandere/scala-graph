package graph.iterator

import graph._
import collection.mutable

class BreadthFirstIterator[V](val graph: Graph[V], val start: V) extends Iterator[V] {
  val queue = mutable.Queue[V]()
  val visited = mutable.Set[V]()
  queue.enqueue(start)
  val adjList = graph.adjList

  def hasNext: Boolean = queue.nonEmpty

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
