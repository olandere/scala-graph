package graph

/**
  * Created by eolander on 1/8/16.
  */
object RenderDot {

  def apply[V](g: Graph[V]): String = {
    g match {
      case g: DirectedGraph[V] => "digraph g {" +
                                  g.edges.map(e => s"${e._1} -> ${e._2} ${e.weight.fold("")(w => s"[label=$w]")}")
                                  .mkString(";") + "}"
      case g: UndirectedGraph[V] => "graph g {" + g.edges.map(e => s"${e._1} -- ${e._2}").mkString(";") + "}"
    }

  }
}
