package graph.algorithms

import graph.{UndirectedGraph, Graph}
import graph.iterator.DepthFirstIterator

/**
  * Created by eolander on 1/9/16.
  */
object ConnectedComponents {

  def apply[V](g: Graph[V]): Set[UndirectedGraph[V]] = {
    val visited: scala.collection.mutable.Set[V] = scala.collection.mutable.Set()

    for {
      v <- g.vertices if !visited(v)
      vertexSet = DepthFirstIterator(g, v).toSet
      edges = vertexSet.flatMap(v => g.edges.filter(_.contains(v)))
      t = visited ++= vertexSet
    } yield UndirectedGraph(vertexSet, edges)

  }

}
