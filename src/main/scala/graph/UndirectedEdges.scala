package graph

trait UndirectedEdges {
  this: Graph =>

  def addEdge(e: Edge*) = {
    val e1 = e.map(e => (e._2, e._1))
    val vs = e.foldLeft(Seq[Vertex]()){(l: Seq[Vertex], e: Edge) => l ++ List(e._1, e._2 )}
    new Graph(this.vertices ++ vs, this.edges ++ e ++ e1) with UndirectedEdges
  }

  def reverse = this
}

object UndirectedGraph {
  def apply(vertices: Set[Vertex], edges:Set[Edge]) : Graph = {
    new Graph(vertices, edges) with UndirectedEdges
  }

  def apply(): Graph = {
    new Graph(Set(), Set()) with UndirectedEdges
  }
}
