package me.zahara.fmc
package collection

case class DependentPair[Key <: Dependent](val key : Key)(val value : key.Value)
