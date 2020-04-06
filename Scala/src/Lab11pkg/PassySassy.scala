package Lab11pkg

object PassySassy
{
    def evaluatePassword(password :String, filters: (String => Boolean)*) :Boolean =
    {
        filters.forall(filter => filter(password))
    }
}
