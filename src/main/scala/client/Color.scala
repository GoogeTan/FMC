package me.zahara.fmc

import syntax.iron.{*, given}

import io.github.iltotore.iron.constraint.numeric.Interval.ClosedOpen


opaque type Color = Int

type ColorPattern = ClosedOpen[0, 256]

def rgba(red : Int :| ColorPattern, green : Int :| ColorPattern, blue : Int :| ColorPattern, alpha : Int :| ColorPattern) : Color =
  0.withRed(red).withGreen(green).withBlue(blue).withAlpha(alpha)
end rgba

extension (color : Color)
  def alpha: Int :| ColorPattern = color & 0xFF000000
  def red  : Int :| ColorPattern = color & 0x00FF0000
  def green: Int :| ColorPattern = color & 0x0000FF00
  def blue : Int :| ColorPattern = color & 0x000000FF

  def withAlpha(alpha : Int :| ColorPattern) : Color =
    (color & 0x00FFFFFF) + (alpha << 24)
  end withAlpha

  def withRed(red: Int :| ColorPattern): Color =
    (color & 0xFF00FFFF) + (red << 16)
  end withRed

  def withGreen(green: Int :| ColorPattern): Color =
    (color & 0xFFFF00FF) + (green << 8)
  end withGreen

  def withBlue(blue: Int :| ColorPattern): Color =
    (color & 0xFFFFFF00) + (blue << 0)
  end withBlue
end extension

def rgb(red : Int :| ColorPattern, green : Int :| ColorPattern, blue : Int :| ColorPattern) : Color = rgba(red, green, blue, 255)
