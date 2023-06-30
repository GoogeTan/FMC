package me.zahara.fmc

import syntax.iron.{*, given}


opaque type Color = Int

type ColorPattern = ClosedOpen[0, 256]

def color(red : Int :| ColorPattern, green : Int :| ColorPattern, blue : Int :| ColorPattern, alpha : Int :| ColorPattern) : Color = ???
