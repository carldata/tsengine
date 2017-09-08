package carldata.sf.core

import carldata.sf.Runtime

/**
  * Core functions and types which can be accessed from the script
  */
object HydrologyModule {
  // Header which will be provided to the compiler
  val header: String =
    """
      |external def manning_flow(n: Number, d: Number, s: Number, u: String): Number
      |external def manning_velocity(n: Number, d: Number, s: Number, u: String): Number
    """.stripMargin

  def apply(): HydrologyModule = new HydrologyModule()
}

class HydrologyModule extends Runtime {

  // Function definition

  /**
    * Calculate manning velocity & flow:
    * 'n': roughness coefficient,
    * 'd': hydraulic diameter, (hydraulic radius is one quater in the case of full pipe)
    * 's': hydraulic slope,
    * 'u': units: mm or in
    */

  def $manning_flow(n: Float, d: Float, s: Float, u: String): Float = {
    $manning_velocity(n, d, s, u) * cross_sectional_area(d)
  }

  def $manning_velocity(n: Float, d: Float, s: Float, u: String): Float = {
    u match {
      case "mm" => ((1 / n) * Math.pow(0.25 * d / 1000 , 0.6667) * Math.pow(s, 0.5)).toFloat
      case _ => ((1.49f / n) * Math.pow(0.25 * d / 12 , 0.6667) * Math.pow(s, 0.5)).toFloat
    }
  }

  /**
    * For pipe flowing full
    **/
  private def cross_sectional_area(d: Float): Float = {
    (Math.PI * d * d).toFloat / 4
  }


}

