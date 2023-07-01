package me.zahara.fmc
package registry

trait Registrable[F[_], T]:
  /**
   * Кладёт объект в очередь на регистрацию.
   * @param resourceLocation имя объекута
   * @param value значение
   */
  def register(resourceLocation: ResourceLocation, value : T) : F[Unit]
end Registrable

/**
 * Кладёт объект в очередь на регистрацию.
 *
 * @param resourceLocation имя объекута
 * @param value            значение
 */
def register[F[_], T](resourceLocation: ResourceLocation, value : T)(using reg : Registrable[F, T]) : F[Unit] =
  reg.register(resourceLocation, value)
end register
