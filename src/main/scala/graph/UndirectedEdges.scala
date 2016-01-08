package graph

trait UndirectedEdges[V] {
  this: Graph[V] =>

  def addEdge(e: Edge[V]*): Graph[V] with UndirectedEdges[V] = {
    val vs = e.foldLeft(Seq[V]()) { (l: Seq[V], e: Edge[V]) => l ++ List[V](e._1, e._2) }
    UndirectedGraph(this.vertices ++ vs, this.edges ++ e)
  }

  def adjList: Map[V, Set[V]] = {
    def merge(a: Map[V, Set[V]], b: Map[V, Set[V]]): Map[V, Set[V]] = {
      if (a.isEmpty) {b} else {
        val (k, v) = a.head
        if (b.contains(k)) {
          Map(k -> (v ++ b(k))) ++ merge(a.tail, b - k)
        } else {
          Map(k -> v) ++ merge(a.tail, b)
        }
      }
    }
    val g_out = edges.groupBy { e: Edge[V] => e._1 }.map { case (k, v) => k -> v.map(_._2) }
    val g_in = edges.groupBy { e: Edge[V] => e._2 }.map { case (k, v) => k -> v.map(_._1) }
    merge(g_in, g_out)
  }

  def reverse: Graph[V] with UndirectedEdges[V] = this

  def union(g: Graph[V]): Graph[V] with UndirectedEdges[V] = {
    require(this.vertices.intersect(g.vertices).isEmpty)
    require(this.edges.intersect(g.edges).isEmpty)
    UndirectedGraph(this.vertices.union(g.vertices), this.edges.union(g.edges))
  }
}

object UndirectedGraph {

  def apply[V](vertices: Set[V], edges: Set[Edge[V]]): Graph[V] with UndirectedEdges[V] = {
    new Graph[V](vertices, edges) with UndirectedEdges[V]
  }

  def apply[V](): Graph[V] with UndirectedEdges[V] = {
    new Graph[V](Set(), Set()) with UndirectedEdges[V]
  }
}
