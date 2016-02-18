package graph.algorithms

import graph.Graph

import scala.collection._

/**
  *
  */
object FloydWarshallAlg {

  def apply[V](g: Graph[V]) = {
    val dist: scala.collection.mutable.Map[V, scala.collection.mutable.Map[V, Double]] = mutable.HashMap()
    val next: scala.collection.mutable.Map[V, scala.collection.mutable.Map[V, V]] = mutable.HashMap()

    for (e <- g.edges) {
      dist(e._1)(e._2) = e.weight.get
    }

    for (k <- g.vertices; i <- g.vertices; j <- g.vertices) {
      if (dist(i)(k) + dist(k)(j) < dist(i)(j)) {
        dist(i)(j) = dist(i)(k) + dist(k)(j)
        next(i)(j) = next(i)(k)
      }
    }
    (dist, next)
  }

}
