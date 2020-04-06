package Lab11pkg

object Zad03
{
  def checkBalancedParents(expression :List[String]) :Boolean =
  {
    val result = expression.foldLeft(0)(
          (n, s) => if(n < 0 ) return false else n + (s match {
            case "(" => 1
            case ")" => -1
            case _ => 0
          }
       )
    )
    result == 0
  }
}