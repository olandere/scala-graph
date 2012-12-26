package graph

trait DirectedEdges[V] {
  this: Graph[V] =>

  def addEdge(e: Edge[V]*) = {
    val vs = e.foldLeft(Seq[V]()){(l: Seq[V], e: Edge[V]) => l ++ List[V](e._1, e._2 )}
    new Graph(this.vertices ++ vs, this.edges ++ e) with DirectedEdges[V]
  }

  def reverse = {
    DirectedGraph(vertices, edges.map {e: Edge[V] => e.reverse})
  }

  def size = this.edges.size

  def union(g: Graph[V]): Graph[V] = {
    require(this.vertices.intersect(g.vertices).isEmpty)
    require(this.edges.intersect(g.edges).isEmpty)
    DirectedGraph(this.vertices.union(g.vertices), this.edges.union(g.edges))
  }
}

object DirectedGraph {
  def apply[V](vertices: Set[V], edges:Set[Edge[V]]) : Graph[V] with DirectedEdges[V] = {
    new Graph[V](vertices, edges) with DirectedEdges[V]
  }

  def apply[V](): Graph[V] with DirectedEdges[V] = {
    new Graph[V](Set[V](), Set[Edge[V]]()) with DirectedEdges[V]
  }
}

