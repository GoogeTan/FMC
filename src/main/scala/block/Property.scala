package me.zahara.fmc
package block

import collection.ListBiMap

case class Property[T](name : String, values : ListBiMap[T, String])
