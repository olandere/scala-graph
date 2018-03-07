package graph.algorithms

import graph.{Edge, Graph}
import scala.collection.mutable

/**
  * Created by eolander on 2/24/16.
  */
object AStar {

  def apply[V](graph: Graph[V], start: V, goal: V, heuristic_cost_estimate: (V, V) => Double): Map[V, V] = {

    val closedSet = mutable.HashSet[V]()
    val openSet = mutable.HashSet[V](start)

    val cameFrom = mutable.HashMap[V, V]()
    val gScore = mutable.HashMap[V, Double]().withDefaultValue(Double.MaxValue)

    gScore(start) = 0.0

    val fScore = mutable.HashMap[V, Double]().withDefaultValue(Double.MaxValue)
    fScore(start) = heuristic_cost_estimate(start, goal)

    while (openSet.nonEmpty) {
      val current = openSet.map{v => (v, fScore(v))}.toSeq.sortBy(_._2).head._1
      openSet -= current
      closedSet += current
      graph.neighbors(current).filterNot(closedSet(_)).foreach{n =>
        val tentative_gScore = gScore(current) + graph.edges.find{e =>
             e == Edge(current, n) || e == Edge(n, current)}.fold(Double.MaxValue)(_.weight.getOrElse(Double.MaxValue))
        if (!openSet.contains(n)) {
          openSet += n
        } else if (tentative_gScore < gScore(n)) {
          cameFrom(n) = current
          gScore(n) = tentative_gScore
          fScore(n) = tentative_gScore + heuristic_cost_estimate(n, goal)
        }}
    }
    cameFrom.toMap
  }

}
