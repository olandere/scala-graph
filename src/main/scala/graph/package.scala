package object graph {

  class Vertex(val name: String) {
    override def toString = name
  }

  object Vertex {
    def apply(name: String) = {
      new Vertex(name)
    }
  }

  //type Edge = (Vertex, Vertex)

  class Edge[V](tail: V, head: V, val weight: Double) {
    val _1 = tail
    val _2 = head

    def reverse = new Edge[V](_2, _1, weight)

    override def toString = "("+tail+", "+head+")"
  }

  object Edge {
    def apply[V](t: V, h: V) = new Edge[V](t, h, 0)

    def apply[V](t: V, h: V, w: Double) = new Edge[V](t, h, w)
  }

  implicit def toEdge(t: Tuple2[Vertex, Vertex]) = new Edge[Vertex](t._1, t._2, 0)
}
