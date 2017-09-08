package carldata.sf.core

import carldata.sf.Runtime

/**
  * Core functions and types which can be accessed from the script
  */
object HydrologyModule {
  // Header which will be provided to the compiler
  val header: String =
    """
      |external def manning_velocity(n: Number, d: Float, s: Float, u: String): Number
    """.stripMargin

  def apply(): HydrologyModule = new HydrologyModule()
}

class HydrologyModule extends Runtime {

  // Function definition

  /**
    * Calculate manning velocity:
    * 'n': roughness coefficient,
    * 'd': hydraulic diameter, (hydraulic radius is one quater in the case of full pipe)
    * 's': hydraulic slope,
    * 'u': units: mm or in
    */
  def $manning_velocity(n: Float, d: Float, s: Float, u: String): Float = {
    u match {
      case "mm" => ((1 / n) * Math.pow((0.25 * d), 0.6667) * Math.pow(s, 0.5)).toFloat
      case "in" => ((1.49f / n) * Math.pow((0.25 * d), 0.6667) * Math.pow(s, 0.5)).toFloat
      case _ => -1f //Wrong unit
    }
  }
}

