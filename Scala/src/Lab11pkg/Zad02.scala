package Lab11pkg

class Zad02
{
  def doStuff() =
  {
    try
    {
      val expression = "-3 + 4 - 1 + 1 + 12 - 5 + 6"

      val result = evaluate(expression.split("\\s").toList)
      println(s"$expression = $result")
    }
    catch {
      case ex: CustomException => ex.printStackTrace
    }
  }

  def evaluate(expression: List[String]): Int = expression match
  {
    case left :: "+" :: right :: rest => evaluate((left.toInt + right.toInt).toString :: rest)
    case left :: "-" :: right :: rest => evaluate((left.toInt - right.toInt).toString :: rest)
    case value :: Nil => value.toInt
  }
}
