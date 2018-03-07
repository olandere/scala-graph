package graph

abstract class Graph[V](val vertices: Set[V], val edges: Set[Edge[V]]) {
  def addEdge(e: Edge[V]*): Graph[V]

  def reverse: Graph[V]

  def adjList: Map[V, Set[V]]

  /** Returns the number of verticies in the graph */
  def order: Int = vertices.size

  /** Returns the number of edges in the graph */
  def size: Int = edges.size

  def degree: Int = 2 * size

  def degree(v: V): Int = edges.filter{_.contains(v)}.size //adjList(v).size

  def union(g: Graph[V]): Graph[V]

  def neighbors(v: V): Set[V] = edges.filter{_.contains(v)}.flatMap{e => Set(e._1, e._2)} - v

  /** Tests whether the graph contains the specified vertex */
  def contains(v: V): Boolean = vertices(v)

  /** Tests whether the graph contains the specified edge */
  def containsEdge(e: Edge[V]): Boolean

  override def equals(that: Any): Boolean = {
    that match {
      case that: Graph[V] => vertices == that.vertices && edges == that.edges
      case _ => false
    }
  }

  override def hashCode: Int = {
    vertices.hashCode() * 41 + edges.hashCode()
  }
}


