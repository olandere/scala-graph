package graph.algorithms

import graph._
import scala.collection.mutable

/**
  * Created by eolander on 2/21/16.
  */
object BellmanFordAlg {
  def apply[V](graph: Graph[V], source: V) = {
    val distance = mutable.HashMap[V, Double]()
    val predecessor = mutable.HashMap[V, V]()

    distance(source) = 0
    1 until (graph.vertices.size) foreach { _ =>
      graph.edges foreach {case Edge(u, v, w) =>
        if (distance.contains(u) && distance(u) + w.getOrElse(0.0) < distance.withDefaultValue(Double.MaxValue)(v)) {
          distance(v) = distance(u) + w.getOrElse(0.0)
          predecessor(v) = u
        }
      }
    }
    (distance, predecessor)
  }

}
