package graph.iterator

import graph.Graph
import collection.mutable

class DepthFirstIterator[V](val graph: Graph[V], val start: V) extends Iterator[V] {
  val queue = mutable.Queue[V]()
  val visited = mutable.Set[V]()
  val stack = mutable.Stack[V]()
  queue.enqueue(start)
  val adjList = graph.adjList

  def hasNext: Boolean = queue.nonEmpty

  def next(): V = {
    val elem = queue.dequeue()
    visited.add(elem)
    val unvisited = adjList(elem).filterNot(visited)
    if (unvisited.isEmpty) {
      val unvisitedStack = stack.filterNot(visited)
      if (unvisitedStack.nonEmpty) {
        queue.enqueue(unvisitedStack.pop())
      }
    } else {
      queue.enqueue(unvisited.head)
      stack.pushAll(unvisited.tail.toIterable)
    }
    elem
  }
}

object DepthFirstIterator {
  def apply[V](graph: Graph[V], start: V): Iterator[V] = {
    new DepthFirstIterator(graph, start)
  }
}
