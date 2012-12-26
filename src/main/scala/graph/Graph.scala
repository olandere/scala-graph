package graph

abstract class Graph[V](val vertices: Set[V], val edges: Set[Edge[V]]) {
  def addEdge(e: Edge[V]*): Graph[V]

  def reverse: Graph[V]

  def adjList = {
    edges.groupBy{e: Edge[V] => e._1}.map{case (k, v) => k -> v.map(_._2)}
  }

  def order = vertices.size

  def size: Int

  def degree = 2 * size

  def union(g: Graph[V]): Graph[V]

  def contains(v: V): Boolean = vertices(v)
}


