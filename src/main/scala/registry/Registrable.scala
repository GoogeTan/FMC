package me.zahara.fmc
package registry

trait Registrable[F[_], T, U]:
  /**
   * КРегистрирует данный объект. Возвращает ссылку(например Block для BlockPrototype)
   * @param resourceLocation имя объекута
   * @param value значение
   */
  def register(resourceLocation: ResourceLocation, value : T) : F[U]
end Registrable

/**
 * Кладёт объект в очередь на регистрацию.
 *
 * @param resourceLocation имя объекута
 * @param value            значение
 */
def register[F[_], T, U](resourceLocation: ResourceLocation, value : T)(using reg : Registrable[F, T, U]) : F[U] =
  reg.register(resourceLocation, value)
end register
