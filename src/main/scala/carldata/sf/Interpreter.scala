package carldata.sf

import carldata.series.TimeSeries
import carldata.sf.compiler.AST._
import carldata.sf.compiler.Executable.ExecCode
import carldata.sf.core.{DBImplementation, TimeSeriesModule}
import org.slf4j.LoggerFactory


/**
  * Script interpreter
  */
object Interpreter {

  def apply(exec: ExecCode): Interpreter = {
    new Interpreter(exec, Seq(core.MathModule(), TimeSeriesModule(), core.DateTimeModule(), core.HydrologyModule()))
  }

  def apply(exec: ExecCode, db: DBImplementation): Interpreter = {
    new Interpreter(exec, Seq(core.MathModule(), TimeSeriesModule(), core.DateTimeModule(), core.HydrologyModule(), core.DBModule(db)))
  }
}


class Interpreter(exec: ExecCode, runtimes: Seq[Runtime]) {

  private val Log = LoggerFactory.getLogger(Interpreter.getClass)

  /**
    * The runtime return either error string or computed value.
    * Before running the following conditions have to be met:
    *   - The program can only be run if number of provided parameters
    * matches number of parameters in function declaration
    */
  def run(funName: String, params: Seq[Any]): Either[String, Any] = {
    try {
      // Add compatible functions as Function1Value
      val sm: Map[String, Any] = exec.functions.flatMap { f =>
        if (f.params.nonEmpty && f.params.forall(_.typeName == NumberType) && f.typeName == NumberType) {
          f.params.size match {
            case 1 =>
              Seq(f.name -> new ((Float) => Float) {
                def apply(x: Float): Float = execFunction(f.name, Seq(x), Map()).asInstanceOf[Float]
              })
            case 2 =>
              Seq(f.name -> new ((Float, Float) => Float) {
                def apply(x1: Float, x2: Float): Float = execFunction(f.name, Seq(x1, x2), Map()).asInstanceOf[Float]
              })
            case 3 =>
              Seq(f.name -> new ((Float, Float, Float) => Float) {
                def apply(x1: Float, x2: Float, x3: Float): Float =
                  execFunction(f.name, Seq(x1, x2, x3), Map()).asInstanceOf[Float]
              })
            case 4 =>
              Seq(f.name -> new ((Float, Float, Float, Float) => Float) {
                def apply(x1: Float, x2: Float, x3: Float, x4: Float): Float =
                  execFunction(f.name, Seq(x1, x2, x3, x4), Map()).asInstanceOf[Float]
              })
            case _ => Seq()
          }
        } else {
          Seq()
        }
      }.toMap
      Right(execFunction(funName, params, sm))
    } catch {
      case e: Exception => Left("Runtime exception: " + e);
    }
  }

  def execFunction(name: String, params: Seq[Any], symbolMemory: Map[String, Any]): Any = {
    exec.functions.find(f => f.name == name && params.lengthCompare(f.params.size) == 0)
      .map { f =>
        val sm = symbolMemory ++ f.params.zip(params).map(x => x._1.name -> x._2).toMap
        execBody(f.body, sm)
      }
      .getOrElse(executeExternalFunction(name, params))
  }

  def execBody(body: FunctionBody, sm: Map[String, Any]): Any = {
    val sm2 = body.assignments.foldLeft(sm) { (t, a) =>
      t + (a.varName -> execExpr(a.expr, t))
    }
    execExpr(body.expr, sm2)
  }

  /** Execute node with the function declaration */
  private def execExpr(expr: Expression, symbolMemory: Map[String, Any]): Any = {
    expr match {
      case MinusOpExpr(e1) => execMinusOpExpr(e1, symbolMemory)
      case BinaryOpExpr(e1, op, e2) => execBinaryOpExpr(e1, op, e2, symbolMemory)
      case NegOpExpr(e1) => execNegOpExpr(e1, symbolMemory)
      case BoolOpExpr(e1, op, e2) => execBoolOpExpr(e1, op, e2, symbolMemory)
      case RelationExpr(e1, op, e2) => execRelationExpr(e1, op, e2, symbolMemory)
      case VariableExpr(name) => symbolMemory.getOrElse(name, Unit)
      case IfExpr(p, e1, e2) => execIfExpr(p, e1, e2, symbolMemory)
      case AppExpr(name, params) =>
        val xs = params.map(x => execExpr(x, symbolMemory))
        execFunction(name, xs, symbolMemory)
      case StringLiteral(text) => text
      case NumberLiteral(v) => v
    }
  }

  def execMinusOpExpr(e1: Expression, mem: Map[String, Any]): Float = {
    val a = execExpr(e1, mem)
    val v = mkFloat(a)
    -v
  }

