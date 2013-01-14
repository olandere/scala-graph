package graph

abstract class Graph[V](val vertices: Set[V], val edges: Set[Edge[V]]) {
  def addEdge(e: Edge[V]*): Graph[V]

  def reverse: Graph[V]

  def adjList: Map[V, Set[V]]

  def order = vertices.size

  def size: Int = edges.size

  def degree = 2 * size

  def union(g: Graph[V]): Graph[V]

  def contains(v: V): Boolean = vertices(v)
}


