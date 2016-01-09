package graph

import graph.algorithms.KruskalsAlg
import KruskalsAlg.UndirectedGraph

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
}
