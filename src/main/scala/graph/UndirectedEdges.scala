package graph

trait UndirectedEdges[V] {
  this: Graph[V] =>

  def addEdge(e: Edge[V]*) = {
    val e1 = e.map(e => e.reverse)
    val vs = e.foldLeft(Seq[V]()){(l: Seq[V], e: Edge[V]) => l ++ List[V](e._1, e._2 )}
    new Graph(this.vertices ++ vs, this.edges ++ e ++ e1) with UndirectedEdges[V]
  }

  def reverse = this

  def size = this.edges.size / 2

  def union(g: Graph[V]): Graph[V] with UndirectedEdges[V] = {
    require(this.vertices.intersect(g.vertices).isEmpty)
    require(this.edges.intersect(g.edges).isEmpty)
    UndirectedGraph(this.vertices.union(g.vertices), this.edges.union(g.edges))
  }
}

object UndirectedGraph {
  def apply[V](vertices: Set[V], edges:Set[Edge[V]]) : Graph[V] with UndirectedEdges[V] = {
    new Graph[V](vertices, edges) with UndirectedEdges[V]
  }

  def apply[V](): Graph[V] with UndirectedEdges[V] = {
    new Graph[V](Set(), Set()) with UndirectedEdges[V]
  }
}
