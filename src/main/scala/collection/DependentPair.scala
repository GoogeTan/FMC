package fmc
package collection

case class DependentPair[D <: Dependent](key : D)(val value : key.Value)
