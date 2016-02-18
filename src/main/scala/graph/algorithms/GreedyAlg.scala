package graph.algorithms

import graph._

import scala.annotation.tailrec

/**
  * Created by eolander on 1/6/16.
  */
object GreedyAlg {

  def apply[V](graph: UndirectedGraph[V]): UndirectedGraph[V] = {
    @tailrec
    def buildGraph(g: UndirectedGraph[V], edges: Set[Edge[V]], endpoints: Set[V]): UndirectedGraph[V] = {
      if (g.vertices == graph.vertices) g else {
        //find edge with least weight containing either endpoint
        val newEdge = edges.filter(p => endpoints(p._1) || endpoints(p._2)).toSeq.sortBy(_.weight).head
        val interiorVertex = if (endpoints(newEdge._1)) newEdge._1 else newEdge._2
        val newEndpoint = if (endpoints(newEdge._1)) newEdge._2 else newEdge._1
        buildGraph(g.addEdge(newEdge), edges -- edges.filter(_.contains(interiorVertex)),
                   endpoints - interiorVertex + newEndpoint)
      }
    }

    val edges = graph.edges.toSeq.sortBy(_.weight)
    val g = UndirectedGraph(Set[V](), Set[Edge[V]]()).addEdge(edges.head)
    buildGraph(g, graph.edges - edges.head, g.vertices)
  }

  def opt2[V](graph: UndirectedGraph[V]): UndirectedGraph[V] = {

    def helper(es: List[List[Edge[V]]], g: UndirectedGraph[V]): UndirectedGraph[V] = {
      def findEdge(v1: V, v2: V) = {
        val edge = Edge(v1, v2)
        graph.edges.find(e => e == edge || e == edge.reverse)
      }

      def validateEdges(e1: Option[Edge[V]], e2: Option[Edge[V]], currentWeight: Double): Boolean = {
        e1.isDefined && e2.isDefined && !g.edges(e1.get) && !g.edges(e2.get) &&
        e1.get.weight.getOrElse(0.0) + e2.get.weight.getOrElse(0.0) < currentWeight
      }

      if (es.isEmpty) g else {
        val List(e1, e2) = es.head
        val currentWeight = e1.weight.getOrElse(0.0) + e2.weight.getOrElse(0.0)
        println(s"currentWeight: $currentWeight")
        val ne1 = findEdge(e1._1, e2._1)
        val ne2 = findEdge(e1._2, e2._2)
        val ne3 = findEdge(e1._1, e2._2)
        val ne4 = findEdge(e1._2, e2._1)
        println(s"$ne1 $ne2 $ne3 $ne4")
        var ng: UndirectedGraph[V] = if (validateEdges(ne1, ne2, currentWeight)) {
          println(s"### $ne1 $ne2 better than $e1 $e2")
          val t = UndirectedGraph(g.vertices, g.edges - e1 - e2 + ne1.get + ne2.get)
          if (ConnectedComponents(t).size == 1) t else g
        } else g
        ng = if (ng == g && validateEdges(ne3, ne4, currentWeight)) {
          println(s"### $ne3 $ne4 better than $e1 $e2")
          val t = UndirectedGraph(g.vertices, g.edges - e1 - e2 + ne3.get + ne4.get)
          if (ConnectedComponents(t).size == 1) t else g
        } else ng
        if (ng == g) helper(es.tail, g) else ng
      }
    }

    val g = GreedyAlg(graph)

    val edgePairs = g.edges.toList.combinations(2).filterNot{case List(e1, e2) => e2.contains(e1._1) || e2.contains(e1._2)}.toList
    helper(edgePairs, g)

//    for {
//      e1 <- g.edges
//      e2 <- g.edges - e1
//    }
     // graph
  }
}
