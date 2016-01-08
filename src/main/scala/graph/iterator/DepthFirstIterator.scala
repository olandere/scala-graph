package graph.iterator

import graph.Graph
import collection.mutable.{Queue, Set, Stack}

class DepthFirstIterator[V](val graph: Graph[V], val start: V) extends Iterator[V] {
  val queue = Queue[V]()
  val visited = Set[V]()
  val stack = Stack[V]()
  queue.enqueue(start)
  val adjList = graph.adjList

  def hasNext: Boolean = !queue.isEmpty

  def next(): V = {
    val elem = queue.dequeue()
    visited.add(elem)
    val unvisited = adjList(elem).filterNot(visited)
    if (unvisited.isEmpty) {
      val unvisitedStack = stack.filterNot(visited)
      if (!unvisitedStack.isEmpty) {
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
