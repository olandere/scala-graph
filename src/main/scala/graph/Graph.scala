package graph

abstract class Graph(val vertices: Set[Vertex], val edges: Set[Edge]) {
  def addEdge(e: Edge*): Graph

  def reverse: Graph

  def adjList = {
    edges.groupBy(e => e._1).map{case (k, v) => k -> v.map(_._2)}
  }

}


