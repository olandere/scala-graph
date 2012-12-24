package graph.iterator

import graph.{Vertex, Graph}
import collection.mutable.{Queue, Set, Stack}

class DepthFirstIterator(val graph: Graph, val start: Vertex) extends Iterator[Vertex] {
  val queue = Queue[Vertex]()
  val visited = Set[Vertex]()
  val stack = Stack[Vertex]()
  queue.enqueue(start)
  val adjList = graph.adjList

  def hasNext = !queue.isEmpty

  def next() = {
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
  def apply(graph: Graph, start: Vertex) = {
    new DepthFirstIterator(graph, start)
  }
}