  //noinspection TypeCheckCanBeMatch
  def execBinaryOpExpr(e1: Expression, op: String, e2: Expression, mem: Map[String, Any]): Any = {
    val a = execExpr(e1, mem)
    val b = execExpr(e2, mem)

    if (a.isInstanceOf[TimeSeries[_]] && b.isInstanceOf[TimeSeries[_]]) {
      val xs: TimeSeries[Float] = a.asInstanceOf[TimeSeries[Float]]
      val ys: TimeSeries[Float] = b.asInstanceOf[TimeSeries[Float]]
      op match {
        case "+" => xs.join(ys).mapValues(v => v._1 + v._2)
        case "-" => xs.join(ys).mapValues(v => v._1 - v._2)
        case "*" => xs.join(ys).mapValues(v => v._1 * v._2)
        case "/" => xs.join(ys).mapValues(v => v._1 / v._2)
        case err =>
          Log.error("Wrong binary operator: " + err)
          0f
      }
    } else if (a.isInstanceOf[TimeSeries[_]]) {
      val xs: TimeSeries[Float] = a.asInstanceOf[TimeSeries[Float]]
      op match {
        case "+" => xs.mapValues(_ + mkFloat(b))
        case "-" => xs.mapValues(_ - mkFloat(b))
        case "*" => xs.mapValues(_ * mkFloat(b))
        case "/" => xs.mapValues(_ / mkFloat(b))
        case err =>
          Log.error("Wrong binary operator: " + err)
          0f
      }
    } else if (b.isInstanceOf[TimeSeries[_]]) {
      val ys: TimeSeries[Float] = b.asInstanceOf[TimeSeries[Float]]
      op match {
        case "+" => ys.mapValues(mkFloat(a) + _)
        case "-" => ys.mapValues(mkFloat(a) - _)
        case "*" => ys.mapValues(mkFloat(a) * _)
        case "/" => ys.mapValues(mkFloat(a) / _)
        case err =>
          Log.error("Wrong binary operator: " + err)
          0f
      }
    } else {
      op match {
        case "+" => mkFloat(a) + mkFloat(b)
        case "-" => mkFloat(a) - mkFloat(b)
        case "*" => mkFloat(a) * mkFloat(b)
        case "/" => mkFloat(a) / mkFloat(b)
        case err =>
          Log.error("Wrong binary operator: " + err)
          0f
      }
    }
  }

  def execNegOpExpr(e1: Expression, mem: Map[String, Any]): Boolean = {
    val a = execExpr(e1, mem)
    val b = mkBool(a)
    !b
  }

  def execBoolOpExpr(e1: Expression, op: String, e2: Expression, mem: Map[String, Any]): TimeSeries[Boolean] = {
    val a = execExpr(e1, mem)
    val b = execExpr(e2, mem)
    if(a.isInstanceOf[TimeSeries[_]] && b.isInstanceOf[TimeSeries[_]] && (op == "&&" || op == "||")){
      val xs: TimeSeries[Boolean] = a.asInstanceOf[TimeSeries[Boolean]]
      val ys: TimeSeries[Boolean] = b.asInstanceOf[TimeSeries[Boolean]]
      op match {
        case "&&" => xs.join(ys).mapValues(xy => xy._1 && xy._2)
        case "||" => xs.join(ys).mapValues(xy => xy._1 || xy._2)
      }
    } else {
      new TimeSeries[Boolean](Seq())
    }
  }

  def execRelationExpr(e1: Expression, op: String, e2: Expression, mem: Map[String, Any]): TimeSeries[Boolean] = {
    val a = execExpr(e1, mem)
    val b = execExpr(e2, mem)

    if(a.isInstanceOf[TimeSeries[_]]){
      val xs: TimeSeries[Float] = a.asInstanceOf[TimeSeries[Float]]
      val y = mkFloat(b)
      op match {
        case "==" => xs.mapValues(_ == y)
        case "!=" => xs.mapValues(_ != y)
        case ">" => xs.mapValues(_ > y)
        case "<" => xs.mapValues(_ < y)
        case ">=" => xs.mapValues(_ >= y)
        case "<=" => xs.mapValues(_ <= y)
        case err =>
          Log.error("Wrong relation operator: " + err)
          new TimeSeries[Boolean](Seq())
      }
    } else if(b.isInstanceOf[TimeSeries[_]]) {
      val xs: TimeSeries[Float] = b.asInstanceOf[TimeSeries[Float]]
      val y = mkFloat(a)
      op match {
        case "==" => xs.mapValues(y == _)
        case "!=" => xs.mapValues(y != _)
        case ">" => xs.mapValues(y > _)
        case "<" => xs.mapValues(y < _)
        case ">=" => xs.mapValues(y >= _)
        case "<=" => xs.mapValues(y <= _)
        case err =>
          Log.error("Wrong relation operator: " + err)
          new TimeSeries[Boolean](Seq())
      }
    } else {
      new TimeSeries[Boolean](Seq())
    }
  }

  def execIfExpr(p: Expression, e1: Expression, e2: Expression, mem: Map[String, Any]): Any = {
    val pred = execExpr(p, mem)
    val a = execExpr(e1, mem)
    val b = execExpr(e2, mem)
    if(pred.isInstanceOf[TimeSeries[_]]) {
      val xs = pred.asInstanceOf[TimeSeries[Boolean]]
      xs.mapValues(x => if(x) a else b)
    } else TimeSeries.empty[Float]
  }

  def mkFloat(a: Any): Float = {
    a match {
      case v: Float => v
      case v: Int => v.toFloat
      case v: Double => v.toFloat
      case v: Boolean => if (v) 1f else 0f
      case _ => 0f
    }
  }

  def mkBool(a: Any): Boolean = {
    a match {
      case b: Boolean => b
      case _ => false
    }
  }

  def executeExternalFunction(name: String, params: Seq[Any]): Any = {
    runtimes.foldLeft[Option[Any]](None) { (acc, r) =>
      if (acc.isEmpty) r.executeFunction(name, params)
      else acc
    } match {
      case Some(value) => value
      case None => throw new NoSuchElementException(name)
    }
  }

}
