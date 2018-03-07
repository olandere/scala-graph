package graph.algorithms

import graph.Graph

import scala.collection._
import scala.collection.mutable
import scala.collection.immutable.Stream.Empty

/**
  *
  */
object FloydWarshallAlg {

  def apply[V](g: Graph[V]): (Map[V, Map[V, Double]], Map[V, Map[V, V]]) = {
    val dist: mutable.Map[V, mutable.Map[V, Double]] = mutable.HashMap()
    val next: mutable.Map[V, mutable.Map[V, V]] = mutable.HashMap()

    for (v <- g.vertices) {
      dist(v) = mutable.HashMap[V, Double](v -> 0)
    }
    for (e <- g.edges) {
      dist(e._1)(e._2) = e.weight.get
      next(e._1) = mutable.HashMap[V, V](e._2 -> e._2)
    }

    for (k <- g.vertices; i <- g.vertices; j <- g.vertices) {
      if (dist(i).withDefaultValue(Double.MaxValue)(k) + dist(k).withDefaultValue(Double.MaxValue)(j) < dist(i).withDefaultValue(Double.MaxValue)(j)) {
        dist(i)(j) = dist(i).withDefaultValue(Double.MaxValue)(k) + dist(k).withDefaultValue(Double.MaxValue)(j)
        next(i)(j) = next(i)(k)
      }
    }
    (dist.toMap, next.toMap)
  }

  def path[V](u: V, v: V, g: Graph[V]): Stream[V] = {
    val(_, next) = apply(g)
    if (!next(u).contains(v)) {
      Empty
    } else {
      def result(u: V, v: V): Stream[V] = u #:: (if (u != v) result(next(u)(v), v) else Empty)
      result(u, v)
    }
  }
}
