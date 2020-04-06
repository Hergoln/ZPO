package Lab11pkg

object PassySassyTest {

  def Test()
  {
    def hasUpper (s: String) = s.exists(c => c.isUpper)
    def hasLower (s: String) = s.exists(c => c.isLower)
    def hasDigit (s: String) = s.exists(c => c.isDigit)
    def has2Digits (s: String) = s.foldLeft(0)((n, c) => n + (if(c.isDigit) 1 else 0) ) >= 2
    def maxLen (max: Int)(s: String) = s.length() < max
    def minLen (min: Int)(s: String) = s.length() > min

    assert(PassySassy.evaluatePassword("abc1", hasUpper, hasLower, hasDigit, minLen(2), maxLen(10)) == false)
    assert(PassySassy.evaluatePassword("ABC1", hasUpper, hasLower, hasDigit,  minLen(2), maxLen(10)) == false)
    assert(PassySassy.evaluatePassword("Abc", hasUpper, hasLower, hasDigit,  minLen(2), maxLen(10)) == false)
    assert(PassySassy.evaluatePassword("Abc1", hasUpper, hasLower, hasDigit,  minLen(5), maxLen(10)) == false)
    assert(PassySassy.evaluatePassword("Abc11", hasUpper, hasLower, hasDigit,  minLen(2), maxLen(4)) == false)
    assert(PassySassy.evaluatePassword("Abc1", hasUpper, hasLower, hasDigit,  minLen(2), maxLen(10)))
    assert(PassySassy.evaluatePassword("Abc1", hasUpper, hasLower, has2Digits,  minLen(2), maxLen(10)) == false)
    assert(PassySassy.evaluatePassword("Abc11", hasUpper, hasLower, has2Digits,  minLen(2), maxLen(10)))
  }

}
