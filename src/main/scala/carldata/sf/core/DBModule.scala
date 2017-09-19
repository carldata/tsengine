package carldata.sf.core

import carldata.sf.Runtime

import scala.collection.Searching._

/**
  * Core functions and types which can be accessed from the script
  */
object DBModule {
  // Header which will be provided to the compiler
  val header: String =
    """
      |external def lookup(id: String, x: Number): Number
    """.stripMargin

  def apply(db: DBImplementation): DBModule = new DBModule(db)
}

trait DBImplementation {
  /** Result of getTable have to be ordered */
  def getTable(id: String): IndexedSeq[(Float, Float)]
}

class DBModule(db: DBImplementation) extends Runtime {
  def $lookup(id: String, x: Float): Float = {
    val lookup_table = db.getTable(id)
    val idx = lookup_table.unzip._1.search(x).insertionPoint
    if (x < lookup_table.head._1) lookup_table.head._2
    else if (idx >= lookup_table.size) lookup_table.last._2
    else {
      val t1 = lookup_table(idx)
      if (t1._1.equals(x)) t1._2
      else {
        val t0 = lookup_table(idx - 1)
        t0._2 + (((t1._2 - t0._2) / (t1._1 - t0._1)) * (x - t0._1))
      }
    }


  }
}

