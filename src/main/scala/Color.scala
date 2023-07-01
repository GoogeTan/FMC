package me.zahara.fmc

import syntax.iron.{*, given}

import io.github.iltotore.iron.constraint.numeric.Interval.ClosedOpen


opaque type Color = Int

type ColorPattern = ClosedOpen[0, 256]

def rgba(red : Int :| ColorPattern, green : Int :| ColorPattern, blue : Int :| ColorPattern, alpha : Int :| ColorPattern) : Color = ???


def rgb(red : Int :| ColorPattern, green : Int :| ColorPattern, blue : Int :| ColorPattern) : Color = rgba(red, green, blue, 255)
