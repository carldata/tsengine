package carldata.sf.core

import carldata.series.TimeSeries
import carldata.sf.Runtime

import scala.collection.Searching._

/**
  * Core functions and types which can be accessed from the script
  */
object DBModule {
  // Header which will be provided to the compiler
  val header: String =
    """
      |external def lookup(id: String, xs: TimeSeries): TimeSeries
    """.stripMargin

  def apply(db: DBImplementation): DBModule = new DBModule(db)
}

trait DBImplementation {
  /** Result of getTable have to be ordered */
  def getTable(id: String): IndexedSeq[(Float, Float)]
}

class DBModule(db: DBImplementation) extends Runtime {
  def $lookup(id: String, xs: TimeSeries[Float]): TimeSeries[Float] = {
    val lookupTable = db.getTable(id)

    def lookup(x: Float): Float = {
      val idx = lookupTable.unzip._1.search(x).insertionPoint
      if (x < lookupTable.head._1) lookupTable.head._2
      else if (idx >= lookupTable.size) lookupTable.last._2
      else {
        val t1 = lookupTable(idx)
        if (t1._1.equals(x)) t1._2
        else {
          val t0 = lookupTable(idx - 1)
          t0._2 + (((t1._2 - t0._2) / (t1._1 - t0._1)) * (x - t0._1))
        }
      }
    }

    xs.mapValues(lookup)
  }
}

