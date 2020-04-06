package Lab11pkg

object Zad03Test {
  def Test() = {
    assert(Zad03.checkBalancedParents("))((".split("").toList) == false)
    assert(Zad03.checkBalancedParents("(()(ab)c)".split("").toList))
    assert(Zad03.checkBalancedParents("((()())".split("").toList) == false)
    assert(Zad03.checkBalancedParents("(()))(".split("").toList) == false)
    assert(Zad03.checkBalancedParents("(aa(b)()c)d".split("").toList))
  }
}