package me.zahara.fmc

import io.github.iltotore.iron.:|
import io.github.iltotore.iron.constraint.all.*
import io.github.iltotore.iron.constraint.numeric.Interval.ClosedOpen

opaque type Color = Int

type ColorPattern = ClosedOpen[0, 256]

def color(red : Int :| ColorPattern, green : Int :| ColorPattern, blue : Int :| ColorPattern, alpha : Int :| ColorPattern) : Color = ???
