package fmc
package level

/**
 * Это самый лютый костыль во всей системе, но он сильно упрощает код.
 * value обязано быть инициализированно при помощи уровня на данной версии игры с данным загрузчиком для соответствующей стороны.
 */
enum Level:
  case Client private[fmc] (private[fmc] val value : Any)
  case Server private[fmc] (private[fmc] val value : Any)
end Level
