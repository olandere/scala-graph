package graph

trait DirectedEdges {
  this: Graph =>

  def addEdge(e: Edge*) = {
    val vs = e.foldLeft(Seq[Vertex]()){(l: Seq[Vertex], e: Edge) => l ++ List(e._1, e._2 )}
    new Graph(this.vertices ++ vs, this.edges ++ e) with DirectedEdges
  }

  def reverse = {
    DirectedGraph(vertices, edges.map {e => (e._2, e._1)})
  }
}

object DirectedGraph {
  def apply(vertices: Set[Vertex], edges:Set[Edge]) : Graph with DirectedEdges = {
    new Graph(vertices, edges) with DirectedEdges
  }

  def apply(): Graph with DirectedEdges = {
    new Graph(Set(), Set()) with DirectedEdges
  }
}

