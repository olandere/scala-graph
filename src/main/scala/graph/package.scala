package object graph {
  import scala.language.implicitConversions

  case class Vertex(name: String) {
    override def toString: String = name
//
//    override def equals(that: Any): Boolean = {
//      that match {
//        case that: Vertex => name == that.name
//        case _ => false
//      }
//    }
  }

//  object Vertex {
//    def apply(name: String) = {
//      new Vertex(name)
//    }
//  }

  //type Edge = (Vertex, Vertex)

  class Edge[V](tail: V, head: V, val weight: Double) {
    val _1 = tail
    val _2 = head

    def reverse: Edge[V] = new Edge[V](_2, _1, weight)

    def contains(vertex: V): Boolean = {_1 == vertex || _2 == vertex}

    //override def toString = "("+tail+", "+head+")"
    override def toString: String = s"$tail --> $head ($weight)"

    override def equals(that: Any): Boolean = {
      that match {
        case that: Edge[V] => _1 == that._1 && _2 == that._2
        case _ => false
      }
    }
  }

  object Edge {
    def apply[V](t: V, h: V): Edge[V] = new Edge[V](t, h, 0)

    def apply[V](t: V, h: V, w: Double): Edge[V] = new Edge[V](t, h, w)
  }

  implicit def toEdge(t: Tuple2[Vertex, Vertex]): Edge[Vertex] = new Edge[Vertex](t._1, t._2, 0)
}
