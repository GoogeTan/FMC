package fmc
package collection

final case class DependentPair[D <: Dependent](key : D)(val value : key.Value)
