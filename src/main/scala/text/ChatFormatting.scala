package fmc
package text

import syntax.iron.{*, given}

import client.*

enum ChatFormatting(
                      name: String,
                      code: Char,
                      isFormat: Boolean,
                      asString: String,
                      id: Int,
                      color: Color | Null
                   ):
  def this(name: String, code: Char, isFormat: Boolean = false, id: Int = -1, color: Color | Null = null) =
    this(name, code, isFormat, "\u00a7" + code, id, color)
  end this

  case Black         extends ChatFormatting("BLACK", '0', id = 0, color = rgb(0, 0, 0))
  case DarkBlue      extends ChatFormatting("DARK_BLUE", '1', id = 1, color = rgb(0, 0, 170))
  case DarkGreen     extends ChatFormatting("DARK_GREEN", '2', id = 2, color = rgb(0, 170, 0))
  case DarkAqua      extends ChatFormatting("DARK_AQUA", '3', id = 3, color = rgb(0, 170, 170))
  case DarkRed       extends ChatFormatting("DARK_RED", '4', id = 4, color = rgb(170, 0, 0))
  case DarkPurple    extends ChatFormatting("DARK_PURPLE", '5',id =  5, color = rgb(170, 0, 170))
  case Gold          extends ChatFormatting("GOLD", '6', id = 6, color = rgb(255, 170, 0))
  case Gray          extends ChatFormatting("GRAY", '7', id = 7, color = rgb(170, 170, 170))
  case DarkGray      extends ChatFormatting("DARK_GRAY", '8',id =  8, color = rgb(85, 85, 85))
  case Blue          extends ChatFormatting("BLUE", '9',id =  9, color = rgb(85, 85, 255))
  case Green         extends ChatFormatting("GREEN", 'a', id = 10, color = rgb(85, 255, 85))
  case Aqua          extends ChatFormatting("AQUA", 'b', id = 11, color = rgb(85, 255, 255))
  case Red           extends ChatFormatting("RED", 'c', id = 12, color = rgb(255, 85, 85))
  case LightPurple   extends ChatFormatting("LIGHT_PURPLE", 'd',id =  13, color = rgb(255, 85, 255))
  case Yellow        extends ChatFormatting("YELLOW", 'e', id = 14, color = rgb(255, 255, 85))
  case White         extends ChatFormatting("WHITE", 'f',id =  15, color = rgb(255, 255, 255))
  case Obfuscated    extends ChatFormatting("OBFUSCATED", 'k', true)
  case Bold          extends ChatFormatting("BOLD", 'l', true)
  case StrikeThrough extends ChatFormatting("STRIKETHROUGH", 'm', true)
  case Underline     extends ChatFormatting("UNDERLINE", 'n', true)
  case Italic        extends ChatFormatting("ITALIC", 'o', true)
  case Reset         extends ChatFormatting("RESET", 'r')
end ChatFormatting
