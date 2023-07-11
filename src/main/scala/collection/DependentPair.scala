package me.zahara.fmc
package collection

case class DependentPair[Key <: Dependent](key : Key)(val value : key.Value)
