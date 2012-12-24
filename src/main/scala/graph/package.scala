package object graph {

  class Vertex(val name: String) {
    override def toString = name
  }

  object Vertex {
    def apply(name: String) = {
      new Vertex(name)
    }
  }

  type Edge = (Vertex, Vertex)
}