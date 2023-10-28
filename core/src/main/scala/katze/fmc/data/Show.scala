package katze.fmc.data

trait Show[-T]:
  def show(value : T) : String
end Show

def show[T : Show](value : T) : String =
  implicitly[Show[T]].show(value)
end show

