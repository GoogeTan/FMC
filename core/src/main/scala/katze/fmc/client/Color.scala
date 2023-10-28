package katze.fmc
package client

import syntax.all.{*, given}

opaque type Color = Int

type ColorPattern = ClosedOpen[0, 256]

def rgba(red : Int :| ColorPattern, green : Int :| ColorPattern, blue : Int :| ColorPattern, alpha : Int :| ColorPattern) : Color =
  withRed(
    withGreen(
      withBlue(
        withAlpha(
          0,
          alpha
        ),
        blue
      ),
      green
    ),
    red
  )
end rgba

def alpha(color : Color): Int :| ColorPattern = color & 0xFF000000
def red(color : Color)  : Int :| ColorPattern = color & 0x00FF0000
def green(color : Color): Int :| ColorPattern = color & 0x0000FF00
def blue(color : Color) : Int :| ColorPattern = color & 0x000000FF

def withAlpha(color : Color, alpha : Int :| ColorPattern) : Color =
  (color & 0x00FFFFFF) + (alpha << 24)
end withAlpha

def withRed(color : Color, red: Int :| ColorPattern): Color =
  (color & 0xFF00FFFF) + (red << 16)
end withRed

def withGreen(color : Color, green: Int :| ColorPattern): Color =
  (color & 0xFFFF00FF) + (green << 8)
end withGreen

def withBlue(color : Color, blue: Int :| ColorPattern): Color =
  (color & 0xFFFFFF00) + (blue << 0)
end withBlue

def rgb(red : Int :| ColorPattern, green : Int :| ColorPattern, blue : Int :| ColorPattern) : Color = rgba(red, green, blue, 255)
